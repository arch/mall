/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;

public class UmsIntegrationConsumeSetting implements Serializable {
    private Long id;

    private Integer deductionPerAmount;

    private Integer maxPercentPerOrder;

    private Integer useUnit;

    private Integer couponStatus;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public UmsIntegrationConsumeSetting withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeductionPerAmount() {
        return deductionPerAmount;
    }

    public UmsIntegrationConsumeSetting withDeductionPerAmount(Integer deductionPerAmount) {
        this.setDeductionPerAmount(deductionPerAmount);
        return this;
    }

    public void setDeductionPerAmount(Integer deductionPerAmount) {
        this.deductionPerAmount = deductionPerAmount;
    }

    public Integer getMaxPercentPerOrder() {
        return maxPercentPerOrder;
    }

    public UmsIntegrationConsumeSetting withMaxPercentPerOrder(Integer maxPercentPerOrder) {
        this.setMaxPercentPerOrder(maxPercentPerOrder);
        return this;
    }

    public void setMaxPercentPerOrder(Integer maxPercentPerOrder) {
        this.maxPercentPerOrder = maxPercentPerOrder;
    }

    public Integer getUseUnit() {
        return useUnit;
    }

    public UmsIntegrationConsumeSetting withUseUnit(Integer useUnit) {
        this.setUseUnit(useUnit);
        return this;
    }

    public void setUseUnit(Integer useUnit) {
        this.useUnit = useUnit;
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public UmsIntegrationConsumeSetting withCouponStatus(Integer couponStatus) {
        this.setCouponStatus(couponStatus);
        return this;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", deductionPerAmount=").append(deductionPerAmount);
        sb.append(", maxPercentPerOrder=").append(maxPercentPerOrder);
        sb.append(", useUnit=").append(useUnit);
        sb.append(", couponStatus=").append(couponStatus);
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
        UmsIntegrationConsumeSetting other = (UmsIntegrationConsumeSetting) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDeductionPerAmount() == null ? other.getDeductionPerAmount() == null : this.getDeductionPerAmount().equals(other.getDeductionPerAmount()))
            && (this.getMaxPercentPerOrder() == null ? other.getMaxPercentPerOrder() == null : this.getMaxPercentPerOrder().equals(other.getMaxPercentPerOrder()))
            && (this.getUseUnit() == null ? other.getUseUnit() == null : this.getUseUnit().equals(other.getUseUnit()))
            && (this.getCouponStatus() == null ? other.getCouponStatus() == null : this.getCouponStatus().equals(other.getCouponStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDeductionPerAmount() == null) ? 0 : getDeductionPerAmount().hashCode());
        result = prime * result + ((getMaxPercentPerOrder() == null) ? 0 : getMaxPercentPerOrder().hashCode());
        result = prime * result + ((getUseUnit() == null) ? 0 : getUseUnit().hashCode());
        result = prime * result + ((getCouponStatus() == null) ? 0 : getCouponStatus().hashCode());
        return result;
    }
}