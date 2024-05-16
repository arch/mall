/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.cms.service;

import com.mall.model.CmsSubject;
import java.util.List;

public interface SubjectService {

    List<CmsSubject> listAll();

    List<CmsSubject> list(String keyword, int pageIndex, int pageSize);
}