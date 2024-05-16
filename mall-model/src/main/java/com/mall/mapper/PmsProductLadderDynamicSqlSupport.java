/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsProductLadderDynamicSqlSupport {
    public static final PmsProductLadder pmsProductLadder = new PmsProductLadder();

    public static final SqlColumn<Long> id = pmsProductLadder.id;

    public static final SqlColumn<Long> productId = pmsProductLadder.productId;

    public static final SqlColumn<Integer> count = pmsProductLadder.count;

    public static final SqlColumn<BigDecimal> discount = pmsProductLadder.discount;

    public static final SqlColumn<BigDecimal> price = pmsProductLadder.price;

    public static final class PmsProductLadder extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<Integer> count = column("count", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> discount = column("discount", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> price = column("price", JDBCType.DECIMAL);

        public PmsProductLadder() {
            super("pms_product_ladder");
        }
    }
}