/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.domain.History;
import com.mall.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "会员商品浏览记录管理")
@RestController
@RequestMapping(value = "/api/history", produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Operation(summary = "创建浏览记录", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping
    public void create(@RequestBody History history) {
        historyService.create(history);
    }

    @Operation(summary = "删除浏览记录", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping
    public void delete(@RequestParam("ids") List<String> ids) {
        historyService.delete(ids);
    }

    @Operation(summary = "清空除浏览记录", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/clear")
    public void clear() {
        historyService.clear();
    }

    @Operation(summary = "分页获取用户浏览记录", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/list")
    public List<History> list(@RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                              @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return historyService.list(pageIndex, pageSize);
    }
}