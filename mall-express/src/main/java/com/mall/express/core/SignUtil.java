/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.express.core;

import org.apache.commons.codec.digest.DigestUtils;

public interface SignUtil {
    static String sign(String msg) {
        return DigestUtils.md5Hex(msg).toUpperCase();
    }

    static String querySign(String param, String key, String customer){
        return sign(param+key+customer);
    }
}