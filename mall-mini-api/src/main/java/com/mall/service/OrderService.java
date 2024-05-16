/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.PayKind;
import com.mall.domain.CartOrder;
import com.mall.domain.ConfirmOrder;
import com.mall.domain.OrderDetail;
import com.mall.domain.OrderParam;
import com.mall.model.OmsOrder;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    ConfirmOrder generateConfirmOrder(List<Long> cartIds);

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CartOrder generateOrder(OrderParam orderParam);

    @Transactional
    boolean paySuccess(long orderId, PayKind kind, String transactionId);

    @Transactional
    Integer cancelTimeoutOrder();

    @Transactional
    void cancelOrder(Long orderId);

    void cancelAsync(long orderId);

    List<OrderDetail> list(Integer status, int pageIndex, int pageSize);

    OrderDetail detail(long orderId);

    Optional<OmsOrder> get(String orderSn);

    void confirmOrderReceived(long orderId);

    void delete(long orderId);
}