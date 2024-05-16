/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CmsMemberReportDynamicSqlSupport {
    public static final CmsMemberReport cmsMemberReport = new CmsMemberReport();

    public static final SqlColumn<Long> id = cmsMemberReport.id;

    public static final SqlColumn<Integer> reportType = cmsMemberReport.reportType;

    public static final SqlColumn<String> reportMemberName = cmsMemberReport.reportMemberName;

    public static final SqlColumn<String> reportObject = cmsMemberReport.reportObject;

    public static final SqlColumn<Integer> reportStatus = cmsMemberReport.reportStatus;

    public static final SqlColumn<Integer> handleStatus = cmsMemberReport.handleStatus;

    public static final SqlColumn<String> note = cmsMemberReport.note;

    public static final SqlColumn<LocalDateTime> createTime = cmsMemberReport.createTime;

    public static final class CmsMemberReport extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Integer> reportType = column("report_type", JDBCType.INTEGER);

        public final SqlColumn<String> reportMemberName = column("report_member_name", JDBCType.VARCHAR);

        public final SqlColumn<String> reportObject = column("report_object", JDBCType.VARCHAR);

        public final SqlColumn<Integer> reportStatus = column("report_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> handleStatus = column("handle_status", JDBCType.INTEGER);

        public final SqlColumn<String> note = column("note", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public CmsMemberReport() {
            super("cms_member_report");
        }
    }
}