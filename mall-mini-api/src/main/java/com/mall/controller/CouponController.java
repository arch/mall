/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.domain.CouponHistoryDetail;
import com.mall.domain.PromotionCartItem;
import com.mall.model.SmsCoupon;
import com.mall.model.SmsCouponHistory;
import com.mall.service.CartItemService;
import com.mall.service.CouponService;
import com.mall.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户优惠券管理")
@RestController
@RequestMapping(value = "/api/coupon", produces = MediaType.APPLICATION_JSON_VALUE)
public class CouponController {
    private final CouponService couponService;
    private final CartItemService cartItemService;
    private final MemberService memberService;

    public CouponController(CouponService couponService, CartItemService cartItemService, MemberService memberService) {
        this.couponService = couponService;
        this.cartItemService = cartItemService;
        this.memberService = memberService;
    }

    @Operation(summary = "领取指定优惠券", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping(value = "/add/{couponId}")
    public void add(@PathVariable long couponId) {
        couponService.add(couponId);
    }

    @Operation(summary = "获取用户优惠券历史列表", security = { @SecurityRequirement(name = "jwtScheme") })
    @Parameters({
            @Parameter(
                    name = "useStatus",
                    description = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
                    required = true,
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = int.class))})
    @GetMapping(value = "/history")
    public List<SmsCouponHistory> history(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        return couponService.history(useStatus);
    }

    @Operation(summary = "获取用户优惠券列表", security = { @SecurityRequirement(name = "jwtScheme") })
    @Parameters({
            @Parameter(
                    name = "useStatus",
                    description = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
                    required = true,
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = int.class))})
    @GetMapping(value = "/list")
    public List<SmsCoupon> list(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        return couponService.list(useStatus);
    }

    @Operation(summary = "获取登录会员购物车的相关优惠券", security = { @SecurityRequirement(name = "jwtScheme") })
    @Parameters({
            @Parameter(
                    name = "type",
                    description = "使用可用:0->不可用；1->可用",
                    required = true,
                    in = ParameterIn.PATH,
                    schema = @Schema(implementation = int.class))})
    @GetMapping(value = "/list/cart/{type:[0-9]+}")
    public List<CouponHistoryDetail> listCart(@PathVariable int type) {
        List<PromotionCartItem> promotionCartItems = cartItemService.listPromotion(null);
        return couponService.listCart(promotionCartItems, type);
    }

    @Operation(summary = "获取当前商品相关优惠券", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/list/of/product/{productId}")
    public List<SmsCoupon> listByProduct(@PathVariable Long productId) {
        return couponService.listByProduct(productId);
    }
}