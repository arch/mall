/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.SmsHomeAdvertise;

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
public interface SmsHomeAdvertiseMapper {
    BasicColumn[] selectList = BasicColumn.columnList(SmsHomeAdvertiseDynamicSqlSupport.id, SmsHomeAdvertiseDynamicSqlSupport.name, SmsHomeAdvertiseDynamicSqlSupport.type, SmsHomeAdvertiseDynamicSqlSupport.pic, SmsHomeAdvertiseDynamicSqlSupport.startTime, SmsHomeAdvertiseDynamicSqlSupport.endTime, SmsHomeAdvertiseDynamicSqlSupport.status, SmsHomeAdvertiseDynamicSqlSupport.clickCount, SmsHomeAdvertiseDynamicSqlSupport.orderCount, SmsHomeAdvertiseDynamicSqlSupport.url, SmsHomeAdvertiseDynamicSqlSupport.note, SmsHomeAdvertiseDynamicSqlSupport.sort);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SmsHomeAdvertise> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SmsHomeAdvertiseResult")
    Optional<SmsHomeAdvertise> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SmsHomeAdvertiseResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="click_count", property="clickCount", jdbcType=JdbcType.INTEGER),
        @Result(column="order_count", property="orderCount", jdbcType=JdbcType.INTEGER),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<SmsHomeAdvertise> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, SmsHomeAdvertiseDynamicSqlSupport.smsHomeAdvertise, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, SmsHomeAdvertiseDynamicSqlSupport.smsHomeAdvertise, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(SmsHomeAdvertiseDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(SmsHomeAdvertise record) {
        return MyBatis3Utils.insert(this::insert, record, SmsHomeAdvertiseDynamicSqlSupport.smsHomeAdvertise, c ->
            c.map(SmsHomeAdvertiseDynamicSqlSupport.name).toProperty("name")
            .map(SmsHomeAdvertiseDynamicSqlSupport.type).toProperty("type")
            .map(SmsHomeAdvertiseDynamicSqlSupport.pic).toProperty("pic")
            .map(SmsHomeAdvertiseDynamicSqlSupport.startTime).toProperty("startTime")
            .map(SmsHomeAdvertiseDynamicSqlSupport.endTime).toProperty("endTime")
            .map(SmsHomeAdvertiseDynamicSqlSupport.status).toProperty("status")
            .map(SmsHomeAdvertiseDynamicSqlSupport.clickCount).toProperty("clickCount")
            .map(SmsHomeAdvertiseDynamicSqlSupport.orderCount).toProperty("orderCount")
            .map(SmsHomeAdvertiseDynamicSqlSupport.url).toProperty("url")
            .map(SmsHomeAdvertiseDynamicSqlSupport.note).toProperty("note")
            .map(SmsHomeAdvertiseDynamicSqlSupport.sort).toProperty("sort")
        );
    }

    default int insertSelective(SmsHomeAdvertise record) {
        return MyBatis3Utils.insert(this::insert, record, SmsHomeAdvertiseDynamicSqlSupport.smsHomeAdvertise, c ->
            c.map(SmsHomeAdvertiseDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(SmsHomeAdvertiseDynamicSqlSupport.type).toPropertyWhenPresent("type", record::getType)
            .map(SmsHomeAdvertiseDynamicSqlSupport.pic).toPropertyWhenPresent("pic", record::getPic)
            .map(SmsHomeAdvertiseDynamicSqlSupport.startTime).toPropertyWhenPresent("startTime", record::getStartTime)
            .map(SmsHomeAdvertiseDynamicSqlSupport.endTime).toPropertyWhenPresent("endTime", record::getEndTime)
            .map(SmsHomeAdvertiseDynamicSqlSupport.status).toPropertyWhenPresent("status", record::getStatus)
            .map(SmsHomeAdvertiseDynamicSqlSupport.clickCount).toPropertyWhenPresent("clickCount", record::getClickCount)
            .map(SmsHomeAdvertiseDynamicSqlSupport.orderCount).toPropertyWhenPresent("orderCount", record::getOrderCount)
            .map(SmsHomeAdvertiseDynamicSqlSupport.url).toPropertyWhenPresent("url", record::getUrl)
            .map(SmsHomeAdvertiseDynamicSqlSupport.note).toPropertyWhenPresent("note", record::getNote)
            .map(SmsHomeAdvertiseDynamicSqlSupport.sort).toPropertyWhenPresent("sort", record::getSort)
        );
    }

    default Optional<SmsHomeAdvertise> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, SmsHomeAdvertiseDynamicSqlSupport.smsHomeAdvertise, completer);
    }

    default List<SmsHomeAdvertise> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, SmsHomeAdvertiseDynamicSqlSupport.smsHomeAdvertise, completer);
    }

    default List<SmsHomeAdvertise> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, SmsHomeAdvertiseDynamicSqlSupport.smsHomeAdvertise, completer);
    }

    default Optional<SmsHomeAdvertise> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(SmsHomeAdvertiseDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, SmsHomeAdvertiseDynamicSqlSupport.smsHomeAdvertise, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SmsHomeAdvertise record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsHomeAdvertiseDynamicSqlSupport.name).equalTo(record::getName)
                .set(SmsHomeAdvertiseDynamicSqlSupport.type).equalTo(record::getType)
                .set(SmsHomeAdvertiseDynamicSqlSupport.pic).equalTo(record::getPic)
                .set(SmsHomeAdvertiseDynamicSqlSupport.startTime).equalTo(record::getStartTime)
                .set(SmsHomeAdvertiseDynamicSqlSupport.endTime).equalTo(record::getEndTime)
                .set(SmsHomeAdvertiseDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(SmsHomeAdvertiseDynamicSqlSupport.clickCount).equalTo(record::getClickCount)
                .set(SmsHomeAdvertiseDynamicSqlSupport.orderCount).equalTo(record::getOrderCount)
                .set(SmsHomeAdvertiseDynamicSqlSupport.url).equalTo(record::getUrl)
                .set(SmsHomeAdvertiseDynamicSqlSupport.note).equalTo(record::getNote)
                .set(SmsHomeAdvertiseDynamicSqlSupport.sort).equalTo(record::getSort);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SmsHomeAdvertise record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsHomeAdvertiseDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(SmsHomeAdvertiseDynamicSqlSupport.type).equalToWhenPresent(record::getType)
                .set(SmsHomeAdvertiseDynamicSqlSupport.pic).equalToWhenPresent(record::getPic)
                .set(SmsHomeAdvertiseDynamicSqlSupport.startTime).equalToWhenPresent(record::getStartTime)
                .set(SmsHomeAdvertiseDynamicSqlSupport.endTime).equalToWhenPresent(record::getEndTime)
                .set(SmsHomeAdvertiseDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(SmsHomeAdvertiseDynamicSqlSupport.clickCount).equalToWhenPresent(record::getClickCount)
                .set(SmsHomeAdvertiseDynamicSqlSupport.orderCount).equalToWhenPresent(record::getOrderCount)
                .set(SmsHomeAdvertiseDynamicSqlSupport.url).equalToWhenPresent(record::getUrl)
                .set(SmsHomeAdvertiseDynamicSqlSupport.note).equalToWhenPresent(record::getNote)
                .set(SmsHomeAdvertiseDynamicSqlSupport.sort).equalToWhenPresent(record::getSort);
    }

    default int updateByPrimaryKey(SmsHomeAdvertise record) {
        return update(c ->
            c.set(SmsHomeAdvertiseDynamicSqlSupport.name).equalTo(record::getName)
            .set(SmsHomeAdvertiseDynamicSqlSupport.type).equalTo(record::getType)
            .set(SmsHomeAdvertiseDynamicSqlSupport.pic).equalTo(record::getPic)
            .set(SmsHomeAdvertiseDynamicSqlSupport.startTime).equalTo(record::getStartTime)
            .set(SmsHomeAdvertiseDynamicSqlSupport.endTime).equalTo(record::getEndTime)
            .set(SmsHomeAdvertiseDynamicSqlSupport.status).equalTo(record::getStatus)
            .set(SmsHomeAdvertiseDynamicSqlSupport.clickCount).equalTo(record::getClickCount)
            .set(SmsHomeAdvertiseDynamicSqlSupport.orderCount).equalTo(record::getOrderCount)
            .set(SmsHomeAdvertiseDynamicSqlSupport.url).equalTo(record::getUrl)
            .set(SmsHomeAdvertiseDynamicSqlSupport.note).equalTo(record::getNote)
            .set(SmsHomeAdvertiseDynamicSqlSupport.sort).equalTo(record::getSort)
            .where(SmsHomeAdvertiseDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(SmsHomeAdvertise record) {
        return update(c ->
            c.set(SmsHomeAdvertiseDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(SmsHomeAdvertiseDynamicSqlSupport.type).equalToWhenPresent(record::getType)
            .set(SmsHomeAdvertiseDynamicSqlSupport.pic).equalToWhenPresent(record::getPic)
            .set(SmsHomeAdvertiseDynamicSqlSupport.startTime).equalToWhenPresent(record::getStartTime)
            .set(SmsHomeAdvertiseDynamicSqlSupport.endTime).equalToWhenPresent(record::getEndTime)
            .set(SmsHomeAdvertiseDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
            .set(SmsHomeAdvertiseDynamicSqlSupport.clickCount).equalToWhenPresent(record::getClickCount)
            .set(SmsHomeAdvertiseDynamicSqlSupport.orderCount).equalToWhenPresent(record::getOrderCount)
            .set(SmsHomeAdvertiseDynamicSqlSupport.url).equalToWhenPresent(record::getUrl)
            .set(SmsHomeAdvertiseDynamicSqlSupport.note).equalToWhenPresent(record::getNote)
            .set(SmsHomeAdvertiseDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
            .where(SmsHomeAdvertiseDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}