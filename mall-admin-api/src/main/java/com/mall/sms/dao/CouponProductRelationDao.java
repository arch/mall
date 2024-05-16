/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.dao;

import com.mall.model.SmsCouponProductRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponProductRelationDao {
    int insertList(@Param("list")List<SmsCouponProductRelation> productRelations);
}