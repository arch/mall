/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.domain;

import com.mall.model.UmsMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MemberDetails implements UserDetails {

    private final UmsMember umsMember;

    public MemberDetails(UmsMember umsMember) {
        this.umsMember = umsMember;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回当前用户的权限
        return Collections.singletonList(new SimpleGrantedAuthority("TEST"));
    }

    @Override
    public String getPassword() {
        return "******";
    }

    @Override
    public String getUsername() {
        return umsMember.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsMember.getStatus() == 1;
    }

    public UmsMember getUmsMember() {
        return umsMember;
    }
}