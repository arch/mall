/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OmsOrderReturnReasonDynamicSqlSupport {
    public static final OmsOrderReturnReason omsOrderReturnReason = new OmsOrderReturnReason();

    public static final SqlColumn<Long> id = omsOrderReturnReason.id;

    public static final SqlColumn<String> name = omsOrderReturnReason.name;

    public static final SqlColumn<Integer> sort = omsOrderReturnReason.sort;

    public static final SqlColumn<Integer> status = omsOrderReturnReason.status;

    public static final SqlColumn<LocalDateTime> createTime = omsOrderReturnReason.createTime;

    public static final class OmsOrderReturnReason extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public OmsOrderReturnReason() {
            super("oms_order_return_reason");
        }
    }
}