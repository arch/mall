/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall;

public enum OrderKind {
    /**
     * 正常订单
     */
    NORMAL(0),
    /**
     * 秒杀订单
     */
    FLASH(1);

    private final int code;

    OrderKind(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderKind valueOf(int code) {
        for (OrderKind v : values()) {
            if (v.getCode() == code) {
                return v;
            }
        }

        throw new IllegalArgumentException("code: " + code + " undefined in OrderKind");
    }
}