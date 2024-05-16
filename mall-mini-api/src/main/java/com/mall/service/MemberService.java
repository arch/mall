/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service;

import com.mall.domain.MemberLoginDto;
import com.mall.domain.MemberUpdateDto;
import com.mall.domain.SensitivePhone;
import com.mall.domain.SensitiveMember;
import com.mall.model.UmsMember;
import com.mall.security.core.Jwt;

public interface MemberService {
    /**
     * 根据用户名获取会员
     */
    UmsMember getByOpenId(String openid);

    /**
     * 根据会员编号获取会员
     */
    UmsMember getById(Long id);

    Jwt login(MemberLoginDto loginDto);

    UmsMember getCurrentMember();

    Jwt refreshToken(String token);

    void updatePhone(long id, SensitivePhone phone);

    void update(long id, SensitiveMember sensitiveMember);

    /**
     * 根据会员id修改会员积分
     */
    void updateIntegration(Long memberId, Integer integration);

    void update(MemberUpdateDto updateDto);
}