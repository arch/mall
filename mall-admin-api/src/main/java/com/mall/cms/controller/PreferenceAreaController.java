/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.cms.controller;

import com.mall.cms.service.PreferenceAreaService;
import com.mall.model.CmsPreferenceArea;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "商品优选管理")
@RestController
@RequestMapping(value = "/api/cms/preference", produces = MediaType.APPLICATION_JSON_VALUE)
public class PreferenceAreaController {

    private final PreferenceAreaService preferenceAreaService;

    public PreferenceAreaController(
            PreferenceAreaService preferenceAreaService) {
        this.preferenceAreaService = preferenceAreaService;
    }

    @Operation(summary = "获取所有商品优选", security = {@SecurityRequirement(name = "jwtScheme")})
    @GetMapping
    public List<CmsPreferenceArea> get() {
        return preferenceAreaService.listAll();
    }
}