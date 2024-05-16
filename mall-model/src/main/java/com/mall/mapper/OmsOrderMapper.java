/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.OmsOrder;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface OmsOrderMapper {
    BasicColumn[] selectList = BasicColumn.columnList(OmsOrderDynamicSqlSupport.id, OmsOrderDynamicSqlSupport.memberId, OmsOrderDynamicSqlSupport.couponId, OmsOrderDynamicSqlSupport.orderSn, OmsOrderDynamicSqlSupport.transactionId, OmsOrderDynamicSqlSupport.memberUsername, OmsOrderDynamicSqlSupport.totalAmount, OmsOrderDynamicSqlSupport.payAmount, OmsOrderDynamicSqlSupport.freightAmount, OmsOrderDynamicSqlSupport.promotionAmount, OmsOrderDynamicSqlSupport.integrationAmount, OmsOrderDynamicSqlSupport.couponAmount, OmsOrderDynamicSqlSupport.discountAmount, OmsOrderDynamicSqlSupport.payType, OmsOrderDynamicSqlSupport.sourceType, OmsOrderDynamicSqlSupport.status, OmsOrderDynamicSqlSupport.orderType, OmsOrderDynamicSqlSupport.deliveryCode, OmsOrderDynamicSqlSupport.deliveryCompany, OmsOrderDynamicSqlSupport.deliverySn, OmsOrderDynamicSqlSupport.autoConfirmDay, OmsOrderDynamicSqlSupport.integration, OmsOrderDynamicSqlSupport.growth, OmsOrderDynamicSqlSupport.promotionInfo, OmsOrderDynamicSqlSupport.billType, OmsOrderDynamicSqlSupport.billHeader, OmsOrderDynamicSqlSupport.billContent, OmsOrderDynamicSqlSupport.billReceiverPhone, OmsOrderDynamicSqlSupport.billReceiverEmail, OmsOrderDynamicSqlSupport.receiverName, OmsOrderDynamicSqlSupport.receiverPhone, OmsOrderDynamicSqlSupport.receiverPostCode, OmsOrderDynamicSqlSupport.receiverProvince, OmsOrderDynamicSqlSupport.receiverCity, OmsOrderDynamicSqlSupport.receiverRegion, OmsOrderDynamicSqlSupport.receiverDetailAddress, OmsOrderDynamicSqlSupport.note, OmsOrderDynamicSqlSupport.confirmStatus, OmsOrderDynamicSqlSupport.deleteStatus, OmsOrderDynamicSqlSupport.useIntegration, OmsOrderDynamicSqlSupport.paymentTime, OmsOrderDynamicSqlSupport.deliveryTime, OmsOrderDynamicSqlSupport.receiveTime, OmsOrderDynamicSqlSupport.commentTime, OmsOrderDynamicSqlSupport.createTime, OmsOrderDynamicSqlSupport.modifyTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<OmsOrder> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OmsOrderResult")
    Optional<OmsOrder> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OmsOrderResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.BIGINT),
        @Result(column="coupon_id", property="couponId", jdbcType=JdbcType.BIGINT),
        @Result(column="order_sn", property="orderSn", jdbcType=JdbcType.VARCHAR),
        @Result(column="transaction_id", property="transactionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_username", property="memberUsername", jdbcType=JdbcType.VARCHAR),
        @Result(column="total_amount", property="totalAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="pay_amount", property="payAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="freight_amount", property="freightAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="promotion_amount", property="promotionAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="integration_amount", property="integrationAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="coupon_amount", property="couponAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="discount_amount", property="discountAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="pay_type", property="payType", jdbcType=JdbcType.INTEGER),
        @Result(column="source_type", property="sourceType", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="order_type", property="orderType", jdbcType=JdbcType.INTEGER),
        @Result(column="delivery_code", property="deliveryCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="delivery_company", property="deliveryCompany", jdbcType=JdbcType.VARCHAR),
        @Result(column="delivery_sn", property="deliverySn", jdbcType=JdbcType.VARCHAR),
        @Result(column="auto_confirm_day", property="autoConfirmDay", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.INTEGER),
        @Result(column="growth", property="growth", jdbcType=JdbcType.INTEGER),
        @Result(column="promotion_info", property="promotionInfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="bill_type", property="billType", jdbcType=JdbcType.INTEGER),
        @Result(column="bill_header", property="billHeader", jdbcType=JdbcType.VARCHAR),
        @Result(column="bill_content", property="billContent", jdbcType=JdbcType.VARCHAR),
        @Result(column="bill_receiver_phone", property="billReceiverPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="bill_receiver_email", property="billReceiverEmail", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver_name", property="receiverName", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver_phone", property="receiverPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver_post_code", property="receiverPostCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver_province", property="receiverProvince", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver_city", property="receiverCity", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver_region", property="receiverRegion", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver_detail_address", property="receiverDetailAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="confirm_status", property="confirmStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="use_integration", property="useIntegration", jdbcType=JdbcType.INTEGER),
        @Result(column="payment_time", property="paymentTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="delivery_time", property="deliveryTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="receive_time", property="receiveTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment_time", property="commentTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<OmsOrder> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, OmsOrderDynamicSqlSupport.omsOrder, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, OmsOrderDynamicSqlSupport.omsOrder, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(OmsOrderDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(OmsOrder record) {
        return MyBatis3Utils.insert(this::insert, record, OmsOrderDynamicSqlSupport.omsOrder, c ->
            c.map(OmsOrderDynamicSqlSupport.memberId).toProperty("memberId")
            .map(OmsOrderDynamicSqlSupport.couponId).toProperty("couponId")
            .map(OmsOrderDynamicSqlSupport.orderSn).toProperty("orderSn")
            .map(OmsOrderDynamicSqlSupport.transactionId).toProperty("transactionId")
            .map(OmsOrderDynamicSqlSupport.memberUsername).toProperty("memberUsername")
            .map(OmsOrderDynamicSqlSupport.totalAmount).toProperty("totalAmount")
            .map(OmsOrderDynamicSqlSupport.payAmount).toProperty("payAmount")
            .map(OmsOrderDynamicSqlSupport.freightAmount).toProperty("freightAmount")
            .map(OmsOrderDynamicSqlSupport.promotionAmount).toProperty("promotionAmount")
            .map(OmsOrderDynamicSqlSupport.integrationAmount).toProperty("integrationAmount")
            .map(OmsOrderDynamicSqlSupport.couponAmount).toProperty("couponAmount")
            .map(OmsOrderDynamicSqlSupport.discountAmount).toProperty("discountAmount")
            .map(OmsOrderDynamicSqlSupport.payType).toProperty("payType")
            .map(OmsOrderDynamicSqlSupport.sourceType).toProperty("sourceType")
            .map(OmsOrderDynamicSqlSupport.status).toProperty("status")
            .map(OmsOrderDynamicSqlSupport.orderType).toProperty("orderType")
            .map(OmsOrderDynamicSqlSupport.deliveryCode).toProperty("deliveryCode")
            .map(OmsOrderDynamicSqlSupport.deliveryCompany).toProperty("deliveryCompany")
            .map(OmsOrderDynamicSqlSupport.deliverySn).toProperty("deliverySn")
            .map(OmsOrderDynamicSqlSupport.autoConfirmDay).toProperty("autoConfirmDay")
            .map(OmsOrderDynamicSqlSupport.integration).toProperty("integration")
            .map(OmsOrderDynamicSqlSupport.growth).toProperty("growth")
            .map(OmsOrderDynamicSqlSupport.promotionInfo).toProperty("promotionInfo")
            .map(OmsOrderDynamicSqlSupport.billType).toProperty("billType")
            .map(OmsOrderDynamicSqlSupport.billHeader).toProperty("billHeader")
            .map(OmsOrderDynamicSqlSupport.billContent).toProperty("billContent")
            .map(OmsOrderDynamicSqlSupport.billReceiverPhone).toProperty("billReceiverPhone")
            .map(OmsOrderDynamicSqlSupport.billReceiverEmail).toProperty("billReceiverEmail")
            .map(OmsOrderDynamicSqlSupport.receiverName).toProperty("receiverName")
            .map(OmsOrderDynamicSqlSupport.receiverPhone).toProperty("receiverPhone")
            .map(OmsOrderDynamicSqlSupport.receiverPostCode).toProperty("receiverPostCode")
            .map(OmsOrderDynamicSqlSupport.receiverProvince).toProperty("receiverProvince")
            .map(OmsOrderDynamicSqlSupport.receiverCity).toProperty("receiverCity")
            .map(OmsOrderDynamicSqlSupport.receiverRegion).toProperty("receiverRegion")
            .map(OmsOrderDynamicSqlSupport.receiverDetailAddress).toProperty("receiverDetailAddress")
            .map(OmsOrderDynamicSqlSupport.note).toProperty("note")
            .map(OmsOrderDynamicSqlSupport.confirmStatus).toProperty("confirmStatus")
            .map(OmsOrderDynamicSqlSupport.deleteStatus).toProperty("deleteStatus")
            .map(OmsOrderDynamicSqlSupport.useIntegration).toProperty("useIntegration")
            .map(OmsOrderDynamicSqlSupport.paymentTime).toProperty("paymentTime")
            .map(OmsOrderDynamicSqlSupport.deliveryTime).toProperty("deliveryTime")
            .map(OmsOrderDynamicSqlSupport.receiveTime).toProperty("receiveTime")
            .map(OmsOrderDynamicSqlSupport.commentTime).toProperty("commentTime")
            .map(OmsOrderDynamicSqlSupport.createTime).toProperty("createTime")
            .map(OmsOrderDynamicSqlSupport.modifyTime).toProperty("modifyTime")
        );
    }

    default int insertSelective(OmsOrder record) {
        return MyBatis3Utils.insert(this::insert, record, OmsOrderDynamicSqlSupport.omsOrder, c ->
            c.map(OmsOrderDynamicSqlSupport.memberId).toPropertyWhenPresent("memberId", record::getMemberId)
            .map(OmsOrderDynamicSqlSupport.couponId).toPropertyWhenPresent("couponId", record::getCouponId)
            .map(OmsOrderDynamicSqlSupport.orderSn).toPropertyWhenPresent("orderSn", record::getOrderSn)
            .map(OmsOrderDynamicSqlSupport.transactionId).toPropertyWhenPresent("transactionId", record::getTransactionId)
            .map(OmsOrderDynamicSqlSupport.memberUsername).toPropertyWhenPresent("memberUsername", record::getMemberUsername)
            .map(OmsOrderDynamicSqlSupport.totalAmount).toPropertyWhenPresent("totalAmount", record::getTotalAmount)
            .map(OmsOrderDynamicSqlSupport.payAmount).toPropertyWhenPresent("payAmount", record::getPayAmount)
            .map(OmsOrderDynamicSqlSupport.freightAmount).toPropertyWhenPresent("freightAmount", record::getFreightAmount)
            .map(OmsOrderDynamicSqlSupport.promotionAmount).toPropertyWhenPresent("promotionAmount", record::getPromotionAmount)
            .map(OmsOrderDynamicSqlSupport.integrationAmount).toPropertyWhenPresent("integrationAmount", record::getIntegrationAmount)
            .map(OmsOrderDynamicSqlSupport.couponAmount).toPropertyWhenPresent("couponAmount", record::getCouponAmount)
            .map(OmsOrderDynamicSqlSupport.discountAmount).toPropertyWhenPresent("discountAmount", record::getDiscountAmount)
            .map(OmsOrderDynamicSqlSupport.payType).toPropertyWhenPresent("payType", record::getPayType)
            .map(OmsOrderDynamicSqlSupport.sourceType).toPropertyWhenPresent("sourceType", record::getSourceType)
            .map(OmsOrderDynamicSqlSupport.status).toPropertyWhenPresent("status", record::getStatus)
            .map(OmsOrderDynamicSqlSupport.orderType).toPropertyWhenPresent("orderType", record::getOrderType)
            .map(OmsOrderDynamicSqlSupport.deliveryCode).toPropertyWhenPresent("deliveryCode", record::getDeliveryCode)
            .map(OmsOrderDynamicSqlSupport.deliveryCompany).toPropertyWhenPresent("deliveryCompany", record::getDeliveryCompany)
            .map(OmsOrderDynamicSqlSupport.deliverySn).toPropertyWhenPresent("deliverySn", record::getDeliverySn)
            .map(OmsOrderDynamicSqlSupport.autoConfirmDay).toPropertyWhenPresent("autoConfirmDay", record::getAutoConfirmDay)
            .map(OmsOrderDynamicSqlSupport.integration).toPropertyWhenPresent("integration", record::getIntegration)
            .map(OmsOrderDynamicSqlSupport.growth).toPropertyWhenPresent("growth", record::getGrowth)
            .map(OmsOrderDynamicSqlSupport.promotionInfo).toPropertyWhenPresent("promotionInfo", record::getPromotionInfo)
            .map(OmsOrderDynamicSqlSupport.billType).toPropertyWhenPresent("billType", record::getBillType)
            .map(OmsOrderDynamicSqlSupport.billHeader).toPropertyWhenPresent("billHeader", record::getBillHeader)
            .map(OmsOrderDynamicSqlSupport.billContent).toPropertyWhenPresent("billContent", record::getBillContent)
            .map(OmsOrderDynamicSqlSupport.billReceiverPhone).toPropertyWhenPresent("billReceiverPhone", record::getBillReceiverPhone)
            .map(OmsOrderDynamicSqlSupport.billReceiverEmail).toPropertyWhenPresent("billReceiverEmail", record::getBillReceiverEmail)
            .map(OmsOrderDynamicSqlSupport.receiverName).toPropertyWhenPresent("receiverName", record::getReceiverName)
            .map(OmsOrderDynamicSqlSupport.receiverPhone).toPropertyWhenPresent("receiverPhone", record::getReceiverPhone)
            .map(OmsOrderDynamicSqlSupport.receiverPostCode).toPropertyWhenPresent("receiverPostCode", record::getReceiverPostCode)
            .map(OmsOrderDynamicSqlSupport.receiverProvince).toPropertyWhenPresent("receiverProvince", record::getReceiverProvince)
            .map(OmsOrderDynamicSqlSupport.receiverCity).toPropertyWhenPresent("receiverCity", record::getReceiverCity)
            .map(OmsOrderDynamicSqlSupport.receiverRegion).toPropertyWhenPresent("receiverRegion", record::getReceiverRegion)
            .map(OmsOrderDynamicSqlSupport.receiverDetailAddress).toPropertyWhenPresent("receiverDetailAddress", record::getReceiverDetailAddress)
            .map(OmsOrderDynamicSqlSupport.note).toPropertyWhenPresent("note", record::getNote)
            .map(OmsOrderDynamicSqlSupport.confirmStatus).toPropertyWhenPresent("confirmStatus", record::getConfirmStatus)
            .map(OmsOrderDynamicSqlSupport.deleteStatus).toPropertyWhenPresent("deleteStatus", record::getDeleteStatus)
            .map(OmsOrderDynamicSqlSupport.useIntegration).toPropertyWhenPresent("useIntegration", record::getUseIntegration)
            .map(OmsOrderDynamicSqlSupport.paymentTime).toPropertyWhenPresent("paymentTime", record::getPaymentTime)
            .map(OmsOrderDynamicSqlSupport.deliveryTime).toPropertyWhenPresent("deliveryTime", record::getDeliveryTime)
            .map(OmsOrderDynamicSqlSupport.receiveTime).toPropertyWhenPresent("receiveTime", record::getReceiveTime)
            .map(OmsOrderDynamicSqlSupport.commentTime).toPropertyWhenPresent("commentTime", record::getCommentTime)
            .map(OmsOrderDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(OmsOrderDynamicSqlSupport.modifyTime).toPropertyWhenPresent("modifyTime", record::getModifyTime)
        );
    }

    default Optional<OmsOrder> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, OmsOrderDynamicSqlSupport.omsOrder, completer);
    }

    default List<OmsOrder> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, OmsOrderDynamicSqlSupport.omsOrder, completer);
    }

    default List<OmsOrder> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, OmsOrderDynamicSqlSupport.omsOrder, completer);
    }

    default Optional<OmsOrder> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(OmsOrderDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, OmsOrderDynamicSqlSupport.omsOrder, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(OmsOrder record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsOrderDynamicSqlSupport.memberId).equalTo(record::getMemberId)
                .set(OmsOrderDynamicSqlSupport.couponId).equalTo(record::getCouponId)
                .set(OmsOrderDynamicSqlSupport.orderSn).equalTo(record::getOrderSn)
                .set(OmsOrderDynamicSqlSupport.transactionId).equalTo(record::getTransactionId)
                .set(OmsOrderDynamicSqlSupport.memberUsername).equalTo(record::getMemberUsername)
                .set(OmsOrderDynamicSqlSupport.totalAmount).equalTo(record::getTotalAmount)
                .set(OmsOrderDynamicSqlSupport.payAmount).equalTo(record::getPayAmount)
                .set(OmsOrderDynamicSqlSupport.freightAmount).equalTo(record::getFreightAmount)
                .set(OmsOrderDynamicSqlSupport.promotionAmount).equalTo(record::getPromotionAmount)
                .set(OmsOrderDynamicSqlSupport.integrationAmount).equalTo(record::getIntegrationAmount)
                .set(OmsOrderDynamicSqlSupport.couponAmount).equalTo(record::getCouponAmount)
                .set(OmsOrderDynamicSqlSupport.discountAmount).equalTo(record::getDiscountAmount)
                .set(OmsOrderDynamicSqlSupport.payType).equalTo(record::getPayType)
                .set(OmsOrderDynamicSqlSupport.sourceType).equalTo(record::getSourceType)
                .set(OmsOrderDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(OmsOrderDynamicSqlSupport.orderType).equalTo(record::getOrderType)
                .set(OmsOrderDynamicSqlSupport.deliveryCode).equalTo(record::getDeliveryCode)
                .set(OmsOrderDynamicSqlSupport.deliveryCompany).equalTo(record::getDeliveryCompany)
                .set(OmsOrderDynamicSqlSupport.deliverySn).equalTo(record::getDeliverySn)
                .set(OmsOrderDynamicSqlSupport.autoConfirmDay).equalTo(record::getAutoConfirmDay)
                .set(OmsOrderDynamicSqlSupport.integration).equalTo(record::getIntegration)
                .set(OmsOrderDynamicSqlSupport.growth).equalTo(record::getGrowth)
                .set(OmsOrderDynamicSqlSupport.promotionInfo).equalTo(record::getPromotionInfo)
                .set(OmsOrderDynamicSqlSupport.billType).equalTo(record::getBillType)
                .set(OmsOrderDynamicSqlSupport.billHeader).equalTo(record::getBillHeader)
                .set(OmsOrderDynamicSqlSupport.billContent).equalTo(record::getBillContent)
                .set(OmsOrderDynamicSqlSupport.billReceiverPhone).equalTo(record::getBillReceiverPhone)
                .set(OmsOrderDynamicSqlSupport.billReceiverEmail).equalTo(record::getBillReceiverEmail)
                .set(OmsOrderDynamicSqlSupport.receiverName).equalTo(record::getReceiverName)
                .set(OmsOrderDynamicSqlSupport.receiverPhone).equalTo(record::getReceiverPhone)
                .set(OmsOrderDynamicSqlSupport.receiverPostCode).equalTo(record::getReceiverPostCode)
                .set(OmsOrderDynamicSqlSupport.receiverProvince).equalTo(record::getReceiverProvince)
                .set(OmsOrderDynamicSqlSupport.receiverCity).equalTo(record::getReceiverCity)
                .set(OmsOrderDynamicSqlSupport.receiverRegion).equalTo(record::getReceiverRegion)
                .set(OmsOrderDynamicSqlSupport.receiverDetailAddress).equalTo(record::getReceiverDetailAddress)
                .set(OmsOrderDynamicSqlSupport.note).equalTo(record::getNote)
                .set(OmsOrderDynamicSqlSupport.confirmStatus).equalTo(record::getConfirmStatus)
                .set(OmsOrderDynamicSqlSupport.deleteStatus).equalTo(record::getDeleteStatus)
                .set(OmsOrderDynamicSqlSupport.useIntegration).equalTo(record::getUseIntegration)
                .set(OmsOrderDynamicSqlSupport.paymentTime).equalTo(record::getPaymentTime)
                .set(OmsOrderDynamicSqlSupport.deliveryTime).equalTo(record::getDeliveryTime)
                .set(OmsOrderDynamicSqlSupport.receiveTime).equalTo(record::getReceiveTime)
                .set(OmsOrderDynamicSqlSupport.commentTime).equalTo(record::getCommentTime)
                .set(OmsOrderDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(OmsOrderDynamicSqlSupport.modifyTime).equalTo(record::getModifyTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(OmsOrder record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsOrderDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
                .set(OmsOrderDynamicSqlSupport.couponId).equalToWhenPresent(record::getCouponId)
                .set(OmsOrderDynamicSqlSupport.orderSn).equalToWhenPresent(record::getOrderSn)
                .set(OmsOrderDynamicSqlSupport.transactionId).equalToWhenPresent(record::getTransactionId)
                .set(OmsOrderDynamicSqlSupport.memberUsername).equalToWhenPresent(record::getMemberUsername)
                .set(OmsOrderDynamicSqlSupport.totalAmount).equalToWhenPresent(record::getTotalAmount)
                .set(OmsOrderDynamicSqlSupport.payAmount).equalToWhenPresent(record::getPayAmount)
                .set(OmsOrderDynamicSqlSupport.freightAmount).equalToWhenPresent(record::getFreightAmount)
                .set(OmsOrderDynamicSqlSupport.promotionAmount).equalToWhenPresent(record::getPromotionAmount)
                .set(OmsOrderDynamicSqlSupport.integrationAmount).equalToWhenPresent(record::getIntegrationAmount)
                .set(OmsOrderDynamicSqlSupport.couponAmount).equalToWhenPresent(record::getCouponAmount)
                .set(OmsOrderDynamicSqlSupport.discountAmount).equalToWhenPresent(record::getDiscountAmount)
                .set(OmsOrderDynamicSqlSupport.payType).equalToWhenPresent(record::getPayType)
                .set(OmsOrderDynamicSqlSupport.sourceType).equalToWhenPresent(record::getSourceType)
                .set(OmsOrderDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(OmsOrderDynamicSqlSupport.orderType).equalToWhenPresent(record::getOrderType)
                .set(OmsOrderDynamicSqlSupport.deliveryCode).equalToWhenPresent(record::getDeliveryCode)
                .set(OmsOrderDynamicSqlSupport.deliveryCompany).equalToWhenPresent(record::getDeliveryCompany)
                .set(OmsOrderDynamicSqlSupport.deliverySn).equalToWhenPresent(record::getDeliverySn)
                .set(OmsOrderDynamicSqlSupport.autoConfirmDay).equalToWhenPresent(record::getAutoConfirmDay)
                .set(OmsOrderDynamicSqlSupport.integration).equalToWhenPresent(record::getIntegration)
                .set(OmsOrderDynamicSqlSupport.growth).equalToWhenPresent(record::getGrowth)
                .set(OmsOrderDynamicSqlSupport.promotionInfo).equalToWhenPresent(record::getPromotionInfo)
                .set(OmsOrderDynamicSqlSupport.billType).equalToWhenPresent(record::getBillType)
                .set(OmsOrderDynamicSqlSupport.billHeader).equalToWhenPresent(record::getBillHeader)
                .set(OmsOrderDynamicSqlSupport.billContent).equalToWhenPresent(record::getBillContent)
                .set(OmsOrderDynamicSqlSupport.billReceiverPhone).equalToWhenPresent(record::getBillReceiverPhone)
                .set(OmsOrderDynamicSqlSupport.billReceiverEmail).equalToWhenPresent(record::getBillReceiverEmail)
                .set(OmsOrderDynamicSqlSupport.receiverName).equalToWhenPresent(record::getReceiverName)
                .set(OmsOrderDynamicSqlSupport.receiverPhone).equalToWhenPresent(record::getReceiverPhone)
                .set(OmsOrderDynamicSqlSupport.receiverPostCode).equalToWhenPresent(record::getReceiverPostCode)
                .set(OmsOrderDynamicSqlSupport.receiverProvince).equalToWhenPresent(record::getReceiverProvince)
                .set(OmsOrderDynamicSqlSupport.receiverCity).equalToWhenPresent(record::getReceiverCity)
                .set(OmsOrderDynamicSqlSupport.receiverRegion).equalToWhenPresent(record::getReceiverRegion)
                .set(OmsOrderDynamicSqlSupport.receiverDetailAddress).equalToWhenPresent(record::getReceiverDetailAddress)
                .set(OmsOrderDynamicSqlSupport.note).equalToWhenPresent(record::getNote)
                .set(OmsOrderDynamicSqlSupport.confirmStatus).equalToWhenPresent(record::getConfirmStatus)
                .set(OmsOrderDynamicSqlSupport.deleteStatus).equalToWhenPresent(record::getDeleteStatus)
                .set(OmsOrderDynamicSqlSupport.useIntegration).equalToWhenPresent(record::getUseIntegration)
                .set(OmsOrderDynamicSqlSupport.paymentTime).equalToWhenPresent(record::getPaymentTime)
                .set(OmsOrderDynamicSqlSupport.deliveryTime).equalToWhenPresent(record::getDeliveryTime)
                .set(OmsOrderDynamicSqlSupport.receiveTime).equalToWhenPresent(record::getReceiveTime)
                .set(OmsOrderDynamicSqlSupport.commentTime).equalToWhenPresent(record::getCommentTime)
                .set(OmsOrderDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(OmsOrderDynamicSqlSupport.modifyTime).equalToWhenPresent(record::getModifyTime);
    }

    default int updateByPrimaryKey(OmsOrder record) {
        return update(c ->
            c.set(OmsOrderDynamicSqlSupport.memberId).equalTo(record::getMemberId)
            .set(OmsOrderDynamicSqlSupport.couponId).equalTo(record::getCouponId)
            .set(OmsOrderDynamicSqlSupport.orderSn).equalTo(record::getOrderSn)
            .set(OmsOrderDynamicSqlSupport.transactionId).equalTo(record::getTransactionId)
            .set(OmsOrderDynamicSqlSupport.memberUsername).equalTo(record::getMemberUsername)
            .set(OmsOrderDynamicSqlSupport.totalAmount).equalTo(record::getTotalAmount)
            .set(OmsOrderDynamicSqlSupport.payAmount).equalTo(record::getPayAmount)
            .set(OmsOrderDynamicSqlSupport.freightAmount).equalTo(record::getFreightAmount)
            .set(OmsOrderDynamicSqlSupport.promotionAmount).equalTo(record::getPromotionAmount)
            .set(OmsOrderDynamicSqlSupport.integrationAmount).equalTo(record::getIntegrationAmount)
            .set(OmsOrderDynamicSqlSupport.couponAmount).equalTo(record::getCouponAmount)
            .set(OmsOrderDynamicSqlSupport.discountAmount).equalTo(record::getDiscountAmount)
            .set(OmsOrderDynamicSqlSupport.payType).equalTo(record::getPayType)
            .set(OmsOrderDynamicSqlSupport.sourceType).equalTo(record::getSourceType)
            .set(OmsOrderDynamicSqlSupport.status).equalTo(record::getStatus)
            .set(OmsOrderDynamicSqlSupport.orderType).equalTo(record::getOrderType)
            .set(OmsOrderDynamicSqlSupport.deliveryCode).equalTo(record::getDeliveryCode)
            .set(OmsOrderDynamicSqlSupport.deliveryCompany).equalTo(record::getDeliveryCompany)
            .set(OmsOrderDynamicSqlSupport.deliverySn).equalTo(record::getDeliverySn)
            .set(OmsOrderDynamicSqlSupport.autoConfirmDay).equalTo(record::getAutoConfirmDay)
            .set(OmsOrderDynamicSqlSupport.integration).equalTo(record::getIntegration)
            .set(OmsOrderDynamicSqlSupport.growth).equalTo(record::getGrowth)
            .set(OmsOrderDynamicSqlSupport.promotionInfo).equalTo(record::getPromotionInfo)
            .set(OmsOrderDynamicSqlSupport.billType).equalTo(record::getBillType)
            .set(OmsOrderDynamicSqlSupport.billHeader).equalTo(record::getBillHeader)
            .set(OmsOrderDynamicSqlSupport.billContent).equalTo(record::getBillContent)
            .set(OmsOrderDynamicSqlSupport.billReceiverPhone).equalTo(record::getBillReceiverPhone)
            .set(OmsOrderDynamicSqlSupport.billReceiverEmail).equalTo(record::getBillReceiverEmail)
            .set(OmsOrderDynamicSqlSupport.receiverName).equalTo(record::getReceiverName)
            .set(OmsOrderDynamicSqlSupport.receiverPhone).equalTo(record::getReceiverPhone)
            .set(OmsOrderDynamicSqlSupport.receiverPostCode).equalTo(record::getReceiverPostCode)
            .set(OmsOrderDynamicSqlSupport.receiverProvince).equalTo(record::getReceiverProvince)
            .set(OmsOrderDynamicSqlSupport.receiverCity).equalTo(record::getReceiverCity)
            .set(OmsOrderDynamicSqlSupport.receiverRegion).equalTo(record::getReceiverRegion)
            .set(OmsOrderDynamicSqlSupport.receiverDetailAddress).equalTo(record::getReceiverDetailAddress)
            .set(OmsOrderDynamicSqlSupport.note).equalTo(record::getNote)
            .set(OmsOrderDynamicSqlSupport.confirmStatus).equalTo(record::getConfirmStatus)
            .set(OmsOrderDynamicSqlSupport.deleteStatus).equalTo(record::getDeleteStatus)
            .set(OmsOrderDynamicSqlSupport.useIntegration).equalTo(record::getUseIntegration)
            .set(OmsOrderDynamicSqlSupport.paymentTime).equalTo(record::getPaymentTime)
            .set(OmsOrderDynamicSqlSupport.deliveryTime).equalTo(record::getDeliveryTime)
            .set(OmsOrderDynamicSqlSupport.receiveTime).equalTo(record::getReceiveTime)
            .set(OmsOrderDynamicSqlSupport.commentTime).equalTo(record::getCommentTime)
            .set(OmsOrderDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .set(OmsOrderDynamicSqlSupport.modifyTime).equalTo(record::getModifyTime)
            .where(OmsOrderDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(OmsOrder record) {
        return update(c ->
            c.set(OmsOrderDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
            .set(OmsOrderDynamicSqlSupport.couponId).equalToWhenPresent(record::getCouponId)
            .set(OmsOrderDynamicSqlSupport.orderSn).equalToWhenPresent(record::getOrderSn)
            .set(OmsOrderDynamicSqlSupport.transactionId).equalToWhenPresent(record::getTransactionId)
            .set(OmsOrderDynamicSqlSupport.memberUsername).equalToWhenPresent(record::getMemberUsername)
            .set(OmsOrderDynamicSqlSupport.totalAmount).equalToWhenPresent(record::getTotalAmount)
            .set(OmsOrderDynamicSqlSupport.payAmount).equalToWhenPresent(record::getPayAmount)
            .set(OmsOrderDynamicSqlSupport.freightAmount).equalToWhenPresent(record::getFreightAmount)
            .set(OmsOrderDynamicSqlSupport.promotionAmount).equalToWhenPresent(record::getPromotionAmount)
            .set(OmsOrderDynamicSqlSupport.integrationAmount).equalToWhenPresent(record::getIntegrationAmount)
            .set(OmsOrderDynamicSqlSupport.couponAmount).equalToWhenPresent(record::getCouponAmount)
            .set(OmsOrderDynamicSqlSupport.discountAmount).equalToWhenPresent(record::getDiscountAmount)
            .set(OmsOrderDynamicSqlSupport.payType).equalToWhenPresent(record::getPayType)
            .set(OmsOrderDynamicSqlSupport.sourceType).equalToWhenPresent(record::getSourceType)
            .set(OmsOrderDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
            .set(OmsOrderDynamicSqlSupport.orderType).equalToWhenPresent(record::getOrderType)
            .set(OmsOrderDynamicSqlSupport.deliveryCode).equalToWhenPresent(record::getDeliveryCode)
            .set(OmsOrderDynamicSqlSupport.deliveryCompany).equalToWhenPresent(record::getDeliveryCompany)
            .set(OmsOrderDynamicSqlSupport.deliverySn).equalToWhenPresent(record::getDeliverySn)
            .set(OmsOrderDynamicSqlSupport.autoConfirmDay).equalToWhenPresent(record::getAutoConfirmDay)
            .set(OmsOrderDynamicSqlSupport.integration).equalToWhenPresent(record::getIntegration)
            .set(OmsOrderDynamicSqlSupport.growth).equalToWhenPresent(record::getGrowth)
            .set(OmsOrderDynamicSqlSupport.promotionInfo).equalToWhenPresent(record::getPromotionInfo)
            .set(OmsOrderDynamicSqlSupport.billType).equalToWhenPresent(record::getBillType)
            .set(OmsOrderDynamicSqlSupport.billHeader).equalToWhenPresent(record::getBillHeader)
            .set(OmsOrderDynamicSqlSupport.billContent).equalToWhenPresent(record::getBillContent)
            .set(OmsOrderDynamicSqlSupport.billReceiverPhone).equalToWhenPresent(record::getBillReceiverPhone)
            .set(OmsOrderDynamicSqlSupport.billReceiverEmail).equalToWhenPresent(record::getBillReceiverEmail)
            .set(OmsOrderDynamicSqlSupport.receiverName).equalToWhenPresent(record::getReceiverName)
            .set(OmsOrderDynamicSqlSupport.receiverPhone).equalToWhenPresent(record::getReceiverPhone)
            .set(OmsOrderDynamicSqlSupport.receiverPostCode).equalToWhenPresent(record::getReceiverPostCode)
            .set(OmsOrderDynamicSqlSupport.receiverProvince).equalToWhenPresent(record::getReceiverProvince)
            .set(OmsOrderDynamicSqlSupport.receiverCity).equalToWhenPresent(record::getReceiverCity)
            .set(OmsOrderDynamicSqlSupport.receiverRegion).equalToWhenPresent(record::getReceiverRegion)
            .set(OmsOrderDynamicSqlSupport.receiverDetailAddress).equalToWhenPresent(record::getReceiverDetailAddress)
            .set(OmsOrderDynamicSqlSupport.note).equalToWhenPresent(record::getNote)
            .set(OmsOrderDynamicSqlSupport.confirmStatus).equalToWhenPresent(record::getConfirmStatus)
            .set(OmsOrderDynamicSqlSupport.deleteStatus).equalToWhenPresent(record::getDeleteStatus)
            .set(OmsOrderDynamicSqlSupport.useIntegration).equalToWhenPresent(record::getUseIntegration)
            .set(OmsOrderDynamicSqlSupport.paymentTime).equalToWhenPresent(record::getPaymentTime)
            .set(OmsOrderDynamicSqlSupport.deliveryTime).equalToWhenPresent(record::getDeliveryTime)
            .set(OmsOrderDynamicSqlSupport.receiveTime).equalToWhenPresent(record::getReceiveTime)
            .set(OmsOrderDynamicSqlSupport.commentTime).equalToWhenPresent(record::getCommentTime)
            .set(OmsOrderDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .set(OmsOrderDynamicSqlSupport.modifyTime).equalToWhenPresent(record::getModifyTime)
            .where(OmsOrderDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}