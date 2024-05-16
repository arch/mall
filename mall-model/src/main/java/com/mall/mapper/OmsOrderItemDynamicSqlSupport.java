/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OmsOrderItemDynamicSqlSupport {
    public static final OmsOrderItem omsOrderItem = new OmsOrderItem();

    public static final SqlColumn<Long> id = omsOrderItem.id;

    public static final SqlColumn<Long> orderId = omsOrderItem.orderId;

    public static final SqlColumn<String> orderSn = omsOrderItem.orderSn;

    public static final SqlColumn<Long> returnId = omsOrderItem.returnId;

    public static final SqlColumn<Long> productId = omsOrderItem.productId;

    public static final SqlColumn<String> productPic = omsOrderItem.productPic;

    public static final SqlColumn<String> productName = omsOrderItem.productName;

    public static final SqlColumn<String> productBrand = omsOrderItem.productBrand;

    public static final SqlColumn<String> productSn = omsOrderItem.productSn;

    public static final SqlColumn<BigDecimal> productPrice = omsOrderItem.productPrice;

    public static final SqlColumn<Integer> productQuantity = omsOrderItem.productQuantity;

    public static final SqlColumn<Long> productSkuId = omsOrderItem.productSkuId;

    public static final SqlColumn<String> productSkuCode = omsOrderItem.productSkuCode;

    public static final SqlColumn<Long> productCategoryId = omsOrderItem.productCategoryId;

    public static final SqlColumn<String> promotionName = omsOrderItem.promotionName;

    public static final SqlColumn<BigDecimal> promotionAmount = omsOrderItem.promotionAmount;

    public static final SqlColumn<BigDecimal> couponAmount = omsOrderItem.couponAmount;

    public static final SqlColumn<BigDecimal> integrationAmount = omsOrderItem.integrationAmount;

    public static final SqlColumn<BigDecimal> realAmount = omsOrderItem.realAmount;

    public static final SqlColumn<Integer> giftIntegration = omsOrderItem.giftIntegration;

    public static final SqlColumn<Integer> giftGrowth = omsOrderItem.giftGrowth;

    public static final SqlColumn<String> productAttr = omsOrderItem.productAttr;

    public static final class OmsOrderItem extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> orderId = column("order_id", JDBCType.BIGINT);

        public final SqlColumn<String> orderSn = column("order_sn", JDBCType.VARCHAR);

        public final SqlColumn<Long> returnId = column("return_id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<String> productPic = column("product_pic", JDBCType.VARCHAR);

        public final SqlColumn<String> productName = column("product_name", JDBCType.VARCHAR);

        public final SqlColumn<String> productBrand = column("product_brand", JDBCType.VARCHAR);

        public final SqlColumn<String> productSn = column("product_sn", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> productPrice = column("product_price", JDBCType.DECIMAL);

        public final SqlColumn<Integer> productQuantity = column("product_quantity", JDBCType.INTEGER);

        public final SqlColumn<Long> productSkuId = column("product_sku_id", JDBCType.BIGINT);

        public final SqlColumn<String> productSkuCode = column("product_sku_code", JDBCType.VARCHAR);

        public final SqlColumn<Long> productCategoryId = column("product_category_id", JDBCType.BIGINT);

        public final SqlColumn<String> promotionName = column("promotion_name", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> promotionAmount = column("promotion_amount", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> couponAmount = column("coupon_amount", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> integrationAmount = column("integration_amount", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> realAmount = column("real_amount", JDBCType.DECIMAL);

        public final SqlColumn<Integer> giftIntegration = column("gift_integration", JDBCType.INTEGER);

        public final SqlColumn<Integer> giftGrowth = column("gift_growth", JDBCType.INTEGER);

        public final SqlColumn<String> productAttr = column("product_attr", JDBCType.VARCHAR);

        public OmsOrderItem() {
            super("oms_order_item");
        }
    }
}