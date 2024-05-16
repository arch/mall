/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.controller;

import com.mall.model.PmsProductCategory;
import com.mall.pms.dto.ProductCategoryDto;
import com.mall.pms.dto.ProductCategoryTreeDto;
import com.mall.pms.service.ProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "商品分类管理")
@RestController
@RequestMapping(value = "/api/pms/product/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @Operation(summary = "添加产品分类", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping
    public void create(@RequestBody @Valid ProductCategoryDto productCategoryDto) {
        productCategoryService.create(productCategoryDto);
    }

    @Operation(summary = "修改商品分类", hidden = true, security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/{id:[0-9]+}")
    public void update(@PathVariable long id, @RequestBody @Valid ProductCategoryDto productCategoryDto) {
        productCategoryService.update(id, productCategoryDto);
    }

    @Operation(summary = "分页查询商品分类", description = "通过上级分类编号分页查询商品分类", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/list/{parentId:[0-9]+}")
    public List<PmsProductCategory> getList(@PathVariable long parentId,
                                            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return productCategoryService.getList(parentId, pageIndex, pageSize);
    }

    @Operation(summary = "查询所有一级分类及子分类", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/list-with-children")
    public List<ProductCategoryTreeDto> get() {
        return productCategoryService.listWithChildren();
    }

    @Operation(summary = "根据id获取商品分类", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/{id:[0-9]+}")
    public PmsProductCategory get(@PathVariable long id) {
        return productCategoryService.getOne(id);
    }

    @Operation(summary = "删除商品分类", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/{id:[0-9]+}")
    public void delete(@PathVariable long id) {
        productCategoryService.delete(id);
    }

    @Operation(summary = "修改导航栏显示状态", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update-nav-status")
    public void updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        productCategoryService.updateNavStatus(ids, navStatus);
    }

    @Operation(summary = "修改显示状态", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update-show-status")
    public void updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        productCategoryService.updateShowStatus(ids, showStatus);
    }
}