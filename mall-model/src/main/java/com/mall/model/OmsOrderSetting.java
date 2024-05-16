/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;

public class OmsOrderSetting implements Serializable {
    private Long id;

    private Integer flashOrderOvertime;

    private Integer normalOrderOvertime;

    private Integer confirmOvertime;

    private Integer finishOvertime;

    private Integer commentOvertime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public OmsOrderSetting withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFlashOrderOvertime() {
        return flashOrderOvertime;
    }

    public OmsOrderSetting withFlashOrderOvertime(Integer flashOrderOvertime) {
        this.setFlashOrderOvertime(flashOrderOvertime);
        return this;
    }

    public void setFlashOrderOvertime(Integer flashOrderOvertime) {
        this.flashOrderOvertime = flashOrderOvertime;
    }

    public Integer getNormalOrderOvertime() {
        return normalOrderOvertime;
    }

    public OmsOrderSetting withNormalOrderOvertime(Integer normalOrderOvertime) {
        this.setNormalOrderOvertime(normalOrderOvertime);
        return this;
    }

    public void setNormalOrderOvertime(Integer normalOrderOvertime) {
        this.normalOrderOvertime = normalOrderOvertime;
    }

    public Integer getConfirmOvertime() {
        return confirmOvertime;
    }

    public OmsOrderSetting withConfirmOvertime(Integer confirmOvertime) {
        this.setConfirmOvertime(confirmOvertime);
        return this;
    }

    public void setConfirmOvertime(Integer confirmOvertime) {
        this.confirmOvertime = confirmOvertime;
    }

    public Integer getFinishOvertime() {
        return finishOvertime;
    }

    public OmsOrderSetting withFinishOvertime(Integer finishOvertime) {
        this.setFinishOvertime(finishOvertime);
        return this;
    }

    public void setFinishOvertime(Integer finishOvertime) {
        this.finishOvertime = finishOvertime;
    }

    public Integer getCommentOvertime() {
        return commentOvertime;
    }

    public OmsOrderSetting withCommentOvertime(Integer commentOvertime) {
        this.setCommentOvertime(commentOvertime);
        return this;
    }

    public void setCommentOvertime(Integer commentOvertime) {
        this.commentOvertime = commentOvertime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", flashOrderOvertime=").append(flashOrderOvertime);
        sb.append(", normalOrderOvertime=").append(normalOrderOvertime);
        sb.append(", confirmOvertime=").append(confirmOvertime);
        sb.append(", finishOvertime=").append(finishOvertime);
        sb.append(", commentOvertime=").append(commentOvertime);
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
        OmsOrderSetting other = (OmsOrderSetting) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFlashOrderOvertime() == null ? other.getFlashOrderOvertime() == null : this.getFlashOrderOvertime().equals(other.getFlashOrderOvertime()))
            && (this.getNormalOrderOvertime() == null ? other.getNormalOrderOvertime() == null : this.getNormalOrderOvertime().equals(other.getNormalOrderOvertime()))
            && (this.getConfirmOvertime() == null ? other.getConfirmOvertime() == null : this.getConfirmOvertime().equals(other.getConfirmOvertime()))
            && (this.getFinishOvertime() == null ? other.getFinishOvertime() == null : this.getFinishOvertime().equals(other.getFinishOvertime()))
            && (this.getCommentOvertime() == null ? other.getCommentOvertime() == null : this.getCommentOvertime().equals(other.getCommentOvertime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFlashOrderOvertime() == null) ? 0 : getFlashOrderOvertime().hashCode());
        result = prime * result + ((getNormalOrderOvertime() == null) ? 0 : getNormalOrderOvertime().hashCode());
        result = prime * result + ((getConfirmOvertime() == null) ? 0 : getConfirmOvertime().hashCode());
        result = prime * result + ((getFinishOvertime() == null) ? 0 : getFinishOvertime().hashCode());
        result = prime * result + ((getCommentOvertime() == null) ? 0 : getCommentOvertime().hashCode());
        return result;
    }
}