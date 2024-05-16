/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.controller;

import com.mall.model.SmsFlashPromotionSession;
import com.mall.sms.dto.FlashPromotionSession;
import com.mall.sms.service.FlashPromotionSessionService;
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
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "限时购场次管理")
@RestController
@RequestMapping(value = "/api/sms/flash/session", produces = MediaType.APPLICATION_JSON_VALUE)
public class FlashPromotionSessionController {

    private final FlashPromotionSessionService flashPromotionSessionService;

    public FlashPromotionSessionController(
            FlashPromotionSessionService flashPromotionSessionService) {
        this.flashPromotionSessionService = flashPromotionSessionService;
    }

    @Operation(summary = "添加场次", security = {@SecurityRequirement(name = "jwtScheme")})
    @PostMapping
    public void create(@RequestBody SmsFlashPromotionSession promotionSession) {
        flashPromotionSessionService.create(promotionSession);
    }

    @Operation(summary = "修改场次", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/{id}")
    public void update(@PathVariable Long id, @RequestBody SmsFlashPromotionSession promotionSession) {
        flashPromotionSessionService.update(id, promotionSession);
    }

    @Operation(summary = "修改启用状态", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/update-status/{id}")
    public void updateStatus(@PathVariable Long id, Integer status) {
        flashPromotionSessionService.updateStatus(id, status);
    }

    @Operation(summary = "删除场次", security = {@SecurityRequirement(name = "jwtScheme")})
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        flashPromotionSessionService.delete(id);
    }

    @Operation(summary = "获取场次详情", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/{id}")
    public SmsFlashPromotionSession get(@PathVariable Long id) {
        return flashPromotionSessionService.get(id);
    }

    @Operation(summary = "获取全部场次", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping
    public List<SmsFlashPromotionSession> list() {
        return flashPromotionSessionService.list();
    }

    @Operation(summary = "获取全部可选场次及其数量", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/list")
    public List<FlashPromotionSession> list(Long flashPromotionId) {
        return flashPromotionSessionService.list(flashPromotionId);
    }
}