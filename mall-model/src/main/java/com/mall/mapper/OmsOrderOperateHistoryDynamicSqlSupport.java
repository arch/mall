/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OmsOrderOperateHistoryDynamicSqlSupport {
    public static final OmsOrderOperateHistory omsOrderOperateHistory = new OmsOrderOperateHistory();

    public static final SqlColumn<Long> id = omsOrderOperateHistory.id;

    public static final SqlColumn<Long> orderId = omsOrderOperateHistory.orderId;

    public static final SqlColumn<String> operateMan = omsOrderOperateHistory.operateMan;

    public static final SqlColumn<Integer> orderStatus = omsOrderOperateHistory.orderStatus;

    public static final SqlColumn<String> note = omsOrderOperateHistory.note;

    public static final SqlColumn<LocalDateTime> createTime = omsOrderOperateHistory.createTime;

    public static final class OmsOrderOperateHistory extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> orderId = column("order_id", JDBCType.BIGINT);

        public final SqlColumn<String> operateMan = column("operate_man", JDBCType.VARCHAR);

        public final SqlColumn<Integer> orderStatus = column("order_status", JDBCType.INTEGER);

        public final SqlColumn<String> note = column("note", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public OmsOrderOperateHistory() {
            super("oms_order_operate_history");
        }
    }
}