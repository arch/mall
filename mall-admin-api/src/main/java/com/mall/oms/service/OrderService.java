/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.service;

import com.mall.oms.dto.OrderDetail;
import com.mall.oms.dto.OrderQueryParam;
import com.mall.oms.dto.ReceiverParam;
import com.mall.model.OmsOrder;
import com.mall.oms.dto.MoneyParam;
import com.mall.oms.dto.OrderDeliveryParam;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {

    List<OmsOrder> list(OrderQueryParam queryParam, int pageIndex, int pageSize);

    @Transactional
    void delivery(List<OrderDeliveryParam> deliveryParams);

    @Transactional
    void close(List<Long> ids, String note);

    void delete(List<Long> ids);

    OrderDetail detail(Long id);

    @Transactional
    void updateReceiver(ReceiverParam receiverParam);

    @Transactional
    void updateMoney(MoneyParam moneyParam);

    @Transactional
    void updateNote(Long id, String note, Integer status);
}