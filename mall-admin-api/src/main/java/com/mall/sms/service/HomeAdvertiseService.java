/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service;

import com.mall.model.SmsHomeAdvertise;
import java.util.List;

public interface HomeAdvertiseService {

    List<SmsHomeAdvertise> list(String name, Integer type, String endTime, int pageIndex, int pageSize);

    void create(SmsHomeAdvertise advertise);

    void update(long id, SmsHomeAdvertise advertise);

    void delete(List<Long> ids);

    void updateStatus(long id, Integer status);

    SmsHomeAdvertise get(Long id);
}