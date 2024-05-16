/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsMemberTagDynamicSqlSupport {
    public static final UmsMemberTag umsMemberTag = new UmsMemberTag();

    public static final SqlColumn<Long> id = umsMemberTag.id;

    public static final SqlColumn<String> name = umsMemberTag.name;

    public static final SqlColumn<Integer> finishOrderCount = umsMemberTag.finishOrderCount;

    public static final SqlColumn<BigDecimal> finishOrderAmount = umsMemberTag.finishOrderAmount;

    public static final class UmsMemberTag extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> finishOrderCount = column("finish_order_count", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> finishOrderAmount = column("finish_order_amount", JDBCType.DECIMAL);

        public UmsMemberTag() {
            super("ums_member_tag");
        }
    }
}