/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.controller;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.domain.MemberLoginDto;
import com.mall.domain.MemberUpdateDto;
import com.mall.domain.SensitivePhone;
import com.mall.domain.SensitiveMember;
import com.mall.model.UmsMember;
import com.mall.service.MemberService;
import com.mall.security.core.Jwt;
import com.mall.security.core.TokenResolver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "会员管理")
@RestController
@RequestMapping(value = "/api/member", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {
    private final MemberService memberService;
    private final TokenResolver tokenResolver;

    public MemberController(MemberService memberService, TokenResolver tokenResolver) {
        this.memberService = memberService;
        this.tokenResolver = tokenResolver;
    }

    @Operation(summary = "获取会员信息", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/me")
    public UmsMember get(Principal principal) {
        Assert.ensure(principal != null, StandardCode.UNAUTHORIZED);
        return memberService.getCurrentMember();
    }

    @Operation(summary = "微信登录")
    @PostMapping(value = "/wx/login")
    public Jwt post(@RequestBody @Valid MemberLoginDto loginDto) {
        return memberService.login(loginDto);
    }

    @Operation(summary = "更新电话号码", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/wx/update-phone/{id}")
    public void put(@PathVariable("id") long id, @RequestBody @Valid SensitivePhone phone) {
        memberService.updatePhone(id, phone);
    }

    @Operation(summary = "更新会员信息", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/wx/update/{id}")
    public void put(@PathVariable("id") long id, @RequestBody @Valid SensitiveMember member) {
        memberService.update(id, member);
    }

    @Operation(summary = "更新会员信息", security = { @SecurityRequirement(name = "jwtScheme") })
    @PutMapping(value = "/update")
    public void put(@RequestBody @Valid MemberUpdateDto updateDto) {
        memberService.update(updateDto);
    }

    @Operation(summary = "刷新TOKEN", security = { @SecurityRequirement(name = "jwtScheme") })
    @GetMapping(value = "/refresh/token")
    public Jwt get(HttpServletRequest request) {
        String token = tokenResolver.resolve(request);
        return memberService.refreshToken(token);
    }
}