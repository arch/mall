/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.pay;

public class PayerAmount extends Amount {
    private long payerTotal;
    private String payerCurrency;

    public long getPayerTotal() {
        return payerTotal;
    }

    public void setPayerTotal(long payerTotal) {
        this.payerTotal = payerTotal;
    }

    public String getPayerCurrency() {
        return payerCurrency;
    }

    public void setPayerCurrency(String payerCurrency) {
        this.payerCurrency = payerCurrency;
    }
}