/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.model.PmsBrand;
import com.mall.model.PmsProduct;
import com.mall.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "前台品牌管理")
@RestController
@RequestMapping(value = "/api/brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Operation(summary = "分页获取推荐品牌")
    @GetMapping(value = "/recommend-list")
    public List<PmsBrand> recommendList(@RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                                        @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return brandService.recommendList(pageIndex, pageSize);
    }

    @Operation(summary = "获取品牌详情")
    @GetMapping(value = "/detail/{id}")
    public PmsBrand detail(@PathVariable long id) {
        return brandService.detail(id);
    }

    @Operation(summary = "分页获取品牌相关商品")
    @GetMapping(value = "/product-list")
    public List<PmsProduct> productList(@RequestParam Long brandId,
                                        @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                                        @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return brandService.productList(brandId, pageIndex, pageSize);
    }
}