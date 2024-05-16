/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service;

import com.mall.model.PmsProductAttribute;
import com.mall.pms.dto.ProductAttribute;
import com.mall.pms.dto.ProductAttributeDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductAttributeService {
    List<PmsProductAttribute> getList(Long categoryId, Integer type, int pageIndex, int pageSize);

    @Transactional
    void create(ProductAttributeDto productAttributeDto);

    void update(Long id, ProductAttributeDto productAttributeDto);

    PmsProductAttribute get(long id);

    @Transactional
    void delete(List<Long> ids);

    List<ProductAttribute> getAttributes(long productCategoryId);
}