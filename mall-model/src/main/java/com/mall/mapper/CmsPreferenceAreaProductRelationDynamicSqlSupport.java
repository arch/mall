/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CmsPreferenceAreaProductRelationDynamicSqlSupport {
    public static final CmsPreferenceAreaProductRelation cmsPreferenceAreaProductRelation = new CmsPreferenceAreaProductRelation();

    public static final SqlColumn<Long> id = cmsPreferenceAreaProductRelation.id;

    public static final SqlColumn<Long> preferenceAreaId = cmsPreferenceAreaProductRelation.preferenceAreaId;

    public static final SqlColumn<Long> productId = cmsPreferenceAreaProductRelation.productId;

    public static final class CmsPreferenceAreaProductRelation extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> preferenceAreaId = column("preference_area_id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public CmsPreferenceAreaProductRelation() {
            super("cms_preference_area_product_relation");
        }
    }
}