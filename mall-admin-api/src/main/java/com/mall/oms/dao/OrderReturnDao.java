/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.dao;

import com.mall.oms.dto.OrderReturnApplyResult;
import com.mall.oms.dto.ReturnApplyQueryParam;
import com.mall.model.OmsOrderReturnApply;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderReturnDao {
    List<OmsOrderReturnApply> listApply(
            @Param("param") ReturnApplyQueryParam queryParam,
            @Param("offset") int offset,
            @Param("limit") int limit);

    OrderReturnApplyResult getApplyDetail(@Param("id") Long id);
}