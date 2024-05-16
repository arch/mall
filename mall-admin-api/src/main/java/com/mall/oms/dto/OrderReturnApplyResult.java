/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.dto;

import com.mall.model.OmsCompanyAddress;
import com.mall.model.OmsOrderReturnApply;
import io.swagger.v3.oas.annotations.media.Schema;

public class OrderReturnApplyResult extends OmsOrderReturnApply {
    @Schema(description =  "公司收货地址")
    private OmsCompanyAddress address;

    public OmsCompanyAddress getAddress() {
        return address;
    }

    public void setAddress(OmsCompanyAddress address) {
        this.address = address;
    }
}