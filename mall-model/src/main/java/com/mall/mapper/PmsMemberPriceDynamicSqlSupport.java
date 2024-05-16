/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsMemberPriceDynamicSqlSupport {
    public static final PmsMemberPrice pmsMemberPrice = new PmsMemberPrice();

    public static final SqlColumn<Long> id = pmsMemberPrice.id;

    public static final SqlColumn<Long> productId = pmsMemberPrice.productId;

    public static final SqlColumn<Long> memberLevelId = pmsMemberPrice.memberLevelId;

    public static final SqlColumn<BigDecimal> memberPrice = pmsMemberPrice.memberPrice;

    public static final SqlColumn<String> memberLevelName = pmsMemberPrice.memberLevelName;

    public static final class PmsMemberPrice extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<Long> memberLevelId = column("member_level_id", JDBCType.BIGINT);

        public final SqlColumn<BigDecimal> memberPrice = column("member_price", JDBCType.DECIMAL);

        public final SqlColumn<String> memberLevelName = column("member_level_name", JDBCType.VARCHAR);

        public PmsMemberPrice() {
            super("pms_member_price");
        }
    }
}