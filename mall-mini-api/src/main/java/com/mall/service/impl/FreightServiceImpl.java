/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import static com.mall.mapper.PmsFreightCompanyDynamicSqlSupport.pmsFreightCompany;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import com.mall.service.FreightService;
import com.mall.mapper.PmsFreightCompanyMapper;
import com.mall.model.PmsFreightCompany;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FreightServiceImpl implements FreightService {
    private final PmsFreightCompanyMapper freightCompanyMapper;

    public FreightServiceImpl(PmsFreightCompanyMapper freightCompanyMapper) {
        this.freightCompanyMapper = freightCompanyMapper;
    }

    @Override
    public List<PmsFreightCompany> list(int pageIndex, int pageSize) {
        return freightCompanyMapper.select(c -> c.limit(pageSize).offset(pageIndex * pageSize));
    }

    @Override
    public List<PmsFreightCompany> search(String nameOrCode) {
        return freightCompanyMapper.select(c ->
                c.where(pmsFreightCompany.name, isLike("%" + nameOrCode + "%"))
                        .or(pmsFreightCompany.code, isLike("%" + nameOrCode + "%")));
    }
}