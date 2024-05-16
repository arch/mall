/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mall.wechat.util.PemUtil;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HttpClientBuilderTest {
    private static CloseableHttpClient httpClient;

    private static String reqdata = "{\n"
            + "    \"stock_id\": \"9433645\",\n"
            + "    \"stock_creator_mchid\": \"1900006511\",\n"
            + "    \"out_request_no\": \"20190522_001中文11\",\n"
            + "    \"appid\": \"wxab8acb865bb1637e\"\n"
            + "}";

    @BeforeAll
    static void setup() throws FileNotFoundException {
        // 商户号
        String mchId = "";
        // 商户证书序列号
        String mchSerialNo = "**1*3*";

        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
                new FileInputStream("src/main/resources/apiclient_key.pem"));
        X509Certificate wechatpayCertificate = PemUtil.loadCertificate(
                new FileInputStream("src/main/resources/apiclient_cert.pem"));

        ArrayList<X509Certificate> listCertificates = new ArrayList<>();
        listCertificates.add(wechatpayCertificate);

        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                .withWechatpay(listCertificates)
                .build();
    }

    @AfterAll
    static void after() throws IOException {
        httpClient.close();
    }

    @Test
    public void getCertificateTest() throws Exception {
        URIBuilder uriBuilder = new URIBuilder("https://api.mch.weixin.qq.com/v3/certificates");
        uriBuilder.setParameter("p", "1&2");
        uriBuilder.setParameter("q", "你好");

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
    public void getCertificatesWithoutCertTest() throws Exception {
        // 商户号
        String mchId = "";
        // 商户证书序列号
        String mchSerialNo = "";

        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
                new FileInputStream("src/main/resources/apiclient_key.pem"));

        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                .withValidator(response -> true)
                .build();

        getCertificateTest();
    }

    @Test
    public void postNonRepeatableEntityTest() throws IOException {
        HttpPost httpPost = new HttpPost(
                "https://api.mch.weixin.qq.com/v3/marketing/favor/users/oHkLxt_htg84TUEbzvlMwQzVDBqo/coupons");
        InputStream stream = new ByteArrayInputStream(reqdata.getBytes(StandardCharsets.UTF_8));
        InputStreamEntity reqEntity = new InputStreamEntity(stream);
        reqEntity.setContentType("application/json");
        httpPost.setEntity(reqEntity);
        httpPost.addHeader("Accept", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            assertTrue(response.getStatusLine().getStatusCode() != 401);
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        }
    }

    @Test
    public void postRepeatableEntityTest() throws IOException {
        HttpPost httpPost = new HttpPost(
                "https://api.mch.weixin.qq.com/v3/marketing/favor/users/oHkLxt_htg84TUEbzvlMwQzVDBqo/coupons");

        // NOTE: 建议指定charset=utf-8。低于4.4.6版本的HttpCore，不能正确的设置字符集，可能导致签名错误
        StringEntity reqEntity = new StringEntity(
                reqdata, ContentType.create("application/json", "utf-8"));
        httpPost.setEntity(reqEntity);
        httpPost.addHeader("Accept", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            assertTrue(response.getStatusLine().getStatusCode() != 401);
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        }
    }
}