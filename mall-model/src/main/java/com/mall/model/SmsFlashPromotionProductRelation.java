/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SmsFlashPromotionProductRelation implements Serializable {
    private Long id;

    private Long flashPromotionId;

    private Long flashPromotionSessionId;

    private Long productId;

    private BigDecimal flashPromotionPrice;

    private Integer flashPromotionCount;

    private Integer flashPromotionLimit;

    private Integer sort;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public SmsFlashPromotionProductRelation withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlashPromotionId() {
        return flashPromotionId;
    }

    public SmsFlashPromotionProductRelation withFlashPromotionId(Long flashPromotionId) {
        this.setFlashPromotionId(flashPromotionId);
        return this;
    }

    public void setFlashPromotionId(Long flashPromotionId) {
        this.flashPromotionId = flashPromotionId;
    }

    public Long getFlashPromotionSessionId() {
        return flashPromotionSessionId;
    }

    public SmsFlashPromotionProductRelation withFlashPromotionSessionId(Long flashPromotionSessionId) {
        this.setFlashPromotionSessionId(flashPromotionSessionId);
        return this;
    }

    public void setFlashPromotionSessionId(Long flashPromotionSessionId) {
        this.flashPromotionSessionId = flashPromotionSessionId;
    }

    public Long getProductId() {
        return productId;
    }

    public SmsFlashPromotionProductRelation withProductId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getFlashPromotionPrice() {
        return flashPromotionPrice;
    }

    public SmsFlashPromotionProductRelation withFlashPromotionPrice(BigDecimal flashPromotionPrice) {
        this.setFlashPromotionPrice(flashPromotionPrice);
        return this;
    }

    public void setFlashPromotionPrice(BigDecimal flashPromotionPrice) {
        this.flashPromotionPrice = flashPromotionPrice;
    }

    public Integer getFlashPromotionCount() {
        return flashPromotionCount;
    }

    public SmsFlashPromotionProductRelation withFlashPromotionCount(Integer flashPromotionCount) {
        this.setFlashPromotionCount(flashPromotionCount);
        return this;
    }

    public void setFlashPromotionCount(Integer flashPromotionCount) {
        this.flashPromotionCount = flashPromotionCount;
    }

    public Integer getFlashPromotionLimit() {
        return flashPromotionLimit;
    }

    public SmsFlashPromotionProductRelation withFlashPromotionLimit(Integer flashPromotionLimit) {
        this.setFlashPromotionLimit(flashPromotionLimit);
        return this;
    }

    public void setFlashPromotionLimit(Integer flashPromotionLimit) {
        this.flashPromotionLimit = flashPromotionLimit;
    }

    public Integer getSort() {
        return sort;
    }

    public SmsFlashPromotionProductRelation withSort(Integer sort) {
        this.setSort(sort);
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", flashPromotionId=").append(flashPromotionId);
        sb.append(", flashPromotionSessionId=").append(flashPromotionSessionId);
        sb.append(", productId=").append(productId);
        sb.append(", flashPromotionPrice=").append(flashPromotionPrice);
        sb.append(", flashPromotionCount=").append(flashPromotionCount);
        sb.append(", flashPromotionLimit=").append(flashPromotionLimit);
        sb.append(", sort=").append(sort);
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
        SmsFlashPromotionProductRelation other = (SmsFlashPromotionProductRelation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFlashPromotionId() == null ? other.getFlashPromotionId() == null : this.getFlashPromotionId().equals(other.getFlashPromotionId()))
            && (this.getFlashPromotionSessionId() == null ? other.getFlashPromotionSessionId() == null : this.getFlashPromotionSessionId().equals(other.getFlashPromotionSessionId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getFlashPromotionPrice() == null ? other.getFlashPromotionPrice() == null : this.getFlashPromotionPrice().equals(other.getFlashPromotionPrice()))
            && (this.getFlashPromotionCount() == null ? other.getFlashPromotionCount() == null : this.getFlashPromotionCount().equals(other.getFlashPromotionCount()))
            && (this.getFlashPromotionLimit() == null ? other.getFlashPromotionLimit() == null : this.getFlashPromotionLimit().equals(other.getFlashPromotionLimit()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFlashPromotionId() == null) ? 0 : getFlashPromotionId().hashCode());
        result = prime * result + ((getFlashPromotionSessionId() == null) ? 0 : getFlashPromotionSessionId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getFlashPromotionPrice() == null) ? 0 : getFlashPromotionPrice().hashCode());
        result = prime * result + ((getFlashPromotionCount() == null) ? 0 : getFlashPromotionCount().hashCode());
        result = prime * result + ((getFlashPromotionLimit() == null) ? 0 : getFlashPromotionLimit().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        return result;
    }
}