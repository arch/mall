/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.controller;

import com.mall.model.SmsHomeRecommendProduct;
import com.mall.sms.service.HomeRecommendProductService;
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

@Tag(name = "首页人气推荐管理")
@RestController
@RequestMapping(value = "/api/sms/home/recommend-product", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeRecommendProductController {

    private final HomeRecommendProductService recommendProductService;

    public HomeRecommendProductController(
            HomeRecommendProductService recommendProductService) {
        this.recommendProductService = recommendProductService;
    }

    @Operation(summary = "添加首页推荐", security = {@SecurityRequirement(name = "jwtScheme")})
    @PostMapping
    public void create(@RequestBody List<SmsHomeRecommendProduct> recommendProducts) {
        recommendProductService.create(recommendProducts);
    }

    @Operation(summary = "修改推荐排序", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/update-sort/{id}")
    public void updateSort(@PathVariable Long id, Integer sort) {
        recommendProductService.updateSort(id, sort);
    }

    @Operation(summary = "批量删除推荐", security = {@SecurityRequirement(name = "jwtScheme")})
    @DeleteMapping
    public void delete(@RequestParam("ids") List<Long> ids) {
        recommendProductService.delete(ids);
    }

    @Operation(summary = "批量修改推荐状态", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/update-recommend-status")
    public void updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        recommendProductService.updateRecommendStatus(ids, recommendStatus);
    }

    @Operation(summary = "分页查询推荐", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping
    public List<SmsHomeRecommendProduct> get(
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
            @RequestParam(value = "pageIndex", defaultValue = "0", required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return recommendProductService.list(productName, recommendStatus, pageIndex, pageSize);
    }
}