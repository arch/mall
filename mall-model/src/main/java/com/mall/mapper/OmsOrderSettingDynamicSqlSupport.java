/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OmsOrderSettingDynamicSqlSupport {
    public static final OmsOrderSetting omsOrderSetting = new OmsOrderSetting();

    public static final SqlColumn<Long> id = omsOrderSetting.id;

    public static final SqlColumn<Integer> flashOrderOvertime = omsOrderSetting.flashOrderOvertime;

    public static final SqlColumn<Integer> normalOrderOvertime = omsOrderSetting.normalOrderOvertime;

    public static final SqlColumn<Integer> confirmOvertime = omsOrderSetting.confirmOvertime;

    public static final SqlColumn<Integer> finishOvertime = omsOrderSetting.finishOvertime;

    public static final SqlColumn<Integer> commentOvertime = omsOrderSetting.commentOvertime;

    public static final class OmsOrderSetting extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Integer> flashOrderOvertime = column("flash_order_overtime", JDBCType.INTEGER);

        public final SqlColumn<Integer> normalOrderOvertime = column("normal_order_overtime", JDBCType.INTEGER);

        public final SqlColumn<Integer> confirmOvertime = column("confirm_overtime", JDBCType.INTEGER);

        public final SqlColumn<Integer> finishOvertime = column("finish_overtime", JDBCType.INTEGER);

        public final SqlColumn<Integer> commentOvertime = column("comment_overtime", JDBCType.INTEGER);

        public OmsOrderSetting() {
            super("oms_order_setting");
        }
    }
}