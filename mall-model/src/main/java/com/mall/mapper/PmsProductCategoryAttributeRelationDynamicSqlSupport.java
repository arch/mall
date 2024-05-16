/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsProductCategoryAttributeRelationDynamicSqlSupport {
    public static final PmsProductCategoryAttributeRelation pmsProductCategoryAttributeRelation = new PmsProductCategoryAttributeRelation();

    public static final SqlColumn<Long> id = pmsProductCategoryAttributeRelation.id;

    public static final SqlColumn<Long> categoryId = pmsProductCategoryAttributeRelation.categoryId;

    public static final SqlColumn<Long> attributeId = pmsProductCategoryAttributeRelation.attributeId;

    public static final class PmsProductCategoryAttributeRelation extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> categoryId = column("category_id", JDBCType.BIGINT);

        public final SqlColumn<Long> attributeId = column("attribute_id", JDBCType.BIGINT);

        public PmsProductCategoryAttributeRelation() {
            super("pms_product_category_attribute_relation");
        }
    }
}