/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.ums.dao;

import com.mall.model.UmsMenu;
import com.mall.model.UmsResource;
import com.mall.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthDao {
    /**
     * 获取用于所有角色
     */
    List<UmsRole> getRoles(@Param("adminId") Long adminId);

    /**
     * 根据后台用户ID获取菜单
     */
    List<UmsMenu> getMenus(@Param("adminId") Long adminId);
    /**
     * 根据角色ID获取菜单
     */
    List<UmsMenu> getMenusByRoleId(@Param("roleId") Long roleId);
    /**
     * 根据角色ID获取资源
     */
    List<UmsResource> getResourcesByRoleId(@Param("roleId") Long roleId);
}