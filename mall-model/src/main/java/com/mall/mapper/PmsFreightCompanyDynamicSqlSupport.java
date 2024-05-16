/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsFreightCompanyDynamicSqlSupport {
    public static final PmsFreightCompany pmsFreightCompany = new PmsFreightCompany();

    public static final SqlColumn<Long> id = pmsFreightCompany.id;

    public static final SqlColumn<String> name = pmsFreightCompany.name;

    public static final SqlColumn<String> code = pmsFreightCompany.code;

    public static final SqlColumn<String> kind = pmsFreightCompany.kind;

    public static final SqlColumn<LocalDateTime> createTime = pmsFreightCompany.createTime;

    public static final class PmsFreightCompany extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> code = column("code", JDBCType.VARCHAR);

        public final SqlColumn<String> kind = column("kind", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public PmsFreightCompany() {
            super("pms_freight_company");
        }
    }
}