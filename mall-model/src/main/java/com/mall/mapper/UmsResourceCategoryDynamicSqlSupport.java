/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsResourceCategoryDynamicSqlSupport {
    public static final UmsResourceCategory umsResourceCategory = new UmsResourceCategory();

    public static final SqlColumn<Long> id = umsResourceCategory.id;

    public static final SqlColumn<String> name = umsResourceCategory.name;

    public static final SqlColumn<Integer> sort = umsResourceCategory.sort;

    public static final SqlColumn<LocalDateTime> createTime = umsResourceCategory.createTime;

    public static final class UmsResourceCategory extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public UmsResourceCategory() {
            super("ums_resource_category");
        }
    }
}