/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsHomeAdvertiseDynamicSqlSupport {
    public static final SmsHomeAdvertise smsHomeAdvertise = new SmsHomeAdvertise();

    public static final SqlColumn<Long> id = smsHomeAdvertise.id;

    public static final SqlColumn<String> name = smsHomeAdvertise.name;

    public static final SqlColumn<Integer> type = smsHomeAdvertise.type;

    public static final SqlColumn<String> pic = smsHomeAdvertise.pic;

    public static final SqlColumn<LocalDateTime> startTime = smsHomeAdvertise.startTime;

    public static final SqlColumn<LocalDateTime> endTime = smsHomeAdvertise.endTime;

    public static final SqlColumn<Integer> status = smsHomeAdvertise.status;

    public static final SqlColumn<Integer> clickCount = smsHomeAdvertise.clickCount;

    public static final SqlColumn<Integer> orderCount = smsHomeAdvertise.orderCount;

    public static final SqlColumn<String> url = smsHomeAdvertise.url;

    public static final SqlColumn<String> note = smsHomeAdvertise.note;

    public static final SqlColumn<Integer> sort = smsHomeAdvertise.sort;

    public static final class SmsHomeAdvertise extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> type = column("type", JDBCType.INTEGER);

        public final SqlColumn<String> pic = column("pic", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> startTime = column("start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> endTime = column("end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<Integer> clickCount = column("click_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> orderCount = column("order_count", JDBCType.INTEGER);

        public final SqlColumn<String> url = column("url", JDBCType.VARCHAR);

        public final SqlColumn<String> note = column("note", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public SmsHomeAdvertise() {
            super("sms_home_advertise");
        }
    }
}