/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.dao;

import com.mall.sms.dto.FlashPromotionProduct;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FlashPromotionProductRelationDao {
    List<FlashPromotionProduct> getList(
            @Param("flashPromotionId") Long flashPromotionId,
            @Param("flashPromotionSessionId") Long flashPromotionSessionId,
            @Param("offset") int offset,
            @Param("limit") int limit);
}