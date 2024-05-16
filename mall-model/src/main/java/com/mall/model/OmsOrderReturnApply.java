/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OmsOrderReturnApply implements Serializable {
    private Long id;

    private Long orderId;

    private Long companyAddressId;

    private Long productId;

    private String orderSn;

    private Long memberId;

    private String memberUsername;

    private BigDecimal returnAmount;

    private String returnName;

    private String returnPhone;

    private String deliveryCode;

    private String deliverySn;

    private Integer status;

    private String productPic;

    private String productName;

    private String productBrand;

    private String productAttr;

    private Integer productCount;

    private BigDecimal productPrice;

    private BigDecimal productRealPrice;

    private String reason;

    private String description;

    private String proofPics;

    private String handleNote;

    private String handleMan;

    private LocalDateTime handleTime;

    private String receiveMan;

    private LocalDateTime receiveTime;

    private String receiveNote;

    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public OmsOrderReturnApply withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OmsOrderReturnApply withOrderId(Long orderId) {
        this.setOrderId(orderId);
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCompanyAddressId() {
        return companyAddressId;
    }

    public OmsOrderReturnApply withCompanyAddressId(Long companyAddressId) {
        this.setCompanyAddressId(companyAddressId);
        return this;
    }

    public void setCompanyAddressId(Long companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

    public Long getProductId() {
        return productId;
    }

    public OmsOrderReturnApply withProductId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public OmsOrderReturnApply withOrderSn(String orderSn) {
        this.setOrderSn(orderSn);
        return this;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public Long getMemberId() {
        return memberId;
    }

    public OmsOrderReturnApply withMemberId(Long memberId) {
        this.setMemberId(memberId);
        return this;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public OmsOrderReturnApply withMemberUsername(String memberUsername) {
        this.setMemberUsername(memberUsername);
        return this;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername == null ? null : memberUsername.trim();
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public OmsOrderReturnApply withReturnAmount(BigDecimal returnAmount) {
        this.setReturnAmount(returnAmount);
        return this;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getReturnName() {
        return returnName;
    }

    public OmsOrderReturnApply withReturnName(String returnName) {
        this.setReturnName(returnName);
        return this;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName == null ? null : returnName.trim();
    }

    public String getReturnPhone() {
        return returnPhone;
    }

    public OmsOrderReturnApply withReturnPhone(String returnPhone) {
        this.setReturnPhone(returnPhone);
        return this;
    }

    public void setReturnPhone(String returnPhone) {
        this.returnPhone = returnPhone == null ? null : returnPhone.trim();
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public OmsOrderReturnApply withDeliveryCode(String deliveryCode) {
        this.setDeliveryCode(deliveryCode);
        return this;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode == null ? null : deliveryCode.trim();
    }

    public String getDeliverySn() {
        return deliverySn;
    }

    public OmsOrderReturnApply withDeliverySn(String deliverySn) {
        this.setDeliverySn(deliverySn);
        return this;
    }

    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn == null ? null : deliverySn.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public OmsOrderReturnApply withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProductPic() {
        return productPic;
    }

    public OmsOrderReturnApply withProductPic(String productPic) {
        this.setProductPic(productPic);
        return this;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic == null ? null : productPic.trim();
    }

    public String getProductName() {
        return productName;
    }

    public OmsOrderReturnApply withProductName(String productName) {
        this.setProductName(productName);
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductBrand() {
        return productBrand;
    }

    public OmsOrderReturnApply withProductBrand(String productBrand) {
        this.setProductBrand(productBrand);
        return this;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand == null ? null : productBrand.trim();
    }

    public String getProductAttr() {
        return productAttr;
    }

    public OmsOrderReturnApply withProductAttr(String productAttr) {
        this.setProductAttr(productAttr);
        return this;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr == null ? null : productAttr.trim();
    }

    public Integer getProductCount() {
        return productCount;
    }

    public OmsOrderReturnApply withProductCount(Integer productCount) {
        this.setProductCount(productCount);
        return this;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public OmsOrderReturnApply withProductPrice(BigDecimal productPrice) {
        this.setProductPrice(productPrice);
        return this;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductRealPrice() {
        return productRealPrice;
    }

    public OmsOrderReturnApply withProductRealPrice(BigDecimal productRealPrice) {
        this.setProductRealPrice(productRealPrice);
        return this;
    }

    public void setProductRealPrice(BigDecimal productRealPrice) {
        this.productRealPrice = productRealPrice;
    }

    public String getReason() {
        return reason;
    }

    public OmsOrderReturnApply withReason(String reason) {
        this.setReason(reason);
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getDescription() {
        return description;
    }

    public OmsOrderReturnApply withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getProofPics() {
        return proofPics;
    }

    public OmsOrderReturnApply withProofPics(String proofPics) {
        this.setProofPics(proofPics);
        return this;
    }

    public void setProofPics(String proofPics) {
        this.proofPics = proofPics == null ? null : proofPics.trim();
    }

    public String getHandleNote() {
        return handleNote;
    }

    public OmsOrderReturnApply withHandleNote(String handleNote) {
        this.setHandleNote(handleNote);
        return this;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote == null ? null : handleNote.trim();
    }

    public String getHandleMan() {
        return handleMan;
    }

    public OmsOrderReturnApply withHandleMan(String handleMan) {
        this.setHandleMan(handleMan);
        return this;
    }

    public void setHandleMan(String handleMan) {
        this.handleMan = handleMan == null ? null : handleMan.trim();
    }

    public LocalDateTime getHandleTime() {
        return handleTime;
    }

    public OmsOrderReturnApply withHandleTime(LocalDateTime handleTime) {
        this.setHandleTime(handleTime);
        return this;
    }

    public void setHandleTime(LocalDateTime handleTime) {
        this.handleTime = handleTime;
    }

    public String getReceiveMan() {
        return receiveMan;
    }

    public OmsOrderReturnApply withReceiveMan(String receiveMan) {
        this.setReceiveMan(receiveMan);
        return this;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan == null ? null : receiveMan.trim();
    }

    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    public OmsOrderReturnApply withReceiveTime(LocalDateTime receiveTime) {
        this.setReceiveTime(receiveTime);
        return this;
    }

    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceiveNote() {
        return receiveNote;
    }

    public OmsOrderReturnApply withReceiveNote(String receiveNote) {
        this.setReceiveNote(receiveNote);
        return this;
    }

    public void setReceiveNote(String receiveNote) {
        this.receiveNote = receiveNote == null ? null : receiveNote.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public OmsOrderReturnApply withCreateTime(LocalDateTime createTime) {
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
        sb.append(", orderId=").append(orderId);
        sb.append(", companyAddressId=").append(companyAddressId);
        sb.append(", productId=").append(productId);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", memberId=").append(memberId);
        sb.append(", memberUsername=").append(memberUsername);
        sb.append(", returnAmount=").append(returnAmount);
        sb.append(", returnName=").append(returnName);
        sb.append(", returnPhone=").append(returnPhone);
        sb.append(", deliveryCode=").append(deliveryCode);
        sb.append(", deliverySn=").append(deliverySn);
        sb.append(", status=").append(status);
        sb.append(", productPic=").append(productPic);
        sb.append(", productName=").append(productName);
        sb.append(", productBrand=").append(productBrand);
        sb.append(", productAttr=").append(productAttr);
        sb.append(", productCount=").append(productCount);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productRealPrice=").append(productRealPrice);
        sb.append(", reason=").append(reason);
        sb.append(", description=").append(description);
        sb.append(", proofPics=").append(proofPics);
        sb.append(", handleNote=").append(handleNote);
        sb.append(", handleMan=").append(handleMan);
        sb.append(", handleTime=").append(handleTime);
        sb.append(", receiveMan=").append(receiveMan);
        sb.append(", receiveTime=").append(receiveTime);
        sb.append(", receiveNote=").append(receiveNote);
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
        OmsOrderReturnApply other = (OmsOrderReturnApply) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getCompanyAddressId() == null ? other.getCompanyAddressId() == null : this.getCompanyAddressId().equals(other.getCompanyAddressId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getOrderSn() == null ? other.getOrderSn() == null : this.getOrderSn().equals(other.getOrderSn()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getMemberUsername() == null ? other.getMemberUsername() == null : this.getMemberUsername().equals(other.getMemberUsername()))
            && (this.getReturnAmount() == null ? other.getReturnAmount() == null : this.getReturnAmount().equals(other.getReturnAmount()))
            && (this.getReturnName() == null ? other.getReturnName() == null : this.getReturnName().equals(other.getReturnName()))
            && (this.getReturnPhone() == null ? other.getReturnPhone() == null : this.getReturnPhone().equals(other.getReturnPhone()))
            && (this.getDeliveryCode() == null ? other.getDeliveryCode() == null : this.getDeliveryCode().equals(other.getDeliveryCode()))
            && (this.getDeliverySn() == null ? other.getDeliverySn() == null : this.getDeliverySn().equals(other.getDeliverySn()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getProductPic() == null ? other.getProductPic() == null : this.getProductPic().equals(other.getProductPic()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductBrand() == null ? other.getProductBrand() == null : this.getProductBrand().equals(other.getProductBrand()))
            && (this.getProductAttr() == null ? other.getProductAttr() == null : this.getProductAttr().equals(other.getProductAttr()))
            && (this.getProductCount() == null ? other.getProductCount() == null : this.getProductCount().equals(other.getProductCount()))
            && (this.getProductPrice() == null ? other.getProductPrice() == null : this.getProductPrice().equals(other.getProductPrice()))
            && (this.getProductRealPrice() == null ? other.getProductRealPrice() == null : this.getProductRealPrice().equals(other.getProductRealPrice()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getProofPics() == null ? other.getProofPics() == null : this.getProofPics().equals(other.getProofPics()))
            && (this.getHandleNote() == null ? other.getHandleNote() == null : this.getHandleNote().equals(other.getHandleNote()))
            && (this.getHandleMan() == null ? other.getHandleMan() == null : this.getHandleMan().equals(other.getHandleMan()))
            && (this.getHandleTime() == null ? other.getHandleTime() == null : this.getHandleTime().equals(other.getHandleTime()))
            && (this.getReceiveMan() == null ? other.getReceiveMan() == null : this.getReceiveMan().equals(other.getReceiveMan()))
            && (this.getReceiveTime() == null ? other.getReceiveTime() == null : this.getReceiveTime().equals(other.getReceiveTime()))
            && (this.getReceiveNote() == null ? other.getReceiveNote() == null : this.getReceiveNote().equals(other.getReceiveNote()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getCompanyAddressId() == null) ? 0 : getCompanyAddressId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getMemberUsername() == null) ? 0 : getMemberUsername().hashCode());
        result = prime * result + ((getReturnAmount() == null) ? 0 : getReturnAmount().hashCode());
        result = prime * result + ((getReturnName() == null) ? 0 : getReturnName().hashCode());
        result = prime * result + ((getReturnPhone() == null) ? 0 : getReturnPhone().hashCode());
        result = prime * result + ((getDeliveryCode() == null) ? 0 : getDeliveryCode().hashCode());
        result = prime * result + ((getDeliverySn() == null) ? 0 : getDeliverySn().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getProductPic() == null) ? 0 : getProductPic().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductBrand() == null) ? 0 : getProductBrand().hashCode());
        result = prime * result + ((getProductAttr() == null) ? 0 : getProductAttr().hashCode());
        result = prime * result + ((getProductCount() == null) ? 0 : getProductCount().hashCode());
        result = prime * result + ((getProductPrice() == null) ? 0 : getProductPrice().hashCode());
        result = prime * result + ((getProductRealPrice() == null) ? 0 : getProductRealPrice().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getProofPics() == null) ? 0 : getProofPics().hashCode());
        result = prime * result + ((getHandleNote() == null) ? 0 : getHandleNote().hashCode());
        result = prime * result + ((getHandleMan() == null) ? 0 : getHandleMan().hashCode());
        result = prime * result + ((getHandleTime() == null) ? 0 : getHandleTime().hashCode());
        result = prime * result + ((getReceiveMan() == null) ? 0 : getReceiveMan().hashCode());
        result = prime * result + ((getReceiveTime() == null) ? 0 : getReceiveTime().hashCode());
        result = prime * result + ((getReceiveNote() == null) ? 0 : getReceiveNote().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}