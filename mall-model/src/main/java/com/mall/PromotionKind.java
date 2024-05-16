/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall;

public enum PromotionKind {
    /**
     * 没有促销使用原价
     */
    OriginalPrice(0),
    /**
     * 使用促销价
     */
    PromotionPrice(1),
    /**
     * 使用会员价
     */
    MemberPrice(2),
    /**
     * 使用阶梯价格
     */
    LadderPrice(3),
    /**
     * 使用满减价格
     */
    FullReductionPrice(4),
    /**
     * 限时购
     */
    LtpPrice(5);

    private final int code;

    PromotionKind(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PromotionKind valueOf(int code) {
        for (PromotionKind pk : values()) {
            if (pk.getCode() == code) {
                return pk;
            }
        }

        throw new IllegalArgumentException("the code: " + code + " not defined by PromotionKind");
    }
}