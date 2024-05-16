/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.SmsFlashPromotionLog;

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
public interface SmsFlashPromotionLogMapper {
    BasicColumn[] selectList = BasicColumn.columnList(SmsFlashPromotionLogDynamicSqlSupport.id, SmsFlashPromotionLogDynamicSqlSupport.memberId, SmsFlashPromotionLogDynamicSqlSupport.productId, SmsFlashPromotionLogDynamicSqlSupport.memberPhone, SmsFlashPromotionLogDynamicSqlSupport.productName, SmsFlashPromotionLogDynamicSqlSupport.subscribeTime, SmsFlashPromotionLogDynamicSqlSupport.sendTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Integer.class)
    int insert(InsertStatementProvider<SmsFlashPromotionLog> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SmsFlashPromotionLogResult")
    Optional<SmsFlashPromotionLog> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SmsFlashPromotionLogResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.INTEGER),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_phone", property="memberPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="subscribe_time", property="subscribeTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="send_time", property="sendTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SmsFlashPromotionLog> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, SmsFlashPromotionLogDynamicSqlSupport.smsFlashPromotionLog, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, SmsFlashPromotionLogDynamicSqlSupport.smsFlashPromotionLog, completer);
    }

    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(SmsFlashPromotionLogDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(SmsFlashPromotionLog record) {
        return MyBatis3Utils.insert(this::insert, record, SmsFlashPromotionLogDynamicSqlSupport.smsFlashPromotionLog, c ->
            c.map(SmsFlashPromotionLogDynamicSqlSupport.memberId).toProperty("memberId")
            .map(SmsFlashPromotionLogDynamicSqlSupport.productId).toProperty("productId")
            .map(SmsFlashPromotionLogDynamicSqlSupport.memberPhone).toProperty("memberPhone")
            .map(SmsFlashPromotionLogDynamicSqlSupport.productName).toProperty("productName")
            .map(SmsFlashPromotionLogDynamicSqlSupport.subscribeTime).toProperty("subscribeTime")
            .map(SmsFlashPromotionLogDynamicSqlSupport.sendTime).toProperty("sendTime")
        );
    }

    default int insertSelective(SmsFlashPromotionLog record) {
        return MyBatis3Utils.insert(this::insert, record, SmsFlashPromotionLogDynamicSqlSupport.smsFlashPromotionLog, c ->
            c.map(SmsFlashPromotionLogDynamicSqlSupport.memberId).toPropertyWhenPresent("memberId", record::getMemberId)
            .map(SmsFlashPromotionLogDynamicSqlSupport.productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(SmsFlashPromotionLogDynamicSqlSupport.memberPhone).toPropertyWhenPresent("memberPhone", record::getMemberPhone)
            .map(SmsFlashPromotionLogDynamicSqlSupport.productName).toPropertyWhenPresent("productName", record::getProductName)
            .map(SmsFlashPromotionLogDynamicSqlSupport.subscribeTime).toPropertyWhenPresent("subscribeTime", record::getSubscribeTime)
            .map(SmsFlashPromotionLogDynamicSqlSupport.sendTime).toPropertyWhenPresent("sendTime", record::getSendTime)
        );
    }

    default Optional<SmsFlashPromotionLog> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, SmsFlashPromotionLogDynamicSqlSupport.smsFlashPromotionLog, completer);
    }

    default List<SmsFlashPromotionLog> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, SmsFlashPromotionLogDynamicSqlSupport.smsFlashPromotionLog, completer);
    }

    default List<SmsFlashPromotionLog> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, SmsFlashPromotionLogDynamicSqlSupport.smsFlashPromotionLog, completer);
    }

    default Optional<SmsFlashPromotionLog> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(SmsFlashPromotionLogDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, SmsFlashPromotionLogDynamicSqlSupport.smsFlashPromotionLog, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SmsFlashPromotionLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsFlashPromotionLogDynamicSqlSupport.memberId).equalTo(record::getMemberId)
                .set(SmsFlashPromotionLogDynamicSqlSupport.productId).equalTo(record::getProductId)
                .set(SmsFlashPromotionLogDynamicSqlSupport.memberPhone).equalTo(record::getMemberPhone)
                .set(SmsFlashPromotionLogDynamicSqlSupport.productName).equalTo(record::getProductName)
                .set(SmsFlashPromotionLogDynamicSqlSupport.subscribeTime).equalTo(record::getSubscribeTime)
                .set(SmsFlashPromotionLogDynamicSqlSupport.sendTime).equalTo(record::getSendTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SmsFlashPromotionLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsFlashPromotionLogDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
                .set(SmsFlashPromotionLogDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
                .set(SmsFlashPromotionLogDynamicSqlSupport.memberPhone).equalToWhenPresent(record::getMemberPhone)
                .set(SmsFlashPromotionLogDynamicSqlSupport.productName).equalToWhenPresent(record::getProductName)
                .set(SmsFlashPromotionLogDynamicSqlSupport.subscribeTime).equalToWhenPresent(record::getSubscribeTime)
                .set(SmsFlashPromotionLogDynamicSqlSupport.sendTime).equalToWhenPresent(record::getSendTime);
    }

    default int updateByPrimaryKey(SmsFlashPromotionLog record) {
        return update(c ->
            c.set(SmsFlashPromotionLogDynamicSqlSupport.memberId).equalTo(record::getMemberId)
            .set(SmsFlashPromotionLogDynamicSqlSupport.productId).equalTo(record::getProductId)
            .set(SmsFlashPromotionLogDynamicSqlSupport.memberPhone).equalTo(record::getMemberPhone)
            .set(SmsFlashPromotionLogDynamicSqlSupport.productName).equalTo(record::getProductName)
            .set(SmsFlashPromotionLogDynamicSqlSupport.subscribeTime).equalTo(record::getSubscribeTime)
            .set(SmsFlashPromotionLogDynamicSqlSupport.sendTime).equalTo(record::getSendTime)
            .where(SmsFlashPromotionLogDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(SmsFlashPromotionLog record) {
        return update(c ->
            c.set(SmsFlashPromotionLogDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
            .set(SmsFlashPromotionLogDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
            .set(SmsFlashPromotionLogDynamicSqlSupport.memberPhone).equalToWhenPresent(record::getMemberPhone)
            .set(SmsFlashPromotionLogDynamicSqlSupport.productName).equalToWhenPresent(record::getProductName)
            .set(SmsFlashPromotionLogDynamicSqlSupport.subscribeTime).equalToWhenPresent(record::getSubscribeTime)
            .set(SmsFlashPromotionLogDynamicSqlSupport.sendTime).equalToWhenPresent(record::getSendTime)
            .where(SmsFlashPromotionLogDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}