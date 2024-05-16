/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsHomeRecommendProductDynamicSqlSupport {
    public static final SmsHomeRecommendProduct smsHomeRecommendProduct = new SmsHomeRecommendProduct();

    public static final SqlColumn<Long> id = smsHomeRecommendProduct.id;

    public static final SqlColumn<Long> productId = smsHomeRecommendProduct.productId;

    public static final SqlColumn<String> productName = smsHomeRecommendProduct.productName;

    public static final SqlColumn<Integer> recommendStatus = smsHomeRecommendProduct.recommendStatus;

    public static final SqlColumn<Integer> sort = smsHomeRecommendProduct.sort;

    public static final class SmsHomeRecommendProduct extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<String> productName = column("product_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> recommendStatus = column("recommend_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public SmsHomeRecommendProduct() {
            super("sms_home_recommend_product");
        }
    }
}