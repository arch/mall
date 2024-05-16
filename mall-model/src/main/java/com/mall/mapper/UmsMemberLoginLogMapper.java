/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsMemberLoginLog;

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
public interface UmsMemberLoginLogMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsMemberLoginLogDynamicSqlSupport.id, UmsMemberLoginLogDynamicSqlSupport.memberId, UmsMemberLoginLogDynamicSqlSupport.createTime, UmsMemberLoginLogDynamicSqlSupport.ip, UmsMemberLoginLogDynamicSqlSupport.city, UmsMemberLoginLogDynamicSqlSupport.loginType, UmsMemberLoginLogDynamicSqlSupport.province);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsMemberLoginLog> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsMemberLoginLogResult")
    Optional<UmsMemberLoginLog> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsMemberLoginLogResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_type", property="loginType", jdbcType=JdbcType.INTEGER),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR)
    })
    List<UmsMemberLoginLog> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsMemberLoginLogDynamicSqlSupport.umsMemberLoginLog, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsMemberLoginLogDynamicSqlSupport.umsMemberLoginLog, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsMemberLoginLogDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsMemberLoginLog record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberLoginLogDynamicSqlSupport.umsMemberLoginLog, c ->
            c.map(UmsMemberLoginLogDynamicSqlSupport.memberId).toProperty("memberId")
            .map(UmsMemberLoginLogDynamicSqlSupport.createTime).toProperty("createTime")
            .map(UmsMemberLoginLogDynamicSqlSupport.ip).toProperty("ip")
            .map(UmsMemberLoginLogDynamicSqlSupport.city).toProperty("city")
            .map(UmsMemberLoginLogDynamicSqlSupport.loginType).toProperty("loginType")
            .map(UmsMemberLoginLogDynamicSqlSupport.province).toProperty("province")
        );
    }

    default int insertSelective(UmsMemberLoginLog record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberLoginLogDynamicSqlSupport.umsMemberLoginLog, c ->
            c.map(UmsMemberLoginLogDynamicSqlSupport.memberId).toPropertyWhenPresent("memberId", record::getMemberId)
            .map(UmsMemberLoginLogDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(UmsMemberLoginLogDynamicSqlSupport.ip).toPropertyWhenPresent("ip", record::getIp)
            .map(UmsMemberLoginLogDynamicSqlSupport.city).toPropertyWhenPresent("city", record::getCity)
            .map(UmsMemberLoginLogDynamicSqlSupport.loginType).toPropertyWhenPresent("loginType", record::getLoginType)
            .map(UmsMemberLoginLogDynamicSqlSupport.province).toPropertyWhenPresent("province", record::getProvince)
        );
    }

    default Optional<UmsMemberLoginLog> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsMemberLoginLogDynamicSqlSupport.umsMemberLoginLog, completer);
    }

    default List<UmsMemberLoginLog> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsMemberLoginLogDynamicSqlSupport.umsMemberLoginLog, completer);
    }

    default List<UmsMemberLoginLog> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsMemberLoginLogDynamicSqlSupport.umsMemberLoginLog, completer);
    }

    default Optional<UmsMemberLoginLog> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsMemberLoginLogDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsMemberLoginLogDynamicSqlSupport.umsMemberLoginLog, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsMemberLoginLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberLoginLogDynamicSqlSupport.memberId).equalTo(record::getMemberId)
                .set(UmsMemberLoginLogDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(UmsMemberLoginLogDynamicSqlSupport.ip).equalTo(record::getIp)
                .set(UmsMemberLoginLogDynamicSqlSupport.city).equalTo(record::getCity)
                .set(UmsMemberLoginLogDynamicSqlSupport.loginType).equalTo(record::getLoginType)
                .set(UmsMemberLoginLogDynamicSqlSupport.province).equalTo(record::getProvince);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsMemberLoginLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberLoginLogDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
                .set(UmsMemberLoginLogDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(UmsMemberLoginLogDynamicSqlSupport.ip).equalToWhenPresent(record::getIp)
                .set(UmsMemberLoginLogDynamicSqlSupport.city).equalToWhenPresent(record::getCity)
                .set(UmsMemberLoginLogDynamicSqlSupport.loginType).equalToWhenPresent(record::getLoginType)
                .set(UmsMemberLoginLogDynamicSqlSupport.province).equalToWhenPresent(record::getProvince);
    }

    default int updateByPrimaryKey(UmsMemberLoginLog record) {
        return update(c ->
            c.set(UmsMemberLoginLogDynamicSqlSupport.memberId).equalTo(record::getMemberId)
            .set(UmsMemberLoginLogDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .set(UmsMemberLoginLogDynamicSqlSupport.ip).equalTo(record::getIp)
            .set(UmsMemberLoginLogDynamicSqlSupport.city).equalTo(record::getCity)
            .set(UmsMemberLoginLogDynamicSqlSupport.loginType).equalTo(record::getLoginType)
            .set(UmsMemberLoginLogDynamicSqlSupport.province).equalTo(record::getProvince)
            .where(UmsMemberLoginLogDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsMemberLoginLog record) {
        return update(c ->
            c.set(UmsMemberLoginLogDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
            .set(UmsMemberLoginLogDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .set(UmsMemberLoginLogDynamicSqlSupport.ip).equalToWhenPresent(record::getIp)
            .set(UmsMemberLoginLogDynamicSqlSupport.city).equalToWhenPresent(record::getCity)
            .set(UmsMemberLoginLogDynamicSqlSupport.loginType).equalToWhenPresent(record::getLoginType)
            .set(UmsMemberLoginLogDynamicSqlSupport.province).equalToWhenPresent(record::getProvince)
            .where(UmsMemberLoginLogDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}