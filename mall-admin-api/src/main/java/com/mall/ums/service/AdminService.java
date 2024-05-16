/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.ums.service;

import com.mall.ums.dto.LoginParam;
import com.mall.model.UmsAdmin;
import com.mall.ums.dto.RegisterParam;
import com.mall.ums.dto.UpdatePwdParam;
import com.mall.ums.dto.LoginAdmin;
import com.mall.security.core.Jwt;

public interface AdminService {
    UmsAdmin register(RegisterParam param);

    Jwt login(LoginParam loginParam);

    Jwt refreshToken(String token);

    void updatePassword(UpdatePwdParam updatePwdParam);

    UmsAdmin getByUsername(String username);

    LoginAdmin getLoginAdmin();
}