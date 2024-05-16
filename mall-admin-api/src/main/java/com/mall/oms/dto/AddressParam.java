/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.dto;

import com.mall.crosscut.validation.Flags;
import io.swagger.v3.oas.annotations.media.Schema;

public class AddressParam {
    @Schema(description = "地址名称")
    private String addressName;
    @Flags({0, 1})
    @Schema(description = "默认发货地址：0->否；1->是")
    private int sendStatus;
    @Flags({0, 1})
    @Schema(description = "是否默认收货地址：0->否；1->是")
    private int receiveStatus;
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

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
    }

    public int getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(int receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

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