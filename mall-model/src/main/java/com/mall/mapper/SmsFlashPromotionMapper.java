/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.SmsFlashPromotion;

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
public interface SmsFlashPromotionMapper {
    BasicColumn[] selectList = BasicColumn.columnList(SmsFlashPromotionDynamicSqlSupport.id, SmsFlashPromotionDynamicSqlSupport.title, SmsFlashPromotionDynamicSqlSupport.startDate, SmsFlashPromotionDynamicSqlSupport.endDate, SmsFlashPromotionDynamicSqlSupport.status, SmsFlashPromotionDynamicSqlSupport.createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SmsFlashPromotion> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SmsFlashPromotionResult")
    Optional<SmsFlashPromotion> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SmsFlashPromotionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.DATE),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.DATE),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SmsFlashPromotion> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, SmsFlashPromotionDynamicSqlSupport.smsFlashPromotion, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, SmsFlashPromotionDynamicSqlSupport.smsFlashPromotion, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(SmsFlashPromotionDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(SmsFlashPromotion record) {
        return MyBatis3Utils.insert(this::insert, record, SmsFlashPromotionDynamicSqlSupport.smsFlashPromotion, c ->
            c.map(SmsFlashPromotionDynamicSqlSupport.title).toProperty("title")
            .map(SmsFlashPromotionDynamicSqlSupport.startDate).toProperty("startDate")
            .map(SmsFlashPromotionDynamicSqlSupport.endDate).toProperty("endDate")
            .map(SmsFlashPromotionDynamicSqlSupport.status).toProperty("status")
            .map(SmsFlashPromotionDynamicSqlSupport.createTime).toProperty("createTime")
        );
    }

    default int insertSelective(SmsFlashPromotion record) {
        return MyBatis3Utils.insert(this::insert, record, SmsFlashPromotionDynamicSqlSupport.smsFlashPromotion, c ->
            c.map(SmsFlashPromotionDynamicSqlSupport.title).toPropertyWhenPresent("title", record::getTitle)
            .map(SmsFlashPromotionDynamicSqlSupport.startDate).toPropertyWhenPresent("startDate", record::getStartDate)
            .map(SmsFlashPromotionDynamicSqlSupport.endDate).toPropertyWhenPresent("endDate", record::getEndDate)
            .map(SmsFlashPromotionDynamicSqlSupport.status).toPropertyWhenPresent("status", record::getStatus)
            .map(SmsFlashPromotionDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<SmsFlashPromotion> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, SmsFlashPromotionDynamicSqlSupport.smsFlashPromotion, completer);
    }

    default List<SmsFlashPromotion> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, SmsFlashPromotionDynamicSqlSupport.smsFlashPromotion, completer);
    }

    default List<SmsFlashPromotion> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, SmsFlashPromotionDynamicSqlSupport.smsFlashPromotion, completer);
    }

    default Optional<SmsFlashPromotion> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(SmsFlashPromotionDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, SmsFlashPromotionDynamicSqlSupport.smsFlashPromotion, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SmsFlashPromotion record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsFlashPromotionDynamicSqlSupport.title).equalTo(record::getTitle)
                .set(SmsFlashPromotionDynamicSqlSupport.startDate).equalTo(record::getStartDate)
                .set(SmsFlashPromotionDynamicSqlSupport.endDate).equalTo(record::getEndDate)
                .set(SmsFlashPromotionDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(SmsFlashPromotionDynamicSqlSupport.createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SmsFlashPromotion record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsFlashPromotionDynamicSqlSupport.title).equalToWhenPresent(record::getTitle)
                .set(SmsFlashPromotionDynamicSqlSupport.startDate).equalToWhenPresent(record::getStartDate)
                .set(SmsFlashPromotionDynamicSqlSupport.endDate).equalToWhenPresent(record::getEndDate)
                .set(SmsFlashPromotionDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(SmsFlashPromotionDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(SmsFlashPromotion record) {
        return update(c ->
            c.set(SmsFlashPromotionDynamicSqlSupport.title).equalTo(record::getTitle)
            .set(SmsFlashPromotionDynamicSqlSupport.startDate).equalTo(record::getStartDate)
            .set(SmsFlashPromotionDynamicSqlSupport.endDate).equalTo(record::getEndDate)
            .set(SmsFlashPromotionDynamicSqlSupport.status).equalTo(record::getStatus)
            .set(SmsFlashPromotionDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .where(SmsFlashPromotionDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(SmsFlashPromotion record) {
        return update(c ->
            c.set(SmsFlashPromotionDynamicSqlSupport.title).equalToWhenPresent(record::getTitle)
            .set(SmsFlashPromotionDynamicSqlSupport.startDate).equalToWhenPresent(record::getStartDate)
            .set(SmsFlashPromotionDynamicSqlSupport.endDate).equalToWhenPresent(record::getEndDate)
            .set(SmsFlashPromotionDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
            .set(SmsFlashPromotionDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .where(SmsFlashPromotionDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}