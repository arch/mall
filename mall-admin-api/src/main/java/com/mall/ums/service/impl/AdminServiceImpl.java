/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.ums.service.impl;

import static com.mall.mapper.UmsAdminDynamicSqlSupport.umsAdmin;
import static com.mall.mapper.UmsAdminDynamicSqlSupport.username;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mall.ums.bo.AdminDetails;
import com.mall.ums.dto.LoginAdmin;
import com.mall.ums.dto.LoginParam;
import com.mall.ums.dto.RegisterParam;
import com.mall.ums.dto.UpdatePwdParam;
import com.mall.ums.service.AdminService;
import com.mall.ums.service.AuthService;
import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.UmsAdminMapper;
import com.mall.model.UmsAdmin;
import com.mall.model.UmsResource;
import com.mall.model.UmsRole;
import com.mall.security.core.Jwt;
import com.mall.security.core.JwtService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final LoadingCache<String, Optional<UmsAdmin>> adminCache;

    private final AuthService authService;
    private final UmsAdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AdminServiceImpl(AuthService authService,
                            UmsAdminMapper adminMapper,
                            PasswordEncoder passwordEncoder,
                            JwtService jwtService) {
        this.authService = authService;
        this.adminMapper = adminMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

        adminCache = CacheBuilder.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public Optional<UmsAdmin> load(@NotNull String username) {
                        return adminMapper.selectOne(c -> c.where(umsAdmin.username, isEqualTo(username)));
                    }
                });
    }

    @Override
    public UmsAdmin register(RegisterParam param) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(param, umsAdmin);
        umsAdmin.setCreateTime(LocalDateTime.now());
        umsAdmin.setStatus(1);

        // 查询是否有相同用户名的用户
        var optional = adminMapper.selectOne(c -> c.where(username, isEqualTo(param.getUsername())));
        if (optional.isPresent()) {
            Assert.failure(StandardCode.BAD_REQUEST.withMessage("该用户已经存在"));
        }

        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        int count = adminMapper.insert(umsAdmin);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

        return umsAdmin;
    }

    @Override
    public Jwt login(LoginParam param) {
        UserDetails userDetails = loadUserByUsername(param.getUsername());
        if (!passwordEncoder.matches(param.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }

        try {
            return jwtService.generate(userDetails.getUsername());
        } catch (Throwable cause) {
            logger.error(cause.getMessage(), cause);

            Assert.failure(cause.getMessage());
        }

        return null;
    }

    @Override
    public Jwt refreshToken(String token) {
        try {
            return jwtService.generate(jwtService.verify(token));
        } catch (Throwable cause) {
            Assert.failure(cause.getMessage());
        }

        return null;
    }

    @Override
    public void updatePassword(UpdatePwdParam param) {
        if (ObjectUtils.isEmpty(param.getUsername())
                || ObjectUtils.isEmpty(param.getOldPassword())
                || ObjectUtils.isEmpty(param.getNewPassword())) {
            Assert.failure(StandardCode.BAD_REQUEST);
        }

        // 查询是否有相同用户名的用户
        var optional = adminMapper.selectOne(c -> c.where(username, isEqualTo(param.getUsername())));
        if (optional.isEmpty()) {
            Assert.failure(StandardCode.BAD_REQUEST.withMessage("该用户不存在"));
        }

        UmsAdmin umsAdmin = optional.get();
        if (!passwordEncoder.matches(param.getOldPassword(), umsAdmin.getPassword())) {
            Assert.failure(StandardCode.BAD_REQUEST.withMessage("旧密码输入错误"));
        }
        umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        int count = adminMapper.updateByPrimaryKey(umsAdmin);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public UmsAdmin getByUsername(String username) {
        return adminCache.getUnchecked(username).orElse(null);
    }

    @Override
    public LoginAdmin getLoginAdmin() {
        LoginAdmin login = new LoginAdmin();

        UmsAdmin admin = getCurrentAdmin();
        login.setUsername(admin.getUsername());
        login.setIcon(admin.getIcon());

        // query & set the authorized menus
        login.setMenus(authService.getMenus(admin.getId()));

        // query the authorized roles
        List<UmsRole> roles = authService.getRoles(admin.getId());

        // only using the role's name
        login.setRoles(roles.stream().map(UmsRole::getName).collect(Collectors.toList()));

        return login;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取用户信息
        UmsAdmin admin = getByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    private List<UmsResource> getResourceList(Long adminId) {
        return null;
    }

    private UmsAdmin getCurrentAdmin() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication authentication = ctx.getAuthentication();

        // ensure had login
        Assert.ensure(authentication != null, StandardCode.UNAUTHORIZED);

        // checking the principal
        if (authentication.getPrincipal() instanceof AdminDetails) {
            AdminDetails details = (AdminDetails) authentication.getPrincipal();
            return details.getUmsAdmin();
        } else {
            return getByUsername(authentication.getName());
        }
    }
}