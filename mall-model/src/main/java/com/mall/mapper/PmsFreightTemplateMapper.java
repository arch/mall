/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsFreightTemplate;

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
public interface PmsFreightTemplateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(PmsFreightTemplateDynamicSqlSupport.id, PmsFreightTemplateDynamicSqlSupport.name, PmsFreightTemplateDynamicSqlSupport.chargeType, PmsFreightTemplateDynamicSqlSupport.firstWeight, PmsFreightTemplateDynamicSqlSupport.firstFee, PmsFreightTemplateDynamicSqlSupport.continueWeight, PmsFreightTemplateDynamicSqlSupport.continueFee, PmsFreightTemplateDynamicSqlSupport.dest);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsFreightTemplate> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsFreightTemplateResult")
    Optional<PmsFreightTemplate> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsFreightTemplateResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="charge_type", property="chargeType", jdbcType=JdbcType.INTEGER),
        @Result(column="first_weight", property="firstWeight", jdbcType=JdbcType.DECIMAL),
        @Result(column="first_fee", property="firstFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="continue_weight", property="continueWeight", jdbcType=JdbcType.DECIMAL),
        @Result(column="continue_fee", property="continueFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="dest", property="dest", jdbcType=JdbcType.VARCHAR)
    })
    List<PmsFreightTemplate> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, PmsFreightTemplateDynamicSqlSupport.pmsFreightTemplate, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, PmsFreightTemplateDynamicSqlSupport.pmsFreightTemplate, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(PmsFreightTemplateDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(PmsFreightTemplate record) {
        return MyBatis3Utils.insert(this::insert, record, PmsFreightTemplateDynamicSqlSupport.pmsFreightTemplate, c ->
            c.map(PmsFreightTemplateDynamicSqlSupport.name).toProperty("name")
            .map(PmsFreightTemplateDynamicSqlSupport.chargeType).toProperty("chargeType")
            .map(PmsFreightTemplateDynamicSqlSupport.firstWeight).toProperty("firstWeight")
            .map(PmsFreightTemplateDynamicSqlSupport.firstFee).toProperty("firstFee")
            .map(PmsFreightTemplateDynamicSqlSupport.continueWeight).toProperty("continueWeight")
            .map(PmsFreightTemplateDynamicSqlSupport.continueFee).toProperty("continueFee")
            .map(PmsFreightTemplateDynamicSqlSupport.dest).toProperty("dest")
        );
    }

    default int insertSelective(PmsFreightTemplate record) {
        return MyBatis3Utils.insert(this::insert, record, PmsFreightTemplateDynamicSqlSupport.pmsFreightTemplate, c ->
            c.map(PmsFreightTemplateDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(PmsFreightTemplateDynamicSqlSupport.chargeType).toPropertyWhenPresent("chargeType", record::getChargeType)
            .map(PmsFreightTemplateDynamicSqlSupport.firstWeight).toPropertyWhenPresent("firstWeight", record::getFirstWeight)
            .map(PmsFreightTemplateDynamicSqlSupport.firstFee).toPropertyWhenPresent("firstFee", record::getFirstFee)
            .map(PmsFreightTemplateDynamicSqlSupport.continueWeight).toPropertyWhenPresent("continueWeight", record::getContinueWeight)
            .map(PmsFreightTemplateDynamicSqlSupport.continueFee).toPropertyWhenPresent("continueFee", record::getContinueFee)
            .map(PmsFreightTemplateDynamicSqlSupport.dest).toPropertyWhenPresent("dest", record::getDest)
        );
    }

    default Optional<PmsFreightTemplate> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, PmsFreightTemplateDynamicSqlSupport.pmsFreightTemplate, completer);
    }

    default List<PmsFreightTemplate> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, PmsFreightTemplateDynamicSqlSupport.pmsFreightTemplate, completer);
    }

    default List<PmsFreightTemplate> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, PmsFreightTemplateDynamicSqlSupport.pmsFreightTemplate, completer);
    }

    default Optional<PmsFreightTemplate> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(PmsFreightTemplateDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, PmsFreightTemplateDynamicSqlSupport.pmsFreightTemplate, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsFreightTemplate record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsFreightTemplateDynamicSqlSupport.name).equalTo(record::getName)
                .set(PmsFreightTemplateDynamicSqlSupport.chargeType).equalTo(record::getChargeType)
                .set(PmsFreightTemplateDynamicSqlSupport.firstWeight).equalTo(record::getFirstWeight)
                .set(PmsFreightTemplateDynamicSqlSupport.firstFee).equalTo(record::getFirstFee)
                .set(PmsFreightTemplateDynamicSqlSupport.continueWeight).equalTo(record::getContinueWeight)
                .set(PmsFreightTemplateDynamicSqlSupport.continueFee).equalTo(record::getContinueFee)
                .set(PmsFreightTemplateDynamicSqlSupport.dest).equalTo(record::getDest);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsFreightTemplate record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsFreightTemplateDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(PmsFreightTemplateDynamicSqlSupport.chargeType).equalToWhenPresent(record::getChargeType)
                .set(PmsFreightTemplateDynamicSqlSupport.firstWeight).equalToWhenPresent(record::getFirstWeight)
                .set(PmsFreightTemplateDynamicSqlSupport.firstFee).equalToWhenPresent(record::getFirstFee)
                .set(PmsFreightTemplateDynamicSqlSupport.continueWeight).equalToWhenPresent(record::getContinueWeight)
                .set(PmsFreightTemplateDynamicSqlSupport.continueFee).equalToWhenPresent(record::getContinueFee)
                .set(PmsFreightTemplateDynamicSqlSupport.dest).equalToWhenPresent(record::getDest);
    }

    default int updateByPrimaryKey(PmsFreightTemplate record) {
        return update(c ->
            c.set(PmsFreightTemplateDynamicSqlSupport.name).equalTo(record::getName)
            .set(PmsFreightTemplateDynamicSqlSupport.chargeType).equalTo(record::getChargeType)
            .set(PmsFreightTemplateDynamicSqlSupport.firstWeight).equalTo(record::getFirstWeight)
            .set(PmsFreightTemplateDynamicSqlSupport.firstFee).equalTo(record::getFirstFee)
            .set(PmsFreightTemplateDynamicSqlSupport.continueWeight).equalTo(record::getContinueWeight)
            .set(PmsFreightTemplateDynamicSqlSupport.continueFee).equalTo(record::getContinueFee)
            .set(PmsFreightTemplateDynamicSqlSupport.dest).equalTo(record::getDest)
            .where(PmsFreightTemplateDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsFreightTemplate record) {
        return update(c ->
            c.set(PmsFreightTemplateDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(PmsFreightTemplateDynamicSqlSupport.chargeType).equalToWhenPresent(record::getChargeType)
            .set(PmsFreightTemplateDynamicSqlSupport.firstWeight).equalToWhenPresent(record::getFirstWeight)
            .set(PmsFreightTemplateDynamicSqlSupport.firstFee).equalToWhenPresent(record::getFirstFee)
            .set(PmsFreightTemplateDynamicSqlSupport.continueWeight).equalToWhenPresent(record::getContinueWeight)
            .set(PmsFreightTemplateDynamicSqlSupport.continueFee).equalToWhenPresent(record::getContinueFee)
            .set(PmsFreightTemplateDynamicSqlSupport.dest).equalToWhenPresent(record::getDest)
            .where(PmsFreightTemplateDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}