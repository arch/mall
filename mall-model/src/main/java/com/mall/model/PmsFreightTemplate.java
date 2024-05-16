/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PmsFreightTemplate implements Serializable {
    private Long id;

    private String name;

    private Integer chargeType;

    private BigDecimal firstWeight;

    private BigDecimal firstFee;

    private BigDecimal continueWeight;

    private BigDecimal continueFee;

    private String dest;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public PmsFreightTemplate withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PmsFreightTemplate withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public PmsFreightTemplate withChargeType(Integer chargeType) {
        this.setChargeType(chargeType);
        return this;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public BigDecimal getFirstWeight() {
        return firstWeight;
    }

    public PmsFreightTemplate withFirstWeight(BigDecimal firstWeight) {
        this.setFirstWeight(firstWeight);
        return this;
    }

    public void setFirstWeight(BigDecimal firstWeight) {
        this.firstWeight = firstWeight;
    }

    public BigDecimal getFirstFee() {
        return firstFee;
    }

    public PmsFreightTemplate withFirstFee(BigDecimal firstFee) {
        this.setFirstFee(firstFee);
        return this;
    }

    public void setFirstFee(BigDecimal firstFee) {
        this.firstFee = firstFee;
    }

    public BigDecimal getContinueWeight() {
        return continueWeight;
    }

    public PmsFreightTemplate withContinueWeight(BigDecimal continueWeight) {
        this.setContinueWeight(continueWeight);
        return this;
    }

    public void setContinueWeight(BigDecimal continueWeight) {
        this.continueWeight = continueWeight;
    }

    public BigDecimal getContinueFee() {
        return continueFee;
    }

    public PmsFreightTemplate withContinueFee(BigDecimal continueFee) {
        this.setContinueFee(continueFee);
        return this;
    }

    public void setContinueFee(BigDecimal continueFee) {
        this.continueFee = continueFee;
    }

    public String getDest() {
        return dest;
    }

    public PmsFreightTemplate withDest(String dest) {
        this.setDest(dest);
        return this;
    }

    public void setDest(String dest) {
        this.dest = dest == null ? null : dest.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", chargeType=").append(chargeType);
        sb.append(", firstWeight=").append(firstWeight);
        sb.append(", firstFee=").append(firstFee);
        sb.append(", continueWeight=").append(continueWeight);
        sb.append(", continueFee=").append(continueFee);
        sb.append(", dest=").append(dest);
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
        PmsFreightTemplate other = (PmsFreightTemplate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getChargeType() == null ? other.getChargeType() == null : this.getChargeType().equals(other.getChargeType()))
            && (this.getFirstWeight() == null ? other.getFirstWeight() == null : this.getFirstWeight().equals(other.getFirstWeight()))
            && (this.getFirstFee() == null ? other.getFirstFee() == null : this.getFirstFee().equals(other.getFirstFee()))
            && (this.getContinueWeight() == null ? other.getContinueWeight() == null : this.getContinueWeight().equals(other.getContinueWeight()))
            && (this.getContinueFee() == null ? other.getContinueFee() == null : this.getContinueFee().equals(other.getContinueFee()))
            && (this.getDest() == null ? other.getDest() == null : this.getDest().equals(other.getDest()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getChargeType() == null) ? 0 : getChargeType().hashCode());
        result = prime * result + ((getFirstWeight() == null) ? 0 : getFirstWeight().hashCode());
        result = prime * result + ((getFirstFee() == null) ? 0 : getFirstFee().hashCode());
        result = prime * result + ((getContinueWeight() == null) ? 0 : getContinueWeight().hashCode());
        result = prime * result + ((getContinueFee() == null) ? 0 : getContinueFee().hashCode());
        result = prime * result + ((getDest() == null) ? 0 : getDest().hashCode());
        return result;
    }
}