/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.security.core;

public interface JwtService {
    Jwt generate(String subject) throws Exception;

    String verify(String token) throws Exception;
}