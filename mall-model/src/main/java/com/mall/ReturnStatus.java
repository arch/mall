/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall;

public enum ReturnStatus {
    // 0->待处理；1->退货中；2->已完成；3->已拒绝
    APPLY(0), RETURNING(1), FINISHED(2), REFUSED(3);

    private final int status;

    ReturnStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static ReturnStatus valueOf(int status) {
        for (ReturnStatus v : values()) {
            if (v.getStatus() == status) {
                return v;
            }
        }

        throw new IllegalArgumentException("status: " + status + " undefined in OrderStatus");
    }
}