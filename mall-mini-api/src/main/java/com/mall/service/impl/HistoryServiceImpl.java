/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import com.mall.domain.History;
import com.mall.service.HistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Override
    public void create(History history) {

    }

    @Override
    public void delete(List<String> ids) {

    }

    @Override
    public void clear() {

    }

    @Override
    public List<History> list(int pageIndex, int pageSize) {
        return null;
    }
}