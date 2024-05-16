/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.sms.service;

import com.mall.model.SmsFlashPromotionSession;
import com.mall.sms.dto.FlashPromotionSession;
import java.util.List;

public interface FlashPromotionSessionService {

    void create(SmsFlashPromotionSession promotionSession);

    void update(Long id, SmsFlashPromotionSession promotionSession);

    void updateStatus(Long id, Integer status);

    void delete(Long id);

    SmsFlashPromotionSession get(Long id);

    List<SmsFlashPromotionSession> list();

    List<FlashPromotionSession> list(Long flashPromotionId);
}