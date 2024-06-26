/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import com.mall.model.PmsProduct;
import com.mall.model.PmsProductFullReduction;
import com.mall.model.PmsProductLadder;
import com.mall.model.PmsSkuStock;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 商品的促销信息，包括sku、打折优惠、满减优惠
 */
public class PromotionProduct extends PmsProduct {
    @Schema(description = "商品库存信息")
    private List<PmsSkuStock> skuStockList;
    @Schema(description = "商品打折信息")
    private List<PmsProductLadder> productLadderList;
    @Schema(description = "商品满减信息")
    private List<PmsProductFullReduction> productFullReductionList;

    public List<PmsSkuStock> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<PmsSkuStock> skuStockList) {
        this.skuStockList = skuStockList;
    }

    public List<PmsProductLadder> getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(List<PmsProductLadder> productLadderList) {
        this.productLadderList = productLadderList;
    }

    public List<PmsProductFullReduction> getProductFullReductionList() {
        return productFullReductionList;
    }

    public void setProductFullReductionList(List<PmsProductFullReduction> productFullReductionList) {
        this.productFullReductionList = productFullReductionList;
    }
}