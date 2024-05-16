/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.SmsFlashPromotionSession;

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
public interface SmsFlashPromotionSessionMapper {
    BasicColumn[] selectList = BasicColumn.columnList(SmsFlashPromotionSessionDynamicSqlSupport.id, SmsFlashPromotionSessionDynamicSqlSupport.name, SmsFlashPromotionSessionDynamicSqlSupport.startTime, SmsFlashPromotionSessionDynamicSqlSupport.endTime, SmsFlashPromotionSessionDynamicSqlSupport.status, SmsFlashPromotionSessionDynamicSqlSupport.createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SmsFlashPromotionSession> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SmsFlashPromotionSessionResult")
    Optional<SmsFlashPromotionSession> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SmsFlashPromotionSessionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIME),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIME),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SmsFlashPromotionSession> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, SmsFlashPromotionSessionDynamicSqlSupport.smsFlashPromotionSession, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, SmsFlashPromotionSessionDynamicSqlSupport.smsFlashPromotionSession, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(SmsFlashPromotionSessionDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(SmsFlashPromotionSession record) {
        return MyBatis3Utils.insert(this::insert, record, SmsFlashPromotionSessionDynamicSqlSupport.smsFlashPromotionSession, c ->
            c.map(SmsFlashPromotionSessionDynamicSqlSupport.name).toProperty("name")
            .map(SmsFlashPromotionSessionDynamicSqlSupport.startTime).toProperty("startTime")
            .map(SmsFlashPromotionSessionDynamicSqlSupport.endTime).toProperty("endTime")
            .map(SmsFlashPromotionSessionDynamicSqlSupport.status).toProperty("status")
            .map(SmsFlashPromotionSessionDynamicSqlSupport.createTime).toProperty("createTime")
        );
    }

    default int insertSelective(SmsFlashPromotionSession record) {
        return MyBatis3Utils.insert(this::insert, record, SmsFlashPromotionSessionDynamicSqlSupport.smsFlashPromotionSession, c ->
            c.map(SmsFlashPromotionSessionDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(SmsFlashPromotionSessionDynamicSqlSupport.startTime).toPropertyWhenPresent("startTime", record::getStartTime)
            .map(SmsFlashPromotionSessionDynamicSqlSupport.endTime).toPropertyWhenPresent("endTime", record::getEndTime)
            .map(SmsFlashPromotionSessionDynamicSqlSupport.status).toPropertyWhenPresent("status", record::getStatus)
            .map(SmsFlashPromotionSessionDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<SmsFlashPromotionSession> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, SmsFlashPromotionSessionDynamicSqlSupport.smsFlashPromotionSession, completer);
    }

    default List<SmsFlashPromotionSession> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, SmsFlashPromotionSessionDynamicSqlSupport.smsFlashPromotionSession, completer);
    }

    default List<SmsFlashPromotionSession> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, SmsFlashPromotionSessionDynamicSqlSupport.smsFlashPromotionSession, completer);
    }

    default Optional<SmsFlashPromotionSession> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(SmsFlashPromotionSessionDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, SmsFlashPromotionSessionDynamicSqlSupport.smsFlashPromotionSession, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SmsFlashPromotionSession record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsFlashPromotionSessionDynamicSqlSupport.name).equalTo(record::getName)
                .set(SmsFlashPromotionSessionDynamicSqlSupport.startTime).equalTo(record::getStartTime)
                .set(SmsFlashPromotionSessionDynamicSqlSupport.endTime).equalTo(record::getEndTime)
                .set(SmsFlashPromotionSessionDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(SmsFlashPromotionSessionDynamicSqlSupport.createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SmsFlashPromotionSession record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsFlashPromotionSessionDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(SmsFlashPromotionSessionDynamicSqlSupport.startTime).equalToWhenPresent(record::getStartTime)
                .set(SmsFlashPromotionSessionDynamicSqlSupport.endTime).equalToWhenPresent(record::getEndTime)
                .set(SmsFlashPromotionSessionDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(SmsFlashPromotionSessionDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(SmsFlashPromotionSession record) {
        return update(c ->
            c.set(SmsFlashPromotionSessionDynamicSqlSupport.name).equalTo(record::getName)
            .set(SmsFlashPromotionSessionDynamicSqlSupport.startTime).equalTo(record::getStartTime)
            .set(SmsFlashPromotionSessionDynamicSqlSupport.endTime).equalTo(record::getEndTime)
            .set(SmsFlashPromotionSessionDynamicSqlSupport.status).equalTo(record::getStatus)
            .set(SmsFlashPromotionSessionDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .where(SmsFlashPromotionSessionDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(SmsFlashPromotionSession record) {
        return update(c ->
            c.set(SmsFlashPromotionSessionDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(SmsFlashPromotionSessionDynamicSqlSupport.startTime).equalToWhenPresent(record::getStartTime)
            .set(SmsFlashPromotionSessionDynamicSqlSupport.endTime).equalToWhenPresent(record::getEndTime)
            .set(SmsFlashPromotionSessionDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
            .set(SmsFlashPromotionSessionDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .where(SmsFlashPromotionSessionDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}