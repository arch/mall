/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsFreightTemplateDynamicSqlSupport {
    public static final PmsFreightTemplate pmsFreightTemplate = new PmsFreightTemplate();

    public static final SqlColumn<Long> id = pmsFreightTemplate.id;

    public static final SqlColumn<String> name = pmsFreightTemplate.name;

    public static final SqlColumn<Integer> chargeType = pmsFreightTemplate.chargeType;

    public static final SqlColumn<BigDecimal> firstWeight = pmsFreightTemplate.firstWeight;

    public static final SqlColumn<BigDecimal> firstFee = pmsFreightTemplate.firstFee;

    public static final SqlColumn<BigDecimal> continueWeight = pmsFreightTemplate.continueWeight;

    public static final SqlColumn<BigDecimal> continueFee = pmsFreightTemplate.continueFee;

    public static final SqlColumn<String> dest = pmsFreightTemplate.dest;

    public static final class PmsFreightTemplate extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> chargeType = column("charge_type", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> firstWeight = column("first_weight", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> firstFee = column("first_fee", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> continueWeight = column("continue_weight", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> continueFee = column("continue_fee", JDBCType.DECIMAL);

        public final SqlColumn<String> dest = column("dest", JDBCType.VARCHAR);

        public PmsFreightTemplate() {
            super("pms_freight_template");
        }
    }
}