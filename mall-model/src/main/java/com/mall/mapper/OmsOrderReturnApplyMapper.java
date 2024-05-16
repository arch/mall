/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.OmsOrderReturnApply;

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
public interface OmsOrderReturnApplyMapper {
    BasicColumn[] selectList = BasicColumn.columnList(OmsOrderReturnApplyDynamicSqlSupport.id, OmsOrderReturnApplyDynamicSqlSupport.orderId, OmsOrderReturnApplyDynamicSqlSupport.companyAddressId, OmsOrderReturnApplyDynamicSqlSupport.productId, OmsOrderReturnApplyDynamicSqlSupport.orderSn, OmsOrderReturnApplyDynamicSqlSupport.memberId, OmsOrderReturnApplyDynamicSqlSupport.memberUsername, OmsOrderReturnApplyDynamicSqlSupport.returnAmount, OmsOrderReturnApplyDynamicSqlSupport.returnName, OmsOrderReturnApplyDynamicSqlSupport.returnPhone, OmsOrderReturnApplyDynamicSqlSupport.deliveryCode, OmsOrderReturnApplyDynamicSqlSupport.deliverySn, OmsOrderReturnApplyDynamicSqlSupport.status, OmsOrderReturnApplyDynamicSqlSupport.productPic, OmsOrderReturnApplyDynamicSqlSupport.productName, OmsOrderReturnApplyDynamicSqlSupport.productBrand, OmsOrderReturnApplyDynamicSqlSupport.productAttr, OmsOrderReturnApplyDynamicSqlSupport.productCount, OmsOrderReturnApplyDynamicSqlSupport.productPrice, OmsOrderReturnApplyDynamicSqlSupport.productRealPrice, OmsOrderReturnApplyDynamicSqlSupport.reason, OmsOrderReturnApplyDynamicSqlSupport.description, OmsOrderReturnApplyDynamicSqlSupport.proofPics, OmsOrderReturnApplyDynamicSqlSupport.handleNote, OmsOrderReturnApplyDynamicSqlSupport.handleMan, OmsOrderReturnApplyDynamicSqlSupport.handleTime, OmsOrderReturnApplyDynamicSqlSupport.receiveMan, OmsOrderReturnApplyDynamicSqlSupport.receiveTime, OmsOrderReturnApplyDynamicSqlSupport.receiveNote, OmsOrderReturnApplyDynamicSqlSupport.createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<OmsOrderReturnApply> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OmsOrderReturnApplyResult")
    Optional<OmsOrderReturnApply> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OmsOrderReturnApplyResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="company_address_id", property="companyAddressId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="order_sn", property="orderSn", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_username", property="memberUsername", jdbcType=JdbcType.VARCHAR),
        @Result(column="return_amount", property="returnAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="return_name", property="returnName", jdbcType=JdbcType.VARCHAR),
        @Result(column="return_phone", property="returnPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="delivery_code", property="deliveryCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="delivery_sn", property="deliverySn", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="product_pic", property="productPic", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_brand", property="productBrand", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_attr", property="productAttr", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_count", property="productCount", jdbcType=JdbcType.INTEGER),
        @Result(column="product_price", property="productPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="product_real_price", property="productRealPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="reason", property="reason", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="proof_pics", property="proofPics", jdbcType=JdbcType.VARCHAR),
        @Result(column="handle_note", property="handleNote", jdbcType=JdbcType.VARCHAR),
        @Result(column="handle_man", property="handleMan", jdbcType=JdbcType.VARCHAR),
        @Result(column="handle_time", property="handleTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="receive_man", property="receiveMan", jdbcType=JdbcType.VARCHAR),
        @Result(column="receive_time", property="receiveTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="receive_note", property="receiveNote", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<OmsOrderReturnApply> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, OmsOrderReturnApplyDynamicSqlSupport.omsOrderReturnApply, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, OmsOrderReturnApplyDynamicSqlSupport.omsOrderReturnApply, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(OmsOrderReturnApplyDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(OmsOrderReturnApply record) {
        return MyBatis3Utils.insert(this::insert, record, OmsOrderReturnApplyDynamicSqlSupport.omsOrderReturnApply, c ->
            c.map(OmsOrderReturnApplyDynamicSqlSupport.orderId).toProperty("orderId")
            .map(OmsOrderReturnApplyDynamicSqlSupport.companyAddressId).toProperty("companyAddressId")
            .map(OmsOrderReturnApplyDynamicSqlSupport.productId).toProperty("productId")
            .map(OmsOrderReturnApplyDynamicSqlSupport.orderSn).toProperty("orderSn")
            .map(OmsOrderReturnApplyDynamicSqlSupport.memberId).toProperty("memberId")
            .map(OmsOrderReturnApplyDynamicSqlSupport.memberUsername).toProperty("memberUsername")
            .map(OmsOrderReturnApplyDynamicSqlSupport.returnAmount).toProperty("returnAmount")
            .map(OmsOrderReturnApplyDynamicSqlSupport.returnName).toProperty("returnName")
            .map(OmsOrderReturnApplyDynamicSqlSupport.returnPhone).toProperty("returnPhone")
            .map(OmsOrderReturnApplyDynamicSqlSupport.deliveryCode).toProperty("deliveryCode")
            .map(OmsOrderReturnApplyDynamicSqlSupport.deliverySn).toProperty("deliverySn")
            .map(OmsOrderReturnApplyDynamicSqlSupport.status).toProperty("status")
            .map(OmsOrderReturnApplyDynamicSqlSupport.productPic).toProperty("productPic")
            .map(OmsOrderReturnApplyDynamicSqlSupport.productName).toProperty("productName")
            .map(OmsOrderReturnApplyDynamicSqlSupport.productBrand).toProperty("productBrand")
            .map(OmsOrderReturnApplyDynamicSqlSupport.productAttr).toProperty("productAttr")
            .map(OmsOrderReturnApplyDynamicSqlSupport.productCount).toProperty("productCount")
            .map(OmsOrderReturnApplyDynamicSqlSupport.productPrice).toProperty("productPrice")
            .map(OmsOrderReturnApplyDynamicSqlSupport.productRealPrice).toProperty("productRealPrice")
            .map(OmsOrderReturnApplyDynamicSqlSupport.reason).toProperty("reason")
            .map(OmsOrderReturnApplyDynamicSqlSupport.description).toProperty("description")
            .map(OmsOrderReturnApplyDynamicSqlSupport.proofPics).toProperty("proofPics")
            .map(OmsOrderReturnApplyDynamicSqlSupport.handleNote).toProperty("handleNote")
            .map(OmsOrderReturnApplyDynamicSqlSupport.handleMan).toProperty("handleMan")
            .map(OmsOrderReturnApplyDynamicSqlSupport.handleTime).toProperty("handleTime")
            .map(OmsOrderReturnApplyDynamicSqlSupport.receiveMan).toProperty("receiveMan")
            .map(OmsOrderReturnApplyDynamicSqlSupport.receiveTime).toProperty("receiveTime")
            .map(OmsOrderReturnApplyDynamicSqlSupport.receiveNote).toProperty("receiveNote")
            .map(OmsOrderReturnApplyDynamicSqlSupport.createTime).toProperty("createTime")
        );
    }

    default int insertSelective(OmsOrderReturnApply record) {
        return MyBatis3Utils.insert(this::insert, record, OmsOrderReturnApplyDynamicSqlSupport.omsOrderReturnApply, c ->
            c.map(OmsOrderReturnApplyDynamicSqlSupport.orderId).toPropertyWhenPresent("orderId", record::getOrderId)
            .map(OmsOrderReturnApplyDynamicSqlSupport.companyAddressId).toPropertyWhenPresent("companyAddressId", record::getCompanyAddressId)
            .map(OmsOrderReturnApplyDynamicSqlSupport.productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(OmsOrderReturnApplyDynamicSqlSupport.orderSn).toPropertyWhenPresent("orderSn", record::getOrderSn)
            .map(OmsOrderReturnApplyDynamicSqlSupport.memberId).toPropertyWhenPresent("memberId", record::getMemberId)
            .map(OmsOrderReturnApplyDynamicSqlSupport.memberUsername).toPropertyWhenPresent("memberUsername", record::getMemberUsername)
            .map(OmsOrderReturnApplyDynamicSqlSupport.returnAmount).toPropertyWhenPresent("returnAmount", record::getReturnAmount)
            .map(OmsOrderReturnApplyDynamicSqlSupport.returnName).toPropertyWhenPresent("returnName", record::getReturnName)
            .map(OmsOrderReturnApplyDynamicSqlSupport.returnPhone).toPropertyWhenPresent("returnPhone", record::getReturnPhone)
            .map(OmsOrderReturnApplyDynamicSqlSupport.deliveryCode).toPropertyWhenPresent("deliveryCode", record::getDeliveryCode)
            .map(OmsOrderReturnApplyDynamicSqlSupport.deliverySn).toPropertyWhenPresent("deliverySn", record::getDeliverySn)
            .map(OmsOrderReturnApplyDynamicSqlSupport.status).toPropertyWhenPresent("status", record::getStatus)
            .map(OmsOrderReturnApplyDynamicSqlSupport.productPic).toPropertyWhenPresent("productPic", record::getProductPic)
            .map(OmsOrderReturnApplyDynamicSqlSupport.productName).toPropertyWhenPresent("productName", record::getProductName)
            .map(OmsOrderReturnApplyDynamicSqlSupport.productBrand).toPropertyWhenPresent("productBrand", record::getProductBrand)
            .map(OmsOrderReturnApplyDynamicSqlSupport.productAttr).toPropertyWhenPresent("productAttr", record::getProductAttr)
            .map(OmsOrderReturnApplyDynamicSqlSupport.productCount).toPropertyWhenPresent("productCount", record::getProductCount)
            .map(OmsOrderReturnApplyDynamicSqlSupport.productPrice).toPropertyWhenPresent("productPrice", record::getProductPrice)
            .map(OmsOrderReturnApplyDynamicSqlSupport.productRealPrice).toPropertyWhenPresent("productRealPrice", record::getProductRealPrice)
            .map(OmsOrderReturnApplyDynamicSqlSupport.reason).toPropertyWhenPresent("reason", record::getReason)
            .map(OmsOrderReturnApplyDynamicSqlSupport.description).toPropertyWhenPresent("description", record::getDescription)
            .map(OmsOrderReturnApplyDynamicSqlSupport.proofPics).toPropertyWhenPresent("proofPics", record::getProofPics)
            .map(OmsOrderReturnApplyDynamicSqlSupport.handleNote).toPropertyWhenPresent("handleNote", record::getHandleNote)
            .map(OmsOrderReturnApplyDynamicSqlSupport.handleMan).toPropertyWhenPresent("handleMan", record::getHandleMan)
            .map(OmsOrderReturnApplyDynamicSqlSupport.handleTime).toPropertyWhenPresent("handleTime", record::getHandleTime)
            .map(OmsOrderReturnApplyDynamicSqlSupport.receiveMan).toPropertyWhenPresent("receiveMan", record::getReceiveMan)
            .map(OmsOrderReturnApplyDynamicSqlSupport.receiveTime).toPropertyWhenPresent("receiveTime", record::getReceiveTime)
            .map(OmsOrderReturnApplyDynamicSqlSupport.receiveNote).toPropertyWhenPresent("receiveNote", record::getReceiveNote)
            .map(OmsOrderReturnApplyDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<OmsOrderReturnApply> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, OmsOrderReturnApplyDynamicSqlSupport.omsOrderReturnApply, completer);
    }

    default List<OmsOrderReturnApply> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, OmsOrderReturnApplyDynamicSqlSupport.omsOrderReturnApply, completer);
    }

    default List<OmsOrderReturnApply> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, OmsOrderReturnApplyDynamicSqlSupport.omsOrderReturnApply, completer);
    }

    default Optional<OmsOrderReturnApply> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(OmsOrderReturnApplyDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, OmsOrderReturnApplyDynamicSqlSupport.omsOrderReturnApply, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(OmsOrderReturnApply record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsOrderReturnApplyDynamicSqlSupport.orderId).equalTo(record::getOrderId)
                .set(OmsOrderReturnApplyDynamicSqlSupport.companyAddressId).equalTo(record::getCompanyAddressId)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productId).equalTo(record::getProductId)
                .set(OmsOrderReturnApplyDynamicSqlSupport.orderSn).equalTo(record::getOrderSn)
                .set(OmsOrderReturnApplyDynamicSqlSupport.memberId).equalTo(record::getMemberId)
                .set(OmsOrderReturnApplyDynamicSqlSupport.memberUsername).equalTo(record::getMemberUsername)
                .set(OmsOrderReturnApplyDynamicSqlSupport.returnAmount).equalTo(record::getReturnAmount)
                .set(OmsOrderReturnApplyDynamicSqlSupport.returnName).equalTo(record::getReturnName)
                .set(OmsOrderReturnApplyDynamicSqlSupport.returnPhone).equalTo(record::getReturnPhone)
                .set(OmsOrderReturnApplyDynamicSqlSupport.deliveryCode).equalTo(record::getDeliveryCode)
                .set(OmsOrderReturnApplyDynamicSqlSupport.deliverySn).equalTo(record::getDeliverySn)
                .set(OmsOrderReturnApplyDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productPic).equalTo(record::getProductPic)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productName).equalTo(record::getProductName)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productBrand).equalTo(record::getProductBrand)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productAttr).equalTo(record::getProductAttr)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productCount).equalTo(record::getProductCount)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productPrice).equalTo(record::getProductPrice)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productRealPrice).equalTo(record::getProductRealPrice)
                .set(OmsOrderReturnApplyDynamicSqlSupport.reason).equalTo(record::getReason)
                .set(OmsOrderReturnApplyDynamicSqlSupport.description).equalTo(record::getDescription)
                .set(OmsOrderReturnApplyDynamicSqlSupport.proofPics).equalTo(record::getProofPics)
                .set(OmsOrderReturnApplyDynamicSqlSupport.handleNote).equalTo(record::getHandleNote)
                .set(OmsOrderReturnApplyDynamicSqlSupport.handleMan).equalTo(record::getHandleMan)
                .set(OmsOrderReturnApplyDynamicSqlSupport.handleTime).equalTo(record::getHandleTime)
                .set(OmsOrderReturnApplyDynamicSqlSupport.receiveMan).equalTo(record::getReceiveMan)
                .set(OmsOrderReturnApplyDynamicSqlSupport.receiveTime).equalTo(record::getReceiveTime)
                .set(OmsOrderReturnApplyDynamicSqlSupport.receiveNote).equalTo(record::getReceiveNote)
                .set(OmsOrderReturnApplyDynamicSqlSupport.createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(OmsOrderReturnApply record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsOrderReturnApplyDynamicSqlSupport.orderId).equalToWhenPresent(record::getOrderId)
                .set(OmsOrderReturnApplyDynamicSqlSupport.companyAddressId).equalToWhenPresent(record::getCompanyAddressId)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
                .set(OmsOrderReturnApplyDynamicSqlSupport.orderSn).equalToWhenPresent(record::getOrderSn)
                .set(OmsOrderReturnApplyDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
                .set(OmsOrderReturnApplyDynamicSqlSupport.memberUsername).equalToWhenPresent(record::getMemberUsername)
                .set(OmsOrderReturnApplyDynamicSqlSupport.returnAmount).equalToWhenPresent(record::getReturnAmount)
                .set(OmsOrderReturnApplyDynamicSqlSupport.returnName).equalToWhenPresent(record::getReturnName)
                .set(OmsOrderReturnApplyDynamicSqlSupport.returnPhone).equalToWhenPresent(record::getReturnPhone)
                .set(OmsOrderReturnApplyDynamicSqlSupport.deliveryCode).equalToWhenPresent(record::getDeliveryCode)
                .set(OmsOrderReturnApplyDynamicSqlSupport.deliverySn).equalToWhenPresent(record::getDeliverySn)
                .set(OmsOrderReturnApplyDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productPic).equalToWhenPresent(record::getProductPic)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productName).equalToWhenPresent(record::getProductName)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productBrand).equalToWhenPresent(record::getProductBrand)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productAttr).equalToWhenPresent(record::getProductAttr)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productCount).equalToWhenPresent(record::getProductCount)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productPrice).equalToWhenPresent(record::getProductPrice)
                .set(OmsOrderReturnApplyDynamicSqlSupport.productRealPrice).equalToWhenPresent(record::getProductRealPrice)
                .set(OmsOrderReturnApplyDynamicSqlSupport.reason).equalToWhenPresent(record::getReason)
                .set(OmsOrderReturnApplyDynamicSqlSupport.description).equalToWhenPresent(record::getDescription)
                .set(OmsOrderReturnApplyDynamicSqlSupport.proofPics).equalToWhenPresent(record::getProofPics)
                .set(OmsOrderReturnApplyDynamicSqlSupport.handleNote).equalToWhenPresent(record::getHandleNote)
                .set(OmsOrderReturnApplyDynamicSqlSupport.handleMan).equalToWhenPresent(record::getHandleMan)
                .set(OmsOrderReturnApplyDynamicSqlSupport.handleTime).equalToWhenPresent(record::getHandleTime)
                .set(OmsOrderReturnApplyDynamicSqlSupport.receiveMan).equalToWhenPresent(record::getReceiveMan)
                .set(OmsOrderReturnApplyDynamicSqlSupport.receiveTime).equalToWhenPresent(record::getReceiveTime)
                .set(OmsOrderReturnApplyDynamicSqlSupport.receiveNote).equalToWhenPresent(record::getReceiveNote)
                .set(OmsOrderReturnApplyDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(OmsOrderReturnApply record) {
        return update(c ->
            c.set(OmsOrderReturnApplyDynamicSqlSupport.orderId).equalTo(record::getOrderId)
            .set(OmsOrderReturnApplyDynamicSqlSupport.companyAddressId).equalTo(record::getCompanyAddressId)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productId).equalTo(record::getProductId)
            .set(OmsOrderReturnApplyDynamicSqlSupport.orderSn).equalTo(record::getOrderSn)
            .set(OmsOrderReturnApplyDynamicSqlSupport.memberId).equalTo(record::getMemberId)
            .set(OmsOrderReturnApplyDynamicSqlSupport.memberUsername).equalTo(record::getMemberUsername)
            .set(OmsOrderReturnApplyDynamicSqlSupport.returnAmount).equalTo(record::getReturnAmount)
            .set(OmsOrderReturnApplyDynamicSqlSupport.returnName).equalTo(record::getReturnName)
            .set(OmsOrderReturnApplyDynamicSqlSupport.returnPhone).equalTo(record::getReturnPhone)
            .set(OmsOrderReturnApplyDynamicSqlSupport.deliveryCode).equalTo(record::getDeliveryCode)
            .set(OmsOrderReturnApplyDynamicSqlSupport.deliverySn).equalTo(record::getDeliverySn)
            .set(OmsOrderReturnApplyDynamicSqlSupport.status).equalTo(record::getStatus)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productPic).equalTo(record::getProductPic)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productName).equalTo(record::getProductName)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productBrand).equalTo(record::getProductBrand)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productAttr).equalTo(record::getProductAttr)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productCount).equalTo(record::getProductCount)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productPrice).equalTo(record::getProductPrice)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productRealPrice).equalTo(record::getProductRealPrice)
            .set(OmsOrderReturnApplyDynamicSqlSupport.reason).equalTo(record::getReason)
            .set(OmsOrderReturnApplyDynamicSqlSupport.description).equalTo(record::getDescription)
            .set(OmsOrderReturnApplyDynamicSqlSupport.proofPics).equalTo(record::getProofPics)
            .set(OmsOrderReturnApplyDynamicSqlSupport.handleNote).equalTo(record::getHandleNote)
            .set(OmsOrderReturnApplyDynamicSqlSupport.handleMan).equalTo(record::getHandleMan)
            .set(OmsOrderReturnApplyDynamicSqlSupport.handleTime).equalTo(record::getHandleTime)
            .set(OmsOrderReturnApplyDynamicSqlSupport.receiveMan).equalTo(record::getReceiveMan)
            .set(OmsOrderReturnApplyDynamicSqlSupport.receiveTime).equalTo(record::getReceiveTime)
            .set(OmsOrderReturnApplyDynamicSqlSupport.receiveNote).equalTo(record::getReceiveNote)
            .set(OmsOrderReturnApplyDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .where(OmsOrderReturnApplyDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(OmsOrderReturnApply record) {
        return update(c ->
            c.set(OmsOrderReturnApplyDynamicSqlSupport.orderId).equalToWhenPresent(record::getOrderId)
            .set(OmsOrderReturnApplyDynamicSqlSupport.companyAddressId).equalToWhenPresent(record::getCompanyAddressId)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
            .set(OmsOrderReturnApplyDynamicSqlSupport.orderSn).equalToWhenPresent(record::getOrderSn)
            .set(OmsOrderReturnApplyDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
            .set(OmsOrderReturnApplyDynamicSqlSupport.memberUsername).equalToWhenPresent(record::getMemberUsername)
            .set(OmsOrderReturnApplyDynamicSqlSupport.returnAmount).equalToWhenPresent(record::getReturnAmount)
            .set(OmsOrderReturnApplyDynamicSqlSupport.returnName).equalToWhenPresent(record::getReturnName)
            .set(OmsOrderReturnApplyDynamicSqlSupport.returnPhone).equalToWhenPresent(record::getReturnPhone)
            .set(OmsOrderReturnApplyDynamicSqlSupport.deliveryCode).equalToWhenPresent(record::getDeliveryCode)
            .set(OmsOrderReturnApplyDynamicSqlSupport.deliverySn).equalToWhenPresent(record::getDeliverySn)
            .set(OmsOrderReturnApplyDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productPic).equalToWhenPresent(record::getProductPic)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productName).equalToWhenPresent(record::getProductName)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productBrand).equalToWhenPresent(record::getProductBrand)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productAttr).equalToWhenPresent(record::getProductAttr)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productCount).equalToWhenPresent(record::getProductCount)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productPrice).equalToWhenPresent(record::getProductPrice)
            .set(OmsOrderReturnApplyDynamicSqlSupport.productRealPrice).equalToWhenPresent(record::getProductRealPrice)
            .set(OmsOrderReturnApplyDynamicSqlSupport.reason).equalToWhenPresent(record::getReason)
            .set(OmsOrderReturnApplyDynamicSqlSupport.description).equalToWhenPresent(record::getDescription)
            .set(OmsOrderReturnApplyDynamicSqlSupport.proofPics).equalToWhenPresent(record::getProofPics)
            .set(OmsOrderReturnApplyDynamicSqlSupport.handleNote).equalToWhenPresent(record::getHandleNote)
            .set(OmsOrderReturnApplyDynamicSqlSupport.handleMan).equalToWhenPresent(record::getHandleMan)
            .set(OmsOrderReturnApplyDynamicSqlSupport.handleTime).equalToWhenPresent(record::getHandleTime)
            .set(OmsOrderReturnApplyDynamicSqlSupport.receiveMan).equalToWhenPresent(record::getReceiveMan)
            .set(OmsOrderReturnApplyDynamicSqlSupport.receiveTime).equalToWhenPresent(record::getReceiveTime)
            .set(OmsOrderReturnApplyDynamicSqlSupport.receiveNote).equalToWhenPresent(record::getReceiveNote)
            .set(OmsOrderReturnApplyDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .where(OmsOrderReturnApplyDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}