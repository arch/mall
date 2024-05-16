/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.domain.BrandAttention;
import com.mall.service.AttentionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "会员关注品牌管理")
@RestController
@RequestMapping(value = "/api/attention", produces = MediaType.APPLICATION_JSON_VALUE)
public class AttentionController {
    private final AttentionService attentionService;

    public AttentionController(AttentionService attentionService) {
        this.attentionService = attentionService;
    }

    @Operation(summary = "添加品牌关注", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping(value = "/add")
    public void add(BrandAttention brandAttention) {
        attentionService.add(brandAttention);
    }

    @Operation(summary = "取消关注", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/delete/{brandId:[0-9]+}")
    public void delete(@PathVariable long brandId) {
        attentionService.delete(brandId);
    }

    @Operation(summary = "显示关注列表", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/list")
    public List<BrandAttention> list(@RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                                     @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return attentionService.list(pageIndex, pageSize);
    }

    @Operation(summary = "显示关注品牌详情", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/{brandId:[0-9]+}")
    public BrandAttention get(@PathVariable long brandId) {
        return attentionService.get(brandId);
    }

    @Operation(summary = "清空关注列表", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/clear")
    public void clear() {
        attentionService.clear();
    }
}