/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsCouponProductRelationDynamicSqlSupport {
    public static final SmsCouponProductRelation smsCouponProductRelation = new SmsCouponProductRelation();

    public static final SqlColumn<Long> id = smsCouponProductRelation.id;

    public static final SqlColumn<Long> couponId = smsCouponProductRelation.couponId;

    public static final SqlColumn<Long> productId = smsCouponProductRelation.productId;

    public static final SqlColumn<String> productName = smsCouponProductRelation.productName;

    public static final SqlColumn<String> productSn = smsCouponProductRelation.productSn;

    public static final class SmsCouponProductRelation extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> couponId = column("coupon_id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<String> productName = column("product_name", JDBCType.VARCHAR);

        public final SqlColumn<String> productSn = column("product_sn", JDBCType.VARCHAR);

        public SmsCouponProductRelation() {
            super("sms_coupon_product_relation");
        }
    }
}