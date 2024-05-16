/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.dto;

import com.mall.model.SmsCoupon;
import com.mall.model.SmsCouponProductCategoryRelation;
import com.mall.model.SmsCouponProductRelation;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

/**
 * 优惠券信息封装，包括绑定商品和绑定分类
 */
public class CouponParam extends SmsCoupon {
    @Schema(description = "优惠券绑定的商品")
    private List<SmsCouponProductRelation> productRelations;
    @Schema(description = "优惠券绑定的商品分类")
    private List<SmsCouponProductCategoryRelation> productCategoryRelations;

    public List<SmsCouponProductRelation> getProductRelations() {
        return productRelations;
    }

    public void setProductRelations(List<SmsCouponProductRelation> productRelations) {
        this.productRelations = productRelations;
    }

    public List<SmsCouponProductCategoryRelation> getProductCategoryRelations() {
        return productCategoryRelations;
    }

    public void setProductCategoryRelations(
            List<SmsCouponProductCategoryRelation> productCategoryRelations) {
        this.productCategoryRelations = productCategoryRelations;
    }
}