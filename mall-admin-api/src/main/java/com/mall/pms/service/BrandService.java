/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service;

import com.mall.model.PmsBrand;
import com.mall.pms.dto.BrandDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BrandService {
    List<PmsBrand> listAll();

    List<PmsBrand> pagedList(String keyword, int pageIndex, int pageSize);

    void create(BrandDto brandDto);

    @Transactional
    void update(long id, BrandDto brandDto);

    void delete(long id);

    PmsBrand getOne(long id);

    void delete(List<Long> ids);

    void updateShowStatus(List<Long> ids, int showStatus);

    void updateFactoryStatus(List<Long> ids, int factoryStatus);
}