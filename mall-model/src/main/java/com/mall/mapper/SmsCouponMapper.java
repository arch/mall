/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.SmsCoupon;

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
public interface SmsCouponMapper {
    BasicColumn[] selectList = BasicColumn.columnList(SmsCouponDynamicSqlSupport.id, SmsCouponDynamicSqlSupport.type, SmsCouponDynamicSqlSupport.name, SmsCouponDynamicSqlSupport.platform, SmsCouponDynamicSqlSupport.count, SmsCouponDynamicSqlSupport.amount, SmsCouponDynamicSqlSupport.perLimit, SmsCouponDynamicSqlSupport.minPoint, SmsCouponDynamicSqlSupport.startTime, SmsCouponDynamicSqlSupport.endTime, SmsCouponDynamicSqlSupport.useType, SmsCouponDynamicSqlSupport.note, SmsCouponDynamicSqlSupport.publishCount, SmsCouponDynamicSqlSupport.useCount, SmsCouponDynamicSqlSupport.receiveCount, SmsCouponDynamicSqlSupport.enableTime, SmsCouponDynamicSqlSupport.code, SmsCouponDynamicSqlSupport.memberLevel);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SmsCoupon> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SmsCouponResult")
    Optional<SmsCoupon> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SmsCouponResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="platform", property="platform", jdbcType=JdbcType.INTEGER),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="per_limit", property="perLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="min_point", property="minPoint", jdbcType=JdbcType.DECIMAL),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="use_type", property="useType", jdbcType=JdbcType.INTEGER),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="publish_count", property="publishCount", jdbcType=JdbcType.INTEGER),
        @Result(column="use_count", property="useCount", jdbcType=JdbcType.INTEGER),
        @Result(column="receive_count", property="receiveCount", jdbcType=JdbcType.INTEGER),
        @Result(column="enable_time", property="enableTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_level", property="memberLevel", jdbcType=JdbcType.INTEGER)
    })
    List<SmsCoupon> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, SmsCouponDynamicSqlSupport.smsCoupon, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, SmsCouponDynamicSqlSupport.smsCoupon, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(SmsCouponDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(SmsCoupon record) {
        return MyBatis3Utils.insert(this::insert, record, SmsCouponDynamicSqlSupport.smsCoupon, c ->
            c.map(SmsCouponDynamicSqlSupport.type).toProperty("type")
            .map(SmsCouponDynamicSqlSupport.name).toProperty("name")
            .map(SmsCouponDynamicSqlSupport.platform).toProperty("platform")
            .map(SmsCouponDynamicSqlSupport.count).toProperty("count")
            .map(SmsCouponDynamicSqlSupport.amount).toProperty("amount")
            .map(SmsCouponDynamicSqlSupport.perLimit).toProperty("perLimit")
            .map(SmsCouponDynamicSqlSupport.minPoint).toProperty("minPoint")
            .map(SmsCouponDynamicSqlSupport.startTime).toProperty("startTime")
            .map(SmsCouponDynamicSqlSupport.endTime).toProperty("endTime")
            .map(SmsCouponDynamicSqlSupport.useType).toProperty("useType")
            .map(SmsCouponDynamicSqlSupport.note).toProperty("note")
            .map(SmsCouponDynamicSqlSupport.publishCount).toProperty("publishCount")
            .map(SmsCouponDynamicSqlSupport.useCount).toProperty("useCount")
            .map(SmsCouponDynamicSqlSupport.receiveCount).toProperty("receiveCount")
            .map(SmsCouponDynamicSqlSupport.enableTime).toProperty("enableTime")
            .map(SmsCouponDynamicSqlSupport.code).toProperty("code")
            .map(SmsCouponDynamicSqlSupport.memberLevel).toProperty("memberLevel")
        );
    }

    default int insertSelective(SmsCoupon record) {
        return MyBatis3Utils.insert(this::insert, record, SmsCouponDynamicSqlSupport.smsCoupon, c ->
            c.map(SmsCouponDynamicSqlSupport.type).toPropertyWhenPresent("type", record::getType)
            .map(SmsCouponDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(SmsCouponDynamicSqlSupport.platform).toPropertyWhenPresent("platform", record::getPlatform)
            .map(SmsCouponDynamicSqlSupport.count).toPropertyWhenPresent("count", record::getCount)
            .map(SmsCouponDynamicSqlSupport.amount).toPropertyWhenPresent("amount", record::getAmount)
            .map(SmsCouponDynamicSqlSupport.perLimit).toPropertyWhenPresent("perLimit", record::getPerLimit)
            .map(SmsCouponDynamicSqlSupport.minPoint).toPropertyWhenPresent("minPoint", record::getMinPoint)
            .map(SmsCouponDynamicSqlSupport.startTime).toPropertyWhenPresent("startTime", record::getStartTime)
            .map(SmsCouponDynamicSqlSupport.endTime).toPropertyWhenPresent("endTime", record::getEndTime)
            .map(SmsCouponDynamicSqlSupport.useType).toPropertyWhenPresent("useType", record::getUseType)
            .map(SmsCouponDynamicSqlSupport.note).toPropertyWhenPresent("note", record::getNote)
            .map(SmsCouponDynamicSqlSupport.publishCount).toPropertyWhenPresent("publishCount", record::getPublishCount)
            .map(SmsCouponDynamicSqlSupport.useCount).toPropertyWhenPresent("useCount", record::getUseCount)
            .map(SmsCouponDynamicSqlSupport.receiveCount).toPropertyWhenPresent("receiveCount", record::getReceiveCount)
            .map(SmsCouponDynamicSqlSupport.enableTime).toPropertyWhenPresent("enableTime", record::getEnableTime)
            .map(SmsCouponDynamicSqlSupport.code).toPropertyWhenPresent("code", record::getCode)
            .map(SmsCouponDynamicSqlSupport.memberLevel).toPropertyWhenPresent("memberLevel", record::getMemberLevel)
        );
    }

    default Optional<SmsCoupon> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, SmsCouponDynamicSqlSupport.smsCoupon, completer);
    }

    default List<SmsCoupon> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, SmsCouponDynamicSqlSupport.smsCoupon, completer);
    }

    default List<SmsCoupon> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, SmsCouponDynamicSqlSupport.smsCoupon, completer);
    }

    default Optional<SmsCoupon> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(SmsCouponDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, SmsCouponDynamicSqlSupport.smsCoupon, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SmsCoupon record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsCouponDynamicSqlSupport.type).equalTo(record::getType)
                .set(SmsCouponDynamicSqlSupport.name).equalTo(record::getName)
                .set(SmsCouponDynamicSqlSupport.platform).equalTo(record::getPlatform)
                .set(SmsCouponDynamicSqlSupport.count).equalTo(record::getCount)
                .set(SmsCouponDynamicSqlSupport.amount).equalTo(record::getAmount)
                .set(SmsCouponDynamicSqlSupport.perLimit).equalTo(record::getPerLimit)
                .set(SmsCouponDynamicSqlSupport.minPoint).equalTo(record::getMinPoint)
                .set(SmsCouponDynamicSqlSupport.startTime).equalTo(record::getStartTime)
                .set(SmsCouponDynamicSqlSupport.endTime).equalTo(record::getEndTime)
                .set(SmsCouponDynamicSqlSupport.useType).equalTo(record::getUseType)
                .set(SmsCouponDynamicSqlSupport.note).equalTo(record::getNote)
                .set(SmsCouponDynamicSqlSupport.publishCount).equalTo(record::getPublishCount)
                .set(SmsCouponDynamicSqlSupport.useCount).equalTo(record::getUseCount)
                .set(SmsCouponDynamicSqlSupport.receiveCount).equalTo(record::getReceiveCount)
                .set(SmsCouponDynamicSqlSupport.enableTime).equalTo(record::getEnableTime)
                .set(SmsCouponDynamicSqlSupport.code).equalTo(record::getCode)
                .set(SmsCouponDynamicSqlSupport.memberLevel).equalTo(record::getMemberLevel);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SmsCoupon record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsCouponDynamicSqlSupport.type).equalToWhenPresent(record::getType)
                .set(SmsCouponDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(SmsCouponDynamicSqlSupport.platform).equalToWhenPresent(record::getPlatform)
                .set(SmsCouponDynamicSqlSupport.count).equalToWhenPresent(record::getCount)
                .set(SmsCouponDynamicSqlSupport.amount).equalToWhenPresent(record::getAmount)
                .set(SmsCouponDynamicSqlSupport.perLimit).equalToWhenPresent(record::getPerLimit)
                .set(SmsCouponDynamicSqlSupport.minPoint).equalToWhenPresent(record::getMinPoint)
                .set(SmsCouponDynamicSqlSupport.startTime).equalToWhenPresent(record::getStartTime)
                .set(SmsCouponDynamicSqlSupport.endTime).equalToWhenPresent(record::getEndTime)
                .set(SmsCouponDynamicSqlSupport.useType).equalToWhenPresent(record::getUseType)
                .set(SmsCouponDynamicSqlSupport.note).equalToWhenPresent(record::getNote)
                .set(SmsCouponDynamicSqlSupport.publishCount).equalToWhenPresent(record::getPublishCount)
                .set(SmsCouponDynamicSqlSupport.useCount).equalToWhenPresent(record::getUseCount)
                .set(SmsCouponDynamicSqlSupport.receiveCount).equalToWhenPresent(record::getReceiveCount)
                .set(SmsCouponDynamicSqlSupport.enableTime).equalToWhenPresent(record::getEnableTime)
                .set(SmsCouponDynamicSqlSupport.code).equalToWhenPresent(record::getCode)
                .set(SmsCouponDynamicSqlSupport.memberLevel).equalToWhenPresent(record::getMemberLevel);
    }

    default int updateByPrimaryKey(SmsCoupon record) {
        return update(c ->
            c.set(SmsCouponDynamicSqlSupport.type).equalTo(record::getType)
            .set(SmsCouponDynamicSqlSupport.name).equalTo(record::getName)
            .set(SmsCouponDynamicSqlSupport.platform).equalTo(record::getPlatform)
            .set(SmsCouponDynamicSqlSupport.count).equalTo(record::getCount)
            .set(SmsCouponDynamicSqlSupport.amount).equalTo(record::getAmount)
            .set(SmsCouponDynamicSqlSupport.perLimit).equalTo(record::getPerLimit)
            .set(SmsCouponDynamicSqlSupport.minPoint).equalTo(record::getMinPoint)
            .set(SmsCouponDynamicSqlSupport.startTime).equalTo(record::getStartTime)
            .set(SmsCouponDynamicSqlSupport.endTime).equalTo(record::getEndTime)
            .set(SmsCouponDynamicSqlSupport.useType).equalTo(record::getUseType)
            .set(SmsCouponDynamicSqlSupport.note).equalTo(record::getNote)
            .set(SmsCouponDynamicSqlSupport.publishCount).equalTo(record::getPublishCount)
            .set(SmsCouponDynamicSqlSupport.useCount).equalTo(record::getUseCount)
            .set(SmsCouponDynamicSqlSupport.receiveCount).equalTo(record::getReceiveCount)
            .set(SmsCouponDynamicSqlSupport.enableTime).equalTo(record::getEnableTime)
            .set(SmsCouponDynamicSqlSupport.code).equalTo(record::getCode)
            .set(SmsCouponDynamicSqlSupport.memberLevel).equalTo(record::getMemberLevel)
            .where(SmsCouponDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(SmsCoupon record) {
        return update(c ->
            c.set(SmsCouponDynamicSqlSupport.type).equalToWhenPresent(record::getType)
            .set(SmsCouponDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(SmsCouponDynamicSqlSupport.platform).equalToWhenPresent(record::getPlatform)
            .set(SmsCouponDynamicSqlSupport.count).equalToWhenPresent(record::getCount)
            .set(SmsCouponDynamicSqlSupport.amount).equalToWhenPresent(record::getAmount)
            .set(SmsCouponDynamicSqlSupport.perLimit).equalToWhenPresent(record::getPerLimit)
            .set(SmsCouponDynamicSqlSupport.minPoint).equalToWhenPresent(record::getMinPoint)
            .set(SmsCouponDynamicSqlSupport.startTime).equalToWhenPresent(record::getStartTime)
            .set(SmsCouponDynamicSqlSupport.endTime).equalToWhenPresent(record::getEndTime)
            .set(SmsCouponDynamicSqlSupport.useType).equalToWhenPresent(record::getUseType)
            .set(SmsCouponDynamicSqlSupport.note).equalToWhenPresent(record::getNote)
            .set(SmsCouponDynamicSqlSupport.publishCount).equalToWhenPresent(record::getPublishCount)
            .set(SmsCouponDynamicSqlSupport.useCount).equalToWhenPresent(record::getUseCount)
            .set(SmsCouponDynamicSqlSupport.receiveCount).equalToWhenPresent(record::getReceiveCount)
            .set(SmsCouponDynamicSqlSupport.enableTime).equalToWhenPresent(record::getEnableTime)
            .set(SmsCouponDynamicSqlSupport.code).equalToWhenPresent(record::getCode)
            .set(SmsCouponDynamicSqlSupport.memberLevel).equalToWhenPresent(record::getMemberLevel)
            .where(SmsCouponDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}