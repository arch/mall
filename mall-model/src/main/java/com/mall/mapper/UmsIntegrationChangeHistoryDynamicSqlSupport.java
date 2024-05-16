/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsIntegrationChangeHistoryDynamicSqlSupport {
    public static final UmsIntegrationChangeHistory umsIntegrationChangeHistory = new UmsIntegrationChangeHistory();

    public static final SqlColumn<Long> id = umsIntegrationChangeHistory.id;

    public static final SqlColumn<Long> memberId = umsIntegrationChangeHistory.memberId;

    public static final SqlColumn<LocalDateTime> createTime = umsIntegrationChangeHistory.createTime;

    public static final SqlColumn<Integer> changeType = umsIntegrationChangeHistory.changeType;

    public static final SqlColumn<Integer> changeCount = umsIntegrationChangeHistory.changeCount;

    public static final SqlColumn<String> operateMan = umsIntegrationChangeHistory.operateMan;

    public static final SqlColumn<String> operateNote = umsIntegrationChangeHistory.operateNote;

    public static final SqlColumn<Integer> sourceType = umsIntegrationChangeHistory.sourceType;

    public static final class UmsIntegrationChangeHistory extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> memberId = column("member_id", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> changeType = column("change_type", JDBCType.INTEGER);

        public final SqlColumn<Integer> changeCount = column("change_count", JDBCType.INTEGER);

        public final SqlColumn<String> operateMan = column("operate_man", JDBCType.VARCHAR);

        public final SqlColumn<String> operateNote = column("operate_note", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sourceType = column("source_type", JDBCType.INTEGER);

        public UmsIntegrationChangeHistory() {
            super("ums_integration_change_history");
        }
    }
}