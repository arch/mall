/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.core;

import com.mall.mq.CancelOrderReceiver;
import com.mall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class Scheduler {
    private static final Logger logger = LoggerFactory.getLogger(CancelOrderReceiver.class);

    private final OrderService orderService;

    public Scheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单
     */
    @Scheduled(cron = "${scheduler.cancel-timeout-order-cron:0 0/10 * ? * ?}")
    private void cancelTimeOutOrder(){
        Integer count = orderService.cancelTimeoutOrder();
        logger.info("取消订单，并根据sku编号释放锁定库存，取消订单数量：{}",count);
    }
}