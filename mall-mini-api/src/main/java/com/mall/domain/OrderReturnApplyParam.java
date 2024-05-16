/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotEmpty;

public class OrderReturnApplyParam {
    @NotEmpty
    @Schema(description = "订单id")
    private Long orderId;
    @NotEmpty
    @Schema(description = "订单项id")
    private Long itemId;
    @NotEmpty
    @Schema(description = "退货人姓名")
    private String returnName;
    @NotEmpty
    @Schema(description = "退货人电话")
    private String returnPhone;
    @NotEmpty
    @Schema(description = "原因")
    private String reason;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "凭证图片，以逗号隔开")
    private String proofPics;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getReturnPhone() {
        return returnPhone;
    }

    public void setReturnPhone(String returnPhone) {
        this.returnPhone = returnPhone;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProofPics() {
        return proofPics;
    }

    public void setProofPics(String proofPics) {
        this.proofPics = proofPics;
    }
}