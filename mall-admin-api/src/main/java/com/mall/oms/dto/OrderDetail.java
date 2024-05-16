/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.dto;

import com.mall.model.OmsOrder;
import com.mall.model.OmsOrderItem;
import com.mall.model.OmsOrderOperateHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class OrderDetail extends OmsOrder {

    @Schema(description = "订单商品列表")
    private List<OmsOrderItem> orderItems;
    @Schema(description = "订单操作记录列表")
    private List<OmsOrderOperateHistory> histories;

    public List<OmsOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OmsOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OmsOrderOperateHistory> getHistories() {
        return histories;
    }

    public void setHistories(List<OmsOrderOperateHistory> histories) {
        this.histories = histories;
    }
}