/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CmsTopicCategoryDynamicSqlSupport {
    public static final CmsTopicCategory cmsTopicCategory = new CmsTopicCategory();

    public static final SqlColumn<Long> id = cmsTopicCategory.id;

    public static final SqlColumn<String> name = cmsTopicCategory.name;

    public static final SqlColumn<String> icon = cmsTopicCategory.icon;

    public static final SqlColumn<Integer> subjectCount = cmsTopicCategory.subjectCount;

    public static final SqlColumn<Integer> showStatus = cmsTopicCategory.showStatus;

    public static final SqlColumn<Integer> sort = cmsTopicCategory.sort;

    public static final class CmsTopicCategory extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> icon = column("icon", JDBCType.VARCHAR);

        public final SqlColumn<Integer> subjectCount = column("subject_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> showStatus = column("show_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public CmsTopicCategory() {
            super("cms_topic_category");
        }
    }
}