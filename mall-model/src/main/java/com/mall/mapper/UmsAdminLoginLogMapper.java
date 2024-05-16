/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsAdminLoginLog;

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
public interface UmsAdminLoginLogMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsAdminLoginLogDynamicSqlSupport.id, UmsAdminLoginLogDynamicSqlSupport.adminId, UmsAdminLoginLogDynamicSqlSupport.createTime, UmsAdminLoginLogDynamicSqlSupport.ip, UmsAdminLoginLogDynamicSqlSupport.address, UmsAdminLoginLogDynamicSqlSupport.userAgent);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsAdminLoginLog> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsAdminLoginLogResult")
    Optional<UmsAdminLoginLog> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsAdminLoginLogResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_agent", property="userAgent", jdbcType=JdbcType.VARCHAR)
    })
    List<UmsAdminLoginLog> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsAdminLoginLogDynamicSqlSupport.umsAdminLoginLog, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsAdminLoginLogDynamicSqlSupport.umsAdminLoginLog, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsAdminLoginLogDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsAdminLoginLog record) {
        return MyBatis3Utils.insert(this::insert, record, UmsAdminLoginLogDynamicSqlSupport.umsAdminLoginLog, c ->
            c.map(UmsAdminLoginLogDynamicSqlSupport.adminId).toProperty("adminId")
            .map(UmsAdminLoginLogDynamicSqlSupport.createTime).toProperty("createTime")
            .map(UmsAdminLoginLogDynamicSqlSupport.ip).toProperty("ip")
            .map(UmsAdminLoginLogDynamicSqlSupport.address).toProperty("address")
            .map(UmsAdminLoginLogDynamicSqlSupport.userAgent).toProperty("userAgent")
        );
    }

    default int insertSelective(UmsAdminLoginLog record) {
        return MyBatis3Utils.insert(this::insert, record, UmsAdminLoginLogDynamicSqlSupport.umsAdminLoginLog, c ->
            c.map(UmsAdminLoginLogDynamicSqlSupport.adminId).toPropertyWhenPresent("adminId", record::getAdminId)
            .map(UmsAdminLoginLogDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(UmsAdminLoginLogDynamicSqlSupport.ip).toPropertyWhenPresent("ip", record::getIp)
            .map(UmsAdminLoginLogDynamicSqlSupport.address).toPropertyWhenPresent("address", record::getAddress)
            .map(UmsAdminLoginLogDynamicSqlSupport.userAgent).toPropertyWhenPresent("userAgent", record::getUserAgent)
        );
    }

    default Optional<UmsAdminLoginLog> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsAdminLoginLogDynamicSqlSupport.umsAdminLoginLog, completer);
    }

    default List<UmsAdminLoginLog> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsAdminLoginLogDynamicSqlSupport.umsAdminLoginLog, completer);
    }

    default List<UmsAdminLoginLog> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsAdminLoginLogDynamicSqlSupport.umsAdminLoginLog, completer);
    }

    default Optional<UmsAdminLoginLog> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsAdminLoginLogDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsAdminLoginLogDynamicSqlSupport.umsAdminLoginLog, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsAdminLoginLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsAdminLoginLogDynamicSqlSupport.adminId).equalTo(record::getAdminId)
                .set(UmsAdminLoginLogDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(UmsAdminLoginLogDynamicSqlSupport.ip).equalTo(record::getIp)
                .set(UmsAdminLoginLogDynamicSqlSupport.address).equalTo(record::getAddress)
                .set(UmsAdminLoginLogDynamicSqlSupport.userAgent).equalTo(record::getUserAgent);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsAdminLoginLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsAdminLoginLogDynamicSqlSupport.adminId).equalToWhenPresent(record::getAdminId)
                .set(UmsAdminLoginLogDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(UmsAdminLoginLogDynamicSqlSupport.ip).equalToWhenPresent(record::getIp)
                .set(UmsAdminLoginLogDynamicSqlSupport.address).equalToWhenPresent(record::getAddress)
                .set(UmsAdminLoginLogDynamicSqlSupport.userAgent).equalToWhenPresent(record::getUserAgent);
    }

    default int updateByPrimaryKey(UmsAdminLoginLog record) {
        return update(c ->
            c.set(UmsAdminLoginLogDynamicSqlSupport.adminId).equalTo(record::getAdminId)
            .set(UmsAdminLoginLogDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .set(UmsAdminLoginLogDynamicSqlSupport.ip).equalTo(record::getIp)
            .set(UmsAdminLoginLogDynamicSqlSupport.address).equalTo(record::getAddress)
            .set(UmsAdminLoginLogDynamicSqlSupport.userAgent).equalTo(record::getUserAgent)
            .where(UmsAdminLoginLogDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsAdminLoginLog record) {
        return update(c ->
            c.set(UmsAdminLoginLogDynamicSqlSupport.adminId).equalToWhenPresent(record::getAdminId)
            .set(UmsAdminLoginLogDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .set(UmsAdminLoginLogDynamicSqlSupport.ip).equalToWhenPresent(record::getIp)
            .set(UmsAdminLoginLogDynamicSqlSupport.address).equalToWhenPresent(record::getAddress)
            .set(UmsAdminLoginLogDynamicSqlSupport.userAgent).equalToWhenPresent(record::getUserAgent)
            .where(UmsAdminLoginLogDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}