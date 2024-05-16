/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import com.mall.domain.BrandAttention;
import com.mall.service.AttentionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttentionServiceImpl implements AttentionService {
    @Override
    public void add(BrandAttention brandAttention) {

    }

    @Override
    public void delete(long brandId) {

    }

    @Override
    public List<BrandAttention> list(int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public BrandAttention get(long brandId) {
        return null;
    }

    @Override
    public void clear() {

    }
}