/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dto;

import com.mall.model.PmsProductAttribute;
import com.mall.model.PmsProductAttributeCategory;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ProductAttributeCategoryDto extends PmsProductAttributeCategory {
    @Schema(description =  "商品属性列表")
    private List<PmsProductAttribute> attributes;

    public List<PmsProductAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<PmsProductAttribute> attributes) {
        this.attributes = attributes;
    }
}