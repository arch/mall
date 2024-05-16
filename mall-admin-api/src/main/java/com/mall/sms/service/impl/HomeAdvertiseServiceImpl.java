/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service.impl;

import static com.mall.mapper.SmsHomeAdvertiseDynamicSqlSupport.smsHomeAdvertise;
import static org.mybatis.dynamic.sql.SqlBuilder.isBetween;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.SmsHomeAdvertiseMapper;
import com.mall.model.SmsHomeAdvertise;
import com.mall.sms.service.HomeAdvertiseService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HomeAdvertiseServiceImpl implements HomeAdvertiseService {
    private final SmsHomeAdvertiseMapper advertiseMapper;

    public HomeAdvertiseServiceImpl(SmsHomeAdvertiseMapper advertiseMapper) {
        this.advertiseMapper = advertiseMapper;
    }

    @Override
    public List<SmsHomeAdvertise> list(String name, Integer type, String endDate, int pageIndex, int pageSize) {
        return advertiseMapper.select(c -> {
            AbstractWhereDSL<?> whereDSL = null;
            if (StringUtils.hasText(name)) {
                whereDSL = c.where(smsHomeAdvertise.name, isLike("%" + name + "%"));
            }

            if (type != null) {
                if (whereDSL != null) {
                    whereDSL.and(smsHomeAdvertise.type, isEqualTo(type));
                } else {
                    c.where(smsHomeAdvertise.type, isEqualTo(type));
                }
            }

            if (!StringUtils.isEmpty(endDate)) {
                String startStr = endDate + " 00:00:00";
                String endStr = endDate + " 23:59:59";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime start = null;
                try {
                    start = LocalDateTime.parse(startStr, formatter);
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
                LocalDateTime end = null;
                try {
                    end = LocalDateTime.parse(endStr, formatter);
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
                if (start != null && end != null) {
                    if (whereDSL != null) {
                        whereDSL.and(smsHomeAdvertise.endTime, isBetween(start).and(end));
                    } else {
                        c.where(smsHomeAdvertise.endTime, isBetween(start).and(end));
                    }
                }
            }

            c.orderBy(smsHomeAdvertise.sort.descending()).limit(pageSize).offset(pageIndex * pageSize);

            return c;
        });
    }

    @Override
    public void create(SmsHomeAdvertise advertise) {
        advertise.setClickCount(0);
        advertise.setOrderCount(0);
        int count = advertiseMapper.insert(advertise);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void update(long id, SmsHomeAdvertise advertise) {
        advertise.setId(id);
        int count = advertiseMapper.updateByPrimaryKeySelective(advertise);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(List<Long> ids) {
        if (ids.isEmpty()) {
            return;
        }
        int count = advertiseMapper.delete(c -> c.where(smsHomeAdvertise.id, isIn(ids)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateStatus(long id, Integer status) {
        int count = advertiseMapper.update(c ->
                c.set(smsHomeAdvertise.status).equalTo(status).where(smsHomeAdvertise.id, isEqualTo(id)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public SmsHomeAdvertise get(Long id) {
        return advertiseMapper.selectByPrimaryKey(id).orElse(null);
    }
}