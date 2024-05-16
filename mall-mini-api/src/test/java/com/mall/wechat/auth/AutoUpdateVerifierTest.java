/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mall.wechat.core.AesDecryptor;
import com.mall.wechat.http.WechatPayHttpClientBuilder;
import com.mall.wechat.http.WechatPayUploadHttpPost;
import com.mall.wechat.util.PemUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AutoUpdateVerifierTest {

    private static CloseableHttpClient httpClient;
    private static AutoUpdateCertificatesVerifier verifier;

    @BeforeAll
    static void setup() throws FileNotFoundException {
        // 商户号
        String mchId = "*51009";
        // 商户证书序列号
        String mchSerialNo = "**00D938B949732E17";
        // api密钥
        String apiV3Key = "**d8261b82";

        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
                new FileInputStream("src/main/resources/apiclient_key.pem"));

        AesDecryptor decryptor = new AesDecryptor(apiV3Key.getBytes(StandardCharsets.UTF_8));

        //使用自动更新的签名验证器，不需要传入证书
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
    public void getCertificateTest() throws Exception {
        URIBuilder uriBuilder = new URIBuilder("https://api.mch.weixin.qq.com/v3/certificates");
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("Accept", "application/json");
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            assertEquals(200, response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        }
    }

    @Test
    public void uploadImageTest() throws Exception {
        String filePath = "/your/home/hellokitty.png";

        URI uri = new URI("https://api.mch.weixin.qq.com/v3/merchant/media/upload");

        File file = new File(filePath);
        try (FileInputStream s1 = new FileInputStream(file)) {
            String sha256 = DigestUtils.sha256Hex(s1);
            try (InputStream s2 = new FileInputStream(file)) {
                WechatPayUploadHttpPost request = new WechatPayUploadHttpPost.Builder(uri)
                        .withImage(file.getName(), sha256, s2)
                        .build();

                try (CloseableHttpResponse response = httpClient.execute(request)) {
                    assertEquals(200, response.getStatusLine().getStatusCode());
                    HttpEntity entity = response.getEntity();
                    // do something useful with the response body
                    // and ensure it is fully consumed
                    String s = EntityUtils.toString(entity);
                    System.out.println(s);
                }
            }
        }
    }
}