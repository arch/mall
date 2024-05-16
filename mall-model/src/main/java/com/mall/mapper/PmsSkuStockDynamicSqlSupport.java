/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsSkuStockDynamicSqlSupport {
    public static final PmsSkuStock pmsSkuStock = new PmsSkuStock();

    public static final SqlColumn<Long> id = pmsSkuStock.id;

    public static final SqlColumn<Long> productId = pmsSkuStock.productId;

    public static final SqlColumn<String> skuCode = pmsSkuStock.skuCode;

    public static final SqlColumn<BigDecimal> price = pmsSkuStock.price;

    public static final SqlColumn<Integer> stock = pmsSkuStock.stock;

    public static final SqlColumn<Integer> lowStock = pmsSkuStock.lowStock;

    public static final SqlColumn<String> pic = pmsSkuStock.pic;

    public static final SqlColumn<Integer> sale = pmsSkuStock.sale;

    public static final SqlColumn<BigDecimal> promotionPrice = pmsSkuStock.promotionPrice;

    public static final SqlColumn<Integer> lockStock = pmsSkuStock.lockStock;

    public static final SqlColumn<String> spData = pmsSkuStock.spData;

    public static final class PmsSkuStock extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<String> skuCode = column("sku_code", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> price = column("price", JDBCType.DECIMAL);

        public final SqlColumn<Integer> stock = column("stock", JDBCType.INTEGER);

        public final SqlColumn<Integer> lowStock = column("low_stock", JDBCType.INTEGER);

        public final SqlColumn<String> pic = column("pic", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sale = column("sale", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> promotionPrice = column("promotion_price", JDBCType.DECIMAL);

        public final SqlColumn<Integer> lockStock = column("lock_stock", JDBCType.INTEGER);

        public final SqlColumn<String> spData = column("sp_data", JDBCType.VARCHAR);

        public PmsSkuStock() {
            super("pms_sku_stock");
        }
    }
}