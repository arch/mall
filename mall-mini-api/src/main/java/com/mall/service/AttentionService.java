/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.domain.BrandAttention;

import java.util.List;

public interface AttentionService {
    void add(BrandAttention brandAttention);

    void delete(long brandId);

    List<BrandAttention> list(int pageIndex, int pageSize);

    BrandAttention get(long brandId);

    void clear();
}