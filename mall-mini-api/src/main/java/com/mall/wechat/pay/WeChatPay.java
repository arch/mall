/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.pay;

import com.mall.crosscut.util.Assert;
import com.mall.conf.WeChatConfiguration;
import com.mall.wechat.auth.AutoUpdateCertificatesVerifier;
import com.mall.wechat.auth.PrivateKeySigner;
import com.mall.wechat.auth.Signer;
import com.mall.wechat.auth.Verifier;
import com.mall.wechat.auth.WechatPayCredentials;
import com.mall.wechat.auth.WechatPayValidator;
import com.mall.wechat.core.AesDecryptor;
import com.mall.wechat.core.Descryptor;
import com.mall.wechat.http.WechatPayHttpClientBuilder;
import com.mall.wechat.util.Json;
import com.mall.wechat.util.PemUtil;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class WeChatPay {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CloseableHttpClient httpClient;
    private final WeChatConfiguration conf;
    private final Signer signer;
    private final Verifier verifier;
    private final AesDecryptor decryptor;

    public WeChatPay(WeChatConfiguration conf) {
        this.conf = conf;
        try {
            PrivateKey privateKey = PemUtil.loadPrivateKey(new ClassPathResource("apiclient_key.pem").getInputStream());
            decryptor = new AesDecryptor(conf.getMerchant().getV3Key().getBytes(StandardCharsets.UTF_8));
            signer = new PrivateKeySigner(conf.getMerchant().getCertSerialNo(), privateKey);
            verifier = new AutoUpdateCertificatesVerifier(
                    new WechatPayCredentials(conf.getMerchant().getId(), signer), decryptor);
            httpClient = WechatPayHttpClientBuilder.create()
                    .withMerchant(conf.getMerchant().getId(), conf.getMerchant().getCertSerialNo(), privateKey)
                    .withValidator(new WechatPayValidator(verifier))
                    .build();
        } catch (Throwable cause) {
            logger.error(cause.getMessage(), cause);
            throw new RuntimeException(cause);
        }
    }

    public Descryptor getDecryptor() {
        return decryptor;
    }

    public boolean verify(String serialNumber, byte[] message, String signature) {
        return verifier.verify(serialNumber, message, signature);
    }

    public Payment prepay(Order order) {
        // https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_1.shtml
        order.setAppid(conf.getMiniProgram().getId());
        order.setMchid(conf.getMerchant().getId());

        try {
            URIBuilder uriBuilder = new URIBuilder("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
            PrepayResult result =  httpPost(uriBuilder.build(), order, PrepayResult.class);
            return new Payment(conf.getMiniProgram().getId(), result.getPrepayId()).sign(signer);
        } catch (URISyntaxException cause) {
            logger.error(cause.getMessage(), cause);
            Assert.failure("调用微信统一下单失败");
        }

        throw new RuntimeException("oops");
    }

    private <R, T> R httpPost(URI uri, T body, Class<R> clazz) {
        HttpPost httpPost = new HttpPost(uri);
        String bodyJson = Json.serialize(body);
        logger.info(bodyJson);
        StringEntity se = new StringEntity(bodyJson, ContentType.APPLICATION_JSON);
        httpPost.setEntity(se);
        httpPost.setHeader("Accept", "application/json");
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            // https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay2_1.shtml
            // https://pay.weixin.qq.com/wiki/doc/apiv3/wechatpay/wechatpay2_0.shtml
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            if (statusCode != 200) {
                ApiResult apiResult = Json.deserialize(json, ApiResult.class);
                logger.error(json);
                Assert.failure(String.format("调用微信支付失败：%s", apiResult.getMessage()));
            }

            return Json.deserialize(json, clazz);
        } catch (IOException cause) {
            throw new RuntimeException(cause.getMessage(), cause);
        }
    }
}