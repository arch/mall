/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class OrderDeliveryParam {

    @Schema(description = "订单id")
    private Long orderId;
    @Schema(description = "物流公司编号")
    private String deliveryCode;
    @Schema(description = "物流公司名称")
    private String deliveryCompany;
    @Schema(description = "物流单号")
    private String deliverySn;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliverySn() {
        return deliverySn;
    }

    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn;
    }
}