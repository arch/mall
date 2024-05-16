/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsFlashPromotionLogDynamicSqlSupport {
    public static final SmsFlashPromotionLog smsFlashPromotionLog = new SmsFlashPromotionLog();

    public static final SqlColumn<Integer> id = smsFlashPromotionLog.id;

    public static final SqlColumn<Integer> memberId = smsFlashPromotionLog.memberId;

    public static final SqlColumn<Long> productId = smsFlashPromotionLog.productId;

    public static final SqlColumn<String> memberPhone = smsFlashPromotionLog.memberPhone;

    public static final SqlColumn<String> productName = smsFlashPromotionLog.productName;

    public static final SqlColumn<LocalDateTime> subscribeTime = smsFlashPromotionLog.subscribeTime;

    public static final SqlColumn<LocalDateTime> sendTime = smsFlashPromotionLog.sendTime;

    public static final class SmsFlashPromotionLog extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> memberId = column("member_id", JDBCType.INTEGER);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<String> memberPhone = column("member_phone", JDBCType.VARCHAR);

        public final SqlColumn<String> productName = column("product_name", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> subscribeTime = column("subscribe_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> sendTime = column("send_time", JDBCType.TIMESTAMP);

        public SmsFlashPromotionLog() {
            super("sms_flash_promotion_log");
        }
    }
}