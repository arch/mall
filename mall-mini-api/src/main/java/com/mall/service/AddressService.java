/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.model.UmsMemberReceiveAddress;

import java.util.List;

public interface AddressService {
    void create(UmsMemberReceiveAddress address);

    void delete(long id);

    void update(long id, UmsMemberReceiveAddress address);

    UmsMemberReceiveAddress get(long id);

    List<UmsMemberReceiveAddress> list();
}