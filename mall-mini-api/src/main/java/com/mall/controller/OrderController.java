/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.express.response.QueryTrackData;
import com.mall.domain.CartOrder;
import com.mall.domain.ConfirmOrder;
import com.mall.domain.OrderDetail;
import com.mall.domain.OrderParam;
import com.mall.service.ExpressService;
import com.mall.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "订单管理")
@RestController
@RequestMapping(value = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @Operation(summary = "根据购物车信息生成确认单信息", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping(value = "/generate-confirm-order")
    public ConfirmOrder generateConfirmOrder(@RequestBody List<Long> cartIds) {
        return orderService.generateConfirmOrder(cartIds);
    }

    @Operation(summary = "根据购物车信息生成订单", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping(value = "/generate-order")
    public CartOrder generatorOrder(@RequestBody OrderParam orderParam) {
        return orderService.generateOrder(orderParam);
    }

    @Operation(summary = "按状态分页获取用户订单列表", security = { @SecurityRequirement(name = "jwtScheme") })
    @Parameters({
            @Parameter(
                    name = "status",
                    description = "订单状态：-1->全部；0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭",
                    required = true,
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = int.class))})
    @GetMapping(value = "/list")
    public List<OrderDetail> list(@RequestParam(value = "status", required = false, defaultValue = "-1") Integer status,
                                  @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {
        return orderService.list(status, pageIndex, pageSize);
    }

    @Operation(summary = "根据ID获取订单详情", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/detail/{orderId:[0-9]+}")
    public OrderDetail detail(@PathVariable("orderId") long orderId) {
        return orderService.detail(orderId);
    }

    @Operation(summary = "用户取消订单", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping(value = "/cancel/{orderId:[0-9]+}")
    public void cancelOrder(@PathVariable long orderId) {
        orderService.cancelOrder(orderId);
    }

    @Operation(summary = "用户确认收货", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping(value = "/confirm-order-received")
    public void confirmOrderReceived(@RequestParam long orderId) {
        orderService.confirmOrderReceived(orderId);
    }

    @Operation(summary = "用户删除订单", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/delete/{orderId:[0-9]+}")
    public void delete(@PathVariable("orderId") long orderId) {
        orderService.delete(orderId);
    }
}