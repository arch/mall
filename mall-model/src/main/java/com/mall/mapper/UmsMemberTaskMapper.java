/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsMemberTask;

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
public interface UmsMemberTaskMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsMemberTaskDynamicSqlSupport.id, UmsMemberTaskDynamicSqlSupport.name, UmsMemberTaskDynamicSqlSupport.growth, UmsMemberTaskDynamicSqlSupport.integration, UmsMemberTaskDynamicSqlSupport.type);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsMemberTask> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsMemberTaskResult")
    Optional<UmsMemberTask> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsMemberTaskResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="growth", property="growth", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    List<UmsMemberTask> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsMemberTaskDynamicSqlSupport.umsMemberTask, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsMemberTaskDynamicSqlSupport.umsMemberTask, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsMemberTaskDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsMemberTask record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberTaskDynamicSqlSupport.umsMemberTask, c ->
            c.map(UmsMemberTaskDynamicSqlSupport.name).toProperty("name")
            .map(UmsMemberTaskDynamicSqlSupport.growth).toProperty("growth")
            .map(UmsMemberTaskDynamicSqlSupport.integration).toProperty("integration")
            .map(UmsMemberTaskDynamicSqlSupport.type).toProperty("type")
        );
    }

    default int insertSelective(UmsMemberTask record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberTaskDynamicSqlSupport.umsMemberTask, c ->
            c.map(UmsMemberTaskDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(UmsMemberTaskDynamicSqlSupport.growth).toPropertyWhenPresent("growth", record::getGrowth)
            .map(UmsMemberTaskDynamicSqlSupport.integration).toPropertyWhenPresent("integration", record::getIntegration)
            .map(UmsMemberTaskDynamicSqlSupport.type).toPropertyWhenPresent("type", record::getType)
        );
    }

    default Optional<UmsMemberTask> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsMemberTaskDynamicSqlSupport.umsMemberTask, completer);
    }

    default List<UmsMemberTask> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsMemberTaskDynamicSqlSupport.umsMemberTask, completer);
    }

    default List<UmsMemberTask> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsMemberTaskDynamicSqlSupport.umsMemberTask, completer);
    }

    default Optional<UmsMemberTask> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsMemberTaskDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsMemberTaskDynamicSqlSupport.umsMemberTask, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsMemberTask record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberTaskDynamicSqlSupport.name).equalTo(record::getName)
                .set(UmsMemberTaskDynamicSqlSupport.growth).equalTo(record::getGrowth)
                .set(UmsMemberTaskDynamicSqlSupport.integration).equalTo(record::getIntegration)
                .set(UmsMemberTaskDynamicSqlSupport.type).equalTo(record::getType);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsMemberTask record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberTaskDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(UmsMemberTaskDynamicSqlSupport.growth).equalToWhenPresent(record::getGrowth)
                .set(UmsMemberTaskDynamicSqlSupport.integration).equalToWhenPresent(record::getIntegration)
                .set(UmsMemberTaskDynamicSqlSupport.type).equalToWhenPresent(record::getType);
    }

    default int updateByPrimaryKey(UmsMemberTask record) {
        return update(c ->
            c.set(UmsMemberTaskDynamicSqlSupport.name).equalTo(record::getName)
            .set(UmsMemberTaskDynamicSqlSupport.growth).equalTo(record::getGrowth)
            .set(UmsMemberTaskDynamicSqlSupport.integration).equalTo(record::getIntegration)
            .set(UmsMemberTaskDynamicSqlSupport.type).equalTo(record::getType)
            .where(UmsMemberTaskDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsMemberTask record) {
        return update(c ->
            c.set(UmsMemberTaskDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(UmsMemberTaskDynamicSqlSupport.growth).equalToWhenPresent(record::getGrowth)
            .set(UmsMemberTaskDynamicSqlSupport.integration).equalToWhenPresent(record::getIntegration)
            .set(UmsMemberTaskDynamicSqlSupport.type).equalToWhenPresent(record::getType)
            .where(UmsMemberTaskDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}