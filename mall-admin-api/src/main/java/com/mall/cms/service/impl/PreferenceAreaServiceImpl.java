/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.cms.service.impl;

import com.mall.cms.service.PreferenceAreaService;
import com.mall.mapper.CmsPreferenceAreaMapper;
import com.mall.model.CmsPreferenceArea;
import java.util.List;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

@Service
public class PreferenceAreaServiceImpl implements PreferenceAreaService {
    private final CmsPreferenceAreaMapper preferenceAreaMapper;

    public PreferenceAreaServiceImpl(
            CmsPreferenceAreaMapper preferenceAreaMapper) {
        this.preferenceAreaMapper = preferenceAreaMapper;
    }

    @Override
    public List<CmsPreferenceArea> listAll() {
        return preferenceAreaMapper.select(SelectDSLCompleter.allRows());
    }
}