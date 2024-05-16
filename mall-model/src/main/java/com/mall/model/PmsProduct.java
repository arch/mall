/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PmsProduct implements Serializable {
    private Long id;

    private Long brandId;

    private Long categoryId;

    private Long freightTemplateId;

    private Long attributeCategoryId;

    private String name;

    private String pic;

    private String productSn;

    private Integer deleteStatus;

    private Integer publishStatus;

    private Integer newStatus;

    private Integer recommendStatus;

    private Integer verifyStatus;

    private Integer sort;

    private Integer sale;

    private BigDecimal price;

    private BigDecimal promotionPrice;

    private Integer giftGrowth;

    private Integer giftPoint;

    private Integer usePointLimit;

    private String subTitle;

    private BigDecimal originalPrice;

    private Integer stock;

    private Integer lowStock;

    private String unit;

    private BigDecimal weight;

    private Integer previewStatus;

    private String serviceIds;

    private String keywords;

    private String note;

    private String albumPics;

    private String detailTitle;

    private LocalDateTime promotionStartTime;

    private LocalDateTime promotionEndTime;

    private Integer promotionPerLimit;

    private Integer promotionType;

    private String brandName;

    private String productCategoryName;

    private String description;

    private String detailDesc;

    private String detailHtml;

    private String detailMobileHtml;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public PmsProduct withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public PmsProduct withBrandId(Long brandId) {
        this.setBrandId(brandId);
        return this;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public PmsProduct withCategoryId(Long categoryId) {
        this.setCategoryId(categoryId);
        return this;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getFreightTemplateId() {
        return freightTemplateId;
    }

    public PmsProduct withFreightTemplateId(Long freightTemplateId) {
        this.setFreightTemplateId(freightTemplateId);
        return this;
    }

    public void setFreightTemplateId(Long freightTemplateId) {
        this.freightTemplateId = freightTemplateId;
    }

    public Long getAttributeCategoryId() {
        return attributeCategoryId;
    }

    public PmsProduct withAttributeCategoryId(Long attributeCategoryId) {
        this.setAttributeCategoryId(attributeCategoryId);
        return this;
    }

    public void setAttributeCategoryId(Long attributeCategoryId) {
        this.attributeCategoryId = attributeCategoryId;
    }

    public String getName() {
        return name;
    }

    public PmsProduct withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPic() {
        return pic;
    }

    public PmsProduct withPic(String pic) {
        this.setPic(pic);
        return this;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getProductSn() {
        return productSn;
    }

    public PmsProduct withProductSn(String productSn) {
        this.setProductSn(productSn);
        return this;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn == null ? null : productSn.trim();
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public PmsProduct withDeleteStatus(Integer deleteStatus) {
        this.setDeleteStatus(deleteStatus);
        return this;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public PmsProduct withPublishStatus(Integer publishStatus) {
        this.setPublishStatus(publishStatus);
        return this;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public PmsProduct withNewStatus(Integer newStatus) {
        this.setNewStatus(newStatus);
        return this;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    public Integer getRecommendStatus() {
        return recommendStatus;
    }

    public PmsProduct withRecommendStatus(Integer recommendStatus) {
        this.setRecommendStatus(recommendStatus);
        return this;
    }

    public void setRecommendStatus(Integer recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public PmsProduct withVerifyStatus(Integer verifyStatus) {
        this.setVerifyStatus(verifyStatus);
        return this;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public PmsProduct withSort(Integer sort) {
        this.setSort(sort);
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSale() {
        return sale;
    }

    public PmsProduct withSale(Integer sale) {
        this.setSale(sale);
        return this;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PmsProduct withPrice(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public PmsProduct withPromotionPrice(BigDecimal promotionPrice) {
        this.setPromotionPrice(promotionPrice);
        return this;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getGiftGrowth() {
        return giftGrowth;
    }

    public PmsProduct withGiftGrowth(Integer giftGrowth) {
        this.setGiftGrowth(giftGrowth);
        return this;
    }

    public void setGiftGrowth(Integer giftGrowth) {
        this.giftGrowth = giftGrowth;
    }

    public Integer getGiftPoint() {
        return giftPoint;
    }

    public PmsProduct withGiftPoint(Integer giftPoint) {
        this.setGiftPoint(giftPoint);
        return this;
    }

    public void setGiftPoint(Integer giftPoint) {
        this.giftPoint = giftPoint;
    }

    public Integer getUsePointLimit() {
        return usePointLimit;
    }

    public PmsProduct withUsePointLimit(Integer usePointLimit) {
        this.setUsePointLimit(usePointLimit);
        return this;
    }

    public void setUsePointLimit(Integer usePointLimit) {
        this.usePointLimit = usePointLimit;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public PmsProduct withSubTitle(String subTitle) {
        this.setSubTitle(subTitle);
        return this;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public PmsProduct withOriginalPrice(BigDecimal originalPrice) {
        this.setOriginalPrice(originalPrice);
        return this;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public PmsProduct withStock(Integer stock) {
        this.setStock(stock);
        return this;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLowStock() {
        return lowStock;
    }

    public PmsProduct withLowStock(Integer lowStock) {
        this.setLowStock(lowStock);
        return this;
    }

    public void setLowStock(Integer lowStock) {
        this.lowStock = lowStock;
    }

    public String getUnit() {
        return unit;
    }

    public PmsProduct withUnit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public PmsProduct withWeight(BigDecimal weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getPreviewStatus() {
        return previewStatus;
    }

    public PmsProduct withPreviewStatus(Integer previewStatus) {
        this.setPreviewStatus(previewStatus);
        return this;
    }

    public void setPreviewStatus(Integer previewStatus) {
        this.previewStatus = previewStatus;
    }

    public String getServiceIds() {
        return serviceIds;
    }

    public PmsProduct withServiceIds(String serviceIds) {
        this.setServiceIds(serviceIds);
        return this;
    }

    public void setServiceIds(String serviceIds) {
        this.serviceIds = serviceIds == null ? null : serviceIds.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public PmsProduct withKeywords(String keywords) {
        this.setKeywords(keywords);
        return this;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getNote() {
        return note;
    }

    public PmsProduct withNote(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getAlbumPics() {
        return albumPics;
    }

    public PmsProduct withAlbumPics(String albumPics) {
        this.setAlbumPics(albumPics);
        return this;
    }

    public void setAlbumPics(String albumPics) {
        this.albumPics = albumPics == null ? null : albumPics.trim();
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public PmsProduct withDetailTitle(String detailTitle) {
        this.setDetailTitle(detailTitle);
        return this;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle == null ? null : detailTitle.trim();
    }

    public LocalDateTime getPromotionStartTime() {
        return promotionStartTime;
    }

    public PmsProduct withPromotionStartTime(LocalDateTime promotionStartTime) {
        this.setPromotionStartTime(promotionStartTime);
        return this;
    }

    public void setPromotionStartTime(LocalDateTime promotionStartTime) {
        this.promotionStartTime = promotionStartTime;
    }

    public LocalDateTime getPromotionEndTime() {
        return promotionEndTime;
    }

    public PmsProduct withPromotionEndTime(LocalDateTime promotionEndTime) {
        this.setPromotionEndTime(promotionEndTime);
        return this;
    }

    public void setPromotionEndTime(LocalDateTime promotionEndTime) {
        this.promotionEndTime = promotionEndTime;
    }

    public Integer getPromotionPerLimit() {
        return promotionPerLimit;
    }

    public PmsProduct withPromotionPerLimit(Integer promotionPerLimit) {
        this.setPromotionPerLimit(promotionPerLimit);
        return this;
    }

    public void setPromotionPerLimit(Integer promotionPerLimit) {
        this.promotionPerLimit = promotionPerLimit;
    }

    public Integer getPromotionType() {
        return promotionType;
    }

    public PmsProduct withPromotionType(Integer promotionType) {
        this.setPromotionType(promotionType);
        return this;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public String getBrandName() {
        return brandName;
    }

    public PmsProduct withBrandName(String brandName) {
        this.setBrandName(brandName);
        return this;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public PmsProduct withProductCategoryName(String productCategoryName) {
        this.setProductCategoryName(productCategoryName);
        return this;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName == null ? null : productCategoryName.trim();
    }

    public String getDescription() {
        return description;
    }

    public PmsProduct withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public PmsProduct withDetailDesc(String detailDesc) {
        this.setDetailDesc(detailDesc);
        return this;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc == null ? null : detailDesc.trim();
    }

    public String getDetailHtml() {
        return detailHtml;
    }

    public PmsProduct withDetailHtml(String detailHtml) {
        this.setDetailHtml(detailHtml);
        return this;
    }

    public void setDetailHtml(String detailHtml) {
        this.detailHtml = detailHtml == null ? null : detailHtml.trim();
    }

    public String getDetailMobileHtml() {
        return detailMobileHtml;
    }

    public PmsProduct withDetailMobileHtml(String detailMobileHtml) {
        this.setDetailMobileHtml(detailMobileHtml);
        return this;
    }

    public void setDetailMobileHtml(String detailMobileHtml) {
        this.detailMobileHtml = detailMobileHtml == null ? null : detailMobileHtml.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", brandId=").append(brandId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", freightTemplateId=").append(freightTemplateId);
        sb.append(", attributeCategoryId=").append(attributeCategoryId);
        sb.append(", name=").append(name);
        sb.append(", pic=").append(pic);
        sb.append(", productSn=").append(productSn);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", publishStatus=").append(publishStatus);
        sb.append(", newStatus=").append(newStatus);
        sb.append(", recommendStatus=").append(recommendStatus);
        sb.append(", verifyStatus=").append(verifyStatus);
        sb.append(", sort=").append(sort);
        sb.append(", sale=").append(sale);
        sb.append(", price=").append(price);
        sb.append(", promotionPrice=").append(promotionPrice);
        sb.append(", giftGrowth=").append(giftGrowth);
        sb.append(", giftPoint=").append(giftPoint);
        sb.append(", usePointLimit=").append(usePointLimit);
        sb.append(", subTitle=").append(subTitle);
        sb.append(", originalPrice=").append(originalPrice);
        sb.append(", stock=").append(stock);
        sb.append(", lowStock=").append(lowStock);
        sb.append(", unit=").append(unit);
        sb.append(", weight=").append(weight);
        sb.append(", previewStatus=").append(previewStatus);
        sb.append(", serviceIds=").append(serviceIds);
        sb.append(", keywords=").append(keywords);
        sb.append(", note=").append(note);
        sb.append(", albumPics=").append(albumPics);
        sb.append(", detailTitle=").append(detailTitle);
        sb.append(", promotionStartTime=").append(promotionStartTime);
        sb.append(", promotionEndTime=").append(promotionEndTime);
        sb.append(", promotionPerLimit=").append(promotionPerLimit);
        sb.append(", promotionType=").append(promotionType);
        sb.append(", brandName=").append(brandName);
        sb.append(", productCategoryName=").append(productCategoryName);
        sb.append(", description=").append(description);
        sb.append(", detailDesc=").append(detailDesc);
        sb.append(", detailHtml=").append(detailHtml);
        sb.append(", detailMobileHtml=").append(detailMobileHtml);
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
        PmsProduct other = (PmsProduct) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getFreightTemplateId() == null ? other.getFreightTemplateId() == null : this.getFreightTemplateId().equals(other.getFreightTemplateId()))
            && (this.getAttributeCategoryId() == null ? other.getAttributeCategoryId() == null : this.getAttributeCategoryId().equals(other.getAttributeCategoryId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPic() == null ? other.getPic() == null : this.getPic().equals(other.getPic()))
            && (this.getProductSn() == null ? other.getProductSn() == null : this.getProductSn().equals(other.getProductSn()))
            && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null : this.getDeleteStatus().equals(other.getDeleteStatus()))
            && (this.getPublishStatus() == null ? other.getPublishStatus() == null : this.getPublishStatus().equals(other.getPublishStatus()))
            && (this.getNewStatus() == null ? other.getNewStatus() == null : this.getNewStatus().equals(other.getNewStatus()))
            && (this.getRecommendStatus() == null ? other.getRecommendStatus() == null : this.getRecommendStatus().equals(other.getRecommendStatus()))
            && (this.getVerifyStatus() == null ? other.getVerifyStatus() == null : this.getVerifyStatus().equals(other.getVerifyStatus()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getSale() == null ? other.getSale() == null : this.getSale().equals(other.getSale()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getPromotionPrice() == null ? other.getPromotionPrice() == null : this.getPromotionPrice().equals(other.getPromotionPrice()))
            && (this.getGiftGrowth() == null ? other.getGiftGrowth() == null : this.getGiftGrowth().equals(other.getGiftGrowth()))
            && (this.getGiftPoint() == null ? other.getGiftPoint() == null : this.getGiftPoint().equals(other.getGiftPoint()))
            && (this.getUsePointLimit() == null ? other.getUsePointLimit() == null : this.getUsePointLimit().equals(other.getUsePointLimit()))
            && (this.getSubTitle() == null ? other.getSubTitle() == null : this.getSubTitle().equals(other.getSubTitle()))
            && (this.getOriginalPrice() == null ? other.getOriginalPrice() == null : this.getOriginalPrice().equals(other.getOriginalPrice()))
            && (this.getStock() == null ? other.getStock() == null : this.getStock().equals(other.getStock()))
            && (this.getLowStock() == null ? other.getLowStock() == null : this.getLowStock().equals(other.getLowStock()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getPreviewStatus() == null ? other.getPreviewStatus() == null : this.getPreviewStatus().equals(other.getPreviewStatus()))
            && (this.getServiceIds() == null ? other.getServiceIds() == null : this.getServiceIds().equals(other.getServiceIds()))
            && (this.getKeywords() == null ? other.getKeywords() == null : this.getKeywords().equals(other.getKeywords()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getAlbumPics() == null ? other.getAlbumPics() == null : this.getAlbumPics().equals(other.getAlbumPics()))
            && (this.getDetailTitle() == null ? other.getDetailTitle() == null : this.getDetailTitle().equals(other.getDetailTitle()))
            && (this.getPromotionStartTime() == null ? other.getPromotionStartTime() == null : this.getPromotionStartTime().equals(other.getPromotionStartTime()))
            && (this.getPromotionEndTime() == null ? other.getPromotionEndTime() == null : this.getPromotionEndTime().equals(other.getPromotionEndTime()))
            && (this.getPromotionPerLimit() == null ? other.getPromotionPerLimit() == null : this.getPromotionPerLimit().equals(other.getPromotionPerLimit()))
            && (this.getPromotionType() == null ? other.getPromotionType() == null : this.getPromotionType().equals(other.getPromotionType()))
            && (this.getBrandName() == null ? other.getBrandName() == null : this.getBrandName().equals(other.getBrandName()))
            && (this.getProductCategoryName() == null ? other.getProductCategoryName() == null : this.getProductCategoryName().equals(other.getProductCategoryName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getDetailDesc() == null ? other.getDetailDesc() == null : this.getDetailDesc().equals(other.getDetailDesc()))
            && (this.getDetailHtml() == null ? other.getDetailHtml() == null : this.getDetailHtml().equals(other.getDetailHtml()))
            && (this.getDetailMobileHtml() == null ? other.getDetailMobileHtml() == null : this.getDetailMobileHtml().equals(other.getDetailMobileHtml()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getFreightTemplateId() == null) ? 0 : getFreightTemplateId().hashCode());
        result = prime * result + ((getAttributeCategoryId() == null) ? 0 : getAttributeCategoryId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPic() == null) ? 0 : getPic().hashCode());
        result = prime * result + ((getProductSn() == null) ? 0 : getProductSn().hashCode());
        result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
        result = prime * result + ((getPublishStatus() == null) ? 0 : getPublishStatus().hashCode());
        result = prime * result + ((getNewStatus() == null) ? 0 : getNewStatus().hashCode());
        result = prime * result + ((getRecommendStatus() == null) ? 0 : getRecommendStatus().hashCode());
        result = prime * result + ((getVerifyStatus() == null) ? 0 : getVerifyStatus().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getSale() == null) ? 0 : getSale().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getPromotionPrice() == null) ? 0 : getPromotionPrice().hashCode());
        result = prime * result + ((getGiftGrowth() == null) ? 0 : getGiftGrowth().hashCode());
        result = prime * result + ((getGiftPoint() == null) ? 0 : getGiftPoint().hashCode());
        result = prime * result + ((getUsePointLimit() == null) ? 0 : getUsePointLimit().hashCode());
        result = prime * result + ((getSubTitle() == null) ? 0 : getSubTitle().hashCode());
        result = prime * result + ((getOriginalPrice() == null) ? 0 : getOriginalPrice().hashCode());
        result = prime * result + ((getStock() == null) ? 0 : getStock().hashCode());
        result = prime * result + ((getLowStock() == null) ? 0 : getLowStock().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getPreviewStatus() == null) ? 0 : getPreviewStatus().hashCode());
        result = prime * result + ((getServiceIds() == null) ? 0 : getServiceIds().hashCode());
        result = prime * result + ((getKeywords() == null) ? 0 : getKeywords().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getAlbumPics() == null) ? 0 : getAlbumPics().hashCode());
        result = prime * result + ((getDetailTitle() == null) ? 0 : getDetailTitle().hashCode());
        result = prime * result + ((getPromotionStartTime() == null) ? 0 : getPromotionStartTime().hashCode());
        result = prime * result + ((getPromotionEndTime() == null) ? 0 : getPromotionEndTime().hashCode());
        result = prime * result + ((getPromotionPerLimit() == null) ? 0 : getPromotionPerLimit().hashCode());
        result = prime * result + ((getPromotionType() == null) ? 0 : getPromotionType().hashCode());
        result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
        result = prime * result + ((getProductCategoryName() == null) ? 0 : getProductCategoryName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getDetailDesc() == null) ? 0 : getDetailDesc().hashCode());
        result = prime * result + ((getDetailHtml() == null) ? 0 : getDetailHtml().hashCode());
        result = prime * result + ((getDetailMobileHtml() == null) ? 0 : getDetailMobileHtml().hashCode());
        return result;
    }
}