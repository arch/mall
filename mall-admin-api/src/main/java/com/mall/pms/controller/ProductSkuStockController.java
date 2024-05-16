/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.controller;

import com.mall.model.PmsSkuStock;
import com.mall.pms.service.ProductSkuStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "SKU商品库存管理")
@RestController
@RequestMapping(value = "/api/pms/product/sku", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductSkuStockController {
    private final ProductSkuStockService productSkuStockService;

    public ProductSkuStockController(ProductSkuStockService productSkuStockService) {
        this.productSkuStockService = productSkuStockService;
    }

    @Operation(summary = "根据商品编号及编号模糊搜索SKU库存", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping(value = "/{productId:[0-9]+}")
    public List<PmsSkuStock> getList(@PathVariable(value = "productId") Long productId,
                                     @RequestParam(value = "keyword", required = false) String keyword) {
        return productSkuStockService.getList(productId, keyword);
    }

    @Operation(summary = "根据商品编号及编号模糊搜索SKU库存", security = {@SecurityRequirement(name = "jwtScheme")})
    @PutMapping(value = "/{productId:[0-9]+}")
    public void update(@PathVariable(value = "productId") Long productId, @RequestBody List<PmsSkuStock> skuStocks) {
        productSkuStockService.update(productId, skuStocks);
    }
}