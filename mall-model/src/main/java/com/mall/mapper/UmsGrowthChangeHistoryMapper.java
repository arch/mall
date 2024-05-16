/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.UmsGrowthChangeHistoryDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsGrowthChangeHistory;
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
public interface UmsGrowthChangeHistoryMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, memberId, createTime, changeType, changeCount, operateMan, operateNote, sourceType);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsGrowthChangeHistory> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsGrowthChangeHistoryResult")
    Optional<UmsGrowthChangeHistory> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsGrowthChangeHistoryResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="change_type", property="changeType", jdbcType=JdbcType.INTEGER),
        @Result(column="change_count", property="changeCount", jdbcType=JdbcType.INTEGER),
        @Result(column="operate_man", property="operateMan", jdbcType=JdbcType.VARCHAR),
        @Result(column="operate_note", property="operateNote", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_type", property="sourceType", jdbcType=JdbcType.INTEGER)
    })
    List<UmsGrowthChangeHistory> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, umsGrowthChangeHistory, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, umsGrowthChangeHistory, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(UmsGrowthChangeHistory record) {
        return MyBatis3Utils.insert(this::insert, record, umsGrowthChangeHistory, c ->
            c.map(memberId).toProperty("memberId")
            .map(createTime).toProperty("createTime")
            .map(changeType).toProperty("changeType")
            .map(changeCount).toProperty("changeCount")
            .map(operateMan).toProperty("operateMan")
            .map(operateNote).toProperty("operateNote")
            .map(sourceType).toProperty("sourceType")
        );
    }

    default int insertSelective(UmsGrowthChangeHistory record) {
        return MyBatis3Utils.insert(this::insert, record, umsGrowthChangeHistory, c ->
            c.map(memberId).toPropertyWhenPresent("memberId", record::getMemberId)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(changeType).toPropertyWhenPresent("changeType", record::getChangeType)
            .map(changeCount).toPropertyWhenPresent("changeCount", record::getChangeCount)
            .map(operateMan).toPropertyWhenPresent("operateMan", record::getOperateMan)
            .map(operateNote).toPropertyWhenPresent("operateNote", record::getOperateNote)
            .map(sourceType).toPropertyWhenPresent("sourceType", record::getSourceType)
        );
    }

    default Optional<UmsGrowthChangeHistory> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, umsGrowthChangeHistory, completer);
    }

    default List<UmsGrowthChangeHistory> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, umsGrowthChangeHistory, completer);
    }

    default List<UmsGrowthChangeHistory> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, umsGrowthChangeHistory, completer);
    }

    default Optional<UmsGrowthChangeHistory> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, umsGrowthChangeHistory, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsGrowthChangeHistory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(memberId).equalTo(record::getMemberId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(changeType).equalTo(record::getChangeType)
                .set(changeCount).equalTo(record::getChangeCount)
                .set(operateMan).equalTo(record::getOperateMan)
                .set(operateNote).equalTo(record::getOperateNote)
                .set(sourceType).equalTo(record::getSourceType);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsGrowthChangeHistory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(memberId).equalToWhenPresent(record::getMemberId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(changeType).equalToWhenPresent(record::getChangeType)
                .set(changeCount).equalToWhenPresent(record::getChangeCount)
                .set(operateMan).equalToWhenPresent(record::getOperateMan)
                .set(operateNote).equalToWhenPresent(record::getOperateNote)
                .set(sourceType).equalToWhenPresent(record::getSourceType);
    }

    default int updateByPrimaryKey(UmsGrowthChangeHistory record) {
        return update(c ->
            c.set(memberId).equalTo(record::getMemberId)
            .set(createTime).equalTo(record::getCreateTime)
            .set(changeType).equalTo(record::getChangeType)
            .set(changeCount).equalTo(record::getChangeCount)
            .set(operateMan).equalTo(record::getOperateMan)
            .set(operateNote).equalTo(record::getOperateNote)
            .set(sourceType).equalTo(record::getSourceType)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsGrowthChangeHistory record) {
        return update(c ->
            c.set(memberId).equalToWhenPresent(record::getMemberId)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(changeType).equalToWhenPresent(record::getChangeType)
            .set(changeCount).equalToWhenPresent(record::getChangeCount)
            .set(operateMan).equalToWhenPresent(record::getOperateMan)
            .set(operateNote).equalToWhenPresent(record::getOperateNote)
            .set(sourceType).equalToWhenPresent(record::getSourceType)
            .where(id, isEqualTo(record::getId))
        );
    }
}