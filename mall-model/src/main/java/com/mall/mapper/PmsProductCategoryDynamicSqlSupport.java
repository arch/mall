/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsProductCategoryDynamicSqlSupport {
    public static final PmsProductCategory pmsProductCategory = new PmsProductCategory();

    public static final SqlColumn<Long> id = pmsProductCategory.id;

    public static final SqlColumn<Long> parentId = pmsProductCategory.parentId;

    public static final SqlColumn<String> name = pmsProductCategory.name;

    public static final SqlColumn<Integer> level = pmsProductCategory.level;

    public static final SqlColumn<Integer> productCount = pmsProductCategory.productCount;

    public static final SqlColumn<String> productUnit = pmsProductCategory.productUnit;

    public static final SqlColumn<Integer> navStatus = pmsProductCategory.navStatus;

    public static final SqlColumn<Integer> showStatus = pmsProductCategory.showStatus;

    public static final SqlColumn<Integer> sort = pmsProductCategory.sort;

    public static final SqlColumn<String> icon = pmsProductCategory.icon;

    public static final SqlColumn<String> keywords = pmsProductCategory.keywords;

    public static final SqlColumn<String> description = pmsProductCategory.description;

    public static final class PmsProductCategory extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> parentId = column("parent_id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> level = column("level", JDBCType.INTEGER);

        public final SqlColumn<Integer> productCount = column("product_count", JDBCType.INTEGER);

        public final SqlColumn<String> productUnit = column("product_unit", JDBCType.VARCHAR);

        public final SqlColumn<Integer> navStatus = column("nav_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> showStatus = column("show_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<String> icon = column("icon", JDBCType.VARCHAR);

        public final SqlColumn<String> keywords = column("keywords", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.LONGVARCHAR);

        public PmsProductCategory() {
            super("pms_product_category");
        }
    }
}