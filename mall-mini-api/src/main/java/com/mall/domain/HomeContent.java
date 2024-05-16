/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import com.mall.model.CmsSubject;
import com.mall.model.PmsBrand;
import com.mall.model.PmsProduct;
import com.mall.model.SmsHomeAdvertise;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class HomeContent {
    @Schema(description = "轮播广告")
    private List<SmsHomeAdvertise> advertiseList;
    @Schema(description = "推荐品牌")
    private List<PmsBrand> brandList;
    @Schema(description = "当前秒杀场次")
    private HomeFlashPromotion homeFlashPromotion;
    @Schema(description = "新品推荐")
    private List<PmsProduct> newProductList;
    @Schema(description = "人气推荐")
    private List<PmsProduct> hotProductList;
    @Schema(description = "推荐专题")
    private List<CmsSubject> subjectList;

    public List<SmsHomeAdvertise> getAdvertiseList() {
        return advertiseList;
    }

    public void setAdvertiseList(List<SmsHomeAdvertise> advertiseList) {
        this.advertiseList = advertiseList;
    }

    public List<PmsBrand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<PmsBrand> brandList) {
        this.brandList = brandList;
    }

    public HomeFlashPromotion getHomeFlashPromotion() {
        return homeFlashPromotion;
    }

    public void setHomeFlashPromotion(HomeFlashPromotion homeFlashPromotion) {
        this.homeFlashPromotion = homeFlashPromotion;
    }

    public List<PmsProduct> getNewProductList() {
        return newProductList;
    }

    public void setNewProductList(List<PmsProduct> newProductList) {
        this.newProductList = newProductList;
    }

    public List<PmsProduct> getHotProductList() {
        return hotProductList;
    }

    public void setHotProductList(List<PmsProduct> hotProductList) {
        this.hotProductList = hotProductList;
    }

    public List<CmsSubject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<CmsSubject> subjectList) {
        this.subjectList = subjectList;
    }
}