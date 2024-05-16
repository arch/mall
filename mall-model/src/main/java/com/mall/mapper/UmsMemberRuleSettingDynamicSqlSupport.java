/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsMemberRuleSettingDynamicSqlSupport {
    public static final UmsMemberRuleSetting umsMemberRuleSetting = new UmsMemberRuleSetting();

    public static final SqlColumn<Long> id = umsMemberRuleSetting.id;

    public static final SqlColumn<Integer> continueSignDay = umsMemberRuleSetting.continueSignDay;

    public static final SqlColumn<Integer> continueSignPoint = umsMemberRuleSetting.continueSignPoint;

    public static final SqlColumn<BigDecimal> consumePerPoint = umsMemberRuleSetting.consumePerPoint;

    public static final SqlColumn<BigDecimal> lowOrderAmount = umsMemberRuleSetting.lowOrderAmount;

    public static final SqlColumn<Integer> maxPointPerOrder = umsMemberRuleSetting.maxPointPerOrder;

    public static final SqlColumn<Integer> type = umsMemberRuleSetting.type;

    public static final class UmsMemberRuleSetting extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Integer> continueSignDay = column("continue_sign_day", JDBCType.INTEGER);

        public final SqlColumn<Integer> continueSignPoint = column("continue_sign_point", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> consumePerPoint = column("consume_per_point", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> lowOrderAmount = column("low_order_amount", JDBCType.DECIMAL);

        public final SqlColumn<Integer> maxPointPerOrder = column("max_point_per_order", JDBCType.INTEGER);

        public final SqlColumn<Integer> type = column("type", JDBCType.INTEGER);

        public UmsMemberRuleSetting() {
            super("ums_member_rule_setting");
        }
    }
}