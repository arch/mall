/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsCouponHistoryDynamicSqlSupport {
    public static final SmsCouponHistory smsCouponHistory = new SmsCouponHistory();

    public static final SqlColumn<Long> id = smsCouponHistory.id;

    public static final SqlColumn<Long> couponId = smsCouponHistory.couponId;

    public static final SqlColumn<Long> memberId = smsCouponHistory.memberId;

    public static final SqlColumn<String> couponCode = smsCouponHistory.couponCode;

    public static final SqlColumn<String> memberNickname = smsCouponHistory.memberNickname;

    public static final SqlColumn<Integer> getType = smsCouponHistory.getType;

    public static final SqlColumn<Integer> useStatus = smsCouponHistory.useStatus;

    public static final SqlColumn<LocalDateTime> useTime = smsCouponHistory.useTime;

    public static final SqlColumn<Long> orderId = smsCouponHistory.orderId;

    public static final SqlColumn<String> orderSn = smsCouponHistory.orderSn;

    public static final SqlColumn<LocalDateTime> createTime = smsCouponHistory.createTime;

    public static final class SmsCouponHistory extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> couponId = column("coupon_id", JDBCType.BIGINT);

        public final SqlColumn<Long> memberId = column("member_id", JDBCType.BIGINT);

        public final SqlColumn<String> couponCode = column("coupon_code", JDBCType.VARCHAR);

        public final SqlColumn<String> memberNickname = column("member_nickname", JDBCType.VARCHAR);

        public final SqlColumn<Integer> getType = column("get_type", JDBCType.INTEGER);

        public final SqlColumn<Integer> useStatus = column("use_status", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> useTime = column("use_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> orderId = column("order_id", JDBCType.BIGINT);

        public final SqlColumn<String> orderSn = column("order_sn", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public SmsCouponHistory() {
            super("sms_coupon_history");
        }
    }
}