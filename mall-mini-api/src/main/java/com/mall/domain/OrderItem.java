/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import com.mall.model.OmsOrderItem;
import io.swagger.v3.oas.annotations.media.Schema;

public class OrderItem extends OmsOrderItem {
    @Schema(description = "售后状态")
    private int returnStatus;

    public int getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(int returnStatus) {
        this.returnStatus = returnStatus;
    }
}