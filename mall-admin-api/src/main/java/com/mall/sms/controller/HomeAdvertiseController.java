/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.controller;

import com.mall.model.SmsHomeAdvertise;
import com.mall.sms.service.HomeAdvertiseService;
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

@Tag(name = "首页轮播广告管理")
@RestController
@RequestMapping(value = "/api/sms/home/advertise", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeAdvertiseController {

    private final HomeAdvertiseService advertiseService;

    public HomeAdvertiseController(HomeAdvertiseService advertiseService) {
        this.advertiseService = advertiseService;
    }

    @Operation(summary = "获取广告详情", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/{id}")
    public SmsHomeAdvertise get(@PathVariable Long id) {
        return advertiseService.get(id);
    }

    @Operation(summary = "分页查询广告", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping
    public List<SmsHomeAdvertise> get(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "pageIndex", defaultValue = "0", required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return advertiseService.list(name, type, endDate, pageIndex, pageSize);
    }

    @Operation(summary = "添加广告", security = {@SecurityRequirement(name = "jwtScheme")})
    @PostMapping
    public void create(@RequestBody SmsHomeAdvertise advertise) {
        advertiseService.create(advertise);
    }

    @Operation(summary = "修改广告", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/{id:[0-9]+}")
    public void update(@PathVariable("id") long id, @RequestBody SmsHomeAdvertise advertise) {
        advertiseService.update(id, advertise);
    }

    @Operation(summary = "修改上下线状态", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/update-status/{id:[0-9]+}")
    public void updateStatus(@PathVariable("id") long id, Integer status) {
        advertiseService.updateStatus(id, status);
    }

    @Operation(summary = "删除广告", security = {@SecurityRequirement(name = "jwtScheme")})
    @DeleteMapping
    public void delete(@RequestParam("ids") List<Long> ids) {
        advertiseService.delete(ids);
    }
}