/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OmsOrder implements Serializable {
    private Long id;

    private Long memberId;

    private Long couponId;

    private String orderSn;

    private String transactionId;

    private String memberUsername;

    private BigDecimal totalAmount;

    private BigDecimal payAmount;

    private BigDecimal freightAmount;

    private BigDecimal promotionAmount;

    private BigDecimal integrationAmount;

    private BigDecimal couponAmount;

    private BigDecimal discountAmount;

    private Integer payType;

    private Integer sourceType;

    private Integer status;

    private Integer orderType;

    private String deliveryCode;

    private String deliveryCompany;

    private String deliverySn;

    private Integer autoConfirmDay;

    private Integer integration;

    private Integer growth;

    private String promotionInfo;

    private Integer billType;

    private String billHeader;

    private String billContent;

    private String billReceiverPhone;

    private String billReceiverEmail;

    private String receiverName;

    private String receiverPhone;

    private String receiverPostCode;

    private String receiverProvince;

    private String receiverCity;

    private String receiverRegion;

    private String receiverDetailAddress;

    private String note;

    private Integer confirmStatus;

    private Integer deleteStatus;

    private Integer useIntegration;

    private LocalDateTime paymentTime;

    private LocalDateTime deliveryTime;

    private LocalDateTime receiveTime;

    private LocalDateTime commentTime;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public OmsOrder withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public OmsOrder withMemberId(Long memberId) {
        this.setMemberId(memberId);
        return this;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public OmsOrder withCouponId(Long couponId) {
        this.setCouponId(couponId);
        return this;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public OmsOrder withOrderSn(String orderSn) {
        this.setOrderSn(orderSn);
        return this;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public OmsOrder withTransactionId(String transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public OmsOrder withMemberUsername(String memberUsername) {
        this.setMemberUsername(memberUsername);
        return this;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername == null ? null : memberUsername.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public OmsOrder withTotalAmount(BigDecimal totalAmount) {
        this.setTotalAmount(totalAmount);
        return this;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public OmsOrder withPayAmount(BigDecimal payAmount) {
        this.setPayAmount(payAmount);
        return this;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public OmsOrder withFreightAmount(BigDecimal freightAmount) {
        this.setFreightAmount(freightAmount);
        return this;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public BigDecimal getPromotionAmount() {
        return promotionAmount;
    }

    public OmsOrder withPromotionAmount(BigDecimal promotionAmount) {
        this.setPromotionAmount(promotionAmount);
        return this;
    }

    public void setPromotionAmount(BigDecimal promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public BigDecimal getIntegrationAmount() {
        return integrationAmount;
    }

    public OmsOrder withIntegrationAmount(BigDecimal integrationAmount) {
        this.setIntegrationAmount(integrationAmount);
        return this;
    }

    public void setIntegrationAmount(BigDecimal integrationAmount) {
        this.integrationAmount = integrationAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public OmsOrder withCouponAmount(BigDecimal couponAmount) {
        this.setCouponAmount(couponAmount);
        return this;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public OmsOrder withDiscountAmount(BigDecimal discountAmount) {
        this.setDiscountAmount(discountAmount);
        return this;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public OmsOrder withPayType(Integer payType) {
        this.setPayType(payType);
        return this;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public OmsOrder withSourceType(Integer sourceType) {
        this.setSourceType(sourceType);
        return this;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getStatus() {
        return status;
    }

    public OmsOrder withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public OmsOrder withOrderType(Integer orderType) {
        this.setOrderType(orderType);
        return this;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public OmsOrder withDeliveryCode(String deliveryCode) {
        this.setDeliveryCode(deliveryCode);
        return this;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode == null ? null : deliveryCode.trim();
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public OmsOrder withDeliveryCompany(String deliveryCompany) {
        this.setDeliveryCompany(deliveryCompany);
        return this;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany == null ? null : deliveryCompany.trim();
    }

    public String getDeliverySn() {
        return deliverySn;
    }

    public OmsOrder withDeliverySn(String deliverySn) {
        this.setDeliverySn(deliverySn);
        return this;
    }

    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn == null ? null : deliverySn.trim();
    }

    public Integer getAutoConfirmDay() {
        return autoConfirmDay;
    }

    public OmsOrder withAutoConfirmDay(Integer autoConfirmDay) {
        this.setAutoConfirmDay(autoConfirmDay);
        return this;
    }

    public void setAutoConfirmDay(Integer autoConfirmDay) {
        this.autoConfirmDay = autoConfirmDay;
    }

    public Integer getIntegration() {
        return integration;
    }

    public OmsOrder withIntegration(Integer integration) {
        this.setIntegration(integration);
        return this;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public Integer getGrowth() {
        return growth;
    }

    public OmsOrder withGrowth(Integer growth) {
        this.setGrowth(growth);
        return this;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public String getPromotionInfo() {
        return promotionInfo;
    }

    public OmsOrder withPromotionInfo(String promotionInfo) {
        this.setPromotionInfo(promotionInfo);
        return this;
    }

    public void setPromotionInfo(String promotionInfo) {
        this.promotionInfo = promotionInfo == null ? null : promotionInfo.trim();
    }

    public Integer getBillType() {
        return billType;
    }

    public OmsOrder withBillType(Integer billType) {
        this.setBillType(billType);
        return this;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public String getBillHeader() {
        return billHeader;
    }

    public OmsOrder withBillHeader(String billHeader) {
        this.setBillHeader(billHeader);
        return this;
    }

    public void setBillHeader(String billHeader) {
        this.billHeader = billHeader == null ? null : billHeader.trim();
    }

    public String getBillContent() {
        return billContent;
    }

    public OmsOrder withBillContent(String billContent) {
        this.setBillContent(billContent);
        return this;
    }

    public void setBillContent(String billContent) {
        this.billContent = billContent == null ? null : billContent.trim();
    }

    public String getBillReceiverPhone() {
        return billReceiverPhone;
    }

    public OmsOrder withBillReceiverPhone(String billReceiverPhone) {
        this.setBillReceiverPhone(billReceiverPhone);
        return this;
    }

    public void setBillReceiverPhone(String billReceiverPhone) {
        this.billReceiverPhone = billReceiverPhone == null ? null : billReceiverPhone.trim();
    }

    public String getBillReceiverEmail() {
        return billReceiverEmail;
    }

    public OmsOrder withBillReceiverEmail(String billReceiverEmail) {
        this.setBillReceiverEmail(billReceiverEmail);
        return this;
    }

    public void setBillReceiverEmail(String billReceiverEmail) {
        this.billReceiverEmail = billReceiverEmail == null ? null : billReceiverEmail.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public OmsOrder withReceiverName(String receiverName) {
        this.setReceiverName(receiverName);
        return this;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public OmsOrder withReceiverPhone(String receiverPhone) {
        this.setReceiverPhone(receiverPhone);
        return this;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    public OmsOrder withReceiverPostCode(String receiverPostCode) {
        this.setReceiverPostCode(receiverPostCode);
        return this;
    }

    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode == null ? null : receiverPostCode.trim();
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public OmsOrder withReceiverProvince(String receiverProvince) {
        this.setReceiverProvince(receiverProvince);
        return this;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince == null ? null : receiverProvince.trim();
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public OmsOrder withReceiverCity(String receiverCity) {
        this.setReceiverCity(receiverCity);
        return this;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity == null ? null : receiverCity.trim();
    }

    public String getReceiverRegion() {
        return receiverRegion;
    }

    public OmsOrder withReceiverRegion(String receiverRegion) {
        this.setReceiverRegion(receiverRegion);
        return this;
    }

    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion == null ? null : receiverRegion.trim();
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public OmsOrder withReceiverDetailAddress(String receiverDetailAddress) {
        this.setReceiverDetailAddress(receiverDetailAddress);
        return this;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress == null ? null : receiverDetailAddress.trim();
    }

    public String getNote() {
        return note;
    }

    public OmsOrder withNote(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public OmsOrder withConfirmStatus(Integer confirmStatus) {
        this.setConfirmStatus(confirmStatus);
        return this;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public OmsOrder withDeleteStatus(Integer deleteStatus) {
        this.setDeleteStatus(deleteStatus);
        return this;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getUseIntegration() {
        return useIntegration;
    }

    public OmsOrder withUseIntegration(Integer useIntegration) {
        this.setUseIntegration(useIntegration);
        return this;
    }

    public void setUseIntegration(Integer useIntegration) {
        this.useIntegration = useIntegration;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public OmsOrder withPaymentTime(LocalDateTime paymentTime) {
        this.setPaymentTime(paymentTime);
        return this;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public OmsOrder withDeliveryTime(LocalDateTime deliveryTime) {
        this.setDeliveryTime(deliveryTime);
        return this;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    public OmsOrder withReceiveTime(LocalDateTime receiveTime) {
        this.setReceiveTime(receiveTime);
        return this;
    }

    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public OmsOrder withCommentTime(LocalDateTime commentTime) {
        this.setCommentTime(commentTime);
        return this;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public OmsOrder withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public OmsOrder withModifyTime(LocalDateTime modifyTime) {
        this.setModifyTime(modifyTime);
        return this;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", couponId=").append(couponId);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", transactionId=").append(transactionId);
        sb.append(", memberUsername=").append(memberUsername);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", freightAmount=").append(freightAmount);
        sb.append(", promotionAmount=").append(promotionAmount);
        sb.append(", integrationAmount=").append(integrationAmount);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", discountAmount=").append(discountAmount);
        sb.append(", payType=").append(payType);
        sb.append(", sourceType=").append(sourceType);
        sb.append(", status=").append(status);
        sb.append(", orderType=").append(orderType);
        sb.append(", deliveryCode=").append(deliveryCode);
        sb.append(", deliveryCompany=").append(deliveryCompany);
        sb.append(", deliverySn=").append(deliverySn);
        sb.append(", autoConfirmDay=").append(autoConfirmDay);
        sb.append(", integration=").append(integration);
        sb.append(", growth=").append(growth);
        sb.append(", promotionInfo=").append(promotionInfo);
        sb.append(", billType=").append(billType);
        sb.append(", billHeader=").append(billHeader);
        sb.append(", billContent=").append(billContent);
        sb.append(", billReceiverPhone=").append(billReceiverPhone);
        sb.append(", billReceiverEmail=").append(billReceiverEmail);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", receiverPhone=").append(receiverPhone);
        sb.append(", receiverPostCode=").append(receiverPostCode);
        sb.append(", receiverProvince=").append(receiverProvince);
        sb.append(", receiverCity=").append(receiverCity);
        sb.append(", receiverRegion=").append(receiverRegion);
        sb.append(", receiverDetailAddress=").append(receiverDetailAddress);
        sb.append(", note=").append(note);
        sb.append(", confirmStatus=").append(confirmStatus);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", useIntegration=").append(useIntegration);
        sb.append(", paymentTime=").append(paymentTime);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", receiveTime=").append(receiveTime);
        sb.append(", commentTime=").append(commentTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
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
        OmsOrder other = (OmsOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getCouponId() == null ? other.getCouponId() == null : this.getCouponId().equals(other.getCouponId()))
            && (this.getOrderSn() == null ? other.getOrderSn() == null : this.getOrderSn().equals(other.getOrderSn()))
            && (this.getTransactionId() == null ? other.getTransactionId() == null : this.getTransactionId().equals(other.getTransactionId()))
            && (this.getMemberUsername() == null ? other.getMemberUsername() == null : this.getMemberUsername().equals(other.getMemberUsername()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getPayAmount() == null ? other.getPayAmount() == null : this.getPayAmount().equals(other.getPayAmount()))
            && (this.getFreightAmount() == null ? other.getFreightAmount() == null : this.getFreightAmount().equals(other.getFreightAmount()))
            && (this.getPromotionAmount() == null ? other.getPromotionAmount() == null : this.getPromotionAmount().equals(other.getPromotionAmount()))
            && (this.getIntegrationAmount() == null ? other.getIntegrationAmount() == null : this.getIntegrationAmount().equals(other.getIntegrationAmount()))
            && (this.getCouponAmount() == null ? other.getCouponAmount() == null : this.getCouponAmount().equals(other.getCouponAmount()))
            && (this.getDiscountAmount() == null ? other.getDiscountAmount() == null : this.getDiscountAmount().equals(other.getDiscountAmount()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getSourceType() == null ? other.getSourceType() == null : this.getSourceType().equals(other.getSourceType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getDeliveryCode() == null ? other.getDeliveryCode() == null : this.getDeliveryCode().equals(other.getDeliveryCode()))
            && (this.getDeliveryCompany() == null ? other.getDeliveryCompany() == null : this.getDeliveryCompany().equals(other.getDeliveryCompany()))
            && (this.getDeliverySn() == null ? other.getDeliverySn() == null : this.getDeliverySn().equals(other.getDeliverySn()))
            && (this.getAutoConfirmDay() == null ? other.getAutoConfirmDay() == null : this.getAutoConfirmDay().equals(other.getAutoConfirmDay()))
            && (this.getIntegration() == null ? other.getIntegration() == null : this.getIntegration().equals(other.getIntegration()))
            && (this.getGrowth() == null ? other.getGrowth() == null : this.getGrowth().equals(other.getGrowth()))
            && (this.getPromotionInfo() == null ? other.getPromotionInfo() == null : this.getPromotionInfo().equals(other.getPromotionInfo()))
            && (this.getBillType() == null ? other.getBillType() == null : this.getBillType().equals(other.getBillType()))
            && (this.getBillHeader() == null ? other.getBillHeader() == null : this.getBillHeader().equals(other.getBillHeader()))
            && (this.getBillContent() == null ? other.getBillContent() == null : this.getBillContent().equals(other.getBillContent()))
            && (this.getBillReceiverPhone() == null ? other.getBillReceiverPhone() == null : this.getBillReceiverPhone().equals(other.getBillReceiverPhone()))
            && (this.getBillReceiverEmail() == null ? other.getBillReceiverEmail() == null : this.getBillReceiverEmail().equals(other.getBillReceiverEmail()))
            && (this.getReceiverName() == null ? other.getReceiverName() == null : this.getReceiverName().equals(other.getReceiverName()))
            && (this.getReceiverPhone() == null ? other.getReceiverPhone() == null : this.getReceiverPhone().equals(other.getReceiverPhone()))
            && (this.getReceiverPostCode() == null ? other.getReceiverPostCode() == null : this.getReceiverPostCode().equals(other.getReceiverPostCode()))
            && (this.getReceiverProvince() == null ? other.getReceiverProvince() == null : this.getReceiverProvince().equals(other.getReceiverProvince()))
            && (this.getReceiverCity() == null ? other.getReceiverCity() == null : this.getReceiverCity().equals(other.getReceiverCity()))
            && (this.getReceiverRegion() == null ? other.getReceiverRegion() == null : this.getReceiverRegion().equals(other.getReceiverRegion()))
            && (this.getReceiverDetailAddress() == null ? other.getReceiverDetailAddress() == null : this.getReceiverDetailAddress().equals(other.getReceiverDetailAddress()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getConfirmStatus() == null ? other.getConfirmStatus() == null : this.getConfirmStatus().equals(other.getConfirmStatus()))
            && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null : this.getDeleteStatus().equals(other.getDeleteStatus()))
            && (this.getUseIntegration() == null ? other.getUseIntegration() == null : this.getUseIntegration().equals(other.getUseIntegration()))
            && (this.getPaymentTime() == null ? other.getPaymentTime() == null : this.getPaymentTime().equals(other.getPaymentTime()))
            && (this.getDeliveryTime() == null ? other.getDeliveryTime() == null : this.getDeliveryTime().equals(other.getDeliveryTime()))
            && (this.getReceiveTime() == null ? other.getReceiveTime() == null : this.getReceiveTime().equals(other.getReceiveTime()))
            && (this.getCommentTime() == null ? other.getCommentTime() == null : this.getCommentTime().equals(other.getCommentTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getCouponId() == null) ? 0 : getCouponId().hashCode());
        result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
        result = prime * result + ((getTransactionId() == null) ? 0 : getTransactionId().hashCode());
        result = prime * result + ((getMemberUsername() == null) ? 0 : getMemberUsername().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getPayAmount() == null) ? 0 : getPayAmount().hashCode());
        result = prime * result + ((getFreightAmount() == null) ? 0 : getFreightAmount().hashCode());
        result = prime * result + ((getPromotionAmount() == null) ? 0 : getPromotionAmount().hashCode());
        result = prime * result + ((getIntegrationAmount() == null) ? 0 : getIntegrationAmount().hashCode());
        result = prime * result + ((getCouponAmount() == null) ? 0 : getCouponAmount().hashCode());
        result = prime * result + ((getDiscountAmount() == null) ? 0 : getDiscountAmount().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getSourceType() == null) ? 0 : getSourceType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getDeliveryCode() == null) ? 0 : getDeliveryCode().hashCode());
        result = prime * result + ((getDeliveryCompany() == null) ? 0 : getDeliveryCompany().hashCode());
        result = prime * result + ((getDeliverySn() == null) ? 0 : getDeliverySn().hashCode());
        result = prime * result + ((getAutoConfirmDay() == null) ? 0 : getAutoConfirmDay().hashCode());
        result = prime * result + ((getIntegration() == null) ? 0 : getIntegration().hashCode());
        result = prime * result + ((getGrowth() == null) ? 0 : getGrowth().hashCode());
        result = prime * result + ((getPromotionInfo() == null) ? 0 : getPromotionInfo().hashCode());
        result = prime * result + ((getBillType() == null) ? 0 : getBillType().hashCode());
        result = prime * result + ((getBillHeader() == null) ? 0 : getBillHeader().hashCode());
        result = prime * result + ((getBillContent() == null) ? 0 : getBillContent().hashCode());
        result = prime * result + ((getBillReceiverPhone() == null) ? 0 : getBillReceiverPhone().hashCode());
        result = prime * result + ((getBillReceiverEmail() == null) ? 0 : getBillReceiverEmail().hashCode());
        result = prime * result + ((getReceiverName() == null) ? 0 : getReceiverName().hashCode());
        result = prime * result + ((getReceiverPhone() == null) ? 0 : getReceiverPhone().hashCode());
        result = prime * result + ((getReceiverPostCode() == null) ? 0 : getReceiverPostCode().hashCode());
        result = prime * result + ((getReceiverProvince() == null) ? 0 : getReceiverProvince().hashCode());
        result = prime * result + ((getReceiverCity() == null) ? 0 : getReceiverCity().hashCode());
        result = prime * result + ((getReceiverRegion() == null) ? 0 : getReceiverRegion().hashCode());
        result = prime * result + ((getReceiverDetailAddress() == null) ? 0 : getReceiverDetailAddress().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getConfirmStatus() == null) ? 0 : getConfirmStatus().hashCode());
        result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
        result = prime * result + ((getUseIntegration() == null) ? 0 : getUseIntegration().hashCode());
        result = prime * result + ((getPaymentTime() == null) ? 0 : getPaymentTime().hashCode());
        result = prime * result + ((getDeliveryTime() == null) ? 0 : getDeliveryTime().hashCode());
        result = prime * result + ((getReceiveTime() == null) ? 0 : getReceiveTime().hashCode());
        result = prime * result + ((getCommentTime() == null) ? 0 : getCommentTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        return result;
    }
}