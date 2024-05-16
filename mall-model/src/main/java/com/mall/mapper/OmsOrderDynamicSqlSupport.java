/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OmsOrderDynamicSqlSupport {
    public static final OmsOrder omsOrder = new OmsOrder();

    public static final SqlColumn<Long> id = omsOrder.id;

    public static final SqlColumn<Long> memberId = omsOrder.memberId;

    public static final SqlColumn<Long> couponId = omsOrder.couponId;

    public static final SqlColumn<String> orderSn = omsOrder.orderSn;

    public static final SqlColumn<String> transactionId = omsOrder.transactionId;

    public static final SqlColumn<String> memberUsername = omsOrder.memberUsername;

    public static final SqlColumn<BigDecimal> totalAmount = omsOrder.totalAmount;

    public static final SqlColumn<BigDecimal> payAmount = omsOrder.payAmount;

    public static final SqlColumn<BigDecimal> freightAmount = omsOrder.freightAmount;

    public static final SqlColumn<BigDecimal> promotionAmount = omsOrder.promotionAmount;

    public static final SqlColumn<BigDecimal> integrationAmount = omsOrder.integrationAmount;

    public static final SqlColumn<BigDecimal> couponAmount = omsOrder.couponAmount;

    public static final SqlColumn<BigDecimal> discountAmount = omsOrder.discountAmount;

    public static final SqlColumn<Integer> payType = omsOrder.payType;

    public static final SqlColumn<Integer> sourceType = omsOrder.sourceType;

    public static final SqlColumn<Integer> status = omsOrder.status;

    public static final SqlColumn<Integer> orderType = omsOrder.orderType;

    public static final SqlColumn<String> deliveryCode = omsOrder.deliveryCode;

    public static final SqlColumn<String> deliveryCompany = omsOrder.deliveryCompany;

    public static final SqlColumn<String> deliverySn = omsOrder.deliverySn;

    public static final SqlColumn<Integer> autoConfirmDay = omsOrder.autoConfirmDay;

    public static final SqlColumn<Integer> integration = omsOrder.integration;

    public static final SqlColumn<Integer> growth = omsOrder.growth;

    public static final SqlColumn<String> promotionInfo = omsOrder.promotionInfo;

    public static final SqlColumn<Integer> billType = omsOrder.billType;

    public static final SqlColumn<String> billHeader = omsOrder.billHeader;

    public static final SqlColumn<String> billContent = omsOrder.billContent;

    public static final SqlColumn<String> billReceiverPhone = omsOrder.billReceiverPhone;

    public static final SqlColumn<String> billReceiverEmail = omsOrder.billReceiverEmail;

    public static final SqlColumn<String> receiverName = omsOrder.receiverName;

    public static final SqlColumn<String> receiverPhone = omsOrder.receiverPhone;

    public static final SqlColumn<String> receiverPostCode = omsOrder.receiverPostCode;

    public static final SqlColumn<String> receiverProvince = omsOrder.receiverProvince;

    public static final SqlColumn<String> receiverCity = omsOrder.receiverCity;

    public static final SqlColumn<String> receiverRegion = omsOrder.receiverRegion;

    public static final SqlColumn<String> receiverDetailAddress = omsOrder.receiverDetailAddress;

    public static final SqlColumn<String> note = omsOrder.note;

    public static final SqlColumn<Integer> confirmStatus = omsOrder.confirmStatus;

    public static final SqlColumn<Integer> deleteStatus = omsOrder.deleteStatus;

    public static final SqlColumn<Integer> useIntegration = omsOrder.useIntegration;

    public static final SqlColumn<LocalDateTime> paymentTime = omsOrder.paymentTime;

    public static final SqlColumn<LocalDateTime> deliveryTime = omsOrder.deliveryTime;

    public static final SqlColumn<LocalDateTime> receiveTime = omsOrder.receiveTime;

    public static final SqlColumn<LocalDateTime> commentTime = omsOrder.commentTime;

    public static final SqlColumn<LocalDateTime> createTime = omsOrder.createTime;

    public static final SqlColumn<LocalDateTime> modifyTime = omsOrder.modifyTime;

    public static final class OmsOrder extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> memberId = column("member_id", JDBCType.BIGINT);

        public final SqlColumn<Long> couponId = column("coupon_id", JDBCType.BIGINT);

        public final SqlColumn<String> orderSn = column("order_sn", JDBCType.VARCHAR);

        public final SqlColumn<String> transactionId = column("transaction_id", JDBCType.VARCHAR);

        public final SqlColumn<String> memberUsername = column("member_username", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> totalAmount = column("total_amount", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> payAmount = column("pay_amount", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> freightAmount = column("freight_amount", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> promotionAmount = column("promotion_amount", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> integrationAmount = column("integration_amount", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> couponAmount = column("coupon_amount", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> discountAmount = column("discount_amount", JDBCType.DECIMAL);

        public final SqlColumn<Integer> payType = column("pay_type", JDBCType.INTEGER);

        public final SqlColumn<Integer> sourceType = column("source_type", JDBCType.INTEGER);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<Integer> orderType = column("order_type", JDBCType.INTEGER);

        public final SqlColumn<String> deliveryCode = column("delivery_code", JDBCType.VARCHAR);

        public final SqlColumn<String> deliveryCompany = column("delivery_company", JDBCType.VARCHAR);

        public final SqlColumn<String> deliverySn = column("delivery_sn", JDBCType.VARCHAR);

        public final SqlColumn<Integer> autoConfirmDay = column("auto_confirm_day", JDBCType.INTEGER);

        public final SqlColumn<Integer> integration = column("integration", JDBCType.INTEGER);

        public final SqlColumn<Integer> growth = column("growth", JDBCType.INTEGER);

        public final SqlColumn<String> promotionInfo = column("promotion_info", JDBCType.VARCHAR);

        public final SqlColumn<Integer> billType = column("bill_type", JDBCType.INTEGER);

        public final SqlColumn<String> billHeader = column("bill_header", JDBCType.VARCHAR);

        public final SqlColumn<String> billContent = column("bill_content", JDBCType.VARCHAR);

        public final SqlColumn<String> billReceiverPhone = column("bill_receiver_phone", JDBCType.VARCHAR);

        public final SqlColumn<String> billReceiverEmail = column("bill_receiver_email", JDBCType.VARCHAR);

        public final SqlColumn<String> receiverName = column("receiver_name", JDBCType.VARCHAR);

        public final SqlColumn<String> receiverPhone = column("receiver_phone", JDBCType.VARCHAR);

        public final SqlColumn<String> receiverPostCode = column("receiver_post_code", JDBCType.VARCHAR);

        public final SqlColumn<String> receiverProvince = column("receiver_province", JDBCType.VARCHAR);

        public final SqlColumn<String> receiverCity = column("receiver_city", JDBCType.VARCHAR);

        public final SqlColumn<String> receiverRegion = column("receiver_region", JDBCType.VARCHAR);

        public final SqlColumn<String> receiverDetailAddress = column("receiver_detail_address", JDBCType.VARCHAR);

        public final SqlColumn<String> note = column("note", JDBCType.VARCHAR);

        public final SqlColumn<Integer> confirmStatus = column("confirm_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> deleteStatus = column("delete_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> useIntegration = column("use_integration", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> paymentTime = column("payment_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> deliveryTime = column("delivery_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> receiveTime = column("receive_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> commentTime = column("comment_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> modifyTime = column("modify_time", JDBCType.TIMESTAMP);

        public OmsOrder() {
            super("oms_order");
        }
    }
}