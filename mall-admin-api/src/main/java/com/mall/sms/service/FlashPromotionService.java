/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service;

import com.mall.model.SmsFlashPromotion;
import java.util.List;

public interface FlashPromotionService {

    void create(SmsFlashPromotion flashPromotion);

    void update(Long id, SmsFlashPromotion flashPromotion);

    void delete(Long id);

    void updateStatus(Long id, Integer status);

    SmsFlashPromotion get(Long id);

    List<SmsFlashPromotion> list(String keyword, int pageIndex, int pageSize);
}