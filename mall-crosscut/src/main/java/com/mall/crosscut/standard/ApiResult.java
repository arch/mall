/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.standard;

/**
 * Represents a standard response protocol
 * @param <T> The type of the raw result
 */
public class ApiResult<T> implements StatusCode {
    private int code;
    private String message;
    private T data;

    public ApiResult() { }

    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public ApiResult(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResult(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(StandardCode.SUCCESS, data);
    }

    public static <T> ApiResult<T> success(T data, String message) {
        return new ApiResult<>(StandardCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ApiResult<T> valueOf(StatusCode statusCode, T data) {
        return new ApiResult<>(statusCode, data);
    }

    public static <T> ApiResult<T> valueOf(StatusCode statusCode) {
        return new ApiResult<>(statusCode);
    }

    public static <T> ApiResult<T> failure(String message) {
        return new ApiResult<>(StandardCode.FAILURE.getCode(), message, null);
    }

    public static <T> ApiResult<T> failure() {
        return valueOf(StandardCode.FAILURE);
    }

    public static <T> ApiResult<T> badRequest() {
        return valueOf(StandardCode.BAD_REQUEST);
    }

    public static <T> ApiResult<T> badRequest(String message) {
        return new ApiResult<>(StandardCode.BAD_REQUEST.getCode(), message, null);
    }

    public static <T> ApiResult<T> unauthorized(T data) {
        return new ApiResult<>(StandardCode.UNAUTHORIZED, data);
    }

    public static <T> ApiResult<T> forbidden(T data) {
        return new ApiResult<>(StandardCode.FORBIDDEN, data);
    }

    public static <T> ApiResult<T> reentry() {
        return new ApiResult<>(StandardCode.REENTRY);
    }

    public static <T> ApiResult<T> reentry(String message) {
        return new ApiResult<>(StandardCode.REENTRY.getCode(), message);
    }

    public static <T> ApiResult<T> retry() {
        return new ApiResult<>(StandardCode.RETRY);
    }

    public static <T> ApiResult<T> retry(String message) {
        if (message == null || message.isEmpty()) {
            message = "please retry again(request again)";
        }
        return new ApiResult<>(StandardCode.RETRY.getCode(), message);
    }

    public static ApiResult<?> SQLFailure(String message) {
        return new ApiResult<>(StandardCode.SQL_FAILURE.getCode(), message);
    }

    public static ApiResult<?> ajax302(String location) {
        return new ApiResult<>(StandardCode.AJAX_REDIRECT, location);
    }
}