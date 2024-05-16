/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service.impl;

import static com.mall.mapper.SmsHomeRecommendProductDynamicSqlSupport.smsHomeRecommendProduct;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.SmsHomeRecommendProductMapper;
import com.mall.model.SmsHomeRecommendProduct;
import com.mall.sms.service.HomeRecommendProductService;
import java.util.List;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HomeRecommendProductServiceImpl implements HomeRecommendProductService {
    private final SmsHomeRecommendProductMapper recommendProductMapper;

    public HomeRecommendProductServiceImpl(
            SmsHomeRecommendProductMapper recommendProductMapper) {
        this.recommendProductMapper = recommendProductMapper;
    }

    @Override
    public void create(List<SmsHomeRecommendProduct> recommendProducts) {
        if (recommendProducts.isEmpty()) {
            return;
        }

        int count = 0;
        for (SmsHomeRecommendProduct recommendProduct : recommendProducts) {
            recommendProduct.setRecommendStatus(1);
            recommendProduct.setSort(0);
            count += recommendProductMapper.insert(recommendProduct);
        }
        Assert.ensure(count == recommendProducts.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, int pageIndex, int pageSize) {
        return recommendProductMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = null;
            if (StringUtils.hasText(productName)) {
                whereDSL = c.where(smsHomeRecommendProduct.productName, isLike("%" + productName + "%"));
            }

            if (recommendStatus != null) {
                if (whereDSL != null) {
                    whereDSL.and(smsHomeRecommendProduct.recommendStatus, isEqualTo(recommendStatus));
                } else {
                    c.where(smsHomeRecommendProduct.recommendStatus, isEqualTo(recommendStatus));
                }
            }

            c.orderBy(smsHomeRecommendProduct.sort.descending()).limit(pageSize).offset(pageIndex * pageSize);

            return c;
        });
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        if (ids.isEmpty()) {
            return;
        }

        int count = recommendProductMapper.update(c ->
                c.set(smsHomeRecommendProduct.recommendStatus).equalTo(recommendStatus).where(smsHomeRecommendProduct.id, isIn(ids)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(List<Long> ids) {
        if (ids.isEmpty()) {
            return;
        }
        int count = recommendProductMapper.delete(c -> c.where(smsHomeRecommendProduct.id, isIn(ids)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateSort(Long id, Integer sort) {
        int count = recommendProductMapper.update(c ->
                c.set(smsHomeRecommendProduct.sort).equalTo(sort).where(smsHomeRecommendProduct.id, isEqualTo(id)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }
}