/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsRole;

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
public interface UmsRoleMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsRoleDynamicSqlSupport.id, UmsRoleDynamicSqlSupport.name, UmsRoleDynamicSqlSupport.description, UmsRoleDynamicSqlSupport.adminCount, UmsRoleDynamicSqlSupport.status, UmsRoleDynamicSqlSupport.sort, UmsRoleDynamicSqlSupport.createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsRole> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsRoleResult")
    Optional<UmsRole> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsRoleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_count", property="adminCount", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UmsRole> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsRoleDynamicSqlSupport.umsRole, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsRoleDynamicSqlSupport.umsRole, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsRoleDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsRole record) {
        return MyBatis3Utils.insert(this::insert, record, UmsRoleDynamicSqlSupport.umsRole, c ->
            c.map(UmsRoleDynamicSqlSupport.name).toProperty("name")
            .map(UmsRoleDynamicSqlSupport.description).toProperty("description")
            .map(UmsRoleDynamicSqlSupport.adminCount).toProperty("adminCount")
            .map(UmsRoleDynamicSqlSupport.status).toProperty("status")
            .map(UmsRoleDynamicSqlSupport.sort).toProperty("sort")
            .map(UmsRoleDynamicSqlSupport.createTime).toProperty("createTime")
        );
    }

    default int insertSelective(UmsRole record) {
        return MyBatis3Utils.insert(this::insert, record, UmsRoleDynamicSqlSupport.umsRole, c ->
            c.map(UmsRoleDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(UmsRoleDynamicSqlSupport.description).toPropertyWhenPresent("description", record::getDescription)
            .map(UmsRoleDynamicSqlSupport.adminCount).toPropertyWhenPresent("adminCount", record::getAdminCount)
            .map(UmsRoleDynamicSqlSupport.status).toPropertyWhenPresent("status", record::getStatus)
            .map(UmsRoleDynamicSqlSupport.sort).toPropertyWhenPresent("sort", record::getSort)
            .map(UmsRoleDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<UmsRole> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsRoleDynamicSqlSupport.umsRole, completer);
    }

    default List<UmsRole> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsRoleDynamicSqlSupport.umsRole, completer);
    }

    default List<UmsRole> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsRoleDynamicSqlSupport.umsRole, completer);
    }

    default Optional<UmsRole> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsRoleDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsRoleDynamicSqlSupport.umsRole, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsRole record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsRoleDynamicSqlSupport.name).equalTo(record::getName)
                .set(UmsRoleDynamicSqlSupport.description).equalTo(record::getDescription)
                .set(UmsRoleDynamicSqlSupport.adminCount).equalTo(record::getAdminCount)
                .set(UmsRoleDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(UmsRoleDynamicSqlSupport.sort).equalTo(record::getSort)
                .set(UmsRoleDynamicSqlSupport.createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsRole record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsRoleDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(UmsRoleDynamicSqlSupport.description).equalToWhenPresent(record::getDescription)
                .set(UmsRoleDynamicSqlSupport.adminCount).equalToWhenPresent(record::getAdminCount)
                .set(UmsRoleDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(UmsRoleDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
                .set(UmsRoleDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(UmsRole record) {
        return update(c ->
            c.set(UmsRoleDynamicSqlSupport.name).equalTo(record::getName)
            .set(UmsRoleDynamicSqlSupport.description).equalTo(record::getDescription)
            .set(UmsRoleDynamicSqlSupport.adminCount).equalTo(record::getAdminCount)
            .set(UmsRoleDynamicSqlSupport.status).equalTo(record::getStatus)
            .set(UmsRoleDynamicSqlSupport.sort).equalTo(record::getSort)
            .set(UmsRoleDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .where(UmsRoleDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsRole record) {
        return update(c ->
            c.set(UmsRoleDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(UmsRoleDynamicSqlSupport.description).equalToWhenPresent(record::getDescription)
            .set(UmsRoleDynamicSqlSupport.adminCount).equalToWhenPresent(record::getAdminCount)
            .set(UmsRoleDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
            .set(UmsRoleDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
            .set(UmsRoleDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .where(UmsRoleDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}