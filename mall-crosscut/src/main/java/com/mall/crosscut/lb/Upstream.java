/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.lb;

public interface Upstream {
    boolean isAvailable();

    String id();
}