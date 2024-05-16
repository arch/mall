/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.dto;

import com.mall.model.SmsFlashPromotionSession;
import io.swagger.v3.oas.annotations.media.Schema;

public class FlashPromotionSession extends SmsFlashPromotionSession {
    @Schema(description = "商品数量")
    private Long productCount;

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }
}