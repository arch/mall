/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.ums.service;

import com.mall.model.UmsMenu;
import com.mall.model.UmsRole;

import java.util.List;

public interface AuthService {
    /**
     * 根据管理员ID获取对应菜单
     */
    List<UmsMenu> getMenus(Long adminId);
    /**
     * 获取用户对于角色
     */
    List<UmsRole> getRoles(Long adminId);
}