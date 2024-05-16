/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import com.mall.dao.HomeDao;
import com.mall.mapper.PmsBrandMapper;
import com.mall.mapper.PmsProductMapper;
import com.mall.model.PmsBrand;
import com.mall.model.PmsProduct;
import com.mall.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mall.mapper.PmsProductDynamicSqlSupport.deleteStatus;
import static com.mall.mapper.PmsProductDynamicSqlSupport.pmsProduct;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class BrandServiceImpl implements BrandService {
    private final HomeDao homeDao;
    private final PmsBrandMapper brandMapper;
    private final PmsProductMapper productMapper;

    public BrandServiceImpl(HomeDao homeDao, PmsBrandMapper brandMapper, PmsProductMapper productMapper) {
        this.homeDao = homeDao;
        this.brandMapper = brandMapper;
        this.productMapper = productMapper;
    }

    @Override
    public List<PmsBrand> recommendList(int pageIndex, int pageSize) {
        return homeDao.getRecommendBrandList(pageIndex * pageSize, pageSize);
    }

    @Override
    public PmsBrand detail(long id) {
        return brandMapper.selectByPrimaryKey(id).orElse(null);
    }

    @Override
    public List<PmsProduct> productList(Long brandId, int pageIndex, int pageSize) {
        return productMapper.select(c ->
                c.where(deleteStatus, isEqualTo(1))
                        .and(pmsProduct.brandId, isEqualTo(brandId))
                        .limit(pageSize).offset(pageIndex * pageSize));
    }
}