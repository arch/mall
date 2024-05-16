/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductEditDto extends ProductDto {
    @Schema(description = "商品所选分类的父id")
    private Long categoryParentId;

    public Long getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Long categoryParentId) {
        this.categoryParentId = categoryParentId;
    }
}