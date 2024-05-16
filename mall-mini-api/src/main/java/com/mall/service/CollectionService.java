/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.domain.ProductCollection;

import java.util.List;

public interface CollectionService {
    void add(ProductCollection productCollection);

    void delete(long productId);

    List<ProductCollection> list(int pageIndex, int pageSize);

    ProductCollection get(long productId);

    void clear();
}