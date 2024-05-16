/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsProductFullReductionDynamicSqlSupport {
    public static final PmsProductFullReduction pmsProductFullReduction = new PmsProductFullReduction();

    public static final SqlColumn<Long> id = pmsProductFullReduction.id;

    public static final SqlColumn<Long> productId = pmsProductFullReduction.productId;

    public static final SqlColumn<BigDecimal> fullPrice = pmsProductFullReduction.fullPrice;

    public static final SqlColumn<BigDecimal> reducePrice = pmsProductFullReduction.reducePrice;

    public static final class PmsProductFullReduction extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<BigDecimal> fullPrice = column("full_price", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> reducePrice = column("reduce_price", JDBCType.DECIMAL);

        public PmsProductFullReduction() {
            super("pms_product_full_reduction");
        }
    }
}