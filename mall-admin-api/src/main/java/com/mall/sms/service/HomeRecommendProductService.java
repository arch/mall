/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service;

import com.mall.model.SmsHomeRecommendProduct;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface HomeRecommendProductService {

    List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, int pageIndex, int pageSize);

    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    void delete(List<Long> ids);

    void updateSort(Long id, Integer sort);

    @Transactional
    void create(List<SmsHomeRecommendProduct> recommendProducts);
}