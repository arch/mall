/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.model.PmsFreightCompany;
import java.util.List;

public interface FreightService {

    List<PmsFreightCompany> list(int pageIndex, int pageSize);

    List<PmsFreightCompany> search(String nameOrCode);
}