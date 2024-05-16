/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dao;

import com.mall.model.PmsMemberPrice;
import com.mall.model.PmsProductFullReduction;
import com.mall.model.PmsProductLadder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductPriceDao {
    int insertMemberPrices(@Param("list") List<PmsMemberPrice> memberPrices);

    int insertLadderPrices(@Param("list") List<PmsProductLadder> productLadders);

    int insertFullReductionPrices(@Param("list") List<PmsProductFullReduction> productFullReductionList);
}