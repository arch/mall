/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CmsSubjectProductRelationDynamicSqlSupport {
    public static final CmsSubjectProductRelation cmsSubjectProductRelation = new CmsSubjectProductRelation();

    public static final SqlColumn<Long> id = cmsSubjectProductRelation.id;

    public static final SqlColumn<Long> subjectId = cmsSubjectProductRelation.subjectId;

    public static final SqlColumn<Long> productId = cmsSubjectProductRelation.productId;

    public static final class CmsSubjectProductRelation extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> subjectId = column("subject_id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public CmsSubjectProductRelation() {
            super("cms_subject_product_relation");
        }
    }
}