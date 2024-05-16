/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.excel.convert;

public interface Converter {

    default Object convert(int row, int cell, Object value) {
        return value;
    }
}