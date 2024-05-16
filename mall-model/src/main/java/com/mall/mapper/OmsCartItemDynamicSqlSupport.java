/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OmsCartItemDynamicSqlSupport {
    public static final OmsCartItem omsCartItem = new OmsCartItem();

    public static final SqlColumn<Long> id = omsCartItem.id;

    public static final SqlColumn<Long> productId = omsCartItem.productId;

    public static final SqlColumn<Long> productSkuId = omsCartItem.productSkuId;

    public static final SqlColumn<Long> memberId = omsCartItem.memberId;

    public static final SqlColumn<String> memberNickname = omsCartItem.memberNickname;

    public static final SqlColumn<Integer> quantity = omsCartItem.quantity;

    public static final SqlColumn<BigDecimal> price = omsCartItem.price;

    public static final SqlColumn<String> productPic = omsCartItem.productPic;

    public static final SqlColumn<String> productName = omsCartItem.productName;

    public static final SqlColumn<String> productSubTitle = omsCartItem.productSubTitle;

    public static final SqlColumn<String> productSkuCode = omsCartItem.productSkuCode;

    public static final SqlColumn<Integer> deleteStatus = omsCartItem.deleteStatus;

    public static final SqlColumn<Long> productCategoryId = omsCartItem.productCategoryId;

    public static final SqlColumn<String> productBrand = omsCartItem.productBrand;

    public static final SqlColumn<String> productSn = omsCartItem.productSn;

    public static final SqlColumn<String> productAttr = omsCartItem.productAttr;

    public static final SqlColumn<LocalDateTime> createDate = omsCartItem.createDate;

    public static final SqlColumn<LocalDateTime> modifyDate = omsCartItem.modifyDate;

    public static final class OmsCartItem extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<Long> productSkuId = column("product_sku_id", JDBCType.BIGINT);

        public final SqlColumn<Long> memberId = column("member_id", JDBCType.BIGINT);

        public final SqlColumn<String> memberNickname = column("member_nickname", JDBCType.VARCHAR);

        public final SqlColumn<Integer> quantity = column("quantity", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> price = column("price", JDBCType.DECIMAL);

        public final SqlColumn<String> productPic = column("product_pic", JDBCType.VARCHAR);

        public final SqlColumn<String> productName = column("product_name", JDBCType.VARCHAR);

        public final SqlColumn<String> productSubTitle = column("product_sub_title", JDBCType.VARCHAR);

        public final SqlColumn<String> productSkuCode = column("product_sku_code", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deleteStatus = column("delete_status", JDBCType.INTEGER);

        public final SqlColumn<Long> productCategoryId = column("product_category_id", JDBCType.BIGINT);

        public final SqlColumn<String> productBrand = column("product_brand", JDBCType.VARCHAR);

        public final SqlColumn<String> productSn = column("product_sn", JDBCType.VARCHAR);

        public final SqlColumn<String> productAttr = column("product_attr", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createDate = column("create_date", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> modifyDate = column("modify_date", JDBCType.TIMESTAMP);

        public OmsCartItem() {
            super("oms_cart_item");
        }
    }
}