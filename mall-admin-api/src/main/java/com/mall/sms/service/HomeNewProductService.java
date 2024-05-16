/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service;

import com.mall.model.SmsHomeNewProduct;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface HomeNewProductService {
    @Transactional
    void create(List<SmsHomeNewProduct> homeNewProducts);

    List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, int pageIndex, int pageSize);

    void updateSort(long id, Integer sort);

    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    void delete(List<Long> ids);
}