/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.ums.dto;

import com.mall.model.UmsMenu;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class LoginAdmin {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "头像")
    private String icon;
    @Schema(description = "已授权菜单")
    private List<UmsMenu> menus;
    @Schema(description = "已授权角色")
    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<UmsMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<UmsMenu> menus) {
        this.menus = menus;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}