/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dao;

import com.mall.pms.dto.ProductAttribute;
import com.mall.pms.dto.ProductAttributeCategoryDto;

import java.util.List;

public interface ProductAttributeDao {
    List<ProductAttributeCategoryDto> selectCategoryWithAttributes();

    List<ProductAttribute> getAttributes(long productCategoryId);
}