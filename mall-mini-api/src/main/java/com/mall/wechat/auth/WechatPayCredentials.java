/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.auth;

import com.mall.wechat.core.Credentials;
import com.mall.wechat.http.WechatPayUploadHttpPost;
import com.mall.wechat.util.Nonce;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.util.EntityUtils;

/**
 * https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay4_0.shtml
 */
public class WechatPayCredentials implements Credentials {
    protected String merchantId;
    protected Signer signer;

    public WechatPayCredentials(String merchantId, Signer signer) {
        this.merchantId = merchantId;
        this.signer = signer;
    }

    public String getMerchantId() {
        return merchantId;
    }

    protected long generateTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    @Override
    public final String getSchema() {
        // https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay4_0.shtml
        return "WECHATPAY2-SHA256-RSA2048";
    }

    @Override
    public final String getToken(HttpRequestWrapper request) throws IOException {
        // https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay4_0.shtml
        String nonceStr = Nonce.generate();
        long timestamp = generateTimestamp();

        String message = buildMessage(nonceStr, timestamp, request);

        SignResult signature = signer.sign(message.getBytes(StandardCharsets.UTF_8));

        return "mchid=\"" + getMerchantId() + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + signature.getSerialNumber() + "\","
                + "signature=\"" + signature.getSignature() + "\"";
    }

    protected final String buildMessage(String nonce, long timestamp, HttpRequestWrapper request) throws IOException {
        URI uri = request.getURI();
        String canonicalUrl = uri.getRawPath();
        if (uri.getQuery() != null) {
            canonicalUrl += "?" + uri.getRawQuery();
        }

        String body = "";
        // PATCH,POST,PUT
        if (request.getOriginal() instanceof WechatPayUploadHttpPost) {
            body = ((WechatPayUploadHttpPost) request.getOriginal()).getMeta();
        } else if (request instanceof HttpEntityEnclosingRequest) {
            body = EntityUtils.toString(((HttpEntityEnclosingRequest) request).getEntity());
        }

        // format: https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay4_0.shtml
        // HTTP请求方法\n
        // URL\n
        // 请求时间戳\n
        // 请求随机串\n
        // 请求报文主体\n
        return request.getRequestLine().getMethod() + "\n"
                + canonicalUrl + "\n"
                + timestamp + "\n"
                + nonce + "\n"
                + body + "\n";
    }
}