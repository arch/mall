/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import io.swagger.v3.oas.annotations.media.Schema;

public class CommentParam {
    private long productId;
    @Schema(description = "评价星数：0->5")
    private int star;
    @Schema(description = "上传图片地址，以逗号隔开")
    private String pics;
    @Schema(description = "评论内容")
    private String content;
    @Schema(description = "评价的ip")
    private String memberIp;
    private String productAttribute;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMemberIp() {
        return memberIp;
    }

    public void setMemberIp(String memberIp) {
        this.memberIp = memberIp;
    }

    public String getProductAttribute() {
        return productAttribute;
    }

    public void setProductAttribute(String productAttribute) {
        this.productAttribute = productAttribute;
    }
}