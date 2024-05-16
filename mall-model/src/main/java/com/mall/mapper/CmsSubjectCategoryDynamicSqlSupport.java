/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CmsSubjectCategoryDynamicSqlSupport {
    public static final CmsSubjectCategory cmsSubjectCategory = new CmsSubjectCategory();

    public static final SqlColumn<Long> id = cmsSubjectCategory.id;

    public static final SqlColumn<String> name = cmsSubjectCategory.name;

    public static final SqlColumn<String> icon = cmsSubjectCategory.icon;

    public static final SqlColumn<Integer> subjectCount = cmsSubjectCategory.subjectCount;

    public static final SqlColumn<Integer> showStatus = cmsSubjectCategory.showStatus;

    public static final SqlColumn<Integer> sort = cmsSubjectCategory.sort;

    public static final class CmsSubjectCategory extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> icon = column("icon", JDBCType.VARCHAR);

        public final SqlColumn<Integer> subjectCount = column("subject_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> showStatus = column("show_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public CmsSubjectCategory() {
            super("cms_subject_category");
        }
    }
}