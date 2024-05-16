/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.domain.CartProduct;
import com.mall.domain.PromotionCartItem;
import com.mall.model.OmsCartItem;
import com.mall.service.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "购物车管理")
@RestController
@RequestMapping(value = "/api/cart", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @Operation(summary = "添加商品到购物车", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping(value = "/add")
    public void add(@RequestBody @Valid OmsCartItem cartItem) {
        cartItemService.add(cartItem);
    }

    @Operation(summary = "获取某个会员的购物车列表", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/list")
    public List<OmsCartItem> get() {
        return cartItemService.list();
    }

    @Operation(summary = "获取某个会员的购物车列表,包括促销信息", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/list/promotion")
    public List<PromotionCartItem> listPromotion(@RequestParam(required = false) List<Long> cartIds) {
        return cartItemService.listPromotion(cartIds);
    }

    @Operation(summary = "修改购物车中某个商品的数量", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update/quantity")
    public void updateQuantity(@RequestParam long id, @RequestParam int quantity) {
        cartItemService.updateQuantity(id, quantity);
    }

    @Operation(summary = "修改购物车中商品的规格", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update/attribute")
    public void updateAttribute(@RequestBody @Valid OmsCartItem cartItem) {
        cartItemService.updateAttribute(cartItem);
    }

    @Operation(summary = "获取购物车中某个商品的规格,用于重选规格", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/product/{productId:[0-9]+}")
    public CartProduct getCartProduct(@PathVariable long productId) {
        return cartItemService.getCartProduct(productId);
    }

    @Operation(summary = "删除购物车中的某些商品", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam("ids") List<Long> ids) {
        cartItemService.delete(ids);
    }

    @Operation(summary = "清空购物车", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/clear")
    public void clear() {
        cartItemService.clear();
    }
}