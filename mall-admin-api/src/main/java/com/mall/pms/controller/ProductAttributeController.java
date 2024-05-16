/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.controller;

import com.mall.model.PmsProductAttribute;
import com.mall.pms.dto.ProductAttribute;
import com.mall.pms.dto.ProductAttributeDto;
import com.mall.pms.service.ProductAttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
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

@Tag(name = "商品属性管理")
@RestController
@RequestMapping(value = "/api/pms/product/attribute", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductAttributeController {
    private final ProductAttributeService productAttributeService;

    public ProductAttributeController(ProductAttributeService productAttributeService) {
        this.productAttributeService = productAttributeService;
    }

    @Operation(summary = "根据分类查询属性列表或参数列表", security = { @SecurityRequirement(name = "jwtScheme") })
    @Parameters({
            @Parameter(
                    name = "type",
                    description = "0表示属性，1表示参数",
                    required = true,
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = int.class))})
    @GetMapping(value = "/of/category/{categoryId}")
    public List<PmsProductAttribute> getList(@PathVariable Long categoryId,
                                             @RequestParam(value = "type", required = false, defaultValue = "0") Integer type,
                                             @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return productAttributeService.getList(categoryId, type, pageIndex, pageSize);
    }

    @Operation(summary = "查询单个商品属性", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/{id:[0-9]+}")
    public PmsProductAttribute get(@PathVariable("id") long id) {
        return productAttributeService.get(id);
    }

    @Operation(summary = "根据商品分类的id获取商品属性及属性分类", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/of/product-category/{productCategoryId:[0-9]+}")
    public List<ProductAttribute> getOfProductCategory(@PathVariable("productCategoryId") long productCategoryId) {
        return productAttributeService.getAttributes(productCategoryId);
    }

    @Operation(summary = "添加商品属性信息", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping
    public void create(@RequestBody @Valid ProductAttributeDto productAttributeDto) {
        productAttributeService.create(productAttributeDto);
    }

    @Operation(summary = "修改商品属性信息", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/{id:[0-9]+}")
    public void update(@PathVariable Long id, @RequestBody @Valid ProductAttributeDto productAttributeDto) {
        productAttributeService.update(id, productAttributeDto);
    }

    @Operation(summary = "批量删除商品属性", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping
    public void delete(@RequestParam("ids") List<Long> ids) {
        productAttributeService.delete(ids);
    }
}