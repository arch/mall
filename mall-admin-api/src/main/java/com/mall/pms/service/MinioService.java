/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service;

import com.mall.pms.dto.MinioUploadDto;
import org.springframework.web.multipart.MultipartFile;

public interface MinioService {
    MinioUploadDto upload(MultipartFile multipartFile);

    void delete(String name);
}