/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsGrowthChangeHistoryDynamicSqlSupport {
    public static final UmsGrowthChangeHistory umsGrowthChangeHistory = new UmsGrowthChangeHistory();

    public static final SqlColumn<Long> id = umsGrowthChangeHistory.id;

    public static final SqlColumn<Long> memberId = umsGrowthChangeHistory.memberId;

    public static final SqlColumn<LocalDateTime> createTime = umsGrowthChangeHistory.createTime;

    public static final SqlColumn<Integer> changeType = umsGrowthChangeHistory.changeType;

    public static final SqlColumn<Integer> changeCount = umsGrowthChangeHistory.changeCount;

    public static final SqlColumn<String> operateMan = umsGrowthChangeHistory.operateMan;

    public static final SqlColumn<String> operateNote = umsGrowthChangeHistory.operateNote;

    public static final SqlColumn<Integer> sourceType = umsGrowthChangeHistory.sourceType;

    public static final class UmsGrowthChangeHistory extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> memberId = column("member_id", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> changeType = column("change_type", JDBCType.INTEGER);

        public final SqlColumn<Integer> changeCount = column("change_count", JDBCType.INTEGER);

        public final SqlColumn<String> operateMan = column("operate_man", JDBCType.VARCHAR);

        public final SqlColumn<String> operateNote = column("operate_note", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sourceType = column("source_type", JDBCType.INTEGER);

        public UmsGrowthChangeHistory() {
            super("ums_growth_change_history");
        }
    }
}