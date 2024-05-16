/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsCouponProductCategoryRelationDynamicSqlSupport {
    public static final SmsCouponProductCategoryRelation smsCouponProductCategoryRelation = new SmsCouponProductCategoryRelation();

    public static final SqlColumn<Long> id = smsCouponProductCategoryRelation.id;

    public static final SqlColumn<Long> couponId = smsCouponProductCategoryRelation.couponId;

    public static final SqlColumn<Long> productCategoryId = smsCouponProductCategoryRelation.productCategoryId;

    public static final SqlColumn<String> productCategoryName = smsCouponProductCategoryRelation.productCategoryName;

    public static final SqlColumn<String> parentCategoryName = smsCouponProductCategoryRelation.parentCategoryName;

    public static final class SmsCouponProductCategoryRelation extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> couponId = column("coupon_id", JDBCType.BIGINT);

        public final SqlColumn<Long> productCategoryId = column("product_category_id", JDBCType.BIGINT);

        public final SqlColumn<String> productCategoryName = column("product_category_name", JDBCType.VARCHAR);

        public final SqlColumn<String> parentCategoryName = column("parent_category_name", JDBCType.VARCHAR);

        public SmsCouponProductCategoryRelation() {
            super("sms_coupon_product_category_relation");
        }
    }
}