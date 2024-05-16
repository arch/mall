/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.model.PmsBrand;
import com.mall.model.PmsProduct;

import java.util.List;

public interface BrandService {
    List<PmsBrand> recommendList(int pageIndex, int pageSize);

    PmsBrand detail(long id);

    List<PmsProduct> productList(Long brandId, int pageIndex, int pageSize);
}