/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service;

import com.mall.model.SmsFlashPromotionProductRelation;
import com.mall.sms.dto.FlashPromotionProduct;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface FlashPromotionProductRelationService {

    long getCount(Long flashPromotionId, Long flashPromotionSessionId);

    @Transactional
    void create(List<SmsFlashPromotionProductRelation> relations);

    void update(Long id, SmsFlashPromotionProductRelation relation);

    void delete(Long id);

    SmsFlashPromotionProductRelation get(Long id);

    List<FlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, int pageIndex, int pageSize);
}