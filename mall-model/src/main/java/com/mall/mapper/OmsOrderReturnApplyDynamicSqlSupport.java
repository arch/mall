/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OmsOrderReturnApplyDynamicSqlSupport {
    public static final OmsOrderReturnApply omsOrderReturnApply = new OmsOrderReturnApply();

    public static final SqlColumn<Long> id = omsOrderReturnApply.id;

    public static final SqlColumn<Long> orderId = omsOrderReturnApply.orderId;

    public static final SqlColumn<Long> companyAddressId = omsOrderReturnApply.companyAddressId;

    public static final SqlColumn<Long> productId = omsOrderReturnApply.productId;

    public static final SqlColumn<String> orderSn = omsOrderReturnApply.orderSn;

    public static final SqlColumn<Long> memberId = omsOrderReturnApply.memberId;

    public static final SqlColumn<String> memberUsername = omsOrderReturnApply.memberUsername;

    public static final SqlColumn<BigDecimal> returnAmount = omsOrderReturnApply.returnAmount;

    public static final SqlColumn<String> returnName = omsOrderReturnApply.returnName;

    public static final SqlColumn<String> returnPhone = omsOrderReturnApply.returnPhone;

    public static final SqlColumn<String> deliveryCode = omsOrderReturnApply.deliveryCode;

    public static final SqlColumn<String> deliverySn = omsOrderReturnApply.deliverySn;

    public static final SqlColumn<Integer> status = omsOrderReturnApply.status;

    public static final SqlColumn<String> productPic = omsOrderReturnApply.productPic;

    public static final SqlColumn<String> productName = omsOrderReturnApply.productName;

    public static final SqlColumn<String> productBrand = omsOrderReturnApply.productBrand;

    public static final SqlColumn<String> productAttr = omsOrderReturnApply.productAttr;

    public static final SqlColumn<Integer> productCount = omsOrderReturnApply.productCount;

    public static final SqlColumn<BigDecimal> productPrice = omsOrderReturnApply.productPrice;

    public static final SqlColumn<BigDecimal> productRealPrice = omsOrderReturnApply.productRealPrice;

    public static final SqlColumn<String> reason = omsOrderReturnApply.reason;

    public static final SqlColumn<String> description = omsOrderReturnApply.description;

    public static final SqlColumn<String> proofPics = omsOrderReturnApply.proofPics;

    public static final SqlColumn<String> handleNote = omsOrderReturnApply.handleNote;

    public static final SqlColumn<String> handleMan = omsOrderReturnApply.handleMan;

    public static final SqlColumn<LocalDateTime> handleTime = omsOrderReturnApply.handleTime;

    public static final SqlColumn<String> receiveMan = omsOrderReturnApply.receiveMan;

    public static final SqlColumn<LocalDateTime> receiveTime = omsOrderReturnApply.receiveTime;

    public static final SqlColumn<String> receiveNote = omsOrderReturnApply.receiveNote;

    public static final SqlColumn<LocalDateTime> createTime = omsOrderReturnApply.createTime;

    public static final class OmsOrderReturnApply extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> orderId = column("order_id", JDBCType.BIGINT);

        public final SqlColumn<Long> companyAddressId = column("company_address_id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<String> orderSn = column("order_sn", JDBCType.VARCHAR);

        public final SqlColumn<Long> memberId = column("member_id", JDBCType.BIGINT);

        public final SqlColumn<String> memberUsername = column("member_username", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> returnAmount = column("return_amount", JDBCType.DECIMAL);

        public final SqlColumn<String> returnName = column("return_name", JDBCType.VARCHAR);

        public final SqlColumn<String> returnPhone = column("return_phone", JDBCType.VARCHAR);

        public final SqlColumn<String> deliveryCode = column("delivery_code", JDBCType.VARCHAR);

        public final SqlColumn<String> deliverySn = column("delivery_sn", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<String> productPic = column("product_pic", JDBCType.VARCHAR);

        public final SqlColumn<String> productName = column("product_name", JDBCType.VARCHAR);

        public final SqlColumn<String> productBrand = column("product_brand", JDBCType.VARCHAR);

        public final SqlColumn<String> productAttr = column("product_attr", JDBCType.VARCHAR);

        public final SqlColumn<Integer> productCount = column("product_count", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> productPrice = column("product_price", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> productRealPrice = column("product_real_price", JDBCType.DECIMAL);

        public final SqlColumn<String> reason = column("reason", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> proofPics = column("proof_pics", JDBCType.VARCHAR);

        public final SqlColumn<String> handleNote = column("handle_note", JDBCType.VARCHAR);

        public final SqlColumn<String> handleMan = column("handle_man", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> handleTime = column("handle_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> receiveMan = column("receive_man", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> receiveTime = column("receive_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> receiveNote = column("receive_note", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public OmsOrderReturnApply() {
            super("oms_order_return_apply");
        }
    }
}