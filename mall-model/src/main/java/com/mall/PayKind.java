/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall;

public enum PayKind {
    /**
     * 未支付
     */
    UNPAID(0),
    /**
     * 支付宝
     */
    ALIPAY(1),
    /**
     * 微信
     */
    WECHAT(2);

    private final int code;

    PayKind(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PayKind valueOf(int code) {
        for (PayKind v : values()) {
            if (v.getCode() == code) {
                return v;
            }
        }

        throw new IllegalArgumentException("code: " + code + " undefined in PayKind");
    }
}