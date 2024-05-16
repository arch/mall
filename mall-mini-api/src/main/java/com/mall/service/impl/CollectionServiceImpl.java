/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import com.mall.domain.ProductCollection;
import com.mall.service.CollectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Override
    public void add(ProductCollection productCollection) {

    }

    @Override
    public void delete(long productId) {

    }

    @Override
    public List<ProductCollection> list(int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public ProductCollection get(long productId) {
        return null;
    }

    @Override
    public void clear() {

    }
}