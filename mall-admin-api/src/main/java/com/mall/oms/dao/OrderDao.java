/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.dao;

import com.mall.oms.dto.OrderDetail;
import com.mall.oms.dto.OrderQueryParam;
import com.mall.model.OmsOrder;
import com.mall.oms.dto.OrderDeliveryParam;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDao {
    List<OmsOrder> getList(@Param("param") OrderQueryParam queryParam, @Param("offset") int offset, @Param("limit") int limit);

    int delivery(@Param("list") List<OrderDeliveryParam> deliveryParams);

    OrderDetail getDetail(@Param("id") Long id);
}