/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service.impl;

import static com.mall.mapper.SmsHomeNewProductDynamicSqlSupport.smsHomeNewProduct;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.SmsHomeNewProductMapper;
import com.mall.model.SmsHomeNewProduct;
import com.mall.sms.service.HomeNewProductService;
import java.util.List;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HomeNewProductServiceImpl implements HomeNewProductService {
    private final SmsHomeNewProductMapper homeNewProductMapper;

    public HomeNewProductServiceImpl(SmsHomeNewProductMapper homeNewProductMapper) {
        this.homeNewProductMapper = homeNewProductMapper;
    }

    @Override
    public void create(List<SmsHomeNewProduct> homeNewProducts) {
        if (homeNewProducts.isEmpty()) {
            return;
        }

        int count = 0;
        for (SmsHomeNewProduct SmsHomeNewProduct : homeNewProducts) {
            SmsHomeNewProduct.setRecommendStatus(1);
            SmsHomeNewProduct.setSort(0);
            count += homeNewProductMapper.insert(SmsHomeNewProduct);
        }
        Assert.ensure(count == homeNewProducts.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, int pageIndex, int pageSize) {
        return homeNewProductMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = null;
            if (StringUtils.hasText(productName)) {
                whereDSL = c.where(smsHomeNewProduct.productName, isLike("%" + productName + "%"));
            }

            if (recommendStatus != null) {
                if (whereDSL != null) {
                    whereDSL.and(smsHomeNewProduct.recommendStatus, isEqualTo(recommendStatus));
                } else {
                    c.where(smsHomeNewProduct.recommendStatus, isEqualTo(recommendStatus));
                }
            }

            c.orderBy(smsHomeNewProduct.sort.descending()).limit(pageSize).offset(pageIndex * pageSize);

            return c;
        });
    }

    @Override
    public void updateSort(long id, Integer sort) {
        int count = homeNewProductMapper.update(c ->
                c.set(smsHomeNewProduct.sort).equalTo(sort).where(smsHomeNewProduct.id, isEqualTo(id)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        if (ids.isEmpty()) {
            return;
        }

        int count = homeNewProductMapper.update(c ->
                c.set(smsHomeNewProduct.recommendStatus).equalTo(recommendStatus).where(smsHomeNewProduct.id, isIn(ids)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(List<Long> ids) {
        if (ids.isEmpty()) {
            return;
        }
        int count = homeNewProductMapper.delete(c -> c.where(smsHomeNewProduct.id, isIn(ids)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }
}