/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsMenuDynamicSqlSupport {
    public static final UmsMenu umsMenu = new UmsMenu();

    public static final SqlColumn<Long> id = umsMenu.id;

    public static final SqlColumn<Long> parentId = umsMenu.parentId;

    public static final SqlColumn<String> title = umsMenu.title;

    public static final SqlColumn<Integer> level = umsMenu.level;

    public static final SqlColumn<Integer> sort = umsMenu.sort;

    public static final SqlColumn<String> name = umsMenu.name;

    public static final SqlColumn<String> icon = umsMenu.icon;

    public static final SqlColumn<Integer> hidden = umsMenu.hidden;

    public static final SqlColumn<LocalDateTime> createTime = umsMenu.createTime;

    public static final class UmsMenu extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> parentId = column("parent_id", JDBCType.BIGINT);

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);

        public final SqlColumn<Integer> level = column("level", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> icon = column("icon", JDBCType.VARCHAR);

        public final SqlColumn<Integer> hidden = column("hidden", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public UmsMenu() {
            super("ums_menu");
        }
    }
}