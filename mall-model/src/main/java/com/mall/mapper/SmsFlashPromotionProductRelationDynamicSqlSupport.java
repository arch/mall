/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsFlashPromotionProductRelationDynamicSqlSupport {
    public static final SmsFlashPromotionProductRelation smsFlashPromotionProductRelation = new SmsFlashPromotionProductRelation();

    public static final SqlColumn<Long> id = smsFlashPromotionProductRelation.id;

    public static final SqlColumn<Long> flashPromotionId = smsFlashPromotionProductRelation.flashPromotionId;

    public static final SqlColumn<Long> flashPromotionSessionId = smsFlashPromotionProductRelation.flashPromotionSessionId;

    public static final SqlColumn<Long> productId = smsFlashPromotionProductRelation.productId;

    public static final SqlColumn<BigDecimal> flashPromotionPrice = smsFlashPromotionProductRelation.flashPromotionPrice;

    public static final SqlColumn<Integer> flashPromotionCount = smsFlashPromotionProductRelation.flashPromotionCount;

    public static final SqlColumn<Integer> flashPromotionLimit = smsFlashPromotionProductRelation.flashPromotionLimit;

    public static final SqlColumn<Integer> sort = smsFlashPromotionProductRelation.sort;

    public static final class SmsFlashPromotionProductRelation extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> flashPromotionId = column("flash_promotion_id", JDBCType.BIGINT);

        public final SqlColumn<Long> flashPromotionSessionId = column("flash_promotion_session_id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<BigDecimal> flashPromotionPrice = column("flash_promotion_price", JDBCType.DECIMAL);

        public final SqlColumn<Integer> flashPromotionCount = column("flash_promotion_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> flashPromotionLimit = column("flash_promotion_limit", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public SmsFlashPromotionProductRelation() {
            super("sms_flash_promotion_product_relation");
        }
    }
}