/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UmsMemberStatisticsInfo implements Serializable {
    private Long id;

    private Long memberId;

    private BigDecimal consumeAmount;

    private Integer orderCount;

    private Integer couponCount;

    private Integer commentCount;

    private Integer returnOrderCount;

    private Integer loginCount;

    private Integer attendCount;

    private Integer fansCount;

    private Integer collectProductCount;

    private Integer collectSubjectCount;

    private Integer collectTopicCount;

    private Integer collectCommentCount;

    private Integer inviteFriendCount;

    private LocalDateTime recentOrderTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public UmsMemberStatisticsInfo withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public UmsMemberStatisticsInfo withMemberId(Long memberId) {
        this.setMemberId(memberId);
        return this;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    public UmsMemberStatisticsInfo withConsumeAmount(BigDecimal consumeAmount) {
        this.setConsumeAmount(consumeAmount);
        return this;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public UmsMemberStatisticsInfo withOrderCount(Integer orderCount) {
        this.setOrderCount(orderCount);
        return this;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public UmsMemberStatisticsInfo withCouponCount(Integer couponCount) {
        this.setCouponCount(couponCount);
        return this;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public UmsMemberStatisticsInfo withCommentCount(Integer commentCount) {
        this.setCommentCount(commentCount);
        return this;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getReturnOrderCount() {
        return returnOrderCount;
    }

    public UmsMemberStatisticsInfo withReturnOrderCount(Integer returnOrderCount) {
        this.setReturnOrderCount(returnOrderCount);
        return this;
    }

    public void setReturnOrderCount(Integer returnOrderCount) {
        this.returnOrderCount = returnOrderCount;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public UmsMemberStatisticsInfo withLoginCount(Integer loginCount) {
        this.setLoginCount(loginCount);
        return this;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getAttendCount() {
        return attendCount;
    }

    public UmsMemberStatisticsInfo withAttendCount(Integer attendCount) {
        this.setAttendCount(attendCount);
        return this;
    }

    public void setAttendCount(Integer attendCount) {
        this.attendCount = attendCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public UmsMemberStatisticsInfo withFansCount(Integer fansCount) {
        this.setFansCount(fansCount);
        return this;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Integer getCollectProductCount() {
        return collectProductCount;
    }

    public UmsMemberStatisticsInfo withCollectProductCount(Integer collectProductCount) {
        this.setCollectProductCount(collectProductCount);
        return this;
    }

    public void setCollectProductCount(Integer collectProductCount) {
        this.collectProductCount = collectProductCount;
    }

    public Integer getCollectSubjectCount() {
        return collectSubjectCount;
    }

    public UmsMemberStatisticsInfo withCollectSubjectCount(Integer collectSubjectCount) {
        this.setCollectSubjectCount(collectSubjectCount);
        return this;
    }

    public void setCollectSubjectCount(Integer collectSubjectCount) {
        this.collectSubjectCount = collectSubjectCount;
    }

    public Integer getCollectTopicCount() {
        return collectTopicCount;
    }

    public UmsMemberStatisticsInfo withCollectTopicCount(Integer collectTopicCount) {
        this.setCollectTopicCount(collectTopicCount);
        return this;
    }

    public void setCollectTopicCount(Integer collectTopicCount) {
        this.collectTopicCount = collectTopicCount;
    }

    public Integer getCollectCommentCount() {
        return collectCommentCount;
    }

    public UmsMemberStatisticsInfo withCollectCommentCount(Integer collectCommentCount) {
        this.setCollectCommentCount(collectCommentCount);
        return this;
    }

    public void setCollectCommentCount(Integer collectCommentCount) {
        this.collectCommentCount = collectCommentCount;
    }

    public Integer getInviteFriendCount() {
        return inviteFriendCount;
    }

    public UmsMemberStatisticsInfo withInviteFriendCount(Integer inviteFriendCount) {
        this.setInviteFriendCount(inviteFriendCount);
        return this;
    }

    public void setInviteFriendCount(Integer inviteFriendCount) {
        this.inviteFriendCount = inviteFriendCount;
    }

    public LocalDateTime getRecentOrderTime() {
        return recentOrderTime;
    }

    public UmsMemberStatisticsInfo withRecentOrderTime(LocalDateTime recentOrderTime) {
        this.setRecentOrderTime(recentOrderTime);
        return this;
    }

    public void setRecentOrderTime(LocalDateTime recentOrderTime) {
        this.recentOrderTime = recentOrderTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", consumeAmount=").append(consumeAmount);
        sb.append(", orderCount=").append(orderCount);
        sb.append(", couponCount=").append(couponCount);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", returnOrderCount=").append(returnOrderCount);
        sb.append(", loginCount=").append(loginCount);
        sb.append(", attendCount=").append(attendCount);
        sb.append(", fansCount=").append(fansCount);
        sb.append(", collectProductCount=").append(collectProductCount);
        sb.append(", collectSubjectCount=").append(collectSubjectCount);
        sb.append(", collectTopicCount=").append(collectTopicCount);
        sb.append(", collectCommentCount=").append(collectCommentCount);
        sb.append(", inviteFriendCount=").append(inviteFriendCount);
        sb.append(", recentOrderTime=").append(recentOrderTime);
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
        UmsMemberStatisticsInfo other = (UmsMemberStatisticsInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getConsumeAmount() == null ? other.getConsumeAmount() == null : this.getConsumeAmount().equals(other.getConsumeAmount()))
            && (this.getOrderCount() == null ? other.getOrderCount() == null : this.getOrderCount().equals(other.getOrderCount()))
            && (this.getCouponCount() == null ? other.getCouponCount() == null : this.getCouponCount().equals(other.getCouponCount()))
            && (this.getCommentCount() == null ? other.getCommentCount() == null : this.getCommentCount().equals(other.getCommentCount()))
            && (this.getReturnOrderCount() == null ? other.getReturnOrderCount() == null : this.getReturnOrderCount().equals(other.getReturnOrderCount()))
            && (this.getLoginCount() == null ? other.getLoginCount() == null : this.getLoginCount().equals(other.getLoginCount()))
            && (this.getAttendCount() == null ? other.getAttendCount() == null : this.getAttendCount().equals(other.getAttendCount()))
            && (this.getFansCount() == null ? other.getFansCount() == null : this.getFansCount().equals(other.getFansCount()))
            && (this.getCollectProductCount() == null ? other.getCollectProductCount() == null : this.getCollectProductCount().equals(other.getCollectProductCount()))
            && (this.getCollectSubjectCount() == null ? other.getCollectSubjectCount() == null : this.getCollectSubjectCount().equals(other.getCollectSubjectCount()))
            && (this.getCollectTopicCount() == null ? other.getCollectTopicCount() == null : this.getCollectTopicCount().equals(other.getCollectTopicCount()))
            && (this.getCollectCommentCount() == null ? other.getCollectCommentCount() == null : this.getCollectCommentCount().equals(other.getCollectCommentCount()))
            && (this.getInviteFriendCount() == null ? other.getInviteFriendCount() == null : this.getInviteFriendCount().equals(other.getInviteFriendCount()))
            && (this.getRecentOrderTime() == null ? other.getRecentOrderTime() == null : this.getRecentOrderTime().equals(other.getRecentOrderTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getConsumeAmount() == null) ? 0 : getConsumeAmount().hashCode());
        result = prime * result + ((getOrderCount() == null) ? 0 : getOrderCount().hashCode());
        result = prime * result + ((getCouponCount() == null) ? 0 : getCouponCount().hashCode());
        result = prime * result + ((getCommentCount() == null) ? 0 : getCommentCount().hashCode());
        result = prime * result + ((getReturnOrderCount() == null) ? 0 : getReturnOrderCount().hashCode());
        result = prime * result + ((getLoginCount() == null) ? 0 : getLoginCount().hashCode());
        result = prime * result + ((getAttendCount() == null) ? 0 : getAttendCount().hashCode());
        result = prime * result + ((getFansCount() == null) ? 0 : getFansCount().hashCode());
        result = prime * result + ((getCollectProductCount() == null) ? 0 : getCollectProductCount().hashCode());
        result = prime * result + ((getCollectSubjectCount() == null) ? 0 : getCollectSubjectCount().hashCode());
        result = prime * result + ((getCollectTopicCount() == null) ? 0 : getCollectTopicCount().hashCode());
        result = prime * result + ((getCollectCommentCount() == null) ? 0 : getCollectCommentCount().hashCode());
        result = prime * result + ((getInviteFriendCount() == null) ? 0 : getInviteFriendCount().hashCode());
        result = prime * result + ((getRecentOrderTime() == null) ? 0 : getRecentOrderTime().hashCode());
        return result;
    }
}