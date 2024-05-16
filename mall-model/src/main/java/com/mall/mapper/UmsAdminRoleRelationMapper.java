/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.UmsAdminRoleRelationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsAdminRoleRelation;
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
public interface UmsAdminRoleRelationMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, adminId, roleId);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsAdminRoleRelation> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsAdminRoleRelationResult")
    Optional<UmsAdminRoleRelation> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsAdminRoleRelationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT)
    })
    List<UmsAdminRoleRelation> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, umsAdminRoleRelation, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, umsAdminRoleRelation, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(UmsAdminRoleRelation record) {
        return MyBatis3Utils.insert(this::insert, record, umsAdminRoleRelation, c ->
            c.map(adminId).toProperty("adminId")
            .map(roleId).toProperty("roleId")
        );
    }

    default int insertSelective(UmsAdminRoleRelation record) {
        return MyBatis3Utils.insert(this::insert, record, umsAdminRoleRelation, c ->
            c.map(adminId).toPropertyWhenPresent("adminId", record::getAdminId)
            .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
        );
    }

    default Optional<UmsAdminRoleRelation> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, umsAdminRoleRelation, completer);
    }

    default List<UmsAdminRoleRelation> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, umsAdminRoleRelation, completer);
    }

    default List<UmsAdminRoleRelation> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, umsAdminRoleRelation, completer);
    }

    default Optional<UmsAdminRoleRelation> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, umsAdminRoleRelation, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsAdminRoleRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(adminId).equalTo(record::getAdminId)
                .set(roleId).equalTo(record::getRoleId);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsAdminRoleRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(adminId).equalToWhenPresent(record::getAdminId)
                .set(roleId).equalToWhenPresent(record::getRoleId);
    }

    default int updateByPrimaryKey(UmsAdminRoleRelation record) {
        return update(c ->
            c.set(adminId).equalTo(record::getAdminId)
            .set(roleId).equalTo(record::getRoleId)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsAdminRoleRelation record) {
        return update(c ->
            c.set(adminId).equalToWhenPresent(record::getAdminId)
            .set(roleId).equalToWhenPresent(record::getRoleId)
            .where(id, isEqualTo(record::getId))
        );
    }
}