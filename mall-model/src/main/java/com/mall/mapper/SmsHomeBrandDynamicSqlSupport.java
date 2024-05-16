/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsHomeBrandDynamicSqlSupport {
    public static final SmsHomeBrand smsHomeBrand = new SmsHomeBrand();

    public static final SqlColumn<Long> id = smsHomeBrand.id;

    public static final SqlColumn<Long> brandId = smsHomeBrand.brandId;

    public static final SqlColumn<String> brandName = smsHomeBrand.brandName;

    public static final SqlColumn<Integer> recommendStatus = smsHomeBrand.recommendStatus;

    public static final SqlColumn<Integer> sort = smsHomeBrand.sort;

    public static final class SmsHomeBrand extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> brandId = column("brand_id", JDBCType.BIGINT);

        public final SqlColumn<String> brandName = column("brand_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> recommendStatus = column("recommend_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public SmsHomeBrand() {
            super("sms_home_brand");
        }
    }
}