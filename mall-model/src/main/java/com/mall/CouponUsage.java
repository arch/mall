/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall;

public enum CouponUsage {
    /**
     * 全场通用
     */
    ALL_POWERFUL(0),
    /**
     * 指定分类
     */
    SPECIFIED_CATEGORY(1),
    /**
     * 指定商品
     */
    SPECIFIED_PRODUCT(2);

    private final int type;

    CouponUsage(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static CouponUsage valueOf(int type) {
        for (CouponUsage v : values()) {
            if (v.getType() == type) {
                return v;
            }
        }

        throw new IllegalArgumentException("type: " + type + " not defined byte CouponUsage");
    }
}