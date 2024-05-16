/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CmsMemberReport implements Serializable {
    private Long id;

    private Integer reportType;

    private String reportMemberName;

    private String reportObject;

    private Integer reportStatus;

    private Integer handleStatus;

    private String note;

    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public CmsMemberReport withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReportType() {
        return reportType;
    }

    public CmsMemberReport withReportType(Integer reportType) {
        this.setReportType(reportType);
        return this;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getReportMemberName() {
        return reportMemberName;
    }

    public CmsMemberReport withReportMemberName(String reportMemberName) {
        this.setReportMemberName(reportMemberName);
        return this;
    }

    public void setReportMemberName(String reportMemberName) {
        this.reportMemberName = reportMemberName == null ? null : reportMemberName.trim();
    }

    public String getReportObject() {
        return reportObject;
    }

    public CmsMemberReport withReportObject(String reportObject) {
        this.setReportObject(reportObject);
        return this;
    }

    public void setReportObject(String reportObject) {
        this.reportObject = reportObject == null ? null : reportObject.trim();
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public CmsMemberReport withReportStatus(Integer reportStatus) {
        this.setReportStatus(reportStatus);
        return this;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public CmsMemberReport withHandleStatus(Integer handleStatus) {
        this.setHandleStatus(handleStatus);
        return this;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getNote() {
        return note;
    }

    public CmsMemberReport withNote(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public CmsMemberReport withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reportType=").append(reportType);
        sb.append(", reportMemberName=").append(reportMemberName);
        sb.append(", reportObject=").append(reportObject);
        sb.append(", reportStatus=").append(reportStatus);
        sb.append(", handleStatus=").append(handleStatus);
        sb.append(", note=").append(note);
        sb.append(", createTime=").append(createTime);
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
        CmsMemberReport other = (CmsMemberReport) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getReportType() == null ? other.getReportType() == null : this.getReportType().equals(other.getReportType()))
            && (this.getReportMemberName() == null ? other.getReportMemberName() == null : this.getReportMemberName().equals(other.getReportMemberName()))
            && (this.getReportObject() == null ? other.getReportObject() == null : this.getReportObject().equals(other.getReportObject()))
            && (this.getReportStatus() == null ? other.getReportStatus() == null : this.getReportStatus().equals(other.getReportStatus()))
            && (this.getHandleStatus() == null ? other.getHandleStatus() == null : this.getHandleStatus().equals(other.getHandleStatus()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getReportType() == null) ? 0 : getReportType().hashCode());
        result = prime * result + ((getReportMemberName() == null) ? 0 : getReportMemberName().hashCode());
        result = prime * result + ((getReportObject() == null) ? 0 : getReportObject().hashCode());
        result = prime * result + ((getReportStatus() == null) ? 0 : getReportStatus().hashCode());
        result = prime * result + ((getHandleStatus() == null) ? 0 : getHandleStatus().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}