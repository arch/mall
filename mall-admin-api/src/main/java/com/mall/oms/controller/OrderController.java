/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.controller;

import com.mall.oms.dto.OrderDetail;
import com.mall.oms.dto.OrderQueryParam;
import com.mall.oms.dto.ReceiverParam;
import com.mall.express.response.QueryTrackData;
import com.mall.model.OmsOrder;
import com.mall.oms.dto.MoneyParam;
import com.mall.oms.dto.OrderDeliveryParam;
import com.mall.oms.service.ExpressService;
import com.mall.oms.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "订单管理")
@RestController
@RequestMapping(value = "/api/oms/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private final OrderService orderService;
    private final ExpressService expressService;

    public OrderController(OrderService orderService, ExpressService expressService) {
        this.orderService = orderService;
        this.expressService = expressService;
    }

    @Operation(summary = "获取物流信息", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/express/{orderId:[0-9]+}")
    public List<QueryTrackData> get(@PathVariable("orderId") long orderId) {
        return expressService.queryExpress(orderId);
    }

    @Operation(summary = "查询订单", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping
    public List<OmsOrder> list(OrderQueryParam queryParam,
                               @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return orderService.list(queryParam, pageIndex, pageSize);
    }

    @Operation(summary = "批量发货", security = {@SecurityRequirement(name = "jwtScheme")})
    @PostMapping(value = "/delivery")
    public void delivery(@RequestBody List<OrderDeliveryParam> deliveryParams) {
        orderService.delivery(deliveryParams);
    }

    @Operation(summary = "批量关闭订单", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/close")
    public void close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        orderService.close(ids, note);
    }

    @Operation(summary = "批量删除订单", security = {@SecurityRequirement(name = "jwtScheme")})
    @DeleteMapping
    public void delete(@RequestParam("ids") List<Long> ids) {
        orderService.delete(ids);
    }

    @Operation(summary = "获取订单详情:订单信息、商品信息、操作记录", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/{id}")
    public OrderDetail get(@PathVariable Long id) {
        return orderService.detail(id);
    }

    @Operation(summary = "修改收货人信息", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/update-receiver")
    public void updateReceiver(@RequestBody ReceiverParam receiverParam) {
        orderService.updateReceiver(receiverParam);
    }

    @Operation(summary = "修改订单费用信息", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/update-money")
    public void updateMoney(@RequestBody MoneyParam moneyParam) {
        orderService.updateMoney(moneyParam);
    }

    @Operation(summary = "备注订单", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/update-note")
    public void updateNote(@RequestParam("id") Long id, @RequestParam("note") String note, @RequestParam("status") Integer status) {
        orderService.updateNote(id, note, status);
    }
}