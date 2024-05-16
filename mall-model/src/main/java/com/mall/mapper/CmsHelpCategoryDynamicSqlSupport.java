/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CmsHelpCategoryDynamicSqlSupport {
    public static final CmsHelpCategory cmsHelpCategory = new CmsHelpCategory();

    public static final SqlColumn<Long> id = cmsHelpCategory.id;

    public static final SqlColumn<String> name = cmsHelpCategory.name;

    public static final SqlColumn<String> icon = cmsHelpCategory.icon;

    public static final SqlColumn<Integer> helpCount = cmsHelpCategory.helpCount;

    public static final SqlColumn<Integer> showStatus = cmsHelpCategory.showStatus;

    public static final SqlColumn<Integer> sort = cmsHelpCategory.sort;

    public static final class CmsHelpCategory extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> icon = column("icon", JDBCType.VARCHAR);

        public final SqlColumn<Integer> helpCount = column("help_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> showStatus = column("show_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public CmsHelpCategory() {
            super("cms_help_category");
        }
    }
}