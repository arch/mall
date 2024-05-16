/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.oms.service;

import com.mall.oms.dto.AddressParam;
import com.mall.model.OmsCompanyAddress;

import java.util.List;

public interface AddressService {

    List<OmsCompanyAddress> list();

    void create(AddressParam address);

    void update(long id, AddressParam address);

    void delete(List<Long> ids);
}