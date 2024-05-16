/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.wechat.core.Credentials;
import com.mall.wechat.core.Descryptor;
import com.mall.wechat.http.WechatPayHttpClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoUpdateCertificatesVerifier implements Verifier {
    private static final Logger logger = LoggerFactory.getLogger(AutoUpdateCertificatesVerifier.class);

    //证书下载地址
    private static final String CertDownloadPath = "https://api.mch.weixin.qq.com/v3/certificates";

    //上次更新时间
    private volatile Instant instant;

    //证书更新间隔时间，单位为分钟
    private final int minutesInterval;

    private CertificatesVerifier verifier;

    private final Credentials credentials;

    private final Descryptor descryptor;

    private final ReentrantLock lock = new ReentrantLock();

    public AutoUpdateCertificatesVerifier(Credentials credentials, Descryptor descryptor) {
        this(credentials, descryptor, TimeInterval.OneHour.getMinutes());
    }

    public AutoUpdateCertificatesVerifier(Credentials credentials, Descryptor descryptor, int minutesInterval) {
        this.credentials = credentials;
        this.descryptor = descryptor;
        this.minutesInterval = minutesInterval;
        //构造时更新证书
        try {
            autoUpdateCert();
            instant = Instant.now();
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean verify(String serialNumber, byte[] message, String signature) {
        if (instant == null || Duration.between(instant, Instant.now()).toMinutes() >= minutesInterval) {
            if (lock.tryLock()) {
                try {
                    autoUpdateCert();
                    //更新时间
                    instant = Instant.now();
                } catch (GeneralSecurityException | IOException e) {
                    logger.warn("Auto update cert failed, exception = " + e);
                } finally {
                    lock.unlock();
                }
            }
        }
        return verifier.verify(serialNumber, message, signature);
    }

    @Override
    public X509Certificate getValidCertificate() {
        return verifier.getValidCertificate();
    }

    private void autoUpdateCert() throws IOException, GeneralSecurityException {
        try (CloseableHttpClient httpClient = WechatPayHttpClientBuilder.create()
                .withCredentials(credentials)
                .withValidator(verifier == null ? (response) -> true : new WechatPayValidator(verifier))
                .build()) {
            HttpGet httpGet = new HttpGet(CertDownloadPath);
            httpGet.addHeader("Accept", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String body = EntityUtils.toString(response.getEntity());
                if (statusCode == 200) {
                    List<X509Certificate> newCertList = deserializeToCerts(body);
                    if (newCertList.isEmpty()) {
                        logger.warn("Cert list is empty");
                        return;
                    }
                    this.verifier = new CertificatesVerifier(newCertList);
                } else {
                    logger.warn("Auto update cert failed, statusCode = " + statusCode + ",body = " + body);
                }
            }
        }
    }

    private List<X509Certificate> deserializeToCerts(String body) throws GeneralSecurityException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataNode = mapper.readTree(body).get("data");
        List<X509Certificate> newCertList = new ArrayList<>();
        if (dataNode != null) {
            for (int i = 0, count = dataNode.size(); i < count; i++) {
                JsonNode encryptCertificateNode = dataNode.get(i).get("encrypt_certificate");
                //解密
                String cert = descryptor.decryptToString(
                        encryptCertificateNode.get("associated_data").toString().replaceAll("\"", "")
                                .getBytes(StandardCharsets.UTF_8),
                        encryptCertificateNode.get("nonce").toString().replaceAll("\"", "")
                                .getBytes(StandardCharsets.UTF_8),
                        encryptCertificateNode.get("ciphertext").toString().replaceAll("\"", ""));

                CertificateFactory cf = CertificateFactory.getInstance("X509");
                X509Certificate x509Cert = (X509Certificate) cf.generateCertificate(
                        new ByteArrayInputStream(cert.getBytes(StandardCharsets.UTF_8))
                );
                try {
                    x509Cert.checkValidity();
                } catch (CertificateExpiredException | CertificateNotYetValidException e) {
                    continue;
                }
                newCertList.add(x509Cert);
            }
        }
        return newCertList;
    }

    //时间间隔枚举，支持一小时、六小时以及十二小时
    public enum TimeInterval {
        OneHour(60), SixHours(60 * 6), TwelveHours(60 * 12);

        private final int minutes;

        TimeInterval(int minutes) {
            this.minutes = minutes;
        }

        public int getMinutes() {
            return minutes;
        }
    }
}