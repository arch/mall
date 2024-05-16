/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.service.AddressService;
import com.mall.service.MemberService;
import com.mall.mapper.UmsMemberReceiveAddressMapper;
import com.mall.model.UmsMember;
import com.mall.model.UmsMemberReceiveAddress;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mall.mapper.UmsMemberMemberTagRelationDynamicSqlSupport.memberId;
import static com.mall.mapper.UmsMemberReceiveAddressDynamicSqlSupport.defaultStatus;
import static com.mall.mapper.UmsMemberReceiveAddressDynamicSqlSupport.umsMemberReceiveAddress;
import static com.mall.mapper.UmsMemberReceiveAddressMapper.updateSelectiveColumns;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class AddressServiceImpl implements AddressService {
    private final MemberService memberService;
    private final UmsMemberReceiveAddressMapper addressMapper;

    public AddressServiceImpl(MemberService memberService, UmsMemberReceiveAddressMapper addressMapper) {
        this.memberService = memberService;
        this.addressMapper = addressMapper;
    }

    @Override
    public void create(UmsMemberReceiveAddress address) {
        UmsMember currentMember = memberService.getCurrentMember();
        address.setMemberId(currentMember.getId());
        int count = addressMapper.insert(address);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(long id) {
        UmsMember currentMember = memberService.getCurrentMember();
        int count = addressMapper.delete(c ->
                c.where(umsMemberReceiveAddress.id, isEqualTo(id))
                        .and(memberId, isEqualTo(currentMember.getId())));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void update(long id, UmsMemberReceiveAddress address) {
        address.setId(null);
        UmsMember currentMember = memberService.getCurrentMember();
        // ensure only update self address
        address.setMemberId(currentMember.getId());
        if (address.getDefaultStatus() == 1) {
            // 先将原来的默认地址去除
            addressMapper.update(c ->
                    c.set(defaultStatus).equalTo(0)
                            .where(memberId, isEqualTo(currentMember.getId()))
                            .and(defaultStatus, isEqualTo(1)));
        }
        int count = addressMapper.update(c -> updateSelectiveColumns(address, c).where(umsMemberReceiveAddress.id, isEqualTo(id)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public UmsMemberReceiveAddress get(long id) {
        UmsMember currentMember = memberService.getCurrentMember();
        var optional = addressMapper.selectOne(c ->
                c.where(umsMemberReceiveAddress.id, isEqualTo(id)).and(memberId, isEqualTo(currentMember.getId())));
        if (optional.isEmpty()) {
            throw new IllegalArgumentException(String.format("地址编号: [%d]不存在", id));
        }

        return optional.get();
    }

    @Override
    public List<UmsMemberReceiveAddress> list() {
        UmsMember currentMember = memberService.getCurrentMember();
        return addressMapper.select(c -> c.where(memberId, isEqualTo(currentMember.getId())));
    }
}