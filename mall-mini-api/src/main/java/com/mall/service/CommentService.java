/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.domain.CommentParam;
import com.mall.model.PmsComment;
import java.util.List;

public interface CommentService {

    List<PmsComment> list(long productId, int pageIndex, int pageSize);

    void create(CommentParam commentParam);
}