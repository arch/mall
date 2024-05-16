/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.util;

import com.mall.crosscut.exception.ApiException;
import com.mall.crosscut.standard.StandardCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertTest {

    @Test
    void failure() {
        assertThrows(ApiException.class, () -> Assert.failure(StandardCode.AJAX_REDIRECT));
    }

    @Test
    void ensure() {
        assertThrows(ApiException.class, () -> Assert.ensure(() -> false, "failure"));
    }
}