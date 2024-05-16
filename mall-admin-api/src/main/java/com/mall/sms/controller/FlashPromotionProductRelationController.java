/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.controller;

import com.mall.model.SmsFlashPromotionProductRelation;
import com.mall.sms.dto.FlashPromotionProduct;
import com.mall.sms.service.FlashPromotionProductRelationService;
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

@Tag(name = "限时购和商品关系管理")
@RestController
@RequestMapping(value = "/api/sms/flash/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class FlashPromotionProductRelationController {

    private final FlashPromotionProductRelationService relationService;

    public FlashPromotionProductRelationController(
            FlashPromotionProductRelationService relationService) {
        this.relationService = relationService;
    }

    @Operation(summary = "批量选择商品添加关联", security = {@SecurityRequirement(name = "jwtScheme")})
    @PostMapping
    public void create(@RequestBody List<SmsFlashPromotionProductRelation> relations) {
        relationService.create(relations);
    }

    @Operation(summary = "修改关联相关信息", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/{id}")
    public void update(@PathVariable Long id, @RequestBody SmsFlashPromotionProductRelation relation) {
        relationService.update(id, relation);
    }

    @Operation(summary = "删除关联")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        relationService.delete(id);
    }

    @Operation(summary = "获取管理商品促销信息", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/{id}")
    public SmsFlashPromotionProductRelation get(@PathVariable Long id) {
        return relationService.get(id);
    }

    @Operation(summary = "分页查询不同场次关联及商品信息", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping
    public List<FlashPromotionProduct> get(
            @RequestParam(value = "flashPromotionId") Long flashPromotionId,
            @RequestParam(value = "flashPromotionSessionId") Long flashPromotionSessionId,
            @RequestParam(value = "pageIndex", defaultValue = "0", required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return relationService.list(flashPromotionId, flashPromotionSessionId, pageIndex, pageSize);
    }
}