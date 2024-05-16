/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.core;

import com.mall.crosscut.standard.ApiResult;
import com.mall.crosscut.standard.StandardCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * This exceptions handler will only convert all exceptions that thrown by API to {@link ApiResult ApiResult}.
 * The exceptions logging or notifying to system maintain developers is delegating to {@link ExceptionNotifier ExceptionNotifier}.
 */
@RestControllerAdvice
public class ApiExceptionHandler {
    private final ExceptionNotifier exceptionNotifier;

    public ApiExceptionHandler(ExceptionNotifier exceptionNotifier) {
        this.exceptionNotifier = exceptionNotifier;
    }

    @ExceptionHandler(Exception.class)
    public ApiResult<?> handler(HttpServletRequest request, Exception cause) {
        ApiResult<?> result = ApiResult.valueOf(StandardCode.valueOf(cause));
        exceptionNotifier.notify(request, cause, result.getMessage());
        return result;
    }
}