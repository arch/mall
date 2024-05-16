/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.auth;

import com.mall.wechat.core.Validator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay4_1.shtml
 */
public class WechatPayValidator implements Validator {
    private static final Logger logger = LoggerFactory.getLogger(WechatPayValidator.class);
    private final Verifier verifier;

    public WechatPayValidator(Verifier verifier) {
        this.verifier = verifier;
    }

    @Override
    public final boolean validate(CloseableHttpResponse response) throws IOException {
        try {
            // https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay4_1.shtml
            validateParameters(response);

            String message = buildMessage(response);

            // 微信支付的平台证书序列号
            String serial = response.getFirstHeader("Wechatpay-Serial").getValue();
            // 微信支付的应答签名
            String signature = response.getFirstHeader("Wechatpay-Signature").getValue();

            if (!verifier.verify(serial, message.getBytes(StandardCharsets.UTF_8), signature)) {
                throw verifyFail("serial=[%s] message=[%s] sign=[%s], request-id=[%s]",
                        serial, message, signature,
                        response.getFirstHeader("Request-ID").getValue());
            }
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return false;
        }

        return true;
    }

    protected final void validateParameters(CloseableHttpResponse response) {
        String requestId;
        if (!response.containsHeader("Request-ID")) {
            throw parameterError("empty Request-ID");
        } else {
            requestId = response.getFirstHeader("Request-ID").getValue();
        }

        if (!response.containsHeader("Wechatpay-Serial")) {
            throw parameterError("empty Wechatpay-Serial, request-id=[%s]", requestId);
        } else if (!response.containsHeader("Wechatpay-Signature")){
            throw parameterError("empty Wechatpay-Signature, request-id=[%s]", requestId);
        } else if (!response.containsHeader("Wechatpay-Timestamp")) {
            throw parameterError("empty Wechatpay-Timestamp, request-id=[%s]", requestId);
        } else if (!response.containsHeader("Wechatpay-Nonce")) {
            throw parameterError("empty Wechatpay-Nonce, request-id=[%s]", requestId);
        } else {
            Header timestamp = response.getFirstHeader("Wechatpay-Timestamp");
            try {
                Instant instant = Instant.ofEpochSecond(Long.parseLong(timestamp.getValue()));
                // 拒绝5分钟之外的应答
                if (Duration.between(instant, Instant.now()).abs().toMinutes() >= 5) {
                    throw parameterError("timestamp=[%s] expires, request-id=[%s]", timestamp.getValue(), requestId);
                }
            } catch (DateTimeException | NumberFormatException e) {
                throw parameterError("invalid timestamp=[%s], request-id=[%s]", timestamp.getValue(), requestId);
            }
        }
    }

    protected final String buildMessage(CloseableHttpResponse response) throws IOException {
        String timestamp = response.getFirstHeader("Wechatpay-Timestamp").getValue();
        String nonce = response.getFirstHeader("Wechatpay-Nonce").getValue();

        String body = getResponseBody(response);

        // format: https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay4_1.shtml
        // 应答时间戳\n
        // 应答随机串\n
        // 应答报文主体\n

        return timestamp + "\n"
                + nonce + "\n"
                + body + "\n";
    }

    protected final String getResponseBody(CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        return (entity != null && entity.isRepeatable()) ? EntityUtils.toString(entity) : "";
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