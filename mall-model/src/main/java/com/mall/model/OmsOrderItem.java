/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OmsOrderItem implements Serializable {
    private Long id;

    private Long orderId;

    private String orderSn;

    private Long returnId;

    private Long productId;

    private String productPic;

    private String productName;

    private String productBrand;

    private String productSn;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private Long productSkuId;

    private String productSkuCode;

    private Long productCategoryId;

    private String promotionName;

    private BigDecimal promotionAmount;

    private BigDecimal couponAmount;

    private BigDecimal integrationAmount;

    private BigDecimal realAmount;

    private Integer giftIntegration;

    private Integer giftGrowth;

    private String productAttr;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public OmsOrderItem withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OmsOrderItem withOrderId(Long orderId) {
        this.setOrderId(orderId);
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public OmsOrderItem withOrderSn(String orderSn) {
        this.setOrderSn(orderSn);
        return this;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public Long getReturnId() {
        return returnId;
    }

    public OmsOrderItem withReturnId(Long returnId) {
        this.setReturnId(returnId);
        return this;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public Long getProductId() {
        return productId;
    }

    public OmsOrderItem withProductId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductPic() {
        return productPic;
    }

    public OmsOrderItem withProductPic(String productPic) {
        this.setProductPic(productPic);
        return this;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic == null ? null : productPic.trim();
    }

    public String getProductName() {
        return productName;
    }

    public OmsOrderItem withProductName(String productName) {
        this.setProductName(productName);
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductBrand() {
        return productBrand;
    }

    public OmsOrderItem withProductBrand(String productBrand) {
        this.setProductBrand(productBrand);
        return this;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand == null ? null : productBrand.trim();
    }

    public String getProductSn() {
        return productSn;
    }

    public OmsOrderItem withProductSn(String productSn) {
        this.setProductSn(productSn);
        return this;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn == null ? null : productSn.trim();
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public OmsOrderItem withProductPrice(BigDecimal productPrice) {
        this.setProductPrice(productPrice);
        return this;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public OmsOrderItem withProductQuantity(Integer productQuantity) {
        this.setProductQuantity(productQuantity);
        return this;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Long getProductSkuId() {
        return productSkuId;
    }

    public OmsOrderItem withProductSkuId(Long productSkuId) {
        this.setProductSkuId(productSkuId);
        return this;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public String getProductSkuCode() {
        return productSkuCode;
    }

    public OmsOrderItem withProductSkuCode(String productSkuCode) {
        this.setProductSkuCode(productSkuCode);
        return this;
    }

    public void setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode == null ? null : productSkuCode.trim();
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public OmsOrderItem withProductCategoryId(Long productCategoryId) {
        this.setProductCategoryId(productCategoryId);
        return this;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public OmsOrderItem withPromotionName(String promotionName) {
        this.setPromotionName(promotionName);
        return this;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName == null ? null : promotionName.trim();
    }

    public BigDecimal getPromotionAmount() {
        return promotionAmount;
    }

    public OmsOrderItem withPromotionAmount(BigDecimal promotionAmount) {
        this.setPromotionAmount(promotionAmount);
        return this;
    }

    public void setPromotionAmount(BigDecimal promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public OmsOrderItem withCouponAmount(BigDecimal couponAmount) {
        this.setCouponAmount(couponAmount);
        return this;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getIntegrationAmount() {
        return integrationAmount;
    }

    public OmsOrderItem withIntegrationAmount(BigDecimal integrationAmount) {
        this.setIntegrationAmount(integrationAmount);
        return this;
    }

    public void setIntegrationAmount(BigDecimal integrationAmount) {
        this.integrationAmount = integrationAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public OmsOrderItem withRealAmount(BigDecimal realAmount) {
        this.setRealAmount(realAmount);
        return this;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public Integer getGiftIntegration() {
        return giftIntegration;
    }

    public OmsOrderItem withGiftIntegration(Integer giftIntegration) {
        this.setGiftIntegration(giftIntegration);
        return this;
    }

    public void setGiftIntegration(Integer giftIntegration) {
        this.giftIntegration = giftIntegration;
    }

    public Integer getGiftGrowth() {
        return giftGrowth;
    }

    public OmsOrderItem withGiftGrowth(Integer giftGrowth) {
        this.setGiftGrowth(giftGrowth);
        return this;
    }

    public void setGiftGrowth(Integer giftGrowth) {
        this.giftGrowth = giftGrowth;
    }

    public String getProductAttr() {
        return productAttr;
    }

    public OmsOrderItem withProductAttr(String productAttr) {
        this.setProductAttr(productAttr);
        return this;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr == null ? null : productAttr.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", returnId=").append(returnId);
        sb.append(", productId=").append(productId);
        sb.append(", productPic=").append(productPic);
        sb.append(", productName=").append(productName);
        sb.append(", productBrand=").append(productBrand);
        sb.append(", productSn=").append(productSn);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productQuantity=").append(productQuantity);
        sb.append(", productSkuId=").append(productSkuId);
        sb.append(", productSkuCode=").append(productSkuCode);
        sb.append(", productCategoryId=").append(productCategoryId);
        sb.append(", promotionName=").append(promotionName);
        sb.append(", promotionAmount=").append(promotionAmount);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", integrationAmount=").append(integrationAmount);
        sb.append(", realAmount=").append(realAmount);
        sb.append(", giftIntegration=").append(giftIntegration);
        sb.append(", giftGrowth=").append(giftGrowth);
        sb.append(", productAttr=").append(productAttr);
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
        OmsOrderItem other = (OmsOrderItem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderSn() == null ? other.getOrderSn() == null : this.getOrderSn().equals(other.getOrderSn()))
            && (this.getReturnId() == null ? other.getReturnId() == null : this.getReturnId().equals(other.getReturnId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductPic() == null ? other.getProductPic() == null : this.getProductPic().equals(other.getProductPic()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductBrand() == null ? other.getProductBrand() == null : this.getProductBrand().equals(other.getProductBrand()))
            && (this.getProductSn() == null ? other.getProductSn() == null : this.getProductSn().equals(other.getProductSn()))
            && (this.getProductPrice() == null ? other.getProductPrice() == null : this.getProductPrice().equals(other.getProductPrice()))
            && (this.getProductQuantity() == null ? other.getProductQuantity() == null : this.getProductQuantity().equals(other.getProductQuantity()))
            && (this.getProductSkuId() == null ? other.getProductSkuId() == null : this.getProductSkuId().equals(other.getProductSkuId()))
            && (this.getProductSkuCode() == null ? other.getProductSkuCode() == null : this.getProductSkuCode().equals(other.getProductSkuCode()))
            && (this.getProductCategoryId() == null ? other.getProductCategoryId() == null : this.getProductCategoryId().equals(other.getProductCategoryId()))
            && (this.getPromotionName() == null ? other.getPromotionName() == null : this.getPromotionName().equals(other.getPromotionName()))
            && (this.getPromotionAmount() == null ? other.getPromotionAmount() == null : this.getPromotionAmount().equals(other.getPromotionAmount()))
            && (this.getCouponAmount() == null ? other.getCouponAmount() == null : this.getCouponAmount().equals(other.getCouponAmount()))
            && (this.getIntegrationAmount() == null ? other.getIntegrationAmount() == null : this.getIntegrationAmount().equals(other.getIntegrationAmount()))
            && (this.getRealAmount() == null ? other.getRealAmount() == null : this.getRealAmount().equals(other.getRealAmount()))
            && (this.getGiftIntegration() == null ? other.getGiftIntegration() == null : this.getGiftIntegration().equals(other.getGiftIntegration()))
            && (this.getGiftGrowth() == null ? other.getGiftGrowth() == null : this.getGiftGrowth().equals(other.getGiftGrowth()))
            && (this.getProductAttr() == null ? other.getProductAttr() == null : this.getProductAttr().equals(other.getProductAttr()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
        result = prime * result + ((getReturnId() == null) ? 0 : getReturnId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductPic() == null) ? 0 : getProductPic().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductBrand() == null) ? 0 : getProductBrand().hashCode());
        result = prime * result + ((getProductSn() == null) ? 0 : getProductSn().hashCode());
        result = prime * result + ((getProductPrice() == null) ? 0 : getProductPrice().hashCode());
        result = prime * result + ((getProductQuantity() == null) ? 0 : getProductQuantity().hashCode());
        result = prime * result + ((getProductSkuId() == null) ? 0 : getProductSkuId().hashCode());
        result = prime * result + ((getProductSkuCode() == null) ? 0 : getProductSkuCode().hashCode());
        result = prime * result + ((getProductCategoryId() == null) ? 0 : getProductCategoryId().hashCode());
        result = prime * result + ((getPromotionName() == null) ? 0 : getPromotionName().hashCode());
        result = prime * result + ((getPromotionAmount() == null) ? 0 : getPromotionAmount().hashCode());
        result = prime * result + ((getCouponAmount() == null) ? 0 : getCouponAmount().hashCode());
        result = prime * result + ((getIntegrationAmount() == null) ? 0 : getIntegrationAmount().hashCode());
        result = prime * result + ((getRealAmount() == null) ? 0 : getRealAmount().hashCode());
        result = prime * result + ((getGiftIntegration() == null) ? 0 : getGiftIntegration().hashCode());
        result = prime * result + ((getGiftGrowth() == null) ? 0 : getGiftGrowth().hashCode());
        result = prime * result + ((getProductAttr() == null) ? 0 : getProductAttr().hashCode());
        return result;
    }
}