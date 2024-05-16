/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsMemberTag;

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
public interface UmsMemberTagMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsMemberTagDynamicSqlSupport.id, UmsMemberTagDynamicSqlSupport.name, UmsMemberTagDynamicSqlSupport.finishOrderCount, UmsMemberTagDynamicSqlSupport.finishOrderAmount);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsMemberTag> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsMemberTagResult")
    Optional<UmsMemberTag> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsMemberTagResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="finish_order_count", property="finishOrderCount", jdbcType=JdbcType.INTEGER),
        @Result(column="finish_order_amount", property="finishOrderAmount", jdbcType=JdbcType.DECIMAL)
    })
    List<UmsMemberTag> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsMemberTagDynamicSqlSupport.umsMemberTag, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsMemberTagDynamicSqlSupport.umsMemberTag, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsMemberTagDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsMemberTag record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberTagDynamicSqlSupport.umsMemberTag, c ->
            c.map(UmsMemberTagDynamicSqlSupport.name).toProperty("name")
            .map(UmsMemberTagDynamicSqlSupport.finishOrderCount).toProperty("finishOrderCount")
            .map(UmsMemberTagDynamicSqlSupport.finishOrderAmount).toProperty("finishOrderAmount")
        );
    }

    default int insertSelective(UmsMemberTag record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberTagDynamicSqlSupport.umsMemberTag, c ->
            c.map(UmsMemberTagDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(UmsMemberTagDynamicSqlSupport.finishOrderCount).toPropertyWhenPresent("finishOrderCount", record::getFinishOrderCount)
            .map(UmsMemberTagDynamicSqlSupport.finishOrderAmount).toPropertyWhenPresent("finishOrderAmount", record::getFinishOrderAmount)
        );
    }

    default Optional<UmsMemberTag> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsMemberTagDynamicSqlSupport.umsMemberTag, completer);
    }

    default List<UmsMemberTag> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsMemberTagDynamicSqlSupport.umsMemberTag, completer);
    }

    default List<UmsMemberTag> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsMemberTagDynamicSqlSupport.umsMemberTag, completer);
    }

    default Optional<UmsMemberTag> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsMemberTagDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsMemberTagDynamicSqlSupport.umsMemberTag, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsMemberTag record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberTagDynamicSqlSupport.name).equalTo(record::getName)
                .set(UmsMemberTagDynamicSqlSupport.finishOrderCount).equalTo(record::getFinishOrderCount)
                .set(UmsMemberTagDynamicSqlSupport.finishOrderAmount).equalTo(record::getFinishOrderAmount);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsMemberTag record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberTagDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(UmsMemberTagDynamicSqlSupport.finishOrderCount).equalToWhenPresent(record::getFinishOrderCount)
                .set(UmsMemberTagDynamicSqlSupport.finishOrderAmount).equalToWhenPresent(record::getFinishOrderAmount);
    }

    default int updateByPrimaryKey(UmsMemberTag record) {
        return update(c ->
            c.set(UmsMemberTagDynamicSqlSupport.name).equalTo(record::getName)
            .set(UmsMemberTagDynamicSqlSupport.finishOrderCount).equalTo(record::getFinishOrderCount)
            .set(UmsMemberTagDynamicSqlSupport.finishOrderAmount).equalTo(record::getFinishOrderAmount)
            .where(UmsMemberTagDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsMemberTag record) {
        return update(c ->
            c.set(UmsMemberTagDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(UmsMemberTagDynamicSqlSupport.finishOrderCount).equalToWhenPresent(record::getFinishOrderCount)
            .set(UmsMemberTagDynamicSqlSupport.finishOrderAmount).equalToWhenPresent(record::getFinishOrderAmount)
            .where(UmsMemberTagDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}