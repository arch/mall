/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class UmsMemberTag implements Serializable {
    private Long id;

    private String name;

    private Integer finishOrderCount;

    private BigDecimal finishOrderAmount;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public UmsMemberTag withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UmsMemberTag withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFinishOrderCount() {
        return finishOrderCount;
    }

    public UmsMemberTag withFinishOrderCount(Integer finishOrderCount) {
        this.setFinishOrderCount(finishOrderCount);
        return this;
    }

    public void setFinishOrderCount(Integer finishOrderCount) {
        this.finishOrderCount = finishOrderCount;
    }

    public BigDecimal getFinishOrderAmount() {
        return finishOrderAmount;
    }

    public UmsMemberTag withFinishOrderAmount(BigDecimal finishOrderAmount) {
        this.setFinishOrderAmount(finishOrderAmount);
        return this;
    }

    public void setFinishOrderAmount(BigDecimal finishOrderAmount) {
        this.finishOrderAmount = finishOrderAmount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", finishOrderCount=").append(finishOrderCount);
        sb.append(", finishOrderAmount=").append(finishOrderAmount);
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
        UmsMemberTag other = (UmsMemberTag) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getFinishOrderCount() == null ? other.getFinishOrderCount() == null : this.getFinishOrderCount().equals(other.getFinishOrderCount()))
            && (this.getFinishOrderAmount() == null ? other.getFinishOrderAmount() == null : this.getFinishOrderAmount().equals(other.getFinishOrderAmount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getFinishOrderCount() == null) ? 0 : getFinishOrderCount().hashCode());
        result = prime * result + ((getFinishOrderAmount() == null) ? 0 : getFinishOrderAmount().hashCode());
        return result;
    }
}