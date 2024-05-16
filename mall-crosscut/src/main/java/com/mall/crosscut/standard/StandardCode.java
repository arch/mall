/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.standard;

import com.fasterxml.jackson.core.JsonParseException;
import com.mall.crosscut.exception.ApiException;
import com.mall.crosscut.exception.ReentryException;
import com.mall.crosscut.exception.RetryException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.Objects;

public enum StandardCode implements StatusCode {
    SUCCESS(200, "操作成功"),
    REENTRY(205, "接口重入"),
    RETRY(206, "服务繁忙，请稍后重试"),
    AJAX_REDIRECT(302, "AJAX重定向"),
    BAD_REQUEST(400, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或TOKEN已经过期"),
    INVALID_TOKEN(401, "无效TOKEN或TOKEN已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    FAILURE(500, "操作失败"),
    SQL_FAILURE(501, "数据库读写异常，请稍后重试");

    private final int code;

    private final String message;

    StandardCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public StatusCode withMessage(String message) {
        return StatusCode.valueOf(this, message);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static StatusCode valueOf(Throwable cause) {
        Class<?> clazz = cause.getClass();
        if (IllegalArgumentException.class.isAssignableFrom(clazz)) {
            return BAD_REQUEST.withMessage(cause.getMessage());
        }

        if (MethodArgumentNotValidException.class.isAssignableFrom(clazz)) {
            return valueOf((MethodArgumentNotValidException) cause);
        }

        if (BindException.class.isAssignableFrom(clazz)) {
            return valueOf((BindException) cause);
        }

        if (HttpMediaTypeNotSupportedException.class.isAssignableFrom(clazz)) {
            return valueOf((HttpMediaTypeNotSupportedException) cause);
        }

        if (HttpRequestMethodNotSupportedException.class.isAssignableFrom(clazz)) {
            return valueOf((HttpRequestMethodNotSupportedException) cause);
        }

        if (MissingServletRequestParameterException.class.isAssignableFrom(clazz)) {
            return valueOf((MissingServletRequestParameterException) cause);
        }

        if (JsonParseException.class.isAssignableFrom(clazz)) {
            return valueOf((JsonParseException) cause);
        }

        if (HttpMessageNotReadableException.class.isAssignableFrom(clazz)) {
            return valueOf((HttpMessageNotReadableException) cause);
        }

        if (NoHandlerFoundException.class.isAssignableFrom(clazz)) {
            return valueOf((NoHandlerFoundException) cause);
        }

        if (MethodArgumentTypeMismatchException.class.isAssignableFrom(clazz)) {
            return valueOf((MethodArgumentTypeMismatchException) cause);
        }

        if (ApiException.class.isAssignableFrom(clazz)) {
            ApiException apiException = ((ApiException) cause);
            if (apiException.getStatusCode() != null) {
                return apiException.getStatusCode();
            }

            return BAD_REQUEST.withMessage(apiException.getMessage());
        }

        if (ReentryException.class.isAssignableFrom(clazz)) {
            return REENTRY.withMessage(cause.getMessage());
        }

        if (RetryException.class.isAssignableFrom(cause.getClass())) {
            return RETRY.withMessage(cause.getMessage());
        }

        if (AccessDeniedException.class.isAssignableFrom(clazz)) {
            return UNAUTHORIZED.withMessage(cause.getMessage());
        }

        return originalOrMySQL(cause);
    }

    private static StatusCode valueOf(MethodArgumentTypeMismatchException cause) {
        String message =
                cause.getName() + " should be of type " + Objects.requireNonNull(cause.getRequiredType()).getName();
        return BAD_REQUEST.withMessage(message);
    }

    private static StatusCode valueOf(NoHandlerFoundException cause) {
        String message = String
                .format("No handler found for '%s' , Url: %s", cause.getHttpMethod(), cause.getRequestURL());
        return BAD_REQUEST.withMessage(message);
    }

    private static StatusCode valueOf(HttpMessageNotReadableException cause) {
        Throwable root = cause.getCause();
        String message;
        if (root instanceof JsonParseException) {
            message = String.format("请求Body不是有效Json数据, 更多信息:%s", cause.getMessage());
        } else {
            message = String.format("请求Body不是有效数据, 更多信息:%s", cause.getMessage());
        }
        return BAD_REQUEST.withMessage(message);
    }

    private static StatusCode valueOf(JsonParseException cause) {
        String message = String.format("请求Body不是有效Json数据, 更多信息:%s", cause.getMessage());
        return BAD_REQUEST.withMessage(message);
    }

    private static StatusCode valueOf(MissingServletRequestParameterException cause) {
        String message = String.format("缺少必要参数: %s, 参数类型: %s", cause.getParameterName(), cause.getParameterType());
        return BAD_REQUEST.withMessage(message);
    }

    private static StatusCode valueOf(HttpRequestMethodNotSupportedException cause) {
        StringBuilder sb = new StringBuilder();
        sb.append(cause.getMethod());
        sb.append(" method is not supported for this request. Supported methods are ");
        Objects.requireNonNull(cause.getSupportedHttpMethods()).forEach(t -> sb.append(t).append(" "));
        return BAD_REQUEST.withMessage(sb.toString());
    }

    private static StatusCode valueOf(HttpMediaTypeNotSupportedException cause) {
        StringBuilder sb = new StringBuilder();
        sb.append(cause.getContentType());
        sb.append(" media type is not supported. Supported media types are ");
        cause.getSupportedMediaTypes().forEach(t -> sb.append(t).append(", "));
        return BAD_REQUEST.withMessage(sb.toString());
    }

    private static StatusCode valueOf(BindException cause) {
        BindingResult bindingResult = cause.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            }
        }
        return BAD_REQUEST.withMessage(message);
    }

    private static StatusCode valueOf(MethodArgumentNotValidException cause) {
        StringBuilder sb = new StringBuilder();
        for (FieldError error : cause.getBindingResult().getFieldErrors()) {
            sb.append(error.getField()).append(": ").append(error.getDefaultMessage());
            sb.append(";");
        }
        for (ObjectError error : cause.getBindingResult().getGlobalErrors()) {
            sb.append(error.getObjectName()).append(": ").append(error.getDefaultMessage());
            sb.append(";");
        }
        return BAD_REQUEST.withMessage(sb.toString());
    }

    private static StatusCode originalOrMySQL(Throwable cause) {
        Throwable root = cause.getCause();
        String message = cause.getMessage();
        while (root != null) {
            if (root instanceof SQLException) {
                SQLException se = (SQLException) cause;
                message = String.format("数据访问异常, vendorCode: %d, SQLState: %s, reason: %s",
                        se.getErrorCode(), se.getSQLState(), se.getMessage());

                return SQL_FAILURE.withMessage(message);
            }

            message = root.getMessage();

            // continue to root
            root = root.getCause();
        }
        return FAILURE.withMessage(message);
    }
}