/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service.impl;

import static com.mall.mapper.SmsHomeBrandDynamicSqlSupport.smsHomeBrand;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.SmsHomeBrandMapper;
import com.mall.model.SmsHomeBrand;
import com.mall.sms.service.HomeBrandService;
import java.util.List;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HomeBrandServiceImpl implements HomeBrandService {

    private final SmsHomeBrandMapper homeBrandMapper;

    public HomeBrandServiceImpl(SmsHomeBrandMapper homeBrandMapper) {
        this.homeBrandMapper = homeBrandMapper;
    }

    @Override
    public List<SmsHomeBrand> list(String brandName, Integer recommendStatus, int pageIndex, int pageSize) {
        return homeBrandMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = null;
            if (StringUtils.hasText(brandName)) {
                whereDSL = c.where(smsHomeBrand.brandName, isLike("%" + brandName + "%"));
            }

            if (recommendStatus != null) {
                if (whereDSL != null) {
                    whereDSL.and(smsHomeBrand.recommendStatus, isEqualTo(recommendStatus));
                } else {
                    c.where(smsHomeBrand.recommendStatus, isEqualTo(recommendStatus));
                }
            }

            c.orderBy(smsHomeBrand.sort.descending()).limit(pageSize).offset(pageIndex * pageSize);

            return c;
        });
    }

    @Override
    public void create(List<SmsHomeBrand> homeBrands) {
        if (homeBrands.isEmpty()) {
            return;
        }

        int count = 0;
        for (SmsHomeBrand smsHomeBrand : homeBrands) {
            smsHomeBrand.setRecommendStatus(1);
            smsHomeBrand.setSort(0);
            count += homeBrandMapper.insert(smsHomeBrand);
        }

        Assert.ensure(count == homeBrands.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateSort(long id, Integer sort) {
        int count = homeBrandMapper.update(c ->
                c.set(smsHomeBrand.sort).equalTo(sort).where(smsHomeBrand.id, isEqualTo(id)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        if (ids.isEmpty()) {
            return;
        }

        int count = homeBrandMapper.update(c ->
                c.set(smsHomeBrand.recommendStatus).equalTo(recommendStatus).where(smsHomeBrand.id, isIn(ids)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(List<Long> ids) {
        if (ids.isEmpty()) {
            return;
        }
        int count = homeBrandMapper.delete(c -> c.where(smsHomeBrand.id, isIn(ids)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }
}