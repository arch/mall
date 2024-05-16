/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.wechat.pay.NotifyResult;
import com.mall.wechat.pay.Payment;
import javax.servlet.http.HttpServletRequest;

public interface WxPayService {
    Payment getPayment(long orderId);

    NotifyResult payNotify(HttpServletRequest request);
}