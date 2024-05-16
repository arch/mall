/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsRoleMenuRelation;

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
public interface UmsRoleMenuRelationMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsRoleMenuRelationDynamicSqlSupport.id, UmsRoleMenuRelationDynamicSqlSupport.roleId, UmsRoleMenuRelationDynamicSqlSupport.menuId);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsRoleMenuRelation> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsRoleMenuRelationResult")
    Optional<UmsRoleMenuRelation> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsRoleMenuRelationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="menu_id", property="menuId", jdbcType=JdbcType.BIGINT)
    })
    List<UmsRoleMenuRelation> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsRoleMenuRelationDynamicSqlSupport.umsRoleMenuRelation, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsRoleMenuRelationDynamicSqlSupport.umsRoleMenuRelation, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsRoleMenuRelationDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsRoleMenuRelation record) {
        return MyBatis3Utils.insert(this::insert, record, UmsRoleMenuRelationDynamicSqlSupport.umsRoleMenuRelation, c ->
            c.map(UmsRoleMenuRelationDynamicSqlSupport.roleId).toProperty("roleId")
            .map(UmsRoleMenuRelationDynamicSqlSupport.menuId).toProperty("menuId")
        );
    }

    default int insertSelective(UmsRoleMenuRelation record) {
        return MyBatis3Utils.insert(this::insert, record, UmsRoleMenuRelationDynamicSqlSupport.umsRoleMenuRelation, c ->
            c.map(UmsRoleMenuRelationDynamicSqlSupport.roleId).toPropertyWhenPresent("roleId", record::getRoleId)
            .map(UmsRoleMenuRelationDynamicSqlSupport.menuId).toPropertyWhenPresent("menuId", record::getMenuId)
        );
    }

    default Optional<UmsRoleMenuRelation> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsRoleMenuRelationDynamicSqlSupport.umsRoleMenuRelation, completer);
    }

    default List<UmsRoleMenuRelation> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsRoleMenuRelationDynamicSqlSupport.umsRoleMenuRelation, completer);
    }

    default List<UmsRoleMenuRelation> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsRoleMenuRelationDynamicSqlSupport.umsRoleMenuRelation, completer);
    }

    default Optional<UmsRoleMenuRelation> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsRoleMenuRelationDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsRoleMenuRelationDynamicSqlSupport.umsRoleMenuRelation, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsRoleMenuRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsRoleMenuRelationDynamicSqlSupport.roleId).equalTo(record::getRoleId)
                .set(UmsRoleMenuRelationDynamicSqlSupport.menuId).equalTo(record::getMenuId);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsRoleMenuRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsRoleMenuRelationDynamicSqlSupport.roleId).equalToWhenPresent(record::getRoleId)
                .set(UmsRoleMenuRelationDynamicSqlSupport.menuId).equalToWhenPresent(record::getMenuId);
    }

    default int updateByPrimaryKey(UmsRoleMenuRelation record) {
        return update(c ->
            c.set(UmsRoleMenuRelationDynamicSqlSupport.roleId).equalTo(record::getRoleId)
            .set(UmsRoleMenuRelationDynamicSqlSupport.menuId).equalTo(record::getMenuId)
            .where(UmsRoleMenuRelationDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsRoleMenuRelation record) {
        return update(c ->
            c.set(UmsRoleMenuRelationDynamicSqlSupport.roleId).equalToWhenPresent(record::getRoleId)
            .set(UmsRoleMenuRelationDynamicSqlSupport.menuId).equalToWhenPresent(record::getMenuId)
            .where(UmsRoleMenuRelationDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}