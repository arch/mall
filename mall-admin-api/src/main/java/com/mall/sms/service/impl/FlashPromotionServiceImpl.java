/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service.impl;

import static com.mall.mapper.SmsFlashPromotionDynamicSqlSupport.smsFlashPromotion;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.SmsFlashPromotionMapper;
import com.mall.model.SmsFlashPromotion;
import com.mall.sms.service.FlashPromotionService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class FlashPromotionServiceImpl implements FlashPromotionService {

    private final SmsFlashPromotionMapper flashPromotionMapper;

    public FlashPromotionServiceImpl(
            SmsFlashPromotionMapper flashPromotionMapper) {
        this.flashPromotionMapper = flashPromotionMapper;
    }

    @Override
    public void create(SmsFlashPromotion flashPromotion) {
        flashPromotion.setCreateTime(LocalDateTime.now());
        int count = flashPromotionMapper.insert(flashPromotion);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void update(Long id, SmsFlashPromotion flashPromotion) {
        flashPromotion.setId(id);
        int count = flashPromotionMapper.updateByPrimaryKey(flashPromotion);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(Long id) {
        int count = flashPromotionMapper.deleteByPrimaryKey(id);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        int count = flashPromotionMapper.update(c ->
                c.set(smsFlashPromotion.status).equalTo(status)
                        .where(smsFlashPromotion.id, isEqualTo(id)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public SmsFlashPromotion get(Long id) {
        return flashPromotionMapper.selectByPrimaryKey(id).orElse(null);
    }

    @Override
    public List<SmsFlashPromotion> list(String keyword, int pageIndex, int pageSize) {
        return flashPromotionMapper.select(c -> {
            if (StringUtils.hasText(keyword)) {
                c.where(smsFlashPromotion.title, isLike("%" + keyword + "%"));
            }

            c.limit(pageSize).offset(pageIndex * pageSize);

            return c;
        });
    }
}