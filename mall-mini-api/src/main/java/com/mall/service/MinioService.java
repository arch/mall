/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.domain.MinioUploadDto;
import org.springframework.web.multipart.MultipartFile;

public interface MinioService {
    MinioUploadDto upload(MultipartFile multipartFile);

    void delete(String name);
}