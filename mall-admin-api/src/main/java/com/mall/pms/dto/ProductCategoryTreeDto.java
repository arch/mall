/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dto;

import com.mall.model.PmsProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ProductCategoryTreeDto extends PmsProductCategory {
    @Schema(description = "子级分类")
    List<PmsProductCategory> children;

    public List<PmsProductCategory> getChildren() {
        return children;
    }

    public void setChildren(List<PmsProductCategory> children) {
        this.children = children;
    }
}