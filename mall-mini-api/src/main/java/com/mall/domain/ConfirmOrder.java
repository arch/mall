/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import com.mall.model.UmsIntegrationConsumeSetting;
import com.mall.model.UmsMemberReceiveAddress;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

public class ConfirmOrder {
    @Schema(description = "包含优惠信息的购物车信息")
    private List<PromotionCartItem> promotionCartItems;
    @Schema(description = "用户收货地址列表")
    private List<UmsMemberReceiveAddress> receiveAddresses;
    @Schema(description = "用户可用优惠券列表")
    private List<CouponHistoryDetail> couponHistoryDetails;
    @Schema(description = "积分使用规则")
    private UmsIntegrationConsumeSetting integrationConsumeSetting;
    @Schema(description = "会员持有的积分")
    private Integer memberIntegration;
    @Schema(description = "计算的金额")
    private CalcAmount calcAmount;

    public List<PromotionCartItem> getPromotionCartItems() {
        return promotionCartItems;
    }

    public void setPromotionCartItems(List<PromotionCartItem> promotionCartItems) {
        this.promotionCartItems = promotionCartItems;
    }

    public List<UmsMemberReceiveAddress> getReceiveAddresses() {
        return receiveAddresses;
    }

    public void setReceiveAddresses(List<UmsMemberReceiveAddress> receiveAddresses) {
        this.receiveAddresses = receiveAddresses;
    }

    public List<CouponHistoryDetail> getCouponHistoryDetails() {
        return couponHistoryDetails;
    }

    public void setCouponHistoryDetails(List<CouponHistoryDetail> couponHistoryDetails) {
        this.couponHistoryDetails = couponHistoryDetails;
    }

    public UmsIntegrationConsumeSetting getIntegrationConsumeSetting() {
        return integrationConsumeSetting;
    }

    public void setIntegrationConsumeSetting(UmsIntegrationConsumeSetting integrationConsumeSetting) {
        this.integrationConsumeSetting = integrationConsumeSetting;
    }

    public Integer getMemberIntegration() {
        return memberIntegration;
    }

    public void setMemberIntegration(Integer memberIntegration) {
        this.memberIntegration = memberIntegration;
    }

    public CalcAmount getCalcAmount() {
        return calcAmount;
    }

    public void setCalcAmount(CalcAmount calcAmount) {
        this.calcAmount = calcAmount;
    }

    public static class CalcAmount{
        @Schema(description = "订单商品总金额")
        private BigDecimal totalAmount;
        @Schema(description = "运费")
        private BigDecimal freightAmount;
        @Schema(description = "活动优惠")
        private BigDecimal promotionAmount;
        @Schema(description = "应付金额")
        private BigDecimal payAmount;

        public BigDecimal getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
        }

        public BigDecimal getFreightAmount() {
            return freightAmount;
        }

        public void setFreightAmount(BigDecimal freightAmount) {
            this.freightAmount = freightAmount;
        }

        public BigDecimal getPromotionAmount() {
            return promotionAmount;
        }

        public void setPromotionAmount(BigDecimal promotionAmount) {
            this.promotionAmount = promotionAmount;
        }

        public BigDecimal getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(BigDecimal payAmount) {
            this.payAmount = payAmount;
        }
    }
}