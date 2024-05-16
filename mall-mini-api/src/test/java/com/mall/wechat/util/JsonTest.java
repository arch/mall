/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.util;

import static org.junit.jupiter.api.Assertions.*;

import com.mall.wechat.api.TokenResult;
import org.junit.jupiter.api.Test;

class JsonTest {

    @Test
    void deserialize() {
        String json = "{\n"
                + "    \"access_token\": \"access token\",\n"
                + "    \"expires_in\": 1000,\n"
                + "    \"errcode\": 0,\n"
                + "    \"errmsg\": \"\"\n"
                + "}";
        TokenResult result = Json.deserialize(json, TokenResult.class);
        assertNotNull(result);
        assertEquals("access token", result.getAccessToken());
    }
}