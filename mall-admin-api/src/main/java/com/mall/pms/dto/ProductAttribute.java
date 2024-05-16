/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductAttribute {
    @Schema(description = "商品属性ID")
    private Long attributeId;
    @Schema(description = "商品属性分类ID")
    private Long categoryId;

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}