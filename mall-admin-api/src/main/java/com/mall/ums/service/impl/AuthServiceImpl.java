/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.ums.service.impl;

import com.mall.ums.service.AuthService;
import com.mall.model.UmsMenu;
import com.mall.model.UmsRole;
import com.mall.ums.dao.AuthDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthDao authDao;

    public AuthServiceImpl(AuthDao authDao) {
        this.authDao = authDao;
    }

    @Override
    public List<UmsMenu> getMenus(Long adminId) {
        return authDao.getMenus(adminId);
    }

    @Override
    public List<UmsRole> getRoles(Long adminId) {
        return authDao.getRoles(adminId);
    }
}