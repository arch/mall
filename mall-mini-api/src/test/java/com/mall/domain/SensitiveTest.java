/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mall.crosscut.util.Json;
import org.junit.jupiter.api.Test;

class SensitiveTest {
    @Test
    void testDecrypt() {
        String json = "{\"errMsg\":\"getPhoneNumber:ok\",\"encryptedData\":\"umZmWnxGyERV6NOIbRvgUKch+94DmIBKEgY+l6wkxI4cwKx9CVQWxBsg8QMyX1Qg3755zu6JppF2iUrcpe3wRLbOk4H+4Zsd6Vvhzya0INWKwsXdt5wryCycysvJ45j7htHFqXzTHKsLDMj8IyX7JefOLnl3iiF3ZcwTln/Ep1ko/I43LT51mQhJ3nBVIc9O81yEqEVTtDhaiwbwILUaOA==\",\"iv\":\"jJBViCoWbvLXw5taQHOn/g==\"}";
        SensitivePhone phone = Json.deserialize(json, SensitivePhone.class);
        assertNotNull(phone);
        assertDoesNotThrow(() -> {
            String raw = phone.decrypt("HyVFkGl5F5OQWJZZaNzBBg==");
        });
    }
}