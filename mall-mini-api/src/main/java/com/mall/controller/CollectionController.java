/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.domain.ProductCollection;
import com.mall.service.CollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "会员收藏管理")
@RestController
@RequestMapping(value = "/api/collection", produces = MediaType.APPLICATION_JSON_VALUE)
public class CollectionController {
    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Operation(summary = "添加商品收藏", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping(value = "/add")
    public void add(ProductCollection productCollection) {
        collectionService.add(productCollection);
    }

    @Operation(summary = "删除收藏商品", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/delete/{productId}")
    public void delete(@PathVariable long productId) {
        collectionService.delete(productId);
    }

    @Operation(summary = "显示收藏列表", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/list")
    public List<ProductCollection> list(@RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                                        @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return collectionService.list(pageIndex, pageSize);
    }

    @Operation(summary = "显示收藏商品详情", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/{productId}")
    public ProductCollection get(@PathVariable long productId) {
        return collectionService.get(productId);
    }

    @Operation(summary = "清空收藏列表", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/clear")
    public void clear() {
        collectionService.clear();
    }

}