/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;
import java.util.List;

public class HomeFlashPromotion {
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime nextStartTime;
    private LocalTime nextEndTime;
    @Schema(description = "属于该秒杀活动的商品")
    private List<FlashPromotionProduct> productList;

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getNextStartTime() {
        return nextStartTime;
    }

    public void setNextStartTime(LocalTime nextStartTime) {
        this.nextStartTime = nextStartTime;
    }

    public LocalTime getNextEndTime() {
        return nextEndTime;
    }

    public void setNextEndTime(LocalTime nextEndTime) {
        this.nextEndTime = nextEndTime;
    }

    public List<FlashPromotionProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<FlashPromotionProduct> productList) {
        this.productList = productList;
    }
}