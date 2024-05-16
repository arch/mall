/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class UmsMemberLevel implements Serializable {
    private Long id;

    private String name;

    private Integer growthPoint;

    private Integer defaultStatus;

    private BigDecimal freeFreightPoint;

    private Integer commentGrowthPoint;

    private Integer privilegeFreeFreight;

    private Integer privilegeSignIn;

    private Integer privilegeComment;

    private Integer privilegePromotion;

    private Integer privilegeMemberPrice;

    private Integer privilegeBirthday;

    private String note;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public UmsMemberLevel withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UmsMemberLevel withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGrowthPoint() {
        return growthPoint;
    }

    public UmsMemberLevel withGrowthPoint(Integer growthPoint) {
        this.setGrowthPoint(growthPoint);
        return this;
    }

    public void setGrowthPoint(Integer growthPoint) {
        this.growthPoint = growthPoint;
    }

    public Integer getDefaultStatus() {
        return defaultStatus;
    }

    public UmsMemberLevel withDefaultStatus(Integer defaultStatus) {
        this.setDefaultStatus(defaultStatus);
        return this;
    }

    public void setDefaultStatus(Integer defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public BigDecimal getFreeFreightPoint() {
        return freeFreightPoint;
    }

    public UmsMemberLevel withFreeFreightPoint(BigDecimal freeFreightPoint) {
        this.setFreeFreightPoint(freeFreightPoint);
        return this;
    }

    public void setFreeFreightPoint(BigDecimal freeFreightPoint) {
        this.freeFreightPoint = freeFreightPoint;
    }

    public Integer getCommentGrowthPoint() {
        return commentGrowthPoint;
    }

    public UmsMemberLevel withCommentGrowthPoint(Integer commentGrowthPoint) {
        this.setCommentGrowthPoint(commentGrowthPoint);
        return this;
    }

    public void setCommentGrowthPoint(Integer commentGrowthPoint) {
        this.commentGrowthPoint = commentGrowthPoint;
    }

    public Integer getPrivilegeFreeFreight() {
        return privilegeFreeFreight;
    }

    public UmsMemberLevel withPrivilegeFreeFreight(Integer privilegeFreeFreight) {
        this.setPrivilegeFreeFreight(privilegeFreeFreight);
        return this;
    }

    public void setPrivilegeFreeFreight(Integer privilegeFreeFreight) {
        this.privilegeFreeFreight = privilegeFreeFreight;
    }

    public Integer getPrivilegeSignIn() {
        return privilegeSignIn;
    }

    public UmsMemberLevel withPrivilegeSignIn(Integer privilegeSignIn) {
        this.setPrivilegeSignIn(privilegeSignIn);
        return this;
    }

    public void setPrivilegeSignIn(Integer privilegeSignIn) {
        this.privilegeSignIn = privilegeSignIn;
    }

    public Integer getPrivilegeComment() {
        return privilegeComment;
    }

    public UmsMemberLevel withPrivilegeComment(Integer privilegeComment) {
        this.setPrivilegeComment(privilegeComment);
        return this;
    }

    public void setPrivilegeComment(Integer privilegeComment) {
        this.privilegeComment = privilegeComment;
    }

    public Integer getPrivilegePromotion() {
        return privilegePromotion;
    }

    public UmsMemberLevel withPrivilegePromotion(Integer privilegePromotion) {
        this.setPrivilegePromotion(privilegePromotion);
        return this;
    }

    public void setPrivilegePromotion(Integer privilegePromotion) {
        this.privilegePromotion = privilegePromotion;
    }

    public Integer getPrivilegeMemberPrice() {
        return privilegeMemberPrice;
    }

    public UmsMemberLevel withPrivilegeMemberPrice(Integer privilegeMemberPrice) {
        this.setPrivilegeMemberPrice(privilegeMemberPrice);
        return this;
    }

    public void setPrivilegeMemberPrice(Integer privilegeMemberPrice) {
        this.privilegeMemberPrice = privilegeMemberPrice;
    }

    public Integer getPrivilegeBirthday() {
        return privilegeBirthday;
    }

    public UmsMemberLevel withPrivilegeBirthday(Integer privilegeBirthday) {
        this.setPrivilegeBirthday(privilegeBirthday);
        return this;
    }

    public void setPrivilegeBirthday(Integer privilegeBirthday) {
        this.privilegeBirthday = privilegeBirthday;
    }

    public String getNote() {
        return note;
    }

    public UmsMemberLevel withNote(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", growthPoint=").append(growthPoint);
        sb.append(", defaultStatus=").append(defaultStatus);
        sb.append(", freeFreightPoint=").append(freeFreightPoint);
        sb.append(", commentGrowthPoint=").append(commentGrowthPoint);
        sb.append(", privilegeFreeFreight=").append(privilegeFreeFreight);
        sb.append(", privilegeSignIn=").append(privilegeSignIn);
        sb.append(", privilegeComment=").append(privilegeComment);
        sb.append(", privilegePromotion=").append(privilegePromotion);
        sb.append(", privilegeMemberPrice=").append(privilegeMemberPrice);
        sb.append(", privilegeBirthday=").append(privilegeBirthday);
        sb.append(", note=").append(note);
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
        UmsMemberLevel other = (UmsMemberLevel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getGrowthPoint() == null ? other.getGrowthPoint() == null : this.getGrowthPoint().equals(other.getGrowthPoint()))
            && (this.getDefaultStatus() == null ? other.getDefaultStatus() == null : this.getDefaultStatus().equals(other.getDefaultStatus()))
            && (this.getFreeFreightPoint() == null ? other.getFreeFreightPoint() == null : this.getFreeFreightPoint().equals(other.getFreeFreightPoint()))
            && (this.getCommentGrowthPoint() == null ? other.getCommentGrowthPoint() == null : this.getCommentGrowthPoint().equals(other.getCommentGrowthPoint()))
            && (this.getPrivilegeFreeFreight() == null ? other.getPrivilegeFreeFreight() == null : this.getPrivilegeFreeFreight().equals(other.getPrivilegeFreeFreight()))
            && (this.getPrivilegeSignIn() == null ? other.getPrivilegeSignIn() == null : this.getPrivilegeSignIn().equals(other.getPrivilegeSignIn()))
            && (this.getPrivilegeComment() == null ? other.getPrivilegeComment() == null : this.getPrivilegeComment().equals(other.getPrivilegeComment()))
            && (this.getPrivilegePromotion() == null ? other.getPrivilegePromotion() == null : this.getPrivilegePromotion().equals(other.getPrivilegePromotion()))
            && (this.getPrivilegeMemberPrice() == null ? other.getPrivilegeMemberPrice() == null : this.getPrivilegeMemberPrice().equals(other.getPrivilegeMemberPrice()))
            && (this.getPrivilegeBirthday() == null ? other.getPrivilegeBirthday() == null : this.getPrivilegeBirthday().equals(other.getPrivilegeBirthday()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getGrowthPoint() == null) ? 0 : getGrowthPoint().hashCode());
        result = prime * result + ((getDefaultStatus() == null) ? 0 : getDefaultStatus().hashCode());
        result = prime * result + ((getFreeFreightPoint() == null) ? 0 : getFreeFreightPoint().hashCode());
        result = prime * result + ((getCommentGrowthPoint() == null) ? 0 : getCommentGrowthPoint().hashCode());
        result = prime * result + ((getPrivilegeFreeFreight() == null) ? 0 : getPrivilegeFreeFreight().hashCode());
        result = prime * result + ((getPrivilegeSignIn() == null) ? 0 : getPrivilegeSignIn().hashCode());
        result = prime * result + ((getPrivilegeComment() == null) ? 0 : getPrivilegeComment().hashCode());
        result = prime * result + ((getPrivilegePromotion() == null) ? 0 : getPrivilegePromotion().hashCode());
        result = prime * result + ((getPrivilegeMemberPrice() == null) ? 0 : getPrivilegeMemberPrice().hashCode());
        result = prime * result + ((getPrivilegeBirthday() == null) ? 0 : getPrivilegeBirthday().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        return result;
    }
}