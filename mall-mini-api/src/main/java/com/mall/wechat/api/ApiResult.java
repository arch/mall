/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.api;

import com.mall.crosscut.util.Assert;
import com.mall.wechat.util.Json;

public class ApiResult {
    private static final int SUCCESS_CODE = 0;

    private int errcode;
    private String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public boolean success() {
        return errcode == SUCCESS_CODE;
    }

    public void ensureSuccess() {
        Assert.ensure(success(), Json.serialize(this));
    }
}