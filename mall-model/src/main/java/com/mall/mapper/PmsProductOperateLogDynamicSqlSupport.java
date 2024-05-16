/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsProductOperateLogDynamicSqlSupport {
    public static final PmsProductOperateLog pmsProductOperateLog = new PmsProductOperateLog();

    public static final SqlColumn<Long> id = pmsProductOperateLog.id;

    public static final SqlColumn<Long> productId = pmsProductOperateLog.productId;

    public static final SqlColumn<BigDecimal> priceOld = pmsProductOperateLog.priceOld;

    public static final SqlColumn<BigDecimal> priceNew = pmsProductOperateLog.priceNew;

    public static final SqlColumn<BigDecimal> salePriceOld = pmsProductOperateLog.salePriceOld;

    public static final SqlColumn<BigDecimal> salePriceNew = pmsProductOperateLog.salePriceNew;

    public static final SqlColumn<Integer> giftPointOld = pmsProductOperateLog.giftPointOld;

    public static final SqlColumn<Integer> giftPointNew = pmsProductOperateLog.giftPointNew;

    public static final SqlColumn<Integer> usePointLimitOld = pmsProductOperateLog.usePointLimitOld;

    public static final SqlColumn<Integer> usePointLimitNew = pmsProductOperateLog.usePointLimitNew;

    public static final SqlColumn<String> operateMan = pmsProductOperateLog.operateMan;

    public static final SqlColumn<LocalDateTime> createTime = pmsProductOperateLog.createTime;

    public static final class PmsProductOperateLog extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<BigDecimal> priceOld = column("price_old", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> priceNew = column("price_new", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> salePriceOld = column("sale_price_old", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> salePriceNew = column("sale_price_new", JDBCType.DECIMAL);

        public final SqlColumn<Integer> giftPointOld = column("gift_point_old", JDBCType.INTEGER);

        public final SqlColumn<Integer> giftPointNew = column("gift_point_new", JDBCType.INTEGER);

        public final SqlColumn<Integer> usePointLimitOld = column("use_point_limit_old", JDBCType.INTEGER);

        public final SqlColumn<Integer> usePointLimitNew = column("use_point_limit_new", JDBCType.INTEGER);

        public final SqlColumn<String> operateMan = column("operate_man", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public PmsProductOperateLog() {
            super("pms_product_operate_log");
        }
    }
}