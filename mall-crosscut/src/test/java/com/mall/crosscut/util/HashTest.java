/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTest {

    @Test
    void md5() {
        String plainText = "m@ll";
        // 9d2278f65987e04e75323af4e7e598a2
        String md5 = HashUtil.md5(plainText);
        assertEquals(32, md5.length());

        String apiv3 = "sunrisem@ll";
        // e134c20381b4c13bb687db8bd8261b82
        String key = HashUtil.md5(apiv3);
        assertEquals(32, key.length());
    }

    @Test
    void sh1() {
        String raw = "{\"nickName\":\"Band\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Guangzhou\",\"province\":\"Guangdong\",\"country\":\"CN\",\"avatarUrl\":\"http://wx.qlogo.cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0\"}HyVFkGl5F5OQWJZZaNzBBg==";
        String sh1 = "75e81ceda165f4ffa64f4068af58c64b8f54b88c";
        assertEquals(sh1, HashUtil.sha1(raw));
    }
}