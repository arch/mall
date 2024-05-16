/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import static com.mall.mapper.UmsMemberDynamicSqlSupport.umsMember;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.crosscut.util.Json;
import com.mall.domain.MemberDetails;
import com.mall.domain.MemberLoginDto;
import com.mall.domain.MemberUpdateDto;
import com.mall.domain.SensitiveMember;
import com.mall.domain.SensitivePhone;
import com.mall.service.MemberService;
import com.mall.mapper.UmsMemberMapper;
import com.mall.model.UmsMember;
import com.mall.wechat.api.SessionResult;
import com.mall.wechat.api.UserPhone;
import com.mall.wechat.api.WeChatClient;
import com.mall.security.core.Jwt;
import com.mall.security.core.JwtService;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.util.Pool;

@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final LoadingCache<String, Optional<UmsMember>> memberCache;
    private final Pool<Jedis> jedisPool;

    private final UmsMemberMapper memberMapper;
    private final JwtService jwtService;
    private final WeChatClient wechatClient;

    public MemberServiceImpl(Pool<Jedis> jedisPool, UmsMemberMapper memberMapper,
            JwtService jwtService, WeChatClient wechatClient) {
        this.jedisPool = jedisPool;
        this.memberMapper = memberMapper;
        this.jwtService = jwtService;

        memberCache = CacheBuilder.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public Optional<UmsMember> load(@NotNull String openid) {
                        return memberMapper.selectOne(c -> c.where(umsMember.openid, isEqualTo(openid)));
                    }
                });
        this.wechatClient = wechatClient;
    }

    @Override
    public UmsMember getByOpenId(String openid) {
        return memberCache.getUnchecked(openid).orElse(null);
    }

    @Override
    public UmsMember getById(Long id) {
        return memberMapper.selectByPrimaryKey(id).orElse(null);
    }

    @Override
    public Jwt login(MemberLoginDto loginDto) {
        Assert.ensure(loginDto != null && StringUtils.hasText(loginDto.getCode()), "必须提供有效微信授权码");
        SessionResult sessionResult = wechatClient.getSession(loginDto.getCode());

        Optional<UmsMember> optional = memberCache.getUnchecked(sessionResult.getOpenid());
        if (optional.isEmpty()) {
            UmsMember umsMember = new UmsMember();
            umsMember.setOpenid(sessionResult.getOpenid());
            int count = memberMapper.insertSelective(umsMember);
            Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
            memberCache.invalidate(sessionResult.getOpenid());
        }

        try (Jedis jedis = jedisPool.getResource()) {
            String status = jedis.set(sessionResult.getOpenid(), sessionResult.getSessionKey());
            logger.info(status);
        }

        try {
            return jwtService.generate(sessionResult.getOpenid());
        } catch (Throwable cause) {
            logger.error(cause.getMessage(), cause);
            Assert.failure(cause.getMessage());
        }

        return null;
    }

    @Override
    public UmsMember getCurrentMember() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication authentication = ctx.getAuthentication();

        // ensure had login
        Assert.ensure(authentication != null, StandardCode.UNAUTHORIZED);

        // checking the principal
        if (authentication.getPrincipal() instanceof MemberDetails) {
            MemberDetails details = (MemberDetails) authentication.getPrincipal();
            return details.getUmsMember();
        } else {
            return getByOpenId(authentication.getName());
        }
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
    public void updatePhone(long id, SensitivePhone sensitivePhone) {
        Optional<UmsMember> optional = memberMapper.selectByPrimaryKey(id);
        Assert.ensure(optional.isPresent(), String.format("会员[%d]不存在", id));
        UmsMember member = optional.get();
        try (Jedis jedis = jedisPool.getResource()) {
            String sessionKey = jedis.get(member.getOpenid());
            String json = sensitivePhone.decrypt(sessionKey);
            UserPhone phone = Json.deserialize(json, UserPhone.class);
            if (Objects.equals(phone.getPurePhoneNumber(), member.getPhone())) {
                return;
            }
            member.setPhone(phone.getPurePhoneNumber());
            int count = memberMapper.updateByPrimaryKeySelective(member);
            Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
            // refresh cache
            memberCache.invalidate(member.getOpenid());
        }
    }

    @Override
    public void update(long id, SensitiveMember sensitiveMember) {
        Optional<UmsMember> optional = memberMapper.selectByPrimaryKey(id);
        Assert.ensure(optional.isPresent(), String.format("会员[%d]不存在", id));
        UmsMember member = optional.get();
        try (Jedis jedis = jedisPool.getResource()) {
            String sessionKey = jedis.get(member.getOpenid());
            sensitiveMember.verifySignature(sessionKey);
            String json = sensitiveMember.decrypt(sessionKey);
            logger.info(json);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsMember member = getByOpenId(username);
        if (member != null) {
            return new MemberDetails(member);
        }
        throw new UsernameNotFoundException("用户名或OPENID错误");
    }

    @Override
    public void update(MemberUpdateDto updateDto) {
        UmsMember member = new UmsMember().withId(updateDto.getId());
        BeanUtils.copyProperties(updateDto, member);
        int count = memberMapper.updateByPrimaryKeySelective(member);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateIntegration(Long memberId, Integer integration) {
        UmsMember record = new UmsMember();
        record.setId(memberId);
        record.setIntegration(integration);
        memberMapper.updateByPrimaryKeySelective(record);

        UmsMember member = getCurrentMember();
        if (member != null) {
            member.setIntegration(integration);

            int count = memberMapper.updateByPrimaryKeySelective(member);
            Assert.ensure(count == 1, StandardCode.SQL_FAILURE);

            // re-cache the member
            memberCache.invalidate(member.getOpenid());
        }
    }
}