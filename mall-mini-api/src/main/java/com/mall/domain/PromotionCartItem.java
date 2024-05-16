/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import com.mall.model.OmsCartItem;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class PromotionCartItem extends OmsCartItem {
    @Schema(description = "促销活动信息")
    private String promotionMessage;
    @Schema(description = "促销活动减去的金额，针对每个商品")
    private BigDecimal reduceAmount = BigDecimal.ZERO;
    @Schema(description = "商品的真实库存（剩余库存-锁定库存）")
    private Integer realStock;
    @Schema(description = "购买商品赠送积分")
    private Integer integration;
    @Schema(description = "购买商品赠送成长值")
    private Integer growth;

    public String getPromotionMessage() {
        return promotionMessage;
    }

    public void setPromotionMessage(String promotionMessage) {
        this.promotionMessage = promotionMessage;
    }

    public BigDecimal getReduceAmount() {
        return reduceAmount;
    }

    public void setReduceAmount(BigDecimal reduceAmount) {
        this.reduceAmount = reduceAmount;
    }

    public Integer getRealStock() {
        return realStock;
    }

    public void setRealStock(Integer realStock) {
        this.realStock = realStock;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }
}