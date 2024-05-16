/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsIntegrationConsumeSettingDynamicSqlSupport {
    public static final UmsIntegrationConsumeSetting umsIntegrationConsumeSetting = new UmsIntegrationConsumeSetting();

    public static final SqlColumn<Long> id = umsIntegrationConsumeSetting.id;

    public static final SqlColumn<Integer> deductionPerAmount = umsIntegrationConsumeSetting.deductionPerAmount;

    public static final SqlColumn<Integer> maxPercentPerOrder = umsIntegrationConsumeSetting.maxPercentPerOrder;

    public static final SqlColumn<Integer> useUnit = umsIntegrationConsumeSetting.useUnit;

    public static final SqlColumn<Integer> couponStatus = umsIntegrationConsumeSetting.couponStatus;

    public static final class UmsIntegrationConsumeSetting extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Integer> deductionPerAmount = column("deduction_per_amount", JDBCType.INTEGER);

        public final SqlColumn<Integer> maxPercentPerOrder = column("max_percent_per_order", JDBCType.INTEGER);

        public final SqlColumn<Integer> useUnit = column("use_unit", JDBCType.INTEGER);

        public final SqlColumn<Integer> couponStatus = column("coupon_status", JDBCType.INTEGER);

        public UmsIntegrationConsumeSetting() {
            super("ums_integration_consume_setting");
        }
    }
}