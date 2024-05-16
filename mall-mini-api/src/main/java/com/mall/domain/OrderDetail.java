/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import com.mall.model.OmsOrder;
import com.mall.model.OmsOrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class OrderDetail extends OmsOrder {
    @Schema(description = "订单项")
    private List<OmsOrderItem> orderItems;

    public List<OmsOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OmsOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}