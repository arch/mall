/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderReturnDetail {
    private Long id;
    @Schema(description = "退款金额")
    private BigDecimal returnAmount;
    @Schema(description = "状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private int status;
    @Schema(description = "原因")
    private String reason;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "处理备注")
    private String handleNote;
    @Schema(description = "处理时间")
    private LocalDateTime handleTime;
    @Schema(description = "申请时间")
    private LocalDateTime createTime;
    @Schema(description = "退货单")
    private String returnFreightSn;
    @Schema(description = "退货地址")
    private Address returnAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getHandleNote() {
        return handleNote;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

    public LocalDateTime getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(LocalDateTime handleTime) {
        this.handleTime = handleTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getReturnFreightSn() {
        return returnFreightSn;
    }

    public void setReturnFreightSn(String returnFreightSn) {
        this.returnFreightSn = returnFreightSn;
    }

    public Address getReturnAddress() {
        return returnAddress;
    }

    public void setReturnAddress(Address returnAddress) {
        this.returnAddress = returnAddress;
    }

    public static class Address {
        @Schema(description = "收发货人姓名")
        private String name;
        @Schema(description = "收货人电话")
        private String phone;
        @Schema(description = "省/直辖市")
        private String province;
        @Schema(description = "市")
        private String city;
        @Schema(description = "区")
        private String region;
        @Schema(description = "详细地址")
        private String detailAddress;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }
    }
}