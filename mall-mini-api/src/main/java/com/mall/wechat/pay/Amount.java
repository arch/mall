/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.wechat.pay;

public class Amount {
    private long total;
    private String currency;

    public static Amount CNY(long total) {
        Amount amount = new Amount();
        amount.setTotal(total);
        amount.setCurrency("CNY");
        return amount;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}