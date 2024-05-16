/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.service.impl;

import static com.mall.mapper.PmsCommentDynamicSqlSupport.pmsComment;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.domain.CommentParam;
import com.mall.service.CommentService;
import com.mall.service.MemberService;
import com.mall.mapper.PmsCommentMapper;
import com.mall.mapper.PmsProductMapper;
import com.mall.model.PmsComment;
import com.mall.model.PmsProduct;
import com.mall.model.UmsMember;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final PmsCommentMapper commentMapper;
    private final PmsProductMapper productMapper;
    private final MemberService memberService;

    public CommentServiceImpl(PmsCommentMapper commentMapper,
            PmsProductMapper productMapper, MemberService memberService) {
        this.commentMapper = commentMapper;
        this.memberService = memberService;
        this.productMapper = productMapper;
    }

    @Override
    public List<PmsComment> list(long productId, int pageIndex, int pageSize) {
        return commentMapper.select(c -> c.where(pmsComment.productId, isEqualTo(productId)).limit(pageSize).offset(pageIndex * pageSize));
    }

    @Override
    public void create(CommentParam param) {
        Optional<PmsProduct> optional = productMapper.selectByPrimaryKey(param.getProductId());
        Assert.ensure(optional.isPresent(), String.format("商品[%d]不存在", param.getProductId()));
        PmsProduct product = optional.get();

        PmsComment comment  = new PmsComment()
                .withProductId(param.getProductId())
                .withCreateTime(LocalDateTime.now());
        BeanUtils.copyProperties(param, comment);

        comment.setProductName(product.getName());

        UmsMember member = memberService.getCurrentMember();
        comment.setMemberNickName(member.getNickname());
        comment.setMemberIcon(member.getIcon());

        int count = commentMapper.insertSelective(comment);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }
}