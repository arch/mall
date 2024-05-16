/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.OmsOrderReturnReason;

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
public interface OmsOrderReturnReasonMapper {
    BasicColumn[] selectList = BasicColumn.columnList(OmsOrderReturnReasonDynamicSqlSupport.id, OmsOrderReturnReasonDynamicSqlSupport.name, OmsOrderReturnReasonDynamicSqlSupport.sort, OmsOrderReturnReasonDynamicSqlSupport.status, OmsOrderReturnReasonDynamicSqlSupport.createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<OmsOrderReturnReason> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OmsOrderReturnReasonResult")
    Optional<OmsOrderReturnReason> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OmsOrderReturnReasonResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<OmsOrderReturnReason> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, OmsOrderReturnReasonDynamicSqlSupport.omsOrderReturnReason, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, OmsOrderReturnReasonDynamicSqlSupport.omsOrderReturnReason, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(OmsOrderReturnReasonDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(OmsOrderReturnReason record) {
        return MyBatis3Utils.insert(this::insert, record, OmsOrderReturnReasonDynamicSqlSupport.omsOrderReturnReason, c ->
            c.map(OmsOrderReturnReasonDynamicSqlSupport.name).toProperty("name")
            .map(OmsOrderReturnReasonDynamicSqlSupport.sort).toProperty("sort")
            .map(OmsOrderReturnReasonDynamicSqlSupport.status).toProperty("status")
            .map(OmsOrderReturnReasonDynamicSqlSupport.createTime).toProperty("createTime")
        );
    }

    default int insertSelective(OmsOrderReturnReason record) {
        return MyBatis3Utils.insert(this::insert, record, OmsOrderReturnReasonDynamicSqlSupport.omsOrderReturnReason, c ->
            c.map(OmsOrderReturnReasonDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(OmsOrderReturnReasonDynamicSqlSupport.sort).toPropertyWhenPresent("sort", record::getSort)
            .map(OmsOrderReturnReasonDynamicSqlSupport.status).toPropertyWhenPresent("status", record::getStatus)
            .map(OmsOrderReturnReasonDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<OmsOrderReturnReason> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, OmsOrderReturnReasonDynamicSqlSupport.omsOrderReturnReason, completer);
    }

    default List<OmsOrderReturnReason> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, OmsOrderReturnReasonDynamicSqlSupport.omsOrderReturnReason, completer);
    }

    default List<OmsOrderReturnReason> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, OmsOrderReturnReasonDynamicSqlSupport.omsOrderReturnReason, completer);
    }

    default Optional<OmsOrderReturnReason> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(OmsOrderReturnReasonDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, OmsOrderReturnReasonDynamicSqlSupport.omsOrderReturnReason, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(OmsOrderReturnReason record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsOrderReturnReasonDynamicSqlSupport.name).equalTo(record::getName)
                .set(OmsOrderReturnReasonDynamicSqlSupport.sort).equalTo(record::getSort)
                .set(OmsOrderReturnReasonDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(OmsOrderReturnReasonDynamicSqlSupport.createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(OmsOrderReturnReason record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsOrderReturnReasonDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(OmsOrderReturnReasonDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
                .set(OmsOrderReturnReasonDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(OmsOrderReturnReasonDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(OmsOrderReturnReason record) {
        return update(c ->
            c.set(OmsOrderReturnReasonDynamicSqlSupport.name).equalTo(record::getName)
            .set(OmsOrderReturnReasonDynamicSqlSupport.sort).equalTo(record::getSort)
            .set(OmsOrderReturnReasonDynamicSqlSupport.status).equalTo(record::getStatus)
            .set(OmsOrderReturnReasonDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .where(OmsOrderReturnReasonDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(OmsOrderReturnReason record) {
        return update(c ->
            c.set(OmsOrderReturnReasonDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(OmsOrderReturnReasonDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
            .set(OmsOrderReturnReasonDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
            .set(OmsOrderReturnReasonDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .where(OmsOrderReturnReasonDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}