/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class OrderParam {
    @Schema(description = "收货地址ID")
    private Long memberReceiveAddressId;
    @Schema(description = "优惠券ID")
    private Long couponId;
    @Schema(description = "使用的积分数")
    private Integer useIntegration;
    @Schema(description = "支付方式")
    private Integer payType;
    @Schema(description = "被选中的购物车商品ID")
    private List<Long> cartIds;

    public Long getMemberReceiveAddressId() {
        return memberReceiveAddressId;
    }

    public void setMemberReceiveAddressId(Long memberReceiveAddressId) {
        this.memberReceiveAddressId = memberReceiveAddressId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Integer getUseIntegration() {
        return useIntegration;
    }

    public void setUseIntegration(Integer useIntegration) {
        this.useIntegration = useIntegration;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public List<Long> getCartIds() {
        return cartIds;
    }

    public void setCartIds(List<Long> cartIds) {
        this.cartIds = cartIds;
    }
}