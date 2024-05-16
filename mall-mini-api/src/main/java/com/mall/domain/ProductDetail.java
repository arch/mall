/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import com.mall.model.PmsBrand;
import com.mall.model.PmsProduct;
import com.mall.model.PmsProductAttribute;
import com.mall.model.PmsProductAttributeValue;
import com.mall.model.PmsProductFullReduction;
import com.mall.model.PmsProductLadder;
import com.mall.model.PmsSkuStock;
import com.mall.model.SmsCoupon;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ProductDetail {
    @Schema(description = "商品信息")
    private PmsProduct product;
    @Schema(description = "商品品牌")
    private PmsBrand brand;
    @Schema(description = "商品属性与参数")
    private List<PmsProductAttribute> attributes;
    @Schema(description = "手动录入的商品属性与参数值")
    private List<PmsProductAttributeValue> attributeValues;
    @Schema(description = "商品的sku库存信息")
    private List<PmsSkuStock> stocks;
    @Schema(description = "商品阶梯价格设置")
    private List<PmsProductLadder> ladders;
    @Schema(description = "商品满减价格设置")
    private List<PmsProductFullReduction> fullReductions;
    @Schema(description = "商品可用优惠券")
    private List<SmsCoupon> coupons;
    @Schema(description = "商品评价数")
    private long commentCount;

    public PmsProduct getProduct() {
        return product;
    }

    public void setProduct(PmsProduct product) {
        this.product = product;
    }

    public PmsBrand getBrand() {
        return brand;
    }

    public void setBrand(PmsBrand brand) {
        this.brand = brand;
    }

    public List<PmsProductAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<PmsProductAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<PmsProductAttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<PmsProductAttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    public List<PmsSkuStock> getStocks() {
        return stocks;
    }

    public void setStocks(List<PmsSkuStock> stocks) {
        this.stocks = stocks;
    }

    public List<PmsProductLadder> getLadders() {
        return ladders;
    }

    public void setLadders(List<PmsProductLadder> ladders) {
        this.ladders = ladders;
    }

    public List<PmsProductFullReduction> getFullReductions() {
        return fullReductions;
    }

    public void setFullReductions(List<PmsProductFullReduction> fullReductions) {
        this.fullReductions = fullReductions;
    }

    public List<SmsCoupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<SmsCoupon> coupons) {
        this.coupons = coupons;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long count) {
        this.commentCount = count;
    }
}