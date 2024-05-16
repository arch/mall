/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service.impl;

import static com.mall.mapper.SmsFlashPromotionSessionDynamicSqlSupport.smsFlashPromotionSession;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.SmsFlashPromotionSessionMapper;
import com.mall.model.SmsFlashPromotionSession;
import com.mall.sms.dto.FlashPromotionSession;
import com.mall.sms.service.FlashPromotionProductRelationService;
import com.mall.sms.service.FlashPromotionSessionService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class FlashPromotionSessionServiceImpl implements FlashPromotionSessionService {

    private final SmsFlashPromotionSessionMapper promotionSessionMapper;
    private final FlashPromotionProductRelationService relationService;

    public FlashPromotionSessionServiceImpl(
            SmsFlashPromotionSessionMapper promotionSessionMapper,
            FlashPromotionProductRelationService relationService) {
        this.promotionSessionMapper = promotionSessionMapper;
        this.relationService = relationService;
    }

    @Override
    public void create(SmsFlashPromotionSession promotionSession) {
        promotionSession.setCreateTime(LocalDateTime.now());
        int count = promotionSessionMapper.insert(promotionSession);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void update(Long id, SmsFlashPromotionSession promotionSession) {
        promotionSession.setId(id);
        int count = promotionSessionMapper.updateByPrimaryKey(promotionSession);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        int count = promotionSessionMapper.update(c ->
                c.set(smsFlashPromotionSession.status).equalTo(status)
                        .where(smsFlashPromotionSession.id, isEqualTo(id)));
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(Long id) {
        int count = promotionSessionMapper.deleteByPrimaryKey(id);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public SmsFlashPromotionSession get(Long id) {
        return promotionSessionMapper.selectByPrimaryKey(id).orElse(null);
    }

    @Override
    public List<SmsFlashPromotionSession> list() {
        return promotionSessionMapper.select(SelectDSLCompleter.allRows());
    }

    @Override
    public List<FlashPromotionSession> list(Long flashPromotionId) {
        List<SmsFlashPromotionSession> list = promotionSessionMapper.select(c ->
                c.where(smsFlashPromotionSession.status, isEqualTo(1)));
        List<FlashPromotionSession> items = new ArrayList<>();
        for (SmsFlashPromotionSession session : list) {
            FlashPromotionSession item = new FlashPromotionSession();
            BeanUtils.copyProperties(session, item);
            long count = relationService.getCount(flashPromotionId, session.getId());
            item.setProductCount(count);
            items.add(item);
        }
        return items;
    }
}