/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.domain.ProductCategory;
import com.mall.domain.ProductDetail;
import com.mall.model.PmsProduct;
import com.mall.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "前台商品管理")
@RestController
@RequestMapping(value = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "综合搜索、筛选、排序")
    @Parameters({
            @Parameter(
                    name = "sort",
                    description = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低",
                    required = true,
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = int.class))})
    @GetMapping(value = "/search")
    public List<PmsProduct> search(@RequestParam(required = false) String keyword,
                                   @RequestParam(required = false) Long brandId,
                                   @RequestParam(required = false) Long productCategoryId,
                                   @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                                   @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                   @RequestParam(required = false, defaultValue = "0") Integer sort) {
        return productService.search(keyword, brandId, productCategoryId, pageIndex, pageSize, sort);
    }

    @Operation(summary = "以树形结构获取所有商品分类")
    @GetMapping(value = "/category-tree")
    public List<ProductCategory> categoryTree() {
        return productService.categoryTree();
    }

    @Operation(summary = "获取前台商品详情")
    @GetMapping(value = "/detail/{id:[0-9]+}")
    public ProductDetail detail(@PathVariable("id") long id) {
        return productService.detail(id);
    }
}