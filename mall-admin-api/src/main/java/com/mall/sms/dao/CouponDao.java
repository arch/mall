/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.dao;

import com.mall.sms.dto.CouponParam;
import org.apache.ibatis.annotations.Param;

public interface CouponDao {

    CouponParam selectOne(@Param("id") Long id);
}