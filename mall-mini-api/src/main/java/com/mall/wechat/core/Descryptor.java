/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.core;

import java.security.GeneralSecurityException;

public interface Descryptor {
    String decryptToString(byte[] associatedData, byte[] nonce, String ciphertext) throws GeneralSecurityException;
}