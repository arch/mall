/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SmsCouponHistory implements Serializable {
    private Long id;

    private Long couponId;

    private Long memberId;

    private String couponCode;

    private String memberNickname;

    private Integer getType;

    private Integer useStatus;

    private LocalDateTime useTime;

    private Long orderId;

    private String orderSn;

    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public SmsCouponHistory withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCouponId() {
        return couponId;
    }

    public SmsCouponHistory withCouponId(Long couponId) {
        this.setCouponId(couponId);
        return this;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public SmsCouponHistory withMemberId(Long memberId) {
        this.setMemberId(memberId);
        return this;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public SmsCouponHistory withCouponCode(String couponCode) {
        this.setCouponCode(couponCode);
        return this;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public SmsCouponHistory withMemberNickname(String memberNickname) {
        this.setMemberNickname(memberNickname);
        return this;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname == null ? null : memberNickname.trim();
    }

    public Integer getGetType() {
        return getType;
    }

    public SmsCouponHistory withGetType(Integer getType) {
        this.setGetType(getType);
        return this;
    }

    public void setGetType(Integer getType) {
        this.getType = getType;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public SmsCouponHistory withUseStatus(Integer useStatus) {
        this.setUseStatus(useStatus);
        return this;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public LocalDateTime getUseTime() {
        return useTime;
    }

    public SmsCouponHistory withUseTime(LocalDateTime useTime) {
        this.setUseTime(useTime);
        return this;
    }

    public void setUseTime(LocalDateTime useTime) {
        this.useTime = useTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public SmsCouponHistory withOrderId(Long orderId) {
        this.setOrderId(orderId);
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public SmsCouponHistory withOrderSn(String orderSn) {
        this.setOrderSn(orderSn);
        return this;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SmsCouponHistory withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", couponId=").append(couponId);
        sb.append(", memberId=").append(memberId);
        sb.append(", couponCode=").append(couponCode);
        sb.append(", memberNickname=").append(memberNickname);
        sb.append(", getType=").append(getType);
        sb.append(", useStatus=").append(useStatus);
        sb.append(", useTime=").append(useTime);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SmsCouponHistory other = (SmsCouponHistory) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCouponId() == null ? other.getCouponId() == null : this.getCouponId().equals(other.getCouponId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getCouponCode() == null ? other.getCouponCode() == null : this.getCouponCode().equals(other.getCouponCode()))
            && (this.getMemberNickname() == null ? other.getMemberNickname() == null : this.getMemberNickname().equals(other.getMemberNickname()))
            && (this.getGetType() == null ? other.getGetType() == null : this.getGetType().equals(other.getGetType()))
            && (this.getUseStatus() == null ? other.getUseStatus() == null : this.getUseStatus().equals(other.getUseStatus()))
            && (this.getUseTime() == null ? other.getUseTime() == null : this.getUseTime().equals(other.getUseTime()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderSn() == null ? other.getOrderSn() == null : this.getOrderSn().equals(other.getOrderSn()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCouponId() == null) ? 0 : getCouponId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getCouponCode() == null) ? 0 : getCouponCode().hashCode());
        result = prime * result + ((getMemberNickname() == null) ? 0 : getMemberNickname().hashCode());
        result = prime * result + ((getGetType() == null) ? 0 : getGetType().hashCode());
        result = prime * result + ((getUseStatus() == null) ? 0 : getUseStatus().hashCode());
        result = prime * result + ((getUseTime() == null) ? 0 : getUseTime().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}