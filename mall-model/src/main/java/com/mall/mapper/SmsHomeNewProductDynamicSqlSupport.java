/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsHomeNewProductDynamicSqlSupport {
    public static final SmsHomeNewProduct smsHomeNewProduct = new SmsHomeNewProduct();

    public static final SqlColumn<Long> id = smsHomeNewProduct.id;

    public static final SqlColumn<Long> productId = smsHomeNewProduct.productId;

    public static final SqlColumn<String> productName = smsHomeNewProduct.productName;

    public static final SqlColumn<Integer> recommendStatus = smsHomeNewProduct.recommendStatus;

    public static final SqlColumn<Integer> sort = smsHomeNewProduct.sort;

    public static final class SmsHomeNewProduct extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<String> productName = column("product_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> recommendStatus = column("recommend_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public SmsHomeNewProduct() {
            super("sms_home_new_product");
        }
    }
}