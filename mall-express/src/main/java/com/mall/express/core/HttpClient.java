/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.express.core;

import com.mall.express.request.BaseRequest;

public class HttpClient {
    private int connectTimeout = 3000;

    private int socketTimeout = 3000;

    public void setTimeOut(int connectTimeout, int socketTimeout) {
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public HttpResult execute(BaseRequest request) throws Exception {
        return HttpUtil.doPost(request.getUrl(), request, connectTimeout, socketTimeout);
    }
}