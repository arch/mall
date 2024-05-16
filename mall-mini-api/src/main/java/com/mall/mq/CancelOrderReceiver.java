/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mq;

import com.mall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的消费者
 * Created by macro on 2018/9/14.
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {
    private static final Logger logger = LoggerFactory.getLogger(CancelOrderReceiver.class);

    private final OrderService orderService;

    public CancelOrderReceiver(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitHandler
    public void handle(Long orderId){
        orderService.cancelOrder(orderId);
        logger.info("process orderId:{}",orderId);
    }
}