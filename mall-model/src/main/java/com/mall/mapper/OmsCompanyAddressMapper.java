/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.OmsCompanyAddress;

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
public interface OmsCompanyAddressMapper {
    BasicColumn[] selectList = BasicColumn.columnList(OmsCompanyAddressDynamicSqlSupport.id, OmsCompanyAddressDynamicSqlSupport.addressName, OmsCompanyAddressDynamicSqlSupport.sendStatus, OmsCompanyAddressDynamicSqlSupport.receiveStatus, OmsCompanyAddressDynamicSqlSupport.name, OmsCompanyAddressDynamicSqlSupport.phone, OmsCompanyAddressDynamicSqlSupport.province, OmsCompanyAddressDynamicSqlSupport.city, OmsCompanyAddressDynamicSqlSupport.region, OmsCompanyAddressDynamicSqlSupport.detailAddress);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<OmsCompanyAddress> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OmsCompanyAddressResult")
    Optional<OmsCompanyAddress> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OmsCompanyAddressResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="address_name", property="addressName", jdbcType=JdbcType.VARCHAR),
        @Result(column="send_status", property="sendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="receive_status", property="receiveStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="region", property="region", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail_address", property="detailAddress", jdbcType=JdbcType.VARCHAR)
    })
    List<OmsCompanyAddress> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, OmsCompanyAddressDynamicSqlSupport.omsCompanyAddress, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, OmsCompanyAddressDynamicSqlSupport.omsCompanyAddress, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(OmsCompanyAddressDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(OmsCompanyAddress record) {
        return MyBatis3Utils.insert(this::insert, record, OmsCompanyAddressDynamicSqlSupport.omsCompanyAddress, c ->
            c.map(OmsCompanyAddressDynamicSqlSupport.addressName).toProperty("addressName")
            .map(OmsCompanyAddressDynamicSqlSupport.sendStatus).toProperty("sendStatus")
            .map(OmsCompanyAddressDynamicSqlSupport.receiveStatus).toProperty("receiveStatus")
            .map(OmsCompanyAddressDynamicSqlSupport.name).toProperty("name")
            .map(OmsCompanyAddressDynamicSqlSupport.phone).toProperty("phone")
            .map(OmsCompanyAddressDynamicSqlSupport.province).toProperty("province")
            .map(OmsCompanyAddressDynamicSqlSupport.city).toProperty("city")
            .map(OmsCompanyAddressDynamicSqlSupport.region).toProperty("region")
            .map(OmsCompanyAddressDynamicSqlSupport.detailAddress).toProperty("detailAddress")
        );
    }

    default int insertSelective(OmsCompanyAddress record) {
        return MyBatis3Utils.insert(this::insert, record, OmsCompanyAddressDynamicSqlSupport.omsCompanyAddress, c ->
            c.map(OmsCompanyAddressDynamicSqlSupport.addressName).toPropertyWhenPresent("addressName", record::getAddressName)
            .map(OmsCompanyAddressDynamicSqlSupport.sendStatus).toPropertyWhenPresent("sendStatus", record::getSendStatus)
            .map(OmsCompanyAddressDynamicSqlSupport.receiveStatus).toPropertyWhenPresent("receiveStatus", record::getReceiveStatus)
            .map(OmsCompanyAddressDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(OmsCompanyAddressDynamicSqlSupport.phone).toPropertyWhenPresent("phone", record::getPhone)
            .map(OmsCompanyAddressDynamicSqlSupport.province).toPropertyWhenPresent("province", record::getProvince)
            .map(OmsCompanyAddressDynamicSqlSupport.city).toPropertyWhenPresent("city", record::getCity)
            .map(OmsCompanyAddressDynamicSqlSupport.region).toPropertyWhenPresent("region", record::getRegion)
            .map(OmsCompanyAddressDynamicSqlSupport.detailAddress).toPropertyWhenPresent("detailAddress", record::getDetailAddress)
        );
    }

    default Optional<OmsCompanyAddress> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, OmsCompanyAddressDynamicSqlSupport.omsCompanyAddress, completer);
    }

    default List<OmsCompanyAddress> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, OmsCompanyAddressDynamicSqlSupport.omsCompanyAddress, completer);
    }

    default List<OmsCompanyAddress> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, OmsCompanyAddressDynamicSqlSupport.omsCompanyAddress, completer);
    }

    default Optional<OmsCompanyAddress> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(OmsCompanyAddressDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, OmsCompanyAddressDynamicSqlSupport.omsCompanyAddress, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(OmsCompanyAddress record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsCompanyAddressDynamicSqlSupport.addressName).equalTo(record::getAddressName)
                .set(OmsCompanyAddressDynamicSqlSupport.sendStatus).equalTo(record::getSendStatus)
                .set(OmsCompanyAddressDynamicSqlSupport.receiveStatus).equalTo(record::getReceiveStatus)
                .set(OmsCompanyAddressDynamicSqlSupport.name).equalTo(record::getName)
                .set(OmsCompanyAddressDynamicSqlSupport.phone).equalTo(record::getPhone)
                .set(OmsCompanyAddressDynamicSqlSupport.province).equalTo(record::getProvince)
                .set(OmsCompanyAddressDynamicSqlSupport.city).equalTo(record::getCity)
                .set(OmsCompanyAddressDynamicSqlSupport.region).equalTo(record::getRegion)
                .set(OmsCompanyAddressDynamicSqlSupport.detailAddress).equalTo(record::getDetailAddress);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(OmsCompanyAddress record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsCompanyAddressDynamicSqlSupport.addressName).equalToWhenPresent(record::getAddressName)
                .set(OmsCompanyAddressDynamicSqlSupport.sendStatus).equalToWhenPresent(record::getSendStatus)
                .set(OmsCompanyAddressDynamicSqlSupport.receiveStatus).equalToWhenPresent(record::getReceiveStatus)
                .set(OmsCompanyAddressDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(OmsCompanyAddressDynamicSqlSupport.phone).equalToWhenPresent(record::getPhone)
                .set(OmsCompanyAddressDynamicSqlSupport.province).equalToWhenPresent(record::getProvince)
                .set(OmsCompanyAddressDynamicSqlSupport.city).equalToWhenPresent(record::getCity)
                .set(OmsCompanyAddressDynamicSqlSupport.region).equalToWhenPresent(record::getRegion)
                .set(OmsCompanyAddressDynamicSqlSupport.detailAddress).equalToWhenPresent(record::getDetailAddress);
    }

    default int updateByPrimaryKey(OmsCompanyAddress record) {
        return update(c ->
            c.set(OmsCompanyAddressDynamicSqlSupport.addressName).equalTo(record::getAddressName)
            .set(OmsCompanyAddressDynamicSqlSupport.sendStatus).equalTo(record::getSendStatus)
            .set(OmsCompanyAddressDynamicSqlSupport.receiveStatus).equalTo(record::getReceiveStatus)
            .set(OmsCompanyAddressDynamicSqlSupport.name).equalTo(record::getName)
            .set(OmsCompanyAddressDynamicSqlSupport.phone).equalTo(record::getPhone)
            .set(OmsCompanyAddressDynamicSqlSupport.province).equalTo(record::getProvince)
            .set(OmsCompanyAddressDynamicSqlSupport.city).equalTo(record::getCity)
            .set(OmsCompanyAddressDynamicSqlSupport.region).equalTo(record::getRegion)
            .set(OmsCompanyAddressDynamicSqlSupport.detailAddress).equalTo(record::getDetailAddress)
            .where(OmsCompanyAddressDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(OmsCompanyAddress record) {
        return update(c ->
            c.set(OmsCompanyAddressDynamicSqlSupport.addressName).equalToWhenPresent(record::getAddressName)
            .set(OmsCompanyAddressDynamicSqlSupport.sendStatus).equalToWhenPresent(record::getSendStatus)
            .set(OmsCompanyAddressDynamicSqlSupport.receiveStatus).equalToWhenPresent(record::getReceiveStatus)
            .set(OmsCompanyAddressDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(OmsCompanyAddressDynamicSqlSupport.phone).equalToWhenPresent(record::getPhone)
            .set(OmsCompanyAddressDynamicSqlSupport.province).equalToWhenPresent(record::getProvince)
            .set(OmsCompanyAddressDynamicSqlSupport.city).equalToWhenPresent(record::getCity)
            .set(OmsCompanyAddressDynamicSqlSupport.region).equalToWhenPresent(record::getRegion)
            .set(OmsCompanyAddressDynamicSqlSupport.detailAddress).equalToWhenPresent(record::getDetailAddress)
            .where(OmsCompanyAddressDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}