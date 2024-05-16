/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.pay;

import com.mall.crosscut.util.Assert;
import org.springframework.util.StringUtils;

public class Order {
    private String appid;
    private String mchid;
    private String description;
    private String outTradeNo;
    private String timeExpire;
    private String attach;
    private String notifyUrl;
    private String goodsTag;
    private Amount amount;
    private Payer payer;

    public static Order notifyURL(String notifyUrl) {
        Assert.ensure(StringUtils.hasText(notifyUrl), "必须提供notify URL");
        Assert.ensure(notifyUrl.startsWith("https"), "通知地址必须是https");
        Order order = new Order();
        order.setNotifyUrl(notifyUrl);
        return order;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && description.length() > 127) {
            this.description = description.substring(0, 126);
        } else {
            this.description = description;
        }
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }
}