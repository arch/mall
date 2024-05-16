/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.controller;

import com.mall.model.PmsProduct;
import com.mall.pms.dto.ProductDto;
import com.mall.pms.dto.ProductEditDto;
import com.mall.pms.dto.ProductQuery;
import com.mall.pms.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "商品管理")
@RestController
@RequestMapping(value = "/api/pms/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "创建商品", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping
    public void create(@RequestBody @Valid ProductDto productDto) {
        productService.create(productDto);
    }

    @Operation(summary = "根据商品id获取商品编辑信息", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/edit/{id:[0-9]+}")
    public ProductEditDto getEdit(@PathVariable("id")long id) {
        return productService.getEdit(id);
    }

    @Operation(summary = "更新商品", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/{id:[0-9]+}")
    public void update(@PathVariable("id") long id, @RequestBody @Valid ProductDto productDto) {
        productService.update(id, productDto);
    }

    @Operation(summary = "查询商品", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping
    public List<PmsProduct> getList(ProductQuery productQuery,
                                    @RequestParam(value = "pageIndex", defaultValue = "0", required = false) int pageIndex,
                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return productService.list(productQuery, pageIndex, pageSize);
    }

    @Operation(summary = "根据商品名称或货号模糊查询", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/search")
    public List<PmsProduct> getList(String keyword) {
        return productService.list(keyword);
    }

    @Operation(summary = "批量修改审核状态", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update-verify-status")
    public void updateVerifyStatus(@RequestParam("ids") List<Long> ids,
                                   @RequestParam("verifyStatus") Integer verifyStatus,
                                   @RequestParam("detail") String detail) {
        productService.updateVerifyStatus(ids, verifyStatus, detail);
    }

    @Operation(summary = "批量上下架", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update-publish-status")
    public void updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                    @RequestParam("publishStatus") Integer publishStatus) {
        productService.updatePublishStatus(ids, publishStatus);
    }

    @Operation(summary = "批量推荐商品", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update-recommend-status")
    public void updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("recommendStatus") Integer recommendStatus) {
        productService.updateRecommendStatus(ids, recommendStatus);
    }

    @Operation(summary = "批量设为新品", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update-new-status")
    public void updateNewStatus(@RequestParam("ids") List<Long> ids,
                                @RequestParam("newStatus") Integer newStatus) {
        productService.updateNewStatus(ids, newStatus);
    }

    @Operation(summary = "批量修改删除状态", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update-delete-status")
    public void updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                   @RequestParam("deleteStatus") Integer deleteStatus) {
        productService.updateDeleteStatus(ids, deleteStatus);
    }
}