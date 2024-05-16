/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.api;

import com.mall.crosscut.util.Assert;
import com.mall.crosscut.util.HashUtil;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.util.ObjectUtils;

public abstract class Sensitive {
    private String iv;
    private String encryptedData;
    private String rawData;
    private String signature;

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void verifySignature(String sessionKey) {
        if (ObjectUtils.isEmpty(signature)) {
            return;
        }
        String signature2 = HashUtil.sha1(rawData + sessionKey);

        Assert.ensure(signature.equals(signature2), "签名验证失败");
    }

    public String decrypt(String sessionKey) {
        byte[] aesKey = Base64.getDecoder().decode(sessionKey);
        byte[] aesIV = Base64.getDecoder().decode(iv);
        byte[] encrypted = Base64.getDecoder().decode(encryptedData);

        try {
            // docs: AES/CBC/PKCS7Padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(aesIV);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, paramSpec);
            return new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8);
        } catch (Throwable cause) {
            Assert.failure("敏感数据解密失败");
        }

        throw new RuntimeException("oops");
    }
}