/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.domain.PromotionCartItem;
import com.mall.model.OmsCartItem;

import java.util.List;

public interface PromotionService {
    /**
     * 计算购物车中的促销活动信息
     * @param cartItemList 购物车
     */
    List<PromotionCartItem> calcCartPromotion(List<OmsCartItem> cartItemList);
}