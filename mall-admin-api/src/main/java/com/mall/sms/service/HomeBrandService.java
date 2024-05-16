/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service;

import com.mall.model.SmsHomeBrand;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface HomeBrandService {

    List<SmsHomeBrand> list(String brandName, Integer recommendStatus, int pageIndex, int pageSize);
    @Transactional
    void create(List<SmsHomeBrand> homeBrands);

    void updateSort(long id, Integer sort);

    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    void delete(List<Long> ids);
}