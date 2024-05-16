/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service.impl;

import com.mall.pms.dao.SkuStockDao;
import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.PmsSkuStockMapper;
import com.mall.model.PmsSkuStock;
import com.mall.pms.service.ProductSkuStockService;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.mall.mapper.PmsSkuStockDynamicSqlSupport.pmsSkuStock;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

@Service
public class ProductSkuStockServiceImpl implements ProductSkuStockService {
    private final PmsSkuStockMapper skuStockMapper;
    private final SkuStockDao skuStockDao;

    public ProductSkuStockServiceImpl(PmsSkuStockMapper skuStockMapper, SkuStockDao skuStockDao) {
        this.skuStockMapper = skuStockMapper;
        this.skuStockDao = skuStockDao;
    }

    @Override
    public List<PmsSkuStock> getList(Long productId, String keyword) {
        return skuStockMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = c.where(pmsSkuStock.productId, isEqualTo(productId));
            if (StringUtils.hasText(keyword)) {
                whereDSL.and(pmsSkuStock.skuCode, isLike("%" + keyword + "%"));
            }
            return c;
        });
    }

    @Override
    public void update(Long productId, List<PmsSkuStock> skuStocks) {
        if (skuStocks.isEmpty())
            return;

        int count = skuStockDao.replaceList(skuStocks);
        Assert.ensure(count == skuStocks.size(), StandardCode.SQL_FAILURE);
    }
}