/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OmsCartItem implements Serializable {
    private Long id;

    private Long productId;

    private Long productSkuId;

    private Long memberId;

    private String memberNickname;

    private Integer quantity;

    private BigDecimal price;

    private String productPic;

    private String productName;

    private String productSubTitle;

    private String productSkuCode;

    private Integer deleteStatus;

    private Long productCategoryId;

    private String productBrand;

    private String productSn;

    private String productAttr;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public OmsCartItem withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public OmsCartItem withProductId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductSkuId() {
        return productSkuId;
    }

    public OmsCartItem withProductSkuId(Long productSkuId) {
        this.setProductSkuId(productSkuId);
        return this;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public OmsCartItem withMemberId(Long memberId) {
        this.setMemberId(memberId);
        return this;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public OmsCartItem withMemberNickname(String memberNickname) {
        this.setMemberNickname(memberNickname);
        return this;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname == null ? null : memberNickname.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OmsCartItem withQuantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OmsCartItem withPrice(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductPic() {
        return productPic;
    }

    public OmsCartItem withProductPic(String productPic) {
        this.setProductPic(productPic);
        return this;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic == null ? null : productPic.trim();
    }

    public String getProductName() {
        return productName;
    }

    public OmsCartItem withProductName(String productName) {
        this.setProductName(productName);
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductSubTitle() {
        return productSubTitle;
    }

    public OmsCartItem withProductSubTitle(String productSubTitle) {
        this.setProductSubTitle(productSubTitle);
        return this;
    }

    public void setProductSubTitle(String productSubTitle) {
        this.productSubTitle = productSubTitle == null ? null : productSubTitle.trim();
    }

    public String getProductSkuCode() {
        return productSkuCode;
    }

    public OmsCartItem withProductSkuCode(String productSkuCode) {
        this.setProductSkuCode(productSkuCode);
        return this;
    }

    public void setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode == null ? null : productSkuCode.trim();
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public OmsCartItem withDeleteStatus(Integer deleteStatus) {
        this.setDeleteStatus(deleteStatus);
        return this;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public OmsCartItem withProductCategoryId(Long productCategoryId) {
        this.setProductCategoryId(productCategoryId);
        return this;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public OmsCartItem withProductBrand(String productBrand) {
        this.setProductBrand(productBrand);
        return this;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand == null ? null : productBrand.trim();
    }

    public String getProductSn() {
        return productSn;
    }

    public OmsCartItem withProductSn(String productSn) {
        this.setProductSn(productSn);
        return this;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn == null ? null : productSn.trim();
    }

    public String getProductAttr() {
        return productAttr;
    }

    public OmsCartItem withProductAttr(String productAttr) {
        this.setProductAttr(productAttr);
        return this;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr == null ? null : productAttr.trim();
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public OmsCartItem withCreateDate(LocalDateTime createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public OmsCartItem withModifyDate(LocalDateTime modifyDate) {
        this.setModifyDate(modifyDate);
        return this;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", productSkuId=").append(productSkuId);
        sb.append(", memberId=").append(memberId);
        sb.append(", memberNickname=").append(memberNickname);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", productPic=").append(productPic);
        sb.append(", productName=").append(productName);
        sb.append(", productSubTitle=").append(productSubTitle);
        sb.append(", productSkuCode=").append(productSkuCode);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", productCategoryId=").append(productCategoryId);
        sb.append(", productBrand=").append(productBrand);
        sb.append(", productSn=").append(productSn);
        sb.append(", productAttr=").append(productAttr);
        sb.append(", createDate=").append(createDate);
        sb.append(", modifyDate=").append(modifyDate);
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
        OmsCartItem other = (OmsCartItem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductSkuId() == null ? other.getProductSkuId() == null : this.getProductSkuId().equals(other.getProductSkuId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getMemberNickname() == null ? other.getMemberNickname() == null : this.getMemberNickname().equals(other.getMemberNickname()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getProductPic() == null ? other.getProductPic() == null : this.getProductPic().equals(other.getProductPic()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductSubTitle() == null ? other.getProductSubTitle() == null : this.getProductSubTitle().equals(other.getProductSubTitle()))
            && (this.getProductSkuCode() == null ? other.getProductSkuCode() == null : this.getProductSkuCode().equals(other.getProductSkuCode()))
            && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null : this.getDeleteStatus().equals(other.getDeleteStatus()))
            && (this.getProductCategoryId() == null ? other.getProductCategoryId() == null : this.getProductCategoryId().equals(other.getProductCategoryId()))
            && (this.getProductBrand() == null ? other.getProductBrand() == null : this.getProductBrand().equals(other.getProductBrand()))
            && (this.getProductSn() == null ? other.getProductSn() == null : this.getProductSn().equals(other.getProductSn()))
            && (this.getProductAttr() == null ? other.getProductAttr() == null : this.getProductAttr().equals(other.getProductAttr()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getModifyDate() == null ? other.getModifyDate() == null : this.getModifyDate().equals(other.getModifyDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductSkuId() == null) ? 0 : getProductSkuId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getMemberNickname() == null) ? 0 : getMemberNickname().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getProductPic() == null) ? 0 : getProductPic().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductSubTitle() == null) ? 0 : getProductSubTitle().hashCode());
        result = prime * result + ((getProductSkuCode() == null) ? 0 : getProductSkuCode().hashCode());
        result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
        result = prime * result + ((getProductCategoryId() == null) ? 0 : getProductCategoryId().hashCode());
        result = prime * result + ((getProductBrand() == null) ? 0 : getProductBrand().hashCode());
        result = prime * result + ((getProductSn() == null) ? 0 : getProductSn().hashCode());
        result = prime * result + ((getProductAttr() == null) ? 0 : getProductAttr().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getModifyDate() == null) ? 0 : getModifyDate().hashCode());
        return result;
    }
}