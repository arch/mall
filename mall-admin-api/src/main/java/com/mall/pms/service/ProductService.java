/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service;

import com.mall.model.PmsProduct;
import com.mall.pms.dto.ProductDto;
import com.mall.pms.dto.ProductEditDto;
import com.mall.pms.dto.ProductQuery;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    void create(ProductDto productDto);

    List<PmsProduct> list(ProductQuery productQuery, int pageIndex, int pageSize);

    ProductEditDto getEdit(long id);

    @Transactional
    void update(long id, ProductDto productDto);

    List<PmsProduct> list(String keyword);

    @Transactional
    void updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);

    void updatePublishStatus(List<Long> ids, Integer publishStatus);

    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    void updateNewStatus(List<Long> ids, Integer newStatus);

    void updateDeleteStatus(List<Long> ids, Integer deleteStatus);
}