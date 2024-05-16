/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsMemberProductCategoryRelationDynamicSqlSupport {
    public static final UmsMemberProductCategoryRelation umsMemberProductCategoryRelation = new UmsMemberProductCategoryRelation();

    public static final SqlColumn<Long> id = umsMemberProductCategoryRelation.id;

    public static final SqlColumn<Long> memberId = umsMemberProductCategoryRelation.memberId;

    public static final SqlColumn<Long> productCategoryId = umsMemberProductCategoryRelation.productCategoryId;

    public static final class UmsMemberProductCategoryRelation extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> memberId = column("member_id", JDBCType.BIGINT);

        public final SqlColumn<Long> productCategoryId = column("product_category_id", JDBCType.BIGINT);

        public UmsMemberProductCategoryRelation() {
            super("ums_member_product_category_relation");
        }
    }
}