/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CmsHelpDynamicSqlSupport {
    public static final CmsHelp cmsHelp = new CmsHelp();

    public static final SqlColumn<Long> id = cmsHelp.id;

    public static final SqlColumn<Long> categoryId = cmsHelp.categoryId;

    public static final SqlColumn<String> icon = cmsHelp.icon;

    public static final SqlColumn<String> title = cmsHelp.title;

    public static final SqlColumn<Integer> showStatus = cmsHelp.showStatus;

    public static final SqlColumn<Integer> readCount = cmsHelp.readCount;

    public static final SqlColumn<LocalDateTime> createTime = cmsHelp.createTime;

    public static final SqlColumn<String> content = cmsHelp.content;

    public static final class CmsHelp extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> categoryId = column("category_id", JDBCType.BIGINT);

        public final SqlColumn<String> icon = column("icon", JDBCType.VARCHAR);

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);

        public final SqlColumn<Integer> showStatus = column("show_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> readCount = column("read_count", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public CmsHelp() {
            super("cms_help");
        }
    }
}