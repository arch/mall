/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.controller;

import com.mall.model.SmsCoupon;
import com.mall.model.SmsCouponHistory;
import com.mall.sms.dto.CouponParam;
import com.mall.sms.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "优惠券管理")
@RestController
@RequestMapping(value = "/api/sms/coupon", produces = MediaType.APPLICATION_JSON_VALUE)
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @Operation(summary = "根据优惠券名称和类型分页获取优惠券列表", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping
    public List<SmsCoupon> get(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "pageIndex", defaultValue = "0", required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return couponService.list(name, type, pageIndex, pageSize);
    }

    @Operation(summary = "获取单个优惠券的详细信息", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/{id}")
    public CouponParam get(@PathVariable Long id) {
        return couponService.get(id);
    }

    @Operation(summary = "添加优惠券", security = {@SecurityRequirement(name = "jwtScheme")})
    @PostMapping
    public void create(@RequestBody CouponParam couponParam) {
        couponService.create(couponParam);
    }

    @Operation(summary = "修改优惠券", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/{id:[0-9]+}")
    public void update(@PathVariable("id") long id, @RequestBody CouponParam couponParam) {
        couponService.update(id, couponParam);
    }

    @Operation(summary = "删除优惠券", security = {@SecurityRequirement(name = "jwtScheme")})
    @DeleteMapping(value = "/{id:[0-9]+}")
    public void delete(@PathVariable(value = "id") Long id) {
        couponService.delete(id);
    }


    @Operation(summary = "根据优惠券id，使用状态，订单编号分页获取领取记录", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/history")
    public List<SmsCouponHistory> history(@RequestParam(value = "couponId", required = false) Long couponId,
            @RequestParam(value = "useStatus", required = false) Integer useStatus,
            @RequestParam(value = "orderSn", required = false) String orderSn,
            @RequestParam(value = "pageIndex", defaultValue = "0", required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return couponService.history(couponId, useStatus, orderSn, pageIndex, pageSize);
    }
}