/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.SmsCouponHistoryDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.SmsCouponHistory;
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
public interface SmsCouponHistoryMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, couponId, memberId, couponCode, memberNickname, getType, useStatus, useTime, orderId, orderSn, createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SmsCouponHistory> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SmsCouponHistoryResult")
    Optional<SmsCouponHistory> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SmsCouponHistoryResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="coupon_id", property="couponId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.BIGINT),
        @Result(column="coupon_code", property="couponCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_nickname", property="memberNickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="get_type", property="getType", jdbcType=JdbcType.INTEGER),
        @Result(column="use_status", property="useStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="use_time", property="useTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="order_sn", property="orderSn", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SmsCouponHistory> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, smsCouponHistory, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, smsCouponHistory, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(SmsCouponHistory record) {
        return MyBatis3Utils.insert(this::insert, record, smsCouponHistory, c ->
            c.map(couponId).toProperty("couponId")
            .map(memberId).toProperty("memberId")
            .map(couponCode).toProperty("couponCode")
            .map(memberNickname).toProperty("memberNickname")
            .map(getType).toProperty("getType")
            .map(useStatus).toProperty("useStatus")
            .map(useTime).toProperty("useTime")
            .map(orderId).toProperty("orderId")
            .map(orderSn).toProperty("orderSn")
            .map(createTime).toProperty("createTime")
        );
    }

    default int insertSelective(SmsCouponHistory record) {
        return MyBatis3Utils.insert(this::insert, record, smsCouponHistory, c ->
            c.map(couponId).toPropertyWhenPresent("couponId", record::getCouponId)
            .map(memberId).toPropertyWhenPresent("memberId", record::getMemberId)
            .map(couponCode).toPropertyWhenPresent("couponCode", record::getCouponCode)
            .map(memberNickname).toPropertyWhenPresent("memberNickname", record::getMemberNickname)
            .map(getType).toPropertyWhenPresent("getType", record::getGetType)
            .map(useStatus).toPropertyWhenPresent("useStatus", record::getUseStatus)
            .map(useTime).toPropertyWhenPresent("useTime", record::getUseTime)
            .map(orderId).toPropertyWhenPresent("orderId", record::getOrderId)
            .map(orderSn).toPropertyWhenPresent("orderSn", record::getOrderSn)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<SmsCouponHistory> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, smsCouponHistory, completer);
    }

    default List<SmsCouponHistory> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, smsCouponHistory, completer);
    }

    default List<SmsCouponHistory> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, smsCouponHistory, completer);
    }

    default Optional<SmsCouponHistory> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, smsCouponHistory, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SmsCouponHistory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(couponId).equalTo(record::getCouponId)
                .set(memberId).equalTo(record::getMemberId)
                .set(couponCode).equalTo(record::getCouponCode)
                .set(memberNickname).equalTo(record::getMemberNickname)
                .set(getType).equalTo(record::getGetType)
                .set(useStatus).equalTo(record::getUseStatus)
                .set(useTime).equalTo(record::getUseTime)
                .set(orderId).equalTo(record::getOrderId)
                .set(orderSn).equalTo(record::getOrderSn)
                .set(createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SmsCouponHistory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(couponId).equalToWhenPresent(record::getCouponId)
                .set(memberId).equalToWhenPresent(record::getMemberId)
                .set(couponCode).equalToWhenPresent(record::getCouponCode)
                .set(memberNickname).equalToWhenPresent(record::getMemberNickname)
                .set(getType).equalToWhenPresent(record::getGetType)
                .set(useStatus).equalToWhenPresent(record::getUseStatus)
                .set(useTime).equalToWhenPresent(record::getUseTime)
                .set(orderId).equalToWhenPresent(record::getOrderId)
                .set(orderSn).equalToWhenPresent(record::getOrderSn)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(SmsCouponHistory record) {
        return update(c ->
            c.set(couponId).equalTo(record::getCouponId)
            .set(memberId).equalTo(record::getMemberId)
            .set(couponCode).equalTo(record::getCouponCode)
            .set(memberNickname).equalTo(record::getMemberNickname)
            .set(getType).equalTo(record::getGetType)
            .set(useStatus).equalTo(record::getUseStatus)
            .set(useTime).equalTo(record::getUseTime)
            .set(orderId).equalTo(record::getOrderId)
            .set(orderSn).equalTo(record::getOrderSn)
            .set(createTime).equalTo(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(SmsCouponHistory record) {
        return update(c ->
            c.set(couponId).equalToWhenPresent(record::getCouponId)
            .set(memberId).equalToWhenPresent(record::getMemberId)
            .set(couponCode).equalToWhenPresent(record::getCouponCode)
            .set(memberNickname).equalToWhenPresent(record::getMemberNickname)
            .set(getType).equalToWhenPresent(record::getGetType)
            .set(useStatus).equalToWhenPresent(record::getUseStatus)
            .set(useTime).equalToWhenPresent(record::getUseTime)
            .set(orderId).equalToWhenPresent(record::getOrderId)
            .set(orderSn).equalToWhenPresent(record::getOrderSn)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}