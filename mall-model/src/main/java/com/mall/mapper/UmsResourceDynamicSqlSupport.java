/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsResourceDynamicSqlSupport {
    public static final UmsResource umsResource = new UmsResource();

    public static final SqlColumn<Long> id = umsResource.id;

    public static final SqlColumn<String> name = umsResource.name;

    public static final SqlColumn<String> url = umsResource.url;

    public static final SqlColumn<String> description = umsResource.description;

    public static final SqlColumn<Long> categoryId = umsResource.categoryId;

    public static final SqlColumn<LocalDateTime> createTime = umsResource.createTime;

    public static final class UmsResource extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> url = column("url", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<Long> categoryId = column("category_id", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public UmsResource() {
            super("ums_resource");
        }
    }
}