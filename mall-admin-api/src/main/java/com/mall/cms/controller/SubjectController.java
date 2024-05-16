/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.cms.controller;

import com.mall.cms.service.SubjectService;
import com.mall.model.CmsSubject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "商品专题管理")
@RestController
@RequestMapping(value = "/api/cms/subject", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Operation(summary = "获取全部商品专题")
    @GetMapping(value = "/list")
    public List<CmsSubject> get() {
        return subjectService.listAll();
    }

    @Operation(summary = "根据专题名称分页获取专题")
    @GetMapping
    public List<CmsSubject> get(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return subjectService.list(keyword, pageIndex, pageSize);
    }
}