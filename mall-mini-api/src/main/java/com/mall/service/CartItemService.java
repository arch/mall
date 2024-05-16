/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.domain.CartProduct;
import com.mall.domain.PromotionCartItem;
import com.mall.model.OmsCartItem;

import java.util.List;

public interface CartItemService {
    void add(OmsCartItem cartItem);

    List<OmsCartItem> list();

    List<PromotionCartItem> listPromotion(List<Long> cartIds);

    void updateQuantity(long id, int quantity);

    CartProduct getCartProduct(long productId);

    void updateAttribute(OmsCartItem cartItem);

    void delete(List<Long> ids);

    void clear();
}