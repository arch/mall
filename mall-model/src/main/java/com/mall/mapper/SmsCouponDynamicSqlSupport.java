/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsCouponDynamicSqlSupport {
    public static final SmsCoupon smsCoupon = new SmsCoupon();

    public static final SqlColumn<Long> id = smsCoupon.id;

    public static final SqlColumn<Integer> type = smsCoupon.type;

    public static final SqlColumn<String> name = smsCoupon.name;

    public static final SqlColumn<Integer> platform = smsCoupon.platform;

    public static final SqlColumn<Integer> count = smsCoupon.count;

    public static final SqlColumn<BigDecimal> amount = smsCoupon.amount;

    public static final SqlColumn<Integer> perLimit = smsCoupon.perLimit;

    public static final SqlColumn<BigDecimal> minPoint = smsCoupon.minPoint;

    public static final SqlColumn<LocalDateTime> startTime = smsCoupon.startTime;

    public static final SqlColumn<LocalDateTime> endTime = smsCoupon.endTime;

    public static final SqlColumn<Integer> useType = smsCoupon.useType;

    public static final SqlColumn<String> note = smsCoupon.note;

    public static final SqlColumn<Integer> publishCount = smsCoupon.publishCount;

    public static final SqlColumn<Integer> useCount = smsCoupon.useCount;

    public static final SqlColumn<Integer> receiveCount = smsCoupon.receiveCount;

    public static final SqlColumn<LocalDateTime> enableTime = smsCoupon.enableTime;

    public static final SqlColumn<String> code = smsCoupon.code;

    public static final SqlColumn<Integer> memberLevel = smsCoupon.memberLevel;

    public static final class SmsCoupon extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Integer> type = column("type", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> platform = column("platform", JDBCType.INTEGER);

        public final SqlColumn<Integer> count = column("count", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> amount = column("amount", JDBCType.DECIMAL);

        public final SqlColumn<Integer> perLimit = column("per_limit", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> minPoint = column("min_point", JDBCType.DECIMAL);

        public final SqlColumn<LocalDateTime> startTime = column("start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> endTime = column("end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> useType = column("use_type", JDBCType.INTEGER);

        public final SqlColumn<String> note = column("note", JDBCType.VARCHAR);

        public final SqlColumn<Integer> publishCount = column("publish_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> useCount = column("use_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> receiveCount = column("receive_count", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> enableTime = column("enable_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> code = column("code", JDBCType.VARCHAR);

        public final SqlColumn<Integer> memberLevel = column("member_level", JDBCType.INTEGER);

        public SmsCoupon() {
            super("sms_coupon");
        }
    }
}