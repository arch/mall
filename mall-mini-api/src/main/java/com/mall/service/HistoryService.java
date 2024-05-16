/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.domain.History;

import java.util.List;

public interface HistoryService {
    void create(History history);

    void delete(List<String> ids);

    void clear();

    List<History> list(int pageIndex, int pageSize);
}