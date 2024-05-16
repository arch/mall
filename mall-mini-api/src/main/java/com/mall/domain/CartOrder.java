/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import com.mall.model.OmsOrder;
import com.mall.model.OmsOrderItem;

import java.util.List;

public class CartOrder {
    private OmsOrder order;
    private List<OmsOrderItem> orderItems;

    public OmsOrder getOrder() {
        return order;
    }

    public void setOrder(OmsOrder order) {
        this.order = order;
    }

    public List<OmsOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OmsOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}