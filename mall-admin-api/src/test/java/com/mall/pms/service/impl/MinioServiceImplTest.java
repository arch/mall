/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class MinioServiceImplTest {
    @Test
    void json() {
        assertDoesNotThrow(() -> {
            String json = MinioServiceImpl.BucketPolicy.readonly("mall");
            assertNotNull(json);
        });
    }
}