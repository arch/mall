/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsRoleDynamicSqlSupport {
    public static final UmsRole umsRole = new UmsRole();

    public static final SqlColumn<Long> id = umsRole.id;

    public static final SqlColumn<String> name = umsRole.name;

    public static final SqlColumn<String> description = umsRole.description;

    public static final SqlColumn<Integer> adminCount = umsRole.adminCount;

    public static final SqlColumn<Integer> status = umsRole.status;

    public static final SqlColumn<Integer> sort = umsRole.sort;

    public static final SqlColumn<LocalDateTime> createTime = umsRole.createTime;

    public static final class UmsRole extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<Integer> adminCount = column("admin_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public UmsRole() {
            super("ums_role");
        }
    }
}