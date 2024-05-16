/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.auth;

import java.security.cert.X509Certificate;

public interface Verifier {
    boolean verify(String serialNumber, byte[] message, String signature);

    X509Certificate getValidCertificate();
}