/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.controller;

import com.mall.model.PmsBrand;
import com.mall.pms.dto.BrandDto;
import com.mall.pms.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "商品品牌管理")
@RestController
@RequestMapping(value = "/api/pms/brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Operation(summary = "根据编号查询品牌信息", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/{id:[0-9]+}")
    public PmsBrand get(@PathVariable("id") long id) {
        return brandService.getOne(id);
    }

    @Operation(summary = "获取全部品牌列表", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/list-all")
    public List<PmsBrand> getList() {
        return brandService.listAll();
    }

    @Operation(summary = "根据品牌名称分页获取品牌列表", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/paged-list")
    public List<PmsBrand> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return brandService.pagedList(keyword, pageIndex, pageSize);
    }

    @Operation(summary = "添加品牌", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping
    public void create(@RequestBody @Valid BrandDto brandDto) {
        brandService.create(brandDto);
    }

    @Operation(summary = "更新品牌", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/{id:[0-9]+}")
    public void update(@PathVariable("id") long id, @RequestBody @Valid BrandDto brandDto) {
        brandService.update(id, brandDto);
    }

    @Operation(summary = "批量更新显示状态", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update-show-status")
    public void updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") int showStatus) {
        brandService.updateShowStatus(ids, showStatus);
    }

    @Operation(summary = "批量更新厂家制造商状态", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update-factory-status")
    public void updateFactorStatus(@RequestParam("ids") List<Long> ids, @RequestParam("factoryStatus") int factoryStatus) {
        brandService.updateFactoryStatus(ids, factoryStatus);
    }

    @Operation(summary = "删除品牌", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/{id:[0-9]+}")
    public void delete(@PathVariable("id") long id) {
        brandService.delete(id);
    }

    @Operation(summary = "批量删除品牌", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/bath-delete")
    public void delete(@RequestParam("ids")List<Long> ids) {
        brandService.delete(ids);
    }
}