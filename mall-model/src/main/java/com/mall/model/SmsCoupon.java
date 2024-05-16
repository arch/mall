/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SmsCoupon implements Serializable {
    private Long id;

    private Integer type;

    private String name;

    private Integer platform;

    private Integer count;

    private BigDecimal amount;

    private Integer perLimit;

    private BigDecimal minPoint;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer useType;

    private String note;

    private Integer publishCount;

    private Integer useCount;

    private Integer receiveCount;

    private LocalDateTime enableTime;

    private String code;

    private Integer memberLevel;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public SmsCoupon withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public SmsCoupon withType(Integer type) {
        this.setType(type);
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public SmsCoupon withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPlatform() {
        return platform;
    }

    public SmsCoupon withPlatform(Integer platform) {
        this.setPlatform(platform);
        return this;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getCount() {
        return count;
    }

    public SmsCoupon withCount(Integer count) {
        this.setCount(count);
        return this;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public SmsCoupon withAmount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPerLimit() {
        return perLimit;
    }

    public SmsCoupon withPerLimit(Integer perLimit) {
        this.setPerLimit(perLimit);
        return this;
    }

    public void setPerLimit(Integer perLimit) {
        this.perLimit = perLimit;
    }

    public BigDecimal getMinPoint() {
        return minPoint;
    }

    public SmsCoupon withMinPoint(BigDecimal minPoint) {
        this.setMinPoint(minPoint);
        return this;
    }

    public void setMinPoint(BigDecimal minPoint) {
        this.minPoint = minPoint;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public SmsCoupon withStartTime(LocalDateTime startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public SmsCoupon withEndTime(LocalDateTime endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getUseType() {
        return useType;
    }

    public SmsCoupon withUseType(Integer useType) {
        this.setUseType(useType);
        return this;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public String getNote() {
        return note;
    }

    public SmsCoupon withNote(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getPublishCount() {
        return publishCount;
    }

    public SmsCoupon withPublishCount(Integer publishCount) {
        this.setPublishCount(publishCount);
        return this;
    }

    public void setPublishCount(Integer publishCount) {
        this.publishCount = publishCount;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public SmsCoupon withUseCount(Integer useCount) {
        this.setUseCount(useCount);
        return this;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public Integer getReceiveCount() {
        return receiveCount;
    }

    public SmsCoupon withReceiveCount(Integer receiveCount) {
        this.setReceiveCount(receiveCount);
        return this;
    }

    public void setReceiveCount(Integer receiveCount) {
        this.receiveCount = receiveCount;
    }

    public LocalDateTime getEnableTime() {
        return enableTime;
    }

    public SmsCoupon withEnableTime(LocalDateTime enableTime) {
        this.setEnableTime(enableTime);
        return this;
    }

    public void setEnableTime(LocalDateTime enableTime) {
        this.enableTime = enableTime;
    }

    public String getCode() {
        return code;
    }

    public SmsCoupon withCode(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public SmsCoupon withMemberLevel(Integer memberLevel) {
        this.setMemberLevel(memberLevel);
        return this;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", platform=").append(platform);
        sb.append(", count=").append(count);
        sb.append(", amount=").append(amount);
        sb.append(", perLimit=").append(perLimit);
        sb.append(", minPoint=").append(minPoint);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", useType=").append(useType);
        sb.append(", note=").append(note);
        sb.append(", publishCount=").append(publishCount);
        sb.append(", useCount=").append(useCount);
        sb.append(", receiveCount=").append(receiveCount);
        sb.append(", enableTime=").append(enableTime);
        sb.append(", code=").append(code);
        sb.append(", memberLevel=").append(memberLevel);
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
        SmsCoupon other = (SmsCoupon) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getPerLimit() == null ? other.getPerLimit() == null : this.getPerLimit().equals(other.getPerLimit()))
            && (this.getMinPoint() == null ? other.getMinPoint() == null : this.getMinPoint().equals(other.getMinPoint()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getUseType() == null ? other.getUseType() == null : this.getUseType().equals(other.getUseType()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getPublishCount() == null ? other.getPublishCount() == null : this.getPublishCount().equals(other.getPublishCount()))
            && (this.getUseCount() == null ? other.getUseCount() == null : this.getUseCount().equals(other.getUseCount()))
            && (this.getReceiveCount() == null ? other.getReceiveCount() == null : this.getReceiveCount().equals(other.getReceiveCount()))
            && (this.getEnableTime() == null ? other.getEnableTime() == null : this.getEnableTime().equals(other.getEnableTime()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getMemberLevel() == null ? other.getMemberLevel() == null : this.getMemberLevel().equals(other.getMemberLevel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getPerLimit() == null) ? 0 : getPerLimit().hashCode());
        result = prime * result + ((getMinPoint() == null) ? 0 : getMinPoint().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getUseType() == null) ? 0 : getUseType().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getPublishCount() == null) ? 0 : getPublishCount().hashCode());
        result = prime * result + ((getUseCount() == null) ? 0 : getUseCount().hashCode());
        result = prime * result + ((getReceiveCount() == null) ? 0 : getReceiveCount().hashCode());
        result = prime * result + ((getEnableTime() == null) ? 0 : getEnableTime().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getMemberLevel() == null) ? 0 : getMemberLevel().hashCode());
        return result;
    }
}