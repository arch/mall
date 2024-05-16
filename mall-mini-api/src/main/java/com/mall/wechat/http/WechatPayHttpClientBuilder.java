/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.http;

import com.mall.wechat.auth.CertificatesVerifier;
import com.mall.wechat.auth.PrivateKeySigner;
import com.mall.wechat.auth.WechatPayCredentials;
import com.mall.wechat.auth.WechatPayValidator;
import com.mall.wechat.core.Credentials;
import com.mall.wechat.core.Validator;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.List;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.execchain.ClientExecChain;

public class WechatPayHttpClientBuilder  extends HttpClientBuilder {
    private static final String os = System.getProperty("os.name") + "/" + System.getProperty("os.version");
    private static final String version = System.getProperty("java.version");

    private Credentials credentials;
    private Validator validator;

    private WechatPayHttpClientBuilder() {
        super();

        String userAgent = String.format(
                "WechatPay-Apache-HttpClient/%s (%s) Java/%s",
                getClass().getPackage().getImplementationVersion(),
                os,
                version == null ? "Unknown" : version);
        setUserAgent(userAgent);
    }

    public static WechatPayHttpClientBuilder create() {
        return new WechatPayHttpClientBuilder();
    }

    public WechatPayHttpClientBuilder withMerchant(String merchantId, String serialNo, PrivateKey privateKey) {
        this.credentials =
                new WechatPayCredentials(merchantId, new PrivateKeySigner(serialNo, privateKey));
        return this;
    }

    public WechatPayHttpClientBuilder withCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public WechatPayHttpClientBuilder withWechatpay(List<X509Certificate> certificates) {
        this.validator = new WechatPayValidator(new CertificatesVerifier(certificates));
        return this;
    }

    public WechatPayHttpClientBuilder withValidator(Validator validator) {
        this.validator = validator;
        return this;
    }

    @Override
    public CloseableHttpClient build() {
        if (credentials == null) {
            throw new IllegalArgumentException("缺少身份认证信息");
        }
        if (validator == null) {
            throw new IllegalArgumentException("缺少签名验证信息");
        }

        return super.build();
    }

    @Override
    protected ClientExecChain decorateProtocolExec(final ClientExecChain requestExecutor) {
        return new SignatureExec(this.credentials, this.validator, requestExecutor);
    }
}