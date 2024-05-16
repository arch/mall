/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.service.impl;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.OmsOrderSettingMapper;
import com.mall.model.OmsOrderSetting;
import com.mall.oms.service.OrderSettingService;
import java.util.List;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

@Service
public class OrderSettingServiceImpl implements OrderSettingService {
    private final OmsOrderSettingMapper orderSettingMapper;

    public OrderSettingServiceImpl(
            OmsOrderSettingMapper orderSettingMapper) {
        this.orderSettingMapper = orderSettingMapper;
    }

    @Override
    public List<OmsOrderSetting> list() {
        return orderSettingMapper.select(SelectDSLCompleter.allRows());
    }

    @Override
    public OmsOrderSetting get(Long id) {
        return orderSettingMapper.selectByPrimaryKey(id).orElse(null);
    }

    @Override
    public void update(Long id, OmsOrderSetting orderSetting) {
        orderSetting.setId(id);
        int count = orderSettingMapper.updateByPrimaryKey(orderSetting);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }
}