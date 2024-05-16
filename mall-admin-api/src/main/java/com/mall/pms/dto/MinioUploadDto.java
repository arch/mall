/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class MinioUploadDto {
    @Schema(description = "文件访问URL")
    private String url;
    @Schema(description = "文件名称")
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}