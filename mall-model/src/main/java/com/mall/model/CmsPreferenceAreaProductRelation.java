/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;

public class CmsPreferenceAreaProductRelation implements Serializable {
    private Long id;

    private Long preferenceAreaId;

    private Long productId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public CmsPreferenceAreaProductRelation withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPreferenceAreaId() {
        return preferenceAreaId;
    }

    public CmsPreferenceAreaProductRelation withPreferenceAreaId(Long preferenceAreaId) {
        this.setPreferenceAreaId(preferenceAreaId);
        return this;
    }

    public void setPreferenceAreaId(Long preferenceAreaId) {
        this.preferenceAreaId = preferenceAreaId;
    }

    public Long getProductId() {
        return productId;
    }

    public CmsPreferenceAreaProductRelation withProductId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", preferenceAreaId=").append(preferenceAreaId);
        sb.append(", productId=").append(productId);
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
        CmsPreferenceAreaProductRelation other = (CmsPreferenceAreaProductRelation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPreferenceAreaId() == null ? other.getPreferenceAreaId() == null : this.getPreferenceAreaId().equals(other.getPreferenceAreaId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPreferenceAreaId() == null) ? 0 : getPreferenceAreaId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        return result;
    }
}