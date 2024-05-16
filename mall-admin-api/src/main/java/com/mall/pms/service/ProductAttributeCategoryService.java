/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service;

import com.mall.model.PmsProductAttributeCategory;
import com.mall.pms.dto.ProductAttributeCategoryDto;

import java.util.List;

public interface ProductAttributeCategoryService {
    void create(String name);

    void update(Long id, String name);

    void delete(Long id);

    PmsProductAttributeCategory get(Long id);

    List<PmsProductAttributeCategory> getList(int pageIndex, int pageSize);

    List<ProductAttributeCategoryDto> listWithAttributes();
}