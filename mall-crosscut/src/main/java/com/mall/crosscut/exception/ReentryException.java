/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.exception;

public class ReentryException extends RuntimeException {
    public ReentryException(String message) {
        super(message);
    }
}