/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.security.core;

import javax.servlet.http.HttpServletRequest;

public interface TokenResolver {
    String resolve(HttpServletRequest request);
}