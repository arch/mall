/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.core;

import java.io.IOException;
import org.apache.http.client.methods.HttpRequestWrapper;

public interface Credentials {
    String getSchema();

    String getToken(HttpRequestWrapper request) throws IOException;
}