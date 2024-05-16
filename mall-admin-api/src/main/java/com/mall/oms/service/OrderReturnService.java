/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.service;

import com.mall.oms.dto.OrderReturnApplyResult;
import com.mall.oms.dto.ReturnApplyQueryParam;
import com.mall.oms.dto.UpdateStatusParam;
import com.mall.model.OmsOrderReturnApply;
import com.mall.model.OmsOrderReturnReason;

import java.util.List;

public interface OrderReturnService {

    List<OmsOrderReturnApply> listApply(ReturnApplyQueryParam queryParam, int pageIndex, int pageSize);

    void deleteApply(List<Long> ids);

    OrderReturnApplyResult getApply(Long id);

    void updateApplyStatus(Long id, UpdateStatusParam updateStatusParam);

    void createReason(OmsOrderReturnReason returnReason);

    void updateReason(Long id, OmsOrderReturnReason returnReason);

    void deleteReason(List<Long> ids);

    List<OmsOrderReturnReason> listReason(int pageIndex, int pageSize);

    OmsOrderReturnReason getReason(Long id);

    void updateReasonStatus(List<Long> ids, Integer status);
}