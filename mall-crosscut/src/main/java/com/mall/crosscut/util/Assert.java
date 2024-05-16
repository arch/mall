/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.util;

import com.mall.crosscut.exception.ApiException;
import com.mall.crosscut.standard.StatusCode;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

public interface Assert {
    static void failure(String message) {
        throw new ApiException(message);
    }

    static void failure(StatusCode statusCode) {
        throw new ApiException(statusCode);
    }

    static<T> void ensure(Predicate<T> predicate, T t, String message) {
        if (!predicate.test(t)) {
            throw new ApiException(message);
        }
    }

    static<T> void ensure(Predicate<T> predicate, T t, StatusCode statusCode) {
        if (!predicate.test(t)) {
            throw new ApiException(statusCode);
        }
    }

    static void ensure(BooleanSupplier predicate, String message) {
        if (!predicate.getAsBoolean()) {
            throw new ApiException(message);
        }
    }

    static void ensure(BooleanSupplier predicate, StatusCode statusCode) {
        if (!predicate.getAsBoolean()) {
            throw new ApiException(statusCode);
        }
    }

    static void ensure(boolean condition, String message) {
        if (!condition) {
            throw new ApiException(message);
        }
    }

    static void ensure(boolean condition, StatusCode statusCode) {
        if (!condition) {
            throw new ApiException(statusCode);
        }
    }
}