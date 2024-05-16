/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.CmsTopic;

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
public interface CmsTopicMapper {
    BasicColumn[] selectList = BasicColumn.columnList(CmsTopicDynamicSqlSupport.id, CmsTopicDynamicSqlSupport.categoryId, CmsTopicDynamicSqlSupport.name, CmsTopicDynamicSqlSupport.startTime, CmsTopicDynamicSqlSupport.endTime, CmsTopicDynamicSqlSupport.attendCount, CmsTopicDynamicSqlSupport.attentionCount, CmsTopicDynamicSqlSupport.readCount, CmsTopicDynamicSqlSupport.awardName, CmsTopicDynamicSqlSupport.attendType, CmsTopicDynamicSqlSupport.createTime, CmsTopicDynamicSqlSupport.content);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CmsTopic> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CmsTopicResult")
    Optional<CmsTopic> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CmsTopicResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="attend_count", property="attendCount", jdbcType=JdbcType.INTEGER),
        @Result(column="attention_count", property="attentionCount", jdbcType=JdbcType.INTEGER),
        @Result(column="read_count", property="readCount", jdbcType=JdbcType.INTEGER),
        @Result(column="award_name", property="awardName", jdbcType=JdbcType.VARCHAR),
        @Result(column="attend_type", property="attendType", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<CmsTopic> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, CmsTopicDynamicSqlSupport.cmsTopic, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, CmsTopicDynamicSqlSupport.cmsTopic, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(CmsTopicDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(CmsTopic record) {
        return MyBatis3Utils.insert(this::insert, record, CmsTopicDynamicSqlSupport.cmsTopic, c ->
            c.map(CmsTopicDynamicSqlSupport.categoryId).toProperty("categoryId")
            .map(CmsTopicDynamicSqlSupport.name).toProperty("name")
            .map(CmsTopicDynamicSqlSupport.startTime).toProperty("startTime")
            .map(CmsTopicDynamicSqlSupport.endTime).toProperty("endTime")
            .map(CmsTopicDynamicSqlSupport.attendCount).toProperty("attendCount")
            .map(CmsTopicDynamicSqlSupport.attentionCount).toProperty("attentionCount")
            .map(CmsTopicDynamicSqlSupport.readCount).toProperty("readCount")
            .map(CmsTopicDynamicSqlSupport.awardName).toProperty("awardName")
            .map(CmsTopicDynamicSqlSupport.attendType).toProperty("attendType")
            .map(CmsTopicDynamicSqlSupport.createTime).toProperty("createTime")
            .map(CmsTopicDynamicSqlSupport.content).toProperty("content")
        );
    }

    default int insertSelective(CmsTopic record) {
        return MyBatis3Utils.insert(this::insert, record, CmsTopicDynamicSqlSupport.cmsTopic, c ->
            c.map(CmsTopicDynamicSqlSupport.categoryId).toPropertyWhenPresent("categoryId", record::getCategoryId)
            .map(CmsTopicDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(CmsTopicDynamicSqlSupport.startTime).toPropertyWhenPresent("startTime", record::getStartTime)
            .map(CmsTopicDynamicSqlSupport.endTime).toPropertyWhenPresent("endTime", record::getEndTime)
            .map(CmsTopicDynamicSqlSupport.attendCount).toPropertyWhenPresent("attendCount", record::getAttendCount)
            .map(CmsTopicDynamicSqlSupport.attentionCount).toPropertyWhenPresent("attentionCount", record::getAttentionCount)
            .map(CmsTopicDynamicSqlSupport.readCount).toPropertyWhenPresent("readCount", record::getReadCount)
            .map(CmsTopicDynamicSqlSupport.awardName).toPropertyWhenPresent("awardName", record::getAwardName)
            .map(CmsTopicDynamicSqlSupport.attendType).toPropertyWhenPresent("attendType", record::getAttendType)
            .map(CmsTopicDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(CmsTopicDynamicSqlSupport.content).toPropertyWhenPresent("content", record::getContent)
        );
    }

    default Optional<CmsTopic> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, CmsTopicDynamicSqlSupport.cmsTopic, completer);
    }

    default List<CmsTopic> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, CmsTopicDynamicSqlSupport.cmsTopic, completer);
    }

    default List<CmsTopic> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, CmsTopicDynamicSqlSupport.cmsTopic, completer);
    }

    default Optional<CmsTopic> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(CmsTopicDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, CmsTopicDynamicSqlSupport.cmsTopic, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(CmsTopic record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsTopicDynamicSqlSupport.categoryId).equalTo(record::getCategoryId)
                .set(CmsTopicDynamicSqlSupport.name).equalTo(record::getName)
                .set(CmsTopicDynamicSqlSupport.startTime).equalTo(record::getStartTime)
                .set(CmsTopicDynamicSqlSupport.endTime).equalTo(record::getEndTime)
                .set(CmsTopicDynamicSqlSupport.attendCount).equalTo(record::getAttendCount)
                .set(CmsTopicDynamicSqlSupport.attentionCount).equalTo(record::getAttentionCount)
                .set(CmsTopicDynamicSqlSupport.readCount).equalTo(record::getReadCount)
                .set(CmsTopicDynamicSqlSupport.awardName).equalTo(record::getAwardName)
                .set(CmsTopicDynamicSqlSupport.attendType).equalTo(record::getAttendType)
                .set(CmsTopicDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(CmsTopicDynamicSqlSupport.content).equalTo(record::getContent);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(CmsTopic record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsTopicDynamicSqlSupport.categoryId).equalToWhenPresent(record::getCategoryId)
                .set(CmsTopicDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(CmsTopicDynamicSqlSupport.startTime).equalToWhenPresent(record::getStartTime)
                .set(CmsTopicDynamicSqlSupport.endTime).equalToWhenPresent(record::getEndTime)
                .set(CmsTopicDynamicSqlSupport.attendCount).equalToWhenPresent(record::getAttendCount)
                .set(CmsTopicDynamicSqlSupport.attentionCount).equalToWhenPresent(record::getAttentionCount)
                .set(CmsTopicDynamicSqlSupport.readCount).equalToWhenPresent(record::getReadCount)
                .set(CmsTopicDynamicSqlSupport.awardName).equalToWhenPresent(record::getAwardName)
                .set(CmsTopicDynamicSqlSupport.attendType).equalToWhenPresent(record::getAttendType)
                .set(CmsTopicDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(CmsTopicDynamicSqlSupport.content).equalToWhenPresent(record::getContent);
    }

    default int updateByPrimaryKey(CmsTopic record) {
        return update(c ->
            c.set(CmsTopicDynamicSqlSupport.categoryId).equalTo(record::getCategoryId)
            .set(CmsTopicDynamicSqlSupport.name).equalTo(record::getName)
            .set(CmsTopicDynamicSqlSupport.startTime).equalTo(record::getStartTime)
            .set(CmsTopicDynamicSqlSupport.endTime).equalTo(record::getEndTime)
            .set(CmsTopicDynamicSqlSupport.attendCount).equalTo(record::getAttendCount)
            .set(CmsTopicDynamicSqlSupport.attentionCount).equalTo(record::getAttentionCount)
            .set(CmsTopicDynamicSqlSupport.readCount).equalTo(record::getReadCount)
            .set(CmsTopicDynamicSqlSupport.awardName).equalTo(record::getAwardName)
            .set(CmsTopicDynamicSqlSupport.attendType).equalTo(record::getAttendType)
            .set(CmsTopicDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .set(CmsTopicDynamicSqlSupport.content).equalTo(record::getContent)
            .where(CmsTopicDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(CmsTopic record) {
        return update(c ->
            c.set(CmsTopicDynamicSqlSupport.categoryId).equalToWhenPresent(record::getCategoryId)
            .set(CmsTopicDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(CmsTopicDynamicSqlSupport.startTime).equalToWhenPresent(record::getStartTime)
            .set(CmsTopicDynamicSqlSupport.endTime).equalToWhenPresent(record::getEndTime)
            .set(CmsTopicDynamicSqlSupport.attendCount).equalToWhenPresent(record::getAttendCount)
            .set(CmsTopicDynamicSqlSupport.attentionCount).equalToWhenPresent(record::getAttentionCount)
            .set(CmsTopicDynamicSqlSupport.readCount).equalToWhenPresent(record::getReadCount)
            .set(CmsTopicDynamicSqlSupport.awardName).equalToWhenPresent(record::getAwardName)
            .set(CmsTopicDynamicSqlSupport.attendType).equalToWhenPresent(record::getAttendType)
            .set(CmsTopicDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .set(CmsTopicDynamicSqlSupport.content).equalToWhenPresent(record::getContent)
            .where(CmsTopicDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}