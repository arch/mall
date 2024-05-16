/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.service;

import com.mall.express.response.QueryTrackData;
import java.util.List;

public interface ExpressService {
    List<QueryTrackData> queryExpress(long orderId);
    List<QueryTrackData> queryReturnExpress(long id);
}