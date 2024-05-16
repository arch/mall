/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.auth;

public interface Signer {
    SignResult sign(byte[] message);
}