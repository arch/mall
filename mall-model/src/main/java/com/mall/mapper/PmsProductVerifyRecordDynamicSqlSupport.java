/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsProductVerifyRecordDynamicSqlSupport {
    public static final PmsProductVerifyRecord pmsProductVerifyRecord = new PmsProductVerifyRecord();

    public static final SqlColumn<Long> id = pmsProductVerifyRecord.id;

    public static final SqlColumn<Long> productId = pmsProductVerifyRecord.productId;

    public static final SqlColumn<LocalDateTime> createTime = pmsProductVerifyRecord.createTime;

    public static final SqlColumn<String> verifyMan = pmsProductVerifyRecord.verifyMan;

    public static final SqlColumn<Integer> status = pmsProductVerifyRecord.status;

    public static final SqlColumn<String> detail = pmsProductVerifyRecord.detail;

    public static final class PmsProductVerifyRecord extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> verifyMan = column("verify_man", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<String> detail = column("detail", JDBCType.VARCHAR);

        public PmsProductVerifyRecord() {
            super("pms_product_verify_record");
        }
    }
}