/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.dto;

import com.mall.model.PmsProduct;
import com.mall.model.SmsFlashPromotionProductRelation;
import io.swagger.v3.oas.annotations.media.Schema;

public class FlashPromotionProduct  extends SmsFlashPromotionProductRelation {
    @Schema(description = "关联商品")
    private PmsProduct product;

    public PmsProduct getProduct() {
        return product;
    }

    public void setProduct(PmsProduct product) {
        this.product = product;
    }
}