/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.cms.service.impl;

import static com.mall.mapper.CmsSubjectDynamicSqlSupport.cmsSubject;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import com.mall.cms.service.SubjectService;
import com.mall.mapper.CmsSubjectMapper;
import com.mall.model.CmsSubject;
import java.util.List;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final CmsSubjectMapper subjectMapper;

    public SubjectServiceImpl(CmsSubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    @Override
    public List<CmsSubject> listAll() {
        return subjectMapper.select(SelectDSLCompleter.allRows());
    }

    @Override
    public List<CmsSubject> list(String keyword, int pageIndex, int pageSize) {
        return subjectMapper.select(c -> {
            if (StringUtils.hasText(keyword)) {
                c.where(cmsSubject.title, isLike("%" + keyword + "%"));
            }

            c.limit(pageSize).offset(pageIndex * pageSize);

            return c;
        });
    }
}