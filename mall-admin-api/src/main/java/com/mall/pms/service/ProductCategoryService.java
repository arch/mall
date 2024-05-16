/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service;

import com.mall.model.PmsProductCategory;
import com.mall.pms.dto.ProductCategoryDto;
import com.mall.pms.dto.ProductCategoryTreeDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductCategoryService {
    @Transactional
    void create(ProductCategoryDto productCategoryDto);

    @Transactional
    void update(long id, ProductCategoryDto productCategoryDto);

    List<PmsProductCategory> getList(long parentId, int pageIndex, int pageSize);

    PmsProductCategory getOne(long id);

    void delete(long id);

    void updateNavStatus(List<Long> ids, Integer navStatus);

    void updateShowStatus(List<Long> ids, Integer showStatus);

    List<ProductCategoryTreeDto> listWithChildren();
}