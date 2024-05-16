/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsFlashPromotionDynamicSqlSupport {
    public static final SmsFlashPromotion smsFlashPromotion = new SmsFlashPromotion();

    public static final SqlColumn<Long> id = smsFlashPromotion.id;

    public static final SqlColumn<String> title = smsFlashPromotion.title;

    public static final SqlColumn<LocalDate> startDate = smsFlashPromotion.startDate;

    public static final SqlColumn<LocalDate> endDate = smsFlashPromotion.endDate;

    public static final SqlColumn<Integer> status = smsFlashPromotion.status;

    public static final SqlColumn<LocalDateTime> createTime = smsFlashPromotion.createTime;

    public static final class SmsFlashPromotion extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);

        public final SqlColumn<LocalDate> startDate = column("start_date", JDBCType.DATE);

        public final SqlColumn<LocalDate> endDate = column("end_date", JDBCType.DATE);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public SmsFlashPromotion() {
            super("sms_flash_promotion");
        }
    }
}