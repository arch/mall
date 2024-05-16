/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.pay;

import com.mall.wechat.auth.SignResult;
import com.mall.wechat.auth.Signer;
import com.mall.wechat.util.Nonce;

import java.nio.charset.StandardCharsets;

public class Payment {
    private static final String signType = "RSA";

    private String appId;
    private long timeStamp;
    private String nonceStr;
    private String prepayId;
    private String paySign;

    public Payment(String appId, String prepayId) {
        // https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_4.shtml
        this.appId = appId;
        this.prepayId = prepayId;
        timeStamp = System.currentTimeMillis() / 1000;
        nonceStr = Nonce.generate();
    }

    public String getAppId() {
        return appId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public String getPackage() {
        return String.format("prepay_id=%s", prepayId);
    }

    public String getSignType() {
        return signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public Payment sign(Signer signer) {
        // https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_4.shtml
        // 小程序appId\n
        // 时间戳\n
        // 时间戳\n
        // 订单详情扩展字符串\n
        String token = appId + "\n"
                + timeStamp + "\n"
                + nonceStr + "\n"
                + getPackage() + "\n";

        SignResult signature = signer.sign(token.getBytes(StandardCharsets.UTF_8));
        paySign = signature.getSignature();
        return this;
    }
}