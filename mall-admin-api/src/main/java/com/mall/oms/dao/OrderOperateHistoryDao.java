/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.dao;

import com.mall.model.OmsOrderOperateHistory;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderOperateHistoryDao {
    int insertList(@Param("list") List<OmsOrderOperateHistory> histories);
}