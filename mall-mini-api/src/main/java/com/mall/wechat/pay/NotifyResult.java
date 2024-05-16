/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.pay;

public class NotifyResult {
    public static final NotifyResult SUCCESS = new NotifyResult("SUCCESS", "成功");

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotifyResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static NotifyResult failure(String message) {
        return new NotifyResult("FAILURE", message);
    }
}