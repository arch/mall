/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import com.mall.crosscut.util.Assert;
import com.mall.PayKind;
import com.mall.domain.OrderDetail;
import com.mall.model.OmsOrder;
import com.mall.model.UmsMember;
import com.mall.service.MemberService;
import com.mall.service.OrderService;
import com.mall.service.WxPayService;
import com.mall.wechat.pay.Amount;
import com.mall.wechat.pay.NotifyParam;
import com.mall.wechat.pay.NotifyResult;
import com.mall.wechat.pay.Order;
import com.mall.wechat.pay.PayResult;
import com.mall.wechat.pay.Payer;
import com.mall.wechat.pay.Payment;
import com.mall.wechat.pay.WeChatPay;
import com.mall.wechat.util.Json;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;

@Service
public class WxPayServiceImpl implements WxPayService {

    private static final String NOTIFY_URL = "https://mini.china-tt.cn/api/wxpay/notify";
    private static final Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);
    private final WeChatPay wechatPay;
    private final OrderService orderService;
    private final MemberService memberService;

    public WxPayServiceImpl(WeChatPay wechatPay, OrderService orderService,
            MemberService memberService) {
        this.wechatPay = wechatPay;
        this.orderService = orderService;
        this.memberService = memberService;
    }

    @Override
    public Payment getPayment(long orderId) {
        OrderDetail od = orderService.detail(orderId);

        // ensure the order exist
        Assert.ensure(od != null, String.format("订单[%d]不存在", orderId));

        UmsMember member = memberService.getCurrentMember();

        // ensure the order exist
        Assert.ensure(member != null, "必须登录才能发起支付");

        Order order = Order.notifyURL(NOTIFY_URL);
        if (ObjectUtils.isEmpty(od.getNote())) {
            order.setDescription(od.getOrderItems().get(0).getProductName());
        } else {
            order.setDescription(od.getNote());
        }

        order.setOutTradeNo(od.getOrderSn());
        order.setAmount(Amount.CNY(od.getPayAmount().multiply(BigDecimal.valueOf(100)).longValue()));
        order.setPayer(Payer.valueOf(member.getOpenid()));

        return wechatPay.prepay(order);
    }

    @Override
    public NotifyResult payNotify(HttpServletRequest request) {
        try {
            String json = verifyAndGetJson(request);
            NotifyParam param = Json.deserialize(json, NotifyParam.class);
            json = param.getResource().decryptToString(wechatPay.getDecryptor());
            logger.info(json);
            PayResult result = Json.deserialize(json, PayResult.class);

            if (result.success()) {
                Optional<OmsOrder> optional = orderService.get(result.getOutTradeNo());
                if (optional.isEmpty()) {
                    String message = String.format("订单[%s]不存在", result.getOutTradeNo());
                    logger.error(message);
                    return NotifyResult.failure(message);
                }

                OmsOrder omsOrder = optional.get();
                if (omsOrder.getPayAmount().multiply(BigDecimal.valueOf(100)).longValue() != result.getAmount()
                        .getTotal()) {
                    String message = String
                            .format("订单[%s]支付金额: %s != 订单金额：%d ", result.getOutTradeNo(), omsOrder.getPayAmount(),
                                    result.getAmount().getTotal());
                    logger.error(message);
                    return NotifyResult.failure(message);
                }
                orderService.paySuccess(omsOrder.getId(), PayKind.WECHAT, result.getTransactionId());
            } else {
                return NotifyResult.failure(result.getTradeState());
            }
            return NotifyResult.SUCCESS;
        } catch (Throwable cause) {
            logger.error(String.format("WeChatPay Notify Failure: %s", cause.getMessage()), cause);
            return NotifyResult.failure(cause.getMessage());
        }
    }

    private String verifyAndGetJson(HttpServletRequest request) throws IOException {
        // https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay4_1.shtml
        validateParameters(request);

        String timestamp = request.getHeader("Wechatpay-Timestamp");
        String nonce = request.getHeader("Wechatpay-Nonce");
        // 微信支付的平台证书序列号
        String serial = request.getHeader("Wechatpay-Serial");
        // 微信支付的应答签名
        String signature = request.getHeader("Wechatpay-Signature");

        byte[] bytes = StreamUtils.copyToByteArray(request.getInputStream());
        String json = "";
        String charset = request.getCharacterEncoding();
        if (bytes.length > 0) {
            json = new String(bytes, charset == null ? StandardCharsets.UTF_8 : Charset.forName(charset));
        }

        logger.info(json);

        // format: https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay4_1.shtml
        // 应答时间戳\n
        // 应答随机串\n
        // 应答报文主体\n
        String message = timestamp + "\n" + nonce + "\n" + json + "\n";
        if (!wechatPay.verify(serial, message.getBytes(StandardCharsets.UTF_8), signature)) {
            throw verifyFail("serial=[%s] message=[%s] sign=[%s], request-id=[%s]",
                    serial, message, signature, request.getHeader("Request-ID"));
        }
        return json;
    }

    private void validateParameters(HttpServletRequest request) {
        String serial = request.getHeader("Wechatpay-Serial");
        if (ObjectUtils.isEmpty(serial)) {
            throw parameterError("empty Wechatpay-Serial");
        }

        String signature = request.getHeader("Wechatpay-Signature");
        if (ObjectUtils.isEmpty(signature)) {
            throw parameterError("empty Wechatpay-Signature");
        }

        String nonce = request.getHeader("Wechatpay-Nonce");
        if (ObjectUtils.isEmpty(nonce)) {
            throw parameterError("empty Wechatpay-Nonce");
        }

        String timestamp = request.getHeader("Wechatpay-Timestamp");
        if (ObjectUtils.isEmpty(timestamp)) {
            throw parameterError("empty Wechatpay-Timestamp");
        }
    }

    private static RuntimeException parameterError(String message, Object... args) {
        message = String.format(message, args);
        return new IllegalArgumentException("parameter error: " + message);
    }

    private static RuntimeException verifyFail(String message, Object... args) {
        message = String.format(message, args);
        return new IllegalArgumentException("signature verify fail: " + message);
    }
}