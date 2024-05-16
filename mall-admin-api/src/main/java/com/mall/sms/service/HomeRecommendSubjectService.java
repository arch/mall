/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service;

import com.mall.model.SmsHomeRecommendSubject;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface HomeRecommendSubjectService {
    @Transactional
    void create(List<SmsHomeRecommendSubject> recommendSubjects);

    void updateSort(Long id, Integer sort);

    void delete(List<Long> ids);

    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, int pageIndex, int pageSize);
}