/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall;

public enum OrderStatus {
    /**
     * 待付款
     */
    UN_PAY(0),
    /**
     * 待发货
     */
    UN_DELIVER(1),
    /**
     * 已发货
     */
    DELIVERED(2),
    /**
     * 已完成
     */
    FINISHED(3),
    /**
     * 已关闭
     */
    CLOSED(4),
    /**
     * 无效订单
     */
    INVALID(5);

    private final int status;

    OrderStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static OrderStatus valueOf(int status) {
        for (OrderStatus v : values()) {
            if (v.getStatus() == status) {
                return v;
            }
        }

        throw new IllegalArgumentException("status: " + status + " undefined in OrderStatus");
    }
}