/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsRoleResourceRelation;

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
public interface UmsRoleResourceRelationMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsRoleResourceRelationDynamicSqlSupport.id, UmsRoleResourceRelationDynamicSqlSupport.roleId, UmsRoleResourceRelationDynamicSqlSupport.resourceId);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsRoleResourceRelation> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsRoleResourceRelationResult")
    Optional<UmsRoleResourceRelation> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsRoleResourceRelationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.BIGINT)
    })
    List<UmsRoleResourceRelation> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsRoleResourceRelationDynamicSqlSupport.umsRoleResourceRelation, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsRoleResourceRelationDynamicSqlSupport.umsRoleResourceRelation, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsRoleResourceRelationDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsRoleResourceRelation record) {
        return MyBatis3Utils.insert(this::insert, record, UmsRoleResourceRelationDynamicSqlSupport.umsRoleResourceRelation, c ->
            c.map(UmsRoleResourceRelationDynamicSqlSupport.roleId).toProperty("roleId")
            .map(UmsRoleResourceRelationDynamicSqlSupport.resourceId).toProperty("resourceId")
        );
    }

    default int insertSelective(UmsRoleResourceRelation record) {
        return MyBatis3Utils.insert(this::insert, record, UmsRoleResourceRelationDynamicSqlSupport.umsRoleResourceRelation, c ->
            c.map(UmsRoleResourceRelationDynamicSqlSupport.roleId).toPropertyWhenPresent("roleId", record::getRoleId)
            .map(UmsRoleResourceRelationDynamicSqlSupport.resourceId).toPropertyWhenPresent("resourceId", record::getResourceId)
        );
    }

    default Optional<UmsRoleResourceRelation> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsRoleResourceRelationDynamicSqlSupport.umsRoleResourceRelation, completer);
    }

    default List<UmsRoleResourceRelation> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsRoleResourceRelationDynamicSqlSupport.umsRoleResourceRelation, completer);
    }

    default List<UmsRoleResourceRelation> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsRoleResourceRelationDynamicSqlSupport.umsRoleResourceRelation, completer);
    }

    default Optional<UmsRoleResourceRelation> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsRoleResourceRelationDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsRoleResourceRelationDynamicSqlSupport.umsRoleResourceRelation, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsRoleResourceRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsRoleResourceRelationDynamicSqlSupport.roleId).equalTo(record::getRoleId)
                .set(UmsRoleResourceRelationDynamicSqlSupport.resourceId).equalTo(record::getResourceId);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsRoleResourceRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsRoleResourceRelationDynamicSqlSupport.roleId).equalToWhenPresent(record::getRoleId)
                .set(UmsRoleResourceRelationDynamicSqlSupport.resourceId).equalToWhenPresent(record::getResourceId);
    }

    default int updateByPrimaryKey(UmsRoleResourceRelation record) {
        return update(c ->
            c.set(UmsRoleResourceRelationDynamicSqlSupport.roleId).equalTo(record::getRoleId)
            .set(UmsRoleResourceRelationDynamicSqlSupport.resourceId).equalTo(record::getResourceId)
            .where(UmsRoleResourceRelationDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsRoleResourceRelation record) {
        return update(c ->
            c.set(UmsRoleResourceRelationDynamicSqlSupport.roleId).equalToWhenPresent(record::getRoleId)
            .set(UmsRoleResourceRelationDynamicSqlSupport.resourceId).equalToWhenPresent(record::getResourceId)
            .where(UmsRoleResourceRelationDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}