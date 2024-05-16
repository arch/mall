/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CmsPreferenceAreaDynamicSqlSupport {
    public static final CmsPreferenceArea cmsPreferenceArea = new CmsPreferenceArea();

    public static final SqlColumn<Long> id = cmsPreferenceArea.id;

    public static final SqlColumn<String> name = cmsPreferenceArea.name;

    public static final SqlColumn<String> subTitle = cmsPreferenceArea.subTitle;

    public static final SqlColumn<Integer> sort = cmsPreferenceArea.sort;

    public static final SqlColumn<Integer> showStatus = cmsPreferenceArea.showStatus;

    public static final SqlColumn<byte[]> pic = cmsPreferenceArea.pic;

    public static final class CmsPreferenceArea extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> subTitle = column("sub_title", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<Integer> showStatus = column("show_status", JDBCType.INTEGER);

        public final SqlColumn<byte[]> pic = column("pic", JDBCType.VARBINARY);

        public CmsPreferenceArea() {
            super("cms_preference_area");
        }
    }
}