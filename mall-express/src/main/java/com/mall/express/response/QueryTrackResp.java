/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.express.response;

import java.util.List;

public class QueryTrackResp {
    private static final int SUCCESS_CODE = 200;
    /**
     * 消息体，请忽略
     */
    private String message;
    /**
     * 快递单当前状态，包括0在途，1揽收，2疑难，3签收，4退签，5派件，6退回，7转投，10待清关，11清关中，12已清关，13清关异常，14拒签 等13个状态
     */
    private int state;
    /**
     * 通讯状态
     */
    private int status;

    /**
     * 快递单明细状态标记
     */
    private String condition;
    /**
     * 是否签收标记
     */
    private int ischeck;
    /**
     * 	快递公司编码,一律用小写字母
     */
    private String com;
    /**
     * 快递单号
     */
    private String nu;

    /**
     * 轨迹详情数组
     */
    private List<QueryTrackData> data;

    /**
     * 查不到轨迹或者其他问题返回码
     */
    private String returnCode;
    /**
     * 查不到轨迹或者其他问题返回结果
     */
    private boolean result;

    public boolean isSuccess() {
        return status == SUCCESS_CODE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getIscheck() {
        return ischeck;
    }

    public void setIscheck(int ischeck) {
        this.ischeck = ischeck;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public List<QueryTrackData> getData() {
        return data;
    }

    public void setData(List<QueryTrackData> data) {
        this.data = data;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}