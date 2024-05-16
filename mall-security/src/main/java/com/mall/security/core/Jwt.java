/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.security.core;

public class Jwt {
    private String token;
    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public static Jwt valueOf(String token, int expiresIn) {
        Jwt jwt = new Jwt();
        jwt.setToken(token);
        jwt.setExpiresIn(expiresIn);
        return jwt;
    }
}