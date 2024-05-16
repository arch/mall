/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.standard;

public interface StatusCode {

    int getCode();

    String getMessage();

    static StatusCode valueOf(final int code, final String message) {
        return new StatusCode() {
            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        };
    }

    static StatusCode valueOf(final StatusCode statusCode, final String message) {
        if (message == null) {
            return statusCode;
        }
        return new StatusCode() {
            @Override
            public int getCode() {
                return statusCode.getCode();
            }

            @Override
            public String getMessage() {
                return message;
            }
        };
    }
}