/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall;

public enum OrderSources {
    PC(0), APP(1);

    private final int source;

    OrderSources(int source) {
        this.source = source;
    }

    public int getSource() {
        return source;
    }
}