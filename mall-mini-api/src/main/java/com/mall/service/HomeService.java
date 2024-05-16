/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.domain.HomeContent;
import com.mall.model.CmsSubject;
import com.mall.model.PmsProduct;
import com.mall.model.PmsProductCategory;

import java.util.List;

public interface HomeService {
    HomeContent content();

    List<PmsProduct> recommendProductList(int pageIndex, int pageSize);

    List<PmsProductCategory> getProductCateList(long parentId);

    List<CmsSubject> getSubjectList(Long categoryId, int pageIndex, int pageSize);

    List<PmsProduct> hotProductList(int pageIndex, int pageSize);

    List<PmsProduct> newProductList(int pageIndex, int pageSize);
}