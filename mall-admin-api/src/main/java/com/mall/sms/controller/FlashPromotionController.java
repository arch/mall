/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.controller;

import com.mall.model.SmsFlashPromotion;
import com.mall.sms.service.FlashPromotionService;
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

@Tag(name = "限时购活动管理")
@RestController
@RequestMapping(value = "/api/sms/flash", produces = MediaType.APPLICATION_JSON_VALUE)
public class FlashPromotionController {
    private final FlashPromotionService flashPromotionService;

    public FlashPromotionController(
            FlashPromotionService flashPromotionService) {
        this.flashPromotionService = flashPromotionService;
    }

    @Operation(summary = "添加活动", security = {@SecurityRequirement(name = "jwtScheme")})
    @PostMapping
    public void create(@RequestBody SmsFlashPromotion flashPromotion) {
        flashPromotionService.create(flashPromotion);
    }

    @Operation(summary = "编辑活动信息", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/{id}")
    public void update(@PathVariable Long id, @RequestBody SmsFlashPromotion flashPromotion) {
        flashPromotionService.update(id, flashPromotion);
    }

    @Operation(summary = "删除活动信息", security = {@SecurityRequirement(name = "jwtScheme")})
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        flashPromotionService.delete(id);
    }

    @Operation(summary = "修改上下线状态", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/update-status/{id}")
    public void update(@PathVariable Long id, Integer status) {
        flashPromotionService.updateStatus(id, status);
    }

    @Operation(summary = "获取活动详情", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/{id}")
    public SmsFlashPromotion get(@PathVariable Long id) {
        return flashPromotionService.get(id);
    }

    @Operation(summary = "根据活动名称分页查询", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping
    public List<SmsFlashPromotion> get(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageIndex", defaultValue = "0", required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return flashPromotionService.list(keyword, pageIndex, pageSize);
    }
}