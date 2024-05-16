/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.auth;

public class SignResult {
    private final String signature;
    private final String serialNumber;

    public String getSignature() {
        return signature;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public SignResult(String signature, String serialNumber) {
        this.signature = signature;
        this.serialNumber = serialNumber;
    }
}