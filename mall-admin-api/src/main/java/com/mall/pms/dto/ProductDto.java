/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dto;

import com.mall.model.CmsPreferenceAreaProductRelation;
import com.mall.model.CmsSubjectProductRelation;
import com.mall.model.PmsMemberPrice;
import com.mall.model.PmsProduct;
import com.mall.model.PmsProductAttributeValue;
import com.mall.model.PmsProductFullReduction;
import com.mall.model.PmsProductLadder;
import com.mall.model.PmsSkuStock;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ProductDto extends PmsProduct {
    @Schema(description = "商品阶梯价格设置")
    private List<PmsProductLadder> productLadders;
    @Schema(description = "商品满减价格设置")
    private List<PmsProductFullReduction> productFullReductions;
    @Schema(description = "商品会员价格设置")
    private List<PmsMemberPrice> memberPrices;
    @Schema(description = "商品的sku库存信息")
    private List<PmsSkuStock> skuStocks;
    @Schema(description = "商品参数及自定义规格属性")
    private List<PmsProductAttributeValue> productAttributeValues;
    @Schema(description = "专题和商品关系")
    private List<CmsSubjectProductRelation> subjectProductRelations;
    @Schema(description = "优选专区和商品的关系")
    private List<CmsPreferenceAreaProductRelation> preferenceAreaProductRelations;

    public List<PmsProductLadder> getProductLadders() {
        return productLadders;
    }

    public void setProductLadders(List<PmsProductLadder> productLadders) {
        this.productLadders = productLadders;
    }

    public List<PmsProductFullReduction> getProductFullReductions() {
        return productFullReductions;
    }

    public void setProductFullReductions(List<PmsProductFullReduction> productFullReductions) {
        this.productFullReductions = productFullReductions;
    }

    public List<PmsMemberPrice> getMemberPrices() {
        return memberPrices;
    }

    public void setMemberPrices(List<PmsMemberPrice> memberPrices) {
        this.memberPrices = memberPrices;
    }

    public List<PmsSkuStock> getSkuStocks() {
        return skuStocks;
    }

    public void setSkuStocks(List<PmsSkuStock> skuStocks) {
        this.skuStocks = skuStocks;
    }

    public List<PmsProductAttributeValue> getProductAttributeValues() {
        return productAttributeValues;
    }

    public void setProductAttributeValues(List<PmsProductAttributeValue> productAttributeValues) {
        this.productAttributeValues = productAttributeValues;
    }

    public List<CmsSubjectProductRelation> getSubjectProductRelations() {
        return subjectProductRelations;
    }

    public void setSubjectProductRelations(List<CmsSubjectProductRelation> subjectProductRelations) {
        this.subjectProductRelations = subjectProductRelations;
    }

    public List<CmsPreferenceAreaProductRelation> getPreferenceAreaProductRelations() {
        return preferenceAreaProductRelations;
    }

    public void setPreferenceAreaProductRelations(List<CmsPreferenceAreaProductRelation> preferenceAreaProductRelations) {
        this.preferenceAreaProductRelations = preferenceAreaProductRelations;
    }
}