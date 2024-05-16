/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.express.response.QueryTrackData;
import com.mall.domain.ExpressParam;
import com.mall.domain.OrderReturnApplyParam;
import com.mall.domain.OrderReturnDetail;
import com.mall.model.OmsOrderReturnReason;
import com.mall.service.ExpressService;
import com.mall.service.OrderReturnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "申请退货管理")
@RestController
@RequestMapping(value = "/api/order/return", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderReturnController {

    private final OrderReturnService orderReturnService;
    private final ExpressService expressService;

    public OrderReturnController(OrderReturnService orderReturnService, ExpressService expressService) {
        this.orderReturnService = orderReturnService;
        this.expressService = expressService;
    }

    @Operation(summary = "售后详情", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/detail/{id}")
    public OrderReturnDetail get(@PathVariable long id) {
        return orderReturnService.detail(id);
    }

    @Operation(summary = "申请退货", security = {@SecurityRequirement(name = "jwtScheme")})
    @PostMapping(value = "/apply")
    public void create(@RequestBody @Valid OrderReturnApplyParam returnApplyParam) {
        orderReturnService.apply(returnApplyParam);
    }

    @Operation(summary = "设置退货运单", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/detail/{id}")
    public void put(@PathVariable long id, @RequestBody @Valid ExpressParam param) {
        orderReturnService.setReturnExpress(id, param);
    }

    @Operation(summary = "查询全部退货原因")
    @GetMapping(value = "/reason")
    public List<OmsOrderReturnReason> get() {
        return orderReturnService.getReasons();
    }

    @Operation(summary = "获取物流信息", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/express/{id:[0-9]+}")
    public List<QueryTrackData> queryExpress(@PathVariable("id") long id) {
        return expressService.queryReturnExpress(id);
    }
}