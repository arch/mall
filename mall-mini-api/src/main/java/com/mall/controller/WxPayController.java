/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.service.WxPayService;
import com.mall.wechat.pay.NotifyResult;
import com.mall.wechat.pay.Payment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "微信支付")
@RestController
@RequestMapping(value = "/api/wxpay", produces = MediaType.APPLICATION_JSON_VALUE)
public class WxPayController {
    private final WxPayService wxPayService;

    public WxPayController(WxPayService wxPayService) {
        this.wxPayService = wxPayService;
    }

    @Operation(summary = "发起微信支付", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/{orderId}")
    public Payment get(@PathVariable("orderId") long orderId) {
        return wxPayService.getPayment(orderId);
    }

    @Operation(summary = "支付通知", hidden = true)
    @PostMapping(value = "/notify")
    public NotifyResult post(HttpServletRequest request) {
        return wxPayService.payNotify(request);
    }
}