/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.controller;

import com.mall.model.OmsOrderSetting;
import com.mall.oms.service.OrderSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "订单设置管理")
@RestController
@RequestMapping(value = "/api/oms/order/setting", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderSettingController {
    private final OrderSettingService orderSettingService;

    public OrderSettingController(
            OrderSettingService orderSettingService) {
        this.orderSettingService = orderSettingService;
    }

    @Operation(summary = "获取指定订单设置", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping
    public List<OmsOrderSetting> get() {
        return orderSettingService.list();
    }

    @Operation(summary = "获取指定订单设置", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/{id}")
    public OmsOrderSetting get(@PathVariable Long id) {
        return orderSettingService.get(id);
    }

    @Operation(summary = "修改指定订单设置", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/{id}")
    public void put(@PathVariable Long id, @RequestBody OmsOrderSetting orderSetting) {
        orderSettingService.update(id,orderSetting);
    }
}