/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsMemberTaskDynamicSqlSupport {
    public static final UmsMemberTask umsMemberTask = new UmsMemberTask();

    public static final SqlColumn<Long> id = umsMemberTask.id;

    public static final SqlColumn<String> name = umsMemberTask.name;

    public static final SqlColumn<Integer> growth = umsMemberTask.growth;

    public static final SqlColumn<Integer> integration = umsMemberTask.integration;

    public static final SqlColumn<Integer> type = umsMemberTask.type;

    public static final class UmsMemberTask extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> growth = column("growth", JDBCType.INTEGER);

        public final SqlColumn<Integer> integration = column("integration", JDBCType.INTEGER);

        public final SqlColumn<Integer> type = column("type", JDBCType.INTEGER);

        public UmsMemberTask() {
            super("ums_member_task");
        }
    }
}