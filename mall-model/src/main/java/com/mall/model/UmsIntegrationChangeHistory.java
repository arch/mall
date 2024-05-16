/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UmsIntegrationChangeHistory implements Serializable {
    private Long id;

    private Long memberId;

    private LocalDateTime createTime;

    private Integer changeType;

    private Integer changeCount;

    private String operateMan;

    private String operateNote;

    private Integer sourceType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public UmsIntegrationChangeHistory withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public UmsIntegrationChangeHistory withMemberId(Long memberId) {
        this.setMemberId(memberId);
        return this;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public UmsIntegrationChangeHistory withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public UmsIntegrationChangeHistory withChangeType(Integer changeType) {
        this.setChangeType(changeType);
        return this;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Integer getChangeCount() {
        return changeCount;
    }

    public UmsIntegrationChangeHistory withChangeCount(Integer changeCount) {
        this.setChangeCount(changeCount);
        return this;
    }

    public void setChangeCount(Integer changeCount) {
        this.changeCount = changeCount;
    }

    public String getOperateMan() {
        return operateMan;
    }

    public UmsIntegrationChangeHistory withOperateMan(String operateMan) {
        this.setOperateMan(operateMan);
        return this;
    }

    public void setOperateMan(String operateMan) {
        this.operateMan = operateMan == null ? null : operateMan.trim();
    }

    public String getOperateNote() {
        return operateNote;
    }

    public UmsIntegrationChangeHistory withOperateNote(String operateNote) {
        this.setOperateNote(operateNote);
        return this;
    }

    public void setOperateNote(String operateNote) {
        this.operateNote = operateNote == null ? null : operateNote.trim();
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public UmsIntegrationChangeHistory withSourceType(Integer sourceType) {
        this.setSourceType(sourceType);
        return this;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", createTime=").append(createTime);
        sb.append(", changeType=").append(changeType);
        sb.append(", changeCount=").append(changeCount);
        sb.append(", operateMan=").append(operateMan);
        sb.append(", operateNote=").append(operateNote);
        sb.append(", sourceType=").append(sourceType);
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
        UmsIntegrationChangeHistory other = (UmsIntegrationChangeHistory) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getChangeType() == null ? other.getChangeType() == null : this.getChangeType().equals(other.getChangeType()))
            && (this.getChangeCount() == null ? other.getChangeCount() == null : this.getChangeCount().equals(other.getChangeCount()))
            && (this.getOperateMan() == null ? other.getOperateMan() == null : this.getOperateMan().equals(other.getOperateMan()))
            && (this.getOperateNote() == null ? other.getOperateNote() == null : this.getOperateNote().equals(other.getOperateNote()))
            && (this.getSourceType() == null ? other.getSourceType() == null : this.getSourceType().equals(other.getSourceType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getChangeType() == null) ? 0 : getChangeType().hashCode());
        result = prime * result + ((getChangeCount() == null) ? 0 : getChangeCount().hashCode());
        result = prime * result + ((getOperateMan() == null) ? 0 : getOperateMan().hashCode());
        result = prime * result + ((getOperateNote() == null) ? 0 : getOperateNote().hashCode());
        result = prime * result + ((getSourceType() == null) ? 0 : getSourceType().hashCode());
        return result;
    }
}