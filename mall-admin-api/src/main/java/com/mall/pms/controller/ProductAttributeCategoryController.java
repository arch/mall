/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.controller;

import com.mall.model.PmsProductAttributeCategory;
import com.mall.pms.dto.ProductAttributeCategoryDto;
import com.mall.pms.service.ProductAttributeCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "商品属性分类管理")
@RestController
@RequestMapping(value = "/api/pms/product/attribute/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductAttributeCategoryController {
    private final ProductAttributeCategoryService productAttributeCategoryService;

    public ProductAttributeCategoryController(ProductAttributeCategoryService productAttributeCategoryService) {
        this.productAttributeCategoryService = productAttributeCategoryService;
    }

    @Operation(summary = "添加商品属性分类", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping
    public void create(@RequestParam String name) {
        productAttributeCategoryService.create(name);
    }

    @Operation(summary = "修改商品属性分类", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/{id:[0-9]+}")
    public void update(@PathVariable Long id, @RequestParam String name) {
        productAttributeCategoryService.update(id, name);
    }

    @Operation(summary = "删除单个商品属性分类", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/{id:[0-9]+}")
    public void delete(@PathVariable Long id) {
        productAttributeCategoryService.delete(id);
    }

    @Operation(summary = "获取单个商品属性分类信息", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/{id:[0-9]+}")
    public PmsProductAttributeCategory get(@PathVariable Long id) {
        return productAttributeCategoryService.get(id);
    }

    @Operation(summary = "分页获取所有商品属性分类", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping
    public List<PmsProductAttributeCategory> getList(@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                                                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return productAttributeCategoryService.getList(pageIndex, pageSize);
    }

    @Operation(summary = "分页获取所有商品属性分类", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/list-with-attributes")
    public List<ProductAttributeCategoryDto> getList() {
        return productAttributeCategoryService.listWithAttributes();
    }
}