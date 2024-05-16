/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.SmsHomeRecommendSubjectDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.SmsHomeRecommendSubject;
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
public interface SmsHomeRecommendSubjectMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, subjectId, subjectName, recommendStatus, sort);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SmsHomeRecommendSubject> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SmsHomeRecommendSubjectResult")
    Optional<SmsHomeRecommendSubject> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SmsHomeRecommendSubjectResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="subject_id", property="subjectId", jdbcType=JdbcType.BIGINT),
        @Result(column="subject_name", property="subjectName", jdbcType=JdbcType.VARCHAR),
        @Result(column="recommend_status", property="recommendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<SmsHomeRecommendSubject> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, smsHomeRecommendSubject, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, smsHomeRecommendSubject, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(SmsHomeRecommendSubject record) {
        return MyBatis3Utils.insert(this::insert, record, smsHomeRecommendSubject, c ->
            c.map(subjectId).toProperty("subjectId")
            .map(subjectName).toProperty("subjectName")
            .map(recommendStatus).toProperty("recommendStatus")
            .map(sort).toProperty("sort")
        );
    }

    default int insertSelective(SmsHomeRecommendSubject record) {
        return MyBatis3Utils.insert(this::insert, record, smsHomeRecommendSubject, c ->
            c.map(subjectId).toPropertyWhenPresent("subjectId", record::getSubjectId)
            .map(subjectName).toPropertyWhenPresent("subjectName", record::getSubjectName)
            .map(recommendStatus).toPropertyWhenPresent("recommendStatus", record::getRecommendStatus)
            .map(sort).toPropertyWhenPresent("sort", record::getSort)
        );
    }

    default Optional<SmsHomeRecommendSubject> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, smsHomeRecommendSubject, completer);
    }

    default List<SmsHomeRecommendSubject> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, smsHomeRecommendSubject, completer);
    }

    default List<SmsHomeRecommendSubject> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, smsHomeRecommendSubject, completer);
    }

    default Optional<SmsHomeRecommendSubject> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, smsHomeRecommendSubject, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SmsHomeRecommendSubject record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(subjectId).equalTo(record::getSubjectId)
                .set(subjectName).equalTo(record::getSubjectName)
                .set(recommendStatus).equalTo(record::getRecommendStatus)
                .set(sort).equalTo(record::getSort);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SmsHomeRecommendSubject record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(subjectId).equalToWhenPresent(record::getSubjectId)
                .set(subjectName).equalToWhenPresent(record::getSubjectName)
                .set(recommendStatus).equalToWhenPresent(record::getRecommendStatus)
                .set(sort).equalToWhenPresent(record::getSort);
    }

    default int updateByPrimaryKey(SmsHomeRecommendSubject record) {
        return update(c ->
            c.set(subjectId).equalTo(record::getSubjectId)
            .set(subjectName).equalTo(record::getSubjectName)
            .set(recommendStatus).equalTo(record::getRecommendStatus)
            .set(sort).equalTo(record::getSort)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(SmsHomeRecommendSubject record) {
        return update(c ->
            c.set(subjectId).equalToWhenPresent(record::getSubjectId)
            .set(subjectName).equalToWhenPresent(record::getSubjectName)
            .set(recommendStatus).equalToWhenPresent(record::getRecommendStatus)
            .set(sort).equalToWhenPresent(record::getSort)
            .where(id, isEqualTo(record::getId))
        );
    }
}