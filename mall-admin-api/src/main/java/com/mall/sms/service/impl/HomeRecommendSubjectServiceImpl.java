/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service.impl;

import static com.mall.mapper.SmsHomeRecommendSubjectDynamicSqlSupport.smsHomeRecommendSubject;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.SmsHomeRecommendSubjectMapper;
import com.mall.model.SmsHomeRecommendSubject;
import com.mall.sms.service.HomeRecommendSubjectService;
import java.util.List;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HomeRecommendSubjectServiceImpl implements HomeRecommendSubjectService {
    private final SmsHomeRecommendSubjectMapper recommendSubjectMapper;

    public HomeRecommendSubjectServiceImpl(
            SmsHomeRecommendSubjectMapper recommendSubjectMapper) {
        this.recommendSubjectMapper = recommendSubjectMapper;
    }

    @Override
    public void create(List<SmsHomeRecommendSubject> recommendSubjects) {
        if (recommendSubjects.isEmpty()) {
            return;
        }

        int count = 0;
        for (SmsHomeRecommendSubject recommendProduct : recommendSubjects) {
            recommendProduct.setRecommendStatus(1);
            recommendProduct.setSort(0);
            count += recommendSubjectMapper.insert(recommendProduct);
        }
        Assert.ensure(count == recommendSubjects.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateSort(Long id, Integer sort) {
        int count = recommendSubjectMapper.update(c ->
                c.set(smsHomeRecommendSubject.sort).equalTo(sort).where(smsHomeRecommendSubject.id, isEqualTo(id)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(List<Long> ids) {
        if (ids.isEmpty()) {
            return;
        }
        int count = recommendSubjectMapper.delete(c -> c.where(smsHomeRecommendSubject.id, isIn(ids)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        if (ids.isEmpty()) {
            return;
        }

        int count = recommendSubjectMapper.update(c ->
                c.set(smsHomeRecommendSubject.recommendStatus).equalTo(recommendStatus).where(smsHomeRecommendSubject.id, isIn(ids)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, int pageIndex,
            int pageSize) {
        return recommendSubjectMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = null;
            if (StringUtils.hasText(subjectName)) {
                whereDSL = c.where(smsHomeRecommendSubject.subjectName, isLike("%" + subjectName + "%"));
            }

            if (recommendStatus != null) {
                if (whereDSL != null) {
                    whereDSL.and(smsHomeRecommendSubject.recommendStatus, isEqualTo(recommendStatus));
                } else {
                    c.where(smsHomeRecommendSubject.recommendStatus, isEqualTo(recommendStatus));
                }
            }

            c.orderBy(smsHomeRecommendSubject.sort.descending()).limit(pageSize).offset(pageIndex * pageSize);

            return c;
        });
    }
}