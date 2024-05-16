/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.SmsHomeBrand;

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
public interface SmsHomeBrandMapper {
    BasicColumn[] selectList = BasicColumn.columnList(SmsHomeBrandDynamicSqlSupport.id, SmsHomeBrandDynamicSqlSupport.brandId, SmsHomeBrandDynamicSqlSupport.brandName, SmsHomeBrandDynamicSqlSupport.recommendStatus, SmsHomeBrandDynamicSqlSupport.sort);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SmsHomeBrand> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SmsHomeBrandResult")
    Optional<SmsHomeBrand> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SmsHomeBrandResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="brand_id", property="brandId", jdbcType=JdbcType.BIGINT),
        @Result(column="brand_name", property="brandName", jdbcType=JdbcType.VARCHAR),
        @Result(column="recommend_status", property="recommendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<SmsHomeBrand> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, SmsHomeBrandDynamicSqlSupport.smsHomeBrand, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, SmsHomeBrandDynamicSqlSupport.smsHomeBrand, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(SmsHomeBrandDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(SmsHomeBrand record) {
        return MyBatis3Utils.insert(this::insert, record, SmsHomeBrandDynamicSqlSupport.smsHomeBrand, c ->
            c.map(SmsHomeBrandDynamicSqlSupport.brandId).toProperty("brandId")
            .map(SmsHomeBrandDynamicSqlSupport.brandName).toProperty("brandName")
            .map(SmsHomeBrandDynamicSqlSupport.recommendStatus).toProperty("recommendStatus")
            .map(SmsHomeBrandDynamicSqlSupport.sort).toProperty("sort")
        );
    }

    default int insertSelective(SmsHomeBrand record) {
        return MyBatis3Utils.insert(this::insert, record, SmsHomeBrandDynamicSqlSupport.smsHomeBrand, c ->
            c.map(SmsHomeBrandDynamicSqlSupport.brandId).toPropertyWhenPresent("brandId", record::getBrandId)
            .map(SmsHomeBrandDynamicSqlSupport.brandName).toPropertyWhenPresent("brandName", record::getBrandName)
            .map(SmsHomeBrandDynamicSqlSupport.recommendStatus).toPropertyWhenPresent("recommendStatus", record::getRecommendStatus)
            .map(SmsHomeBrandDynamicSqlSupport.sort).toPropertyWhenPresent("sort", record::getSort)
        );
    }

    default Optional<SmsHomeBrand> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, SmsHomeBrandDynamicSqlSupport.smsHomeBrand, completer);
    }

    default List<SmsHomeBrand> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, SmsHomeBrandDynamicSqlSupport.smsHomeBrand, completer);
    }

    default List<SmsHomeBrand> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, SmsHomeBrandDynamicSqlSupport.smsHomeBrand, completer);
    }

    default Optional<SmsHomeBrand> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(SmsHomeBrandDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, SmsHomeBrandDynamicSqlSupport.smsHomeBrand, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SmsHomeBrand record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsHomeBrandDynamicSqlSupport.brandId).equalTo(record::getBrandId)
                .set(SmsHomeBrandDynamicSqlSupport.brandName).equalTo(record::getBrandName)
                .set(SmsHomeBrandDynamicSqlSupport.recommendStatus).equalTo(record::getRecommendStatus)
                .set(SmsHomeBrandDynamicSqlSupport.sort).equalTo(record::getSort);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SmsHomeBrand record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsHomeBrandDynamicSqlSupport.brandId).equalToWhenPresent(record::getBrandId)
                .set(SmsHomeBrandDynamicSqlSupport.brandName).equalToWhenPresent(record::getBrandName)
                .set(SmsHomeBrandDynamicSqlSupport.recommendStatus).equalToWhenPresent(record::getRecommendStatus)
                .set(SmsHomeBrandDynamicSqlSupport.sort).equalToWhenPresent(record::getSort);
    }

    default int updateByPrimaryKey(SmsHomeBrand record) {
        return update(c ->
            c.set(SmsHomeBrandDynamicSqlSupport.brandId).equalTo(record::getBrandId)
            .set(SmsHomeBrandDynamicSqlSupport.brandName).equalTo(record::getBrandName)
            .set(SmsHomeBrandDynamicSqlSupport.recommendStatus).equalTo(record::getRecommendStatus)
            .set(SmsHomeBrandDynamicSqlSupport.sort).equalTo(record::getSort)
            .where(SmsHomeBrandDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(SmsHomeBrand record) {
        return update(c ->
            c.set(SmsHomeBrandDynamicSqlSupport.brandId).equalToWhenPresent(record::getBrandId)
            .set(SmsHomeBrandDynamicSqlSupport.brandName).equalToWhenPresent(record::getBrandName)
            .set(SmsHomeBrandDynamicSqlSupport.recommendStatus).equalToWhenPresent(record::getRecommendStatus)
            .set(SmsHomeBrandDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
            .where(SmsHomeBrandDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}