/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.UmsMemberReceiveAddressDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsMemberReceiveAddress;
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
public interface UmsMemberReceiveAddressMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, memberId, name, phoneNumber, defaultStatus, postCode, province, city, region, detailAddress);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsMemberReceiveAddress> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsMemberReceiveAddressResult")
    Optional<UmsMemberReceiveAddress> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsMemberReceiveAddressResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone_number", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="default_status", property="defaultStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="post_code", property="postCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="region", property="region", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail_address", property="detailAddress", jdbcType=JdbcType.VARCHAR)
    })
    List<UmsMemberReceiveAddress> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, umsMemberReceiveAddress, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, umsMemberReceiveAddress, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(UmsMemberReceiveAddress record) {
        return MyBatis3Utils.insert(this::insert, record, umsMemberReceiveAddress, c ->
            c.map(memberId).toProperty("memberId")
            .map(name).toProperty("name")
            .map(phoneNumber).toProperty("phoneNumber")
            .map(defaultStatus).toProperty("defaultStatus")
            .map(postCode).toProperty("postCode")
            .map(province).toProperty("province")
            .map(city).toProperty("city")
            .map(region).toProperty("region")
            .map(detailAddress).toProperty("detailAddress")
        );
    }

    default int insertSelective(UmsMemberReceiveAddress record) {
        return MyBatis3Utils.insert(this::insert, record, umsMemberReceiveAddress, c ->
            c.map(memberId).toPropertyWhenPresent("memberId", record::getMemberId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(phoneNumber).toPropertyWhenPresent("phoneNumber", record::getPhoneNumber)
            .map(defaultStatus).toPropertyWhenPresent("defaultStatus", record::getDefaultStatus)
            .map(postCode).toPropertyWhenPresent("postCode", record::getPostCode)
            .map(province).toPropertyWhenPresent("province", record::getProvince)
            .map(city).toPropertyWhenPresent("city", record::getCity)
            .map(region).toPropertyWhenPresent("region", record::getRegion)
            .map(detailAddress).toPropertyWhenPresent("detailAddress", record::getDetailAddress)
        );
    }

    default Optional<UmsMemberReceiveAddress> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, umsMemberReceiveAddress, completer);
    }

    default List<UmsMemberReceiveAddress> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, umsMemberReceiveAddress, completer);
    }

    default List<UmsMemberReceiveAddress> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, umsMemberReceiveAddress, completer);
    }

    default Optional<UmsMemberReceiveAddress> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, umsMemberReceiveAddress, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsMemberReceiveAddress record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(memberId).equalTo(record::getMemberId)
                .set(name).equalTo(record::getName)
                .set(phoneNumber).equalTo(record::getPhoneNumber)
                .set(defaultStatus).equalTo(record::getDefaultStatus)
                .set(postCode).equalTo(record::getPostCode)
                .set(province).equalTo(record::getProvince)
                .set(city).equalTo(record::getCity)
                .set(region).equalTo(record::getRegion)
                .set(detailAddress).equalTo(record::getDetailAddress);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsMemberReceiveAddress record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(memberId).equalToWhenPresent(record::getMemberId)
                .set(name).equalToWhenPresent(record::getName)
                .set(phoneNumber).equalToWhenPresent(record::getPhoneNumber)
                .set(defaultStatus).equalToWhenPresent(record::getDefaultStatus)
                .set(postCode).equalToWhenPresent(record::getPostCode)
                .set(province).equalToWhenPresent(record::getProvince)
                .set(city).equalToWhenPresent(record::getCity)
                .set(region).equalToWhenPresent(record::getRegion)
                .set(detailAddress).equalToWhenPresent(record::getDetailAddress);
    }

    default int updateByPrimaryKey(UmsMemberReceiveAddress record) {
        return update(c ->
            c.set(memberId).equalTo(record::getMemberId)
            .set(name).equalTo(record::getName)
            .set(phoneNumber).equalTo(record::getPhoneNumber)
            .set(defaultStatus).equalTo(record::getDefaultStatus)
            .set(postCode).equalTo(record::getPostCode)
            .set(province).equalTo(record::getProvince)
            .set(city).equalTo(record::getCity)
            .set(region).equalTo(record::getRegion)
            .set(detailAddress).equalTo(record::getDetailAddress)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsMemberReceiveAddress record) {
        return update(c ->
            c.set(memberId).equalToWhenPresent(record::getMemberId)
            .set(name).equalToWhenPresent(record::getName)
            .set(phoneNumber).equalToWhenPresent(record::getPhoneNumber)
            .set(defaultStatus).equalToWhenPresent(record::getDefaultStatus)
            .set(postCode).equalToWhenPresent(record::getPostCode)
            .set(province).equalToWhenPresent(record::getProvince)
            .set(city).equalToWhenPresent(record::getCity)
            .set(region).equalToWhenPresent(record::getRegion)
            .set(detailAddress).equalToWhenPresent(record::getDetailAddress)
            .where(id, isEqualTo(record::getId))
        );
    }
}