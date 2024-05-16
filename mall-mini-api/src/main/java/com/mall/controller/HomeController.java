/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.domain.HomeContent;
import com.mall.model.CmsSubject;
import com.mall.model.PmsProduct;
import com.mall.model.PmsProductCategory;
import com.mall.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "首页内容管理")
@RestController
@RequestMapping(value = "/api/home", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {
    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @Operation(summary = "首页内容页信息展示")
    @GetMapping(value = "/content")
    public HomeContent content() {
        return homeService.content();
    }

    @Operation(summary = "分页获取推荐商品")
    @GetMapping(value = "/recommend-product-list")
    public List<PmsProduct> recommendProductList(@RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                                                 @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return homeService.recommendProductList(pageIndex, pageSize);
    }

    @Operation(summary = "获取首页商品分类")
    @GetMapping(value = "/product-category-list/{parentId}")
    public List<PmsProductCategory> getProductCateList(@PathVariable("parentId") long parentId) {
        return homeService.getProductCateList(parentId);
    }

    @Operation(summary = "根据分类获取专题")
    @GetMapping(value = "/subject-list/{parentId}")
    public List<CmsSubject> getSubjectList(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                           @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                                           @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return homeService.getSubjectList(categoryId, pageIndex, pageSize);
    }

    @Operation(summary = "分页获取人气推荐商品")
    @GetMapping(value = "/hot-product-list")
    public List<PmsProduct> hotProductList(@RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                                           @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return homeService.hotProductList(pageIndex, pageSize);
    }

    @Operation(summary = "分页获取新品推荐商品")
    @GetMapping(value = "/new-product-list")
    public List<PmsProduct> newProductList(@RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                                           @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return homeService.newProductList(pageIndex, pageSize);
    }
}