/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.domain.ExpressParam;
import com.mall.domain.OrderReturnApplyParam;
import com.mall.domain.OrderReturnDetail;
import com.mall.model.OmsOrderReturnReason;
import java.util.List;
import javax.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

public interface OrderReturnService {
    @Transactional
    void apply(OrderReturnApplyParam returnApplyParam);

    List<OmsOrderReturnReason> getReasons();

    OrderReturnDetail detail(long id);

    void setReturnExpress(long id, @Valid ExpressParam param);
}