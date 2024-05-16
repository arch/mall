/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsAdminLoginLogDynamicSqlSupport {
    public static final UmsAdminLoginLog umsAdminLoginLog = new UmsAdminLoginLog();

    public static final SqlColumn<Long> id = umsAdminLoginLog.id;

    public static final SqlColumn<Long> adminId = umsAdminLoginLog.adminId;

    public static final SqlColumn<LocalDateTime> createTime = umsAdminLoginLog.createTime;

    public static final SqlColumn<String> ip = umsAdminLoginLog.ip;

    public static final SqlColumn<String> address = umsAdminLoginLog.address;

    public static final SqlColumn<String> userAgent = umsAdminLoginLog.userAgent;

    public static final class UmsAdminLoginLog extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> adminId = column("admin_id", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> ip = column("ip", JDBCType.VARCHAR);

        public final SqlColumn<String> address = column("address", JDBCType.VARCHAR);

        public final SqlColumn<String> userAgent = column("user_agent", JDBCType.VARCHAR);

        public UmsAdminLoginLog() {
            super("ums_admin_login_log");
        }
    }
}