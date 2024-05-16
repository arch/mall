/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service.impl;

import static com.mall.mapper.SmsFlashPromotionProductRelationDynamicSqlSupport.smsFlashPromotionProductRelation;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.mall.crosscut.standard.StandardCode;
import com.mall.crosscut.util.Assert;
import com.mall.mapper.SmsFlashPromotionProductRelationMapper;
import com.mall.model.SmsFlashPromotionProductRelation;
import com.mall.sms.dao.FlashPromotionProductRelationDao;
import com.mall.sms.dto.FlashPromotionProduct;
import com.mall.sms.service.FlashPromotionProductRelationService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FlashPromotionProductRelationServiceImpl implements FlashPromotionProductRelationService {

    private final SmsFlashPromotionProductRelationMapper relationMapper;
    private final FlashPromotionProductRelationDao relationDao;

    public FlashPromotionProductRelationServiceImpl(
            SmsFlashPromotionProductRelationMapper relationMapper, FlashPromotionProductRelationDao relationDao) {
        this.relationMapper = relationMapper;
        this.relationDao = relationDao;
    }

    @Override
    public long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        return relationMapper.count(c ->
                c.where(smsFlashPromotionProductRelation.flashPromotionId, isEqualTo(flashPromotionId))
                        .and(smsFlashPromotionProductRelation.flashPromotionSessionId,
                                isEqualTo(flashPromotionSessionId)));
    }

    @Override
    public void create(List<SmsFlashPromotionProductRelation> relations) {
        if (relations.isEmpty()) {
            return;
        }

        int count = 0;
        for (SmsFlashPromotionProductRelation relation : relations) {
            count += relationMapper.insert(relation);
        }
        Assert.ensure(count == relations.size(), StandardCode.SQL_FAILURE);
    }

    @Override
    public void update(Long id, SmsFlashPromotionProductRelation relation) {
        relation.setId(id);
        int count = relationMapper.updateByPrimaryKey(relation);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public void delete(Long id) {
        int count = relationMapper.deleteByPrimaryKey(id);
        Assert.ensure(count == 1, StandardCode.SQL_FAILURE);
    }

    @Override
    public SmsFlashPromotionProductRelation get(Long id) {
        return relationMapper.selectByPrimaryKey(id).orElse(null);
    }

    @Override
    public List<FlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, int pageIndex,
            int pageSize) {
        return relationDao.getList(flashPromotionId, flashPromotionSessionId, pageIndex * pageSize, pageSize);
    }
}