/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.api;

import com.mall.crosscut.util.Assert;
import com.mall.conf.WeChatConfiguration;
import com.mall.wechat.http.WechatPayHttpClientBuilder;
import com.mall.wechat.util.Json;
import com.mall.wechat.util.PemUtil;
import com.mall.wechat.auth.AutoUpdateCertificatesVerifier;
import com.mall.wechat.auth.PrivateKeySigner;
import com.mall.wechat.auth.WechatPayCredentials;
import com.mall.wechat.auth.WechatPayValidator;
import com.mall.wechat.core.AesDecryptor;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class WeChatClient {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CloseableHttpClient httpClient;
    private final WeChatConfiguration conf;

    public WeChatClient(WeChatConfiguration conf) {
        this.conf = conf;
        try {
            PrivateKey privateKey = PemUtil.loadPrivateKey(new ClassPathResource("apiclient_key.pem").getInputStream());
            AesDecryptor decryptor = new AesDecryptor(conf.getMerchant().getV3Key().getBytes(StandardCharsets.UTF_8));
            AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                    new WechatPayCredentials(conf.getMerchant().getId(),
                            new PrivateKeySigner(conf.getMerchant().getCertSerialNo(), privateKey)), decryptor);

            httpClient = WechatPayHttpClientBuilder.create()
                    .withMerchant(conf.getMerchant().getId(), conf.getMerchant().getCertSerialNo(), privateKey)
                    .withValidator(new WechatPayValidator(verifier))
                    .build();
        } catch (Throwable cause) {
            logger.error(cause.getMessage(), cause);
            throw new RuntimeException(cause);
        }
    }

    public SessionResult getSession(String code) {
        try {
            URIBuilder uriBuilder = new URIBuilder("https://api.weixin.qq.com/sns/jscode2session");
            uriBuilder.setParameter("appid", conf.getMiniProgram().getId());
            uriBuilder.setParameter("secret", conf.getMiniProgram().getSecret());
            uriBuilder.setParameter("js_code", code);
            uriBuilder.setParameter("grant_type", "authorization_code");
            return httpGet(uriBuilder.build(), SessionResult.class);
        } catch (URISyntaxException cause) {
            logger.error(cause.getMessage(), cause);
            Assert.failure("微信登录凭证校验失败");
        }

        throw new RuntimeException("oops");
    }

    public TokenResult getAccessToken() {
        try {
            URIBuilder uriBuilder = new URIBuilder("https://api.weixin.qq.com/cgi-bin/token");
            uriBuilder.setParameter("appid", conf.getMiniProgram().getId());
            uriBuilder.setParameter("secret", conf.getMiniProgram().getSecret());
            uriBuilder.setParameter("grant_type", "client_credential");
            return httpGet(uriBuilder.build(), TokenResult.class);
        } catch (URISyntaxException cause) {
            logger.error(cause.getMessage(), cause);
            Assert.failure("获取小程序全局唯一后台接口调用凭据失败");
        }

        throw new RuntimeException("oops");
    }

    private <T extends ApiResult> T httpGet(URI uri, Class<T> clazz) {
        HttpGet httpGet = new HttpGet(uri);
        httpGet.addHeader("Accept", "application/json");
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            Assert.ensure(response.getStatusLine().getStatusCode() == 200,
                    Json.serialize(response.getStatusLine()));
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            T result = Json.deserialize(json, clazz);
            result.ensureSuccess();
            return result;
        } catch (IOException cause) {
            throw new RuntimeException(cause.getMessage(), cause);
        }
    }
}