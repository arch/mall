/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsFlashPromotionSessionDynamicSqlSupport {
    public static final SmsFlashPromotionSession smsFlashPromotionSession = new SmsFlashPromotionSession();

    public static final SqlColumn<Long> id = smsFlashPromotionSession.id;

    public static final SqlColumn<String> name = smsFlashPromotionSession.name;

    public static final SqlColumn<LocalTime> startTime = smsFlashPromotionSession.startTime;

    public static final SqlColumn<LocalTime> endTime = smsFlashPromotionSession.endTime;

    public static final SqlColumn<Integer> status = smsFlashPromotionSession.status;

    public static final SqlColumn<LocalDateTime> createTime = smsFlashPromotionSession.createTime;

    public static final class SmsFlashPromotionSession extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<LocalTime> startTime = column("start_time", JDBCType.TIME);

        public final SqlColumn<LocalTime> endTime = column("end_time", JDBCType.TIME);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public SmsFlashPromotionSession() {
            super("sms_flash_promotion_session");
        }
    }
}