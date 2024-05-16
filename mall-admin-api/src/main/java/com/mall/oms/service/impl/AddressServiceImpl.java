/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.service.impl;

import static com.mall.mapper.OmsCompanyAddressDynamicSqlSupport.id;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

import com.mall.oms.dto.AddressParam;
import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.OmsCompanyAddressMapper;
import com.mall.model.OmsCompanyAddress;
import com.mall.oms.service.AddressService;
import java.util.List;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final OmsCompanyAddressMapper addressMapper;

    public AddressServiceImpl(
            OmsCompanyAddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public List<OmsCompanyAddress> list() {
        return addressMapper.select(SelectDSLCompleter.allRows());
    }

    @Override
    public void create(AddressParam address) {
        OmsCompanyAddress companyAddress = new OmsCompanyAddress();
        BeanUtils.copyProperties(address, companyAddress);
        int count = addressMapper.insertSelective(companyAddress);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void update(long id, AddressParam address) {
        OmsCompanyAddress companyAddress = new OmsCompanyAddress().withId(id);
        BeanUtils.copyProperties(address, companyAddress);
        int count = addressMapper.updateByPrimaryKeySelective(companyAddress);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(List<Long> ids) {
        Assert.ensure(ids.size() > 0, "必须提供需要删除的地址ID");
        int count = addressMapper.delete(c -> c.where(id, isIn(ids)));
        Assert.ensure(count == ids.size(), StandardCode.SQL_FAILURE.withMessage("删除失败，请检查地址ID是否正确"));
    }
}