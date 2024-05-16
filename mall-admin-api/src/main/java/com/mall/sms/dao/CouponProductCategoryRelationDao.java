/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.dao;

import com.mall.model.SmsCouponProductCategoryRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponProductCategoryRelationDao {
    int insertList(@Param("list")List<SmsCouponProductCategoryRelation> productCategoryRelations);
}