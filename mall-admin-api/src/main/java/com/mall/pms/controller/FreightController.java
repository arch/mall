/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.controller;

import com.mall.model.PmsFreightCompany;
import com.mall.pms.service.FreightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "快递公司管理")
@RestController
@RequestMapping(value = "/api/freight", produces = MediaType.APPLICATION_JSON_VALUE)
public class FreightController {
    private final FreightService freightService;

    public FreightController(FreightService freightService) {
        this.freightService = freightService;
    }

    @Operation(summary = "获取所有快递公司", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/company")
    public List<PmsFreightCompany> get(
            @RequestParam(value = "pageIndex", defaultValue = "0", required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize) {
        return freightService.list(pageIndex, pageSize);
    }

    @Operation(summary = "搜索快递公司", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/company/search")
    public List<PmsFreightCompany> search(@RequestParam String nameOrCode) {
        return freightService.search(nameOrCode);
    }
}