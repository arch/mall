/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.pay;

public class Payer {
    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Payer() { }

    public Payer(String openid) {
        this.openid = openid;
    }

    public static Payer valueOf(String openid) {
        return new Payer(openid);
    }
}