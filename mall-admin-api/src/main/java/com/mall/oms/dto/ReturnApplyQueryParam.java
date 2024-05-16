/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReturnApplyQueryParam {

    @Schema(description = "服务单号")
    private Long id;
    @Schema(description = "收货人姓名/号码")
    private String receiverKeyword;
    @Schema(description = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer status;
    @Schema(description = "申请时间")
    private String createTime;
    @Schema(description = "处理人员")
    private String handleMan;
    @Schema(description = "处理时间")
    private String handleTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiverKeyword() {
        return receiverKeyword;
    }

    public void setReceiverKeyword(String receiverKeyword) {
        this.receiverKeyword = receiverKeyword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHandleMan() {
        return handleMan;
    }

    public void setHandleMan(String handleMan) {
        this.handleMan = handleMan;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }
}