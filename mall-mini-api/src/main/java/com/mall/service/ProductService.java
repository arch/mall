/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.domain.ProductCategory;
import com.mall.domain.ProductDetail;
import com.mall.model.PmsProduct;

import java.util.List;

public interface ProductService {
    List<ProductCategory> categoryTree();

    ProductDetail detail(long id);

    List<PmsProduct> search(String keyword, Long brandId, Long productCategoryId, int pageIndex, int pageSize, Integer sort);
}