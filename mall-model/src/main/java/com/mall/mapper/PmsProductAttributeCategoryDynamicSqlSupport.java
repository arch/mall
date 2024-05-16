/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsProductAttributeCategoryDynamicSqlSupport {
    public static final PmsProductAttributeCategory pmsProductAttributeCategory = new PmsProductAttributeCategory();

    public static final SqlColumn<Long> id = pmsProductAttributeCategory.id;

    public static final SqlColumn<String> name = pmsProductAttributeCategory.name;

    public static final SqlColumn<Integer> attributeCount = pmsProductAttributeCategory.attributeCount;

    public static final SqlColumn<Integer> paramCount = pmsProductAttributeCategory.paramCount;

    public static final class PmsProductAttributeCategory extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> attributeCount = column("attribute_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> paramCount = column("param_count", JDBCType.INTEGER);

        public PmsProductAttributeCategory() {
            super("pms_product_attribute_category");
        }
    }
}