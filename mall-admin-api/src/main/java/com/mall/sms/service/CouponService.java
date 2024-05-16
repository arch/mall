/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service;

import com.mall.model.SmsCoupon;
import com.mall.model.SmsCouponHistory;
import com.mall.sms.dto.CouponParam;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface CouponService {
    @Transactional
    void create(CouponParam couponParam);
    @Transactional
    void delete(Long id);
    @Transactional
    void update(long id, CouponParam couponParam);

    List<SmsCoupon> list(String name, Integer type, int pageIndex, int pageSize);

    CouponParam get(Long id);

    List<SmsCouponHistory> history(Long couponId, Integer useStatus, String orderSn, int pageIndex, int pageSize);
}