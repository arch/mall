/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.util;

import java.security.SecureRandom;

public interface Nonce {
    String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    SecureRandom RANDOM = new SecureRandom();

    static String generate() {
        char[] chars = new char[32];
        for (int index = 0; index < chars.length; ++index) {
            chars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(chars);
    }
}