/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.auth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class PrivateKeySigner implements Signer {
    private final String certificateSerialNumber;

    private final PrivateKey privateKey;

    public PrivateKeySigner(String serialNumber, PrivateKey privateKey) {
        this.certificateSerialNumber = serialNumber;
        this.privateKey = privateKey;
    }

    @Override
    public SignResult sign(byte[] message) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey);
            sign.update(message);

            return new SignResult(
                    Base64.getEncoder().encodeToString(sign.sign()),
                    certificateSerialNumber);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持SHA256withRSA", e);
        } catch (SignatureException e) {
            throw new RuntimeException("签名计算失败", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("无效的私钥", e);
        }
    }
}