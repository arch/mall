/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.service;

import com.mall.model.OmsOrderSetting;
import java.util.List;

public interface OrderSettingService {

    List<OmsOrderSetting> list();

    OmsOrderSetting get(Long id);

    void update(Long id, OmsOrderSetting orderSetting);
}