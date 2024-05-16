/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.security.core;

import com.nimbusds.jose.JOSEException;

public class JwtInvalidException extends JOSEException {
    public JwtInvalidException(final String message) {
        super(message);
    }

    public JwtInvalidException(final String message, final Throwable cause) {
        super(message, cause);
    }
}