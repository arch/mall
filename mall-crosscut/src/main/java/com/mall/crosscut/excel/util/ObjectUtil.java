/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.excel.util;

import java.util.Collection;

public interface ObjectUtil {

    static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    static boolean hasText(String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}