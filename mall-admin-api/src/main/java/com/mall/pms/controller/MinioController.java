/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.controller;

import com.mall.pms.dto.MinioUploadDto;
import com.mall.pms.service.MinioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "对象存储管理")
@RestController
@RequestMapping(value = "/api/minio")
public class MinioController {
    private final MinioService minioService;

    public MinioController(MinioService minioService) {
        this.minioService = minioService;
    }

    @Operation(summary = "上传文件", security = { @SecurityRequirement(name = "jwtScheme") })
    @PostMapping(value = "/upload")
    public MinioUploadDto upload(@RequestBody MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("文件列表为空，请重新选择");
        }
        return minioService.upload(file);
    }

    @Operation(summary = "删除文件", security = { @SecurityRequirement(name = "jwtScheme") })
    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam("name") String name) {
        minioService.delete(name);
    }
}