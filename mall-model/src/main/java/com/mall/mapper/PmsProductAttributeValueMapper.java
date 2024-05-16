/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsProductAttributeValue;

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
public interface PmsProductAttributeValueMapper {
    BasicColumn[] selectList = BasicColumn.columnList(PmsProductAttributeValueDynamicSqlSupport.id, PmsProductAttributeValueDynamicSqlSupport.productId, PmsProductAttributeValueDynamicSqlSupport.productAttributeId, PmsProductAttributeValueDynamicSqlSupport.value);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsProductAttributeValue> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsProductAttributeValueResult")
    Optional<PmsProductAttributeValue> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsProductAttributeValueResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_attribute_id", property="productAttributeId", jdbcType=JdbcType.BIGINT),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR)
    })
    List<PmsProductAttributeValue> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, PmsProductAttributeValueDynamicSqlSupport.pmsProductAttributeValue, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, PmsProductAttributeValueDynamicSqlSupport.pmsProductAttributeValue, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(PmsProductAttributeValueDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(PmsProductAttributeValue record) {
        return MyBatis3Utils.insert(this::insert, record, PmsProductAttributeValueDynamicSqlSupport.pmsProductAttributeValue, c ->
            c.map(PmsProductAttributeValueDynamicSqlSupport.productId).toProperty("productId")
            .map(PmsProductAttributeValueDynamicSqlSupport.productAttributeId).toProperty("productAttributeId")
            .map(PmsProductAttributeValueDynamicSqlSupport.value).toProperty("value")
        );
    }

    default int insertSelective(PmsProductAttributeValue record) {
        return MyBatis3Utils.insert(this::insert, record, PmsProductAttributeValueDynamicSqlSupport.pmsProductAttributeValue, c ->
            c.map(PmsProductAttributeValueDynamicSqlSupport.productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(PmsProductAttributeValueDynamicSqlSupport.productAttributeId).toPropertyWhenPresent("productAttributeId", record::getProductAttributeId)
            .map(PmsProductAttributeValueDynamicSqlSupport.value).toPropertyWhenPresent("value", record::getValue)
        );
    }

    default Optional<PmsProductAttributeValue> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, PmsProductAttributeValueDynamicSqlSupport.pmsProductAttributeValue, completer);
    }

    default List<PmsProductAttributeValue> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, PmsProductAttributeValueDynamicSqlSupport.pmsProductAttributeValue, completer);
    }

    default List<PmsProductAttributeValue> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, PmsProductAttributeValueDynamicSqlSupport.pmsProductAttributeValue, completer);
    }

    default Optional<PmsProductAttributeValue> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(PmsProductAttributeValueDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, PmsProductAttributeValueDynamicSqlSupport.pmsProductAttributeValue, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsProductAttributeValue record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsProductAttributeValueDynamicSqlSupport.productId).equalTo(record::getProductId)
                .set(PmsProductAttributeValueDynamicSqlSupport.productAttributeId).equalTo(record::getProductAttributeId)
                .set(PmsProductAttributeValueDynamicSqlSupport.value).equalTo(record::getValue);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsProductAttributeValue record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsProductAttributeValueDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
                .set(PmsProductAttributeValueDynamicSqlSupport.productAttributeId).equalToWhenPresent(record::getProductAttributeId)
                .set(PmsProductAttributeValueDynamicSqlSupport.value).equalToWhenPresent(record::getValue);
    }

    default int updateByPrimaryKey(PmsProductAttributeValue record) {
        return update(c ->
            c.set(PmsProductAttributeValueDynamicSqlSupport.productId).equalTo(record::getProductId)
            .set(PmsProductAttributeValueDynamicSqlSupport.productAttributeId).equalTo(record::getProductAttributeId)
            .set(PmsProductAttributeValueDynamicSqlSupport.value).equalTo(record::getValue)
            .where(PmsProductAttributeValueDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsProductAttributeValue record) {
        return update(c ->
            c.set(PmsProductAttributeValueDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
            .set(PmsProductAttributeValueDynamicSqlSupport.productAttributeId).equalToWhenPresent(record::getProductAttributeId)
            .set(PmsProductAttributeValueDynamicSqlSupport.value).equalToWhenPresent(record::getValue)
            .where(PmsProductAttributeValueDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}