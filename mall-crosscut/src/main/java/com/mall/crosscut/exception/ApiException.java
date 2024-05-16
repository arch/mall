/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.exception;

import com.mall.crosscut.standard.ApiResult;
import com.mall.crosscut.standard.StatusCode;

/**
 * This exception class will be used in those scenarios when an exception should be thrown but provide
 * a custom {@link ApiResult}, such as customizing the status code and more meaningful message.
 */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final StatusCode statusCode;

    public ApiException(String message) {
        super(message);
        statusCode = null;
    }

    public ApiException(Throwable cause) {
        super(cause);
        statusCode = null;
    }

    public ApiException(StatusCode statusCode) {
        super(String.format("%d: %s", statusCode.getCode(), statusCode.getMessage()));
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}