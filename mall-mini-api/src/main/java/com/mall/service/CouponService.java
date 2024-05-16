/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.domain.CouponHistoryDetail;
import com.mall.domain.PromotionCartItem;
import com.mall.model.SmsCoupon;
import com.mall.model.SmsCouponHistory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CouponService {
    @Transactional
    void add(long couponId);

    List<SmsCouponHistory> history(Integer useStatus);

    List<SmsCoupon> list(Integer useStatus);

    List<CouponHistoryDetail> listCart(List<PromotionCartItem> promotionCartItems, int type);

    List<SmsCoupon> listByProduct(Long productId);
}