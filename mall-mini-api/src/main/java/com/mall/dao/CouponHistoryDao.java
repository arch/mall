/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.dao;

import com.mall.domain.CouponHistoryDetail;
import com.mall.model.SmsCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponHistoryDao {
    /**
     * 获取优惠券历史详情
     */
    List<CouponHistoryDetail> getDetailList(@Param("memberId") Long memberId);
    /**
     * 获取指定会员优惠券列表
     */
    List<SmsCoupon> getCouponList(@Param("memberId") Long memberId, @Param("useStatus")Integer useStatus);
}