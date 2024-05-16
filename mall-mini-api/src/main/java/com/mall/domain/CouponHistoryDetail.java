/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import com.mall.model.SmsCoupon;
import com.mall.model.SmsCouponHistory;
import com.mall.model.SmsCouponProductCategoryRelation;
import com.mall.model.SmsCouponProductRelation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class CouponHistoryDetail extends SmsCouponHistory {
    @Schema(description = "相关优惠券信息")
    private SmsCoupon coupon;
    @Schema(description = "优惠券关联商品")
    private List<SmsCouponProductRelation> productRelations;
    @Schema(description = "优惠券关联商品分类")
    private List<SmsCouponProductCategoryRelation> categoryRelations;

    public SmsCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(SmsCoupon coupon) {
        this.coupon = coupon;
    }

    public List<SmsCouponProductRelation> getProductRelations() {
        return productRelations;
    }

    public void setProductRelations(List<SmsCouponProductRelation> productRelations) {
        this.productRelations = productRelations;
    }

    public List<SmsCouponProductCategoryRelation> getCategoryRelations() {
        return categoryRelations;
    }

    public void setCategoryRelations(List<SmsCouponProductCategoryRelation> categoryRelations) {
        this.categoryRelations = categoryRelations;
    }
}