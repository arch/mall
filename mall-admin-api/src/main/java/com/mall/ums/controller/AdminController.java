/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.ums.controller;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.model.UmsAdmin;
import com.mall.ums.dto.LoginParam;
import com.mall.ums.dto.RegisterParam;
import com.mall.ums.dto.UpdatePwdParam;
import com.mall.ums.dto.LoginAdmin;
import com.mall.ums.service.AdminService;
import com.mall.security.core.Jwt;
import com.mall.security.core.TokenResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Tag(name = "后台用户管理")
@RestController
@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {
    private final AdminService adminService;
    private final TokenResolver tokenResolver;

    public AdminController(AdminService adminService, TokenResolver tokenResolver) {
        this.adminService = adminService;
        this.tokenResolver = tokenResolver;
    }

    @Operation(summary = "用户注册")
    @PostMapping(value = "/register")
    public UmsAdmin register(@RequestBody @Valid RegisterParam param) {
        return adminService.register(param);
    }

    @Operation(summary = "用户登录")
    @PostMapping(value = "/login")
    public Jwt login(@RequestBody @Valid LoginParam loginParam) {
        return adminService.login(loginParam);
    }

    @Operation(summary = "登出当前用户", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/logout")
    public void logout(HttpServletResponse response) throws IOException {
        response.sendRedirect("/logout");
    }

    @Operation(summary = "获取当前登录用户", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/me")
    public LoginAdmin get(Principal principal) {
        Assert.ensure(principal != null, StandardCode.UNAUTHORIZED);
        return adminService.getLoginAdmin();
    }

    @Operation(summary = "修改密码", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update-password")
    public void updatePassword(@RequestBody @Valid UpdatePwdParam updatePwdParam) {
        adminService.updatePassword(updatePwdParam);
    }

    @Operation(summary = "刷新TOKEN", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/refresh-token")
    public Jwt refreshToken(HttpServletRequest request) {
        String token = tokenResolver.resolve(request);
        return adminService.refreshToken(token);
    }
}