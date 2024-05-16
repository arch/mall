/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.controller;

import com.mall.oms.dto.OrderReturnApplyResult;
import com.mall.oms.dto.ReturnApplyQueryParam;
import com.mall.oms.dto.UpdateStatusParam;
import com.mall.express.response.QueryTrackData;
import com.mall.model.OmsOrderReturnApply;
import com.mall.model.OmsOrderReturnReason;
import com.mall.oms.service.ExpressService;
import com.mall.oms.service.OrderReturnService;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "订单退货管理")
@RestController
@RequestMapping(value = "/api/oms/order/return", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderReturnController {

    private final OrderReturnService returnService;
    private final ExpressService expressService;
    public OrderReturnController(OrderReturnService returnService, ExpressService expressService) {
        this.returnService = returnService;
        this.expressService = expressService;
    }

    @Operation(summary = "分页查询退货申请", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/apply")
    public List<OmsOrderReturnApply> get(
            ReturnApplyQueryParam queryParam,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return returnService.listApply(queryParam, pageIndex, pageSize);
    }

    @Operation(summary = "获取物流信息", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/express/{id:[0-9]+}")
    public List<QueryTrackData> get(@PathVariable("id") long id) {
        return expressService.queryReturnExpress(id);
    }

    @Operation(summary = "批量删除申请", security = {@SecurityRequirement(name = "jwtScheme")})
    @DeleteMapping(value = "/apply")
    public void delete(@RequestParam("ids") List<Long> ids) {
        returnService.deleteApply(ids);
    }

    @Operation(summary = "获取退货申请详情", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/apply/{id}")
    public OrderReturnApplyResult getApply(@PathVariable Long id) {
        return returnService.getApply(id);
    }

    @Operation(summary = "修改申请状态", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/apply/update-status/{id}")
    public void updateApplyStatus(@PathVariable Long id, @RequestBody UpdateStatusParam updateStatusParam) {
        returnService.updateApplyStatus(id, updateStatusParam);
    }

    @Operation(summary = "添加退货原因")
    @PostMapping(value = "/reason")
    public void createReason(@RequestBody OmsOrderReturnReason returnReason) {
        returnService.createReason(returnReason);
    }

    @Operation(summary = "修改退货原因")
    @PutMapping(value = "/reason/{id}")
    public void updateReason(@PathVariable Long id, @RequestBody OmsOrderReturnReason returnReason) {
        returnService.updateReason(id, returnReason);
    }

    @Operation(summary = "批量删除退货原因")
    @DeleteMapping(value = "/reason")
    public void deleteReason(@RequestParam("ids") List<Long> ids) {
        returnService.deleteReason(ids);
    }

    @Operation(summary = "分页查询全部退货原因")
    @GetMapping(value = "/reason")
    public List<OmsOrderReturnReason> get(
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return returnService.listReason(pageIndex, pageSize);
    }

    @Operation(summary = "获取单个退货原因详情信息")
    @RequestMapping(value = "/reason/{id}", method = RequestMethod.GET)
    public OmsOrderReturnReason getReason(@PathVariable Long id) {
        return returnService.getReason(id);
    }

    @Operation(summary = "修改退货原因启用状态")
    @RequestMapping(value = "/reason/update-status", method = RequestMethod.POST)
    public void updateReasonStatus(@RequestParam(value = "status") Integer status,
            @RequestParam("ids") List<Long> ids) {
        returnService.updateReasonStatus(ids, status);
    }
}