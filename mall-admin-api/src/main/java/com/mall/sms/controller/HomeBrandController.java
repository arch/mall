/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.controller;

import com.mall.model.SmsHomeBrand;
import com.mall.sms.service.HomeBrandService;
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

@Tag(name = "首页品牌管理")
@RestController
@RequestMapping(value = "/api/sms/home/brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeBrandController {

    private final HomeBrandService homeBrandService;

    public HomeBrandController(HomeBrandService homeBrandService) {
        this.homeBrandService = homeBrandService;
    }

    @Operation(summary = "分页查询推荐品牌", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping
    public List<SmsHomeBrand> get(
            @RequestParam(value = "brandName", required = false) String brandName,
            @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
            @RequestParam(value = "pageIndex", defaultValue = "0", required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return homeBrandService.list(brandName, recommendStatus, pageIndex, pageSize);
    }

    @Operation(summary = "添加首页推荐品牌", security = {@SecurityRequirement(name = "jwtScheme")})
    @PostMapping
    public void create(@RequestBody List<SmsHomeBrand> homeBrands) {
        homeBrandService.create(homeBrands);
    }

    @Operation(summary = "修改品牌排序", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/update-sort/{id:[0-9]+}")
    public void updateSort(@PathVariable("id") long id, Integer sort) {
        homeBrandService.updateSort(id, sort);
    }

    @Operation(summary = "批量修改推荐状态", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/update-recommend-status")
    public void updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        homeBrandService.updateRecommendStatus(ids, recommendStatus);
    }

    @Operation(summary = "批量删除推荐品牌", security = {@SecurityRequirement(name = "jwtScheme")})
    @DeleteMapping
    public void delete(@RequestParam("ids") List<Long> ids) {
        homeBrandService.delete(ids);
    }
}