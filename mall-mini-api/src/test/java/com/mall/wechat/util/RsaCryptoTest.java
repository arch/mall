/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mall.wechat.auth.AutoUpdateCertificatesVerifier;
import com.mall.wechat.auth.PrivateKeySigner;
import com.mall.wechat.auth.WechatPayCredentials;
import com.mall.wechat.auth.WechatPayValidator;
import com.mall.wechat.core.AesDecryptor;
import com.mall.wechat.http.WechatPayHttpClientBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RsaCryptoTest {
    private static CloseableHttpClient httpClient;
    private static AutoUpdateCertificatesVerifier verifier;

    @BeforeAll
    static void setup() throws FileNotFoundException {
        // 商户号
        String mchId = "";
        // 商户证书序列号
        String mchSerialNo = "";
        // api密钥
        String apiV3Key = "";

        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
                new FileInputStream("src/main/resources/apiclient_key.pem"));

        AesDecryptor decryptor = new AesDecryptor(apiV3Key.getBytes(StandardCharsets.UTF_8));

        // 使用自动更新的签名验证器，不需要传入证书
        verifier = new AutoUpdateCertificatesVerifier(
                new WechatPayCredentials(mchId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)), decryptor);


        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                .withValidator(new WechatPayValidator(verifier))
                .build();
    }

    @AfterAll
    static void after() throws IOException {
        httpClient.close();
    }

    @Test
    public void encryptTest() throws Exception {
        String text = "helloworld";
        String ciphertext = RsaCryptoUtil.encryptOAEP(text, verifier.getValidCertificate());
        System.out.println("ciphertext: " + ciphertext);
    }

    @Test
    public void postEncryptDataTest() throws Exception {
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/smartguide/guides");

        String text = "helloworld";
        String ciphertext = RsaCryptoUtil.encryptOAEP(text, verifier.getValidCertificate());

        String data = "{\n"
                + "  \"store_id\" : 1234,\n"
                + "  \"corpid\" : \"1234567890\",\n"
                + "  \"name\" : \"" + ciphertext + "\",\n"
                + "  \"mobile\" : \"" + ciphertext + "\",\n"
                + "  \"qr_code\" : \"https://open.work.weixin.qq.com/wwopen/userQRCode?vcode=xxx\",\n"
                + "  \"sub_mchid\" : \"1234567890\",\n"
                + "  \"avatar\" : \"logo\",\n"
                + "  \"userid\" : \"robert\"\n"
                + "}";
        StringEntity reqEntity = new StringEntity(
                data, ContentType.create("application/json", "utf-8"));
        httpPost.setEntity(reqEntity);
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Wechatpay-Serial", "");

        try (CloseableHttpResponse response = httpClient.execute(httpPost);) {
            assertTrue(response.getStatusLine().getStatusCode() != 401);
            String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            assertNotNull(json);
            assertTrue(response.getStatusLine().getStatusCode() != 400);
            HttpEntity entity2 = response.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        }
    }

    @Test
    void serialNumberBigIntegerConvert() {
        String serialNumber = "36A46F02119A2CD44BE42B215723DFB3026233FE";
        BigInteger val = new BigInteger(serialNumber, 16);
        String serialNumber2 = val.toString(16).toUpperCase();
        String s3 = String.format("%X", val);
        String s4 = val.toString();
        assertEquals(serialNumber, serialNumber2);
    }
}