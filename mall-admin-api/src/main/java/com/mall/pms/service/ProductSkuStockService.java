/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service;

import com.mall.model.PmsSkuStock;

import java.util.List;

public interface ProductSkuStockService {
    List<PmsSkuStock> getList(Long productId, String keyword);

    void update(Long productId, List<PmsSkuStock> skuStocks);
}