/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.SmsHomeNewProduct;

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
public interface SmsHomeNewProductMapper {
    BasicColumn[] selectList = BasicColumn.columnList(SmsHomeNewProductDynamicSqlSupport.id, SmsHomeNewProductDynamicSqlSupport.productId, SmsHomeNewProductDynamicSqlSupport.productName, SmsHomeNewProductDynamicSqlSupport.recommendStatus, SmsHomeNewProductDynamicSqlSupport.sort);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SmsHomeNewProduct> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SmsHomeNewProductResult")
    Optional<SmsHomeNewProduct> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SmsHomeNewProductResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="recommend_status", property="recommendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<SmsHomeNewProduct> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, SmsHomeNewProductDynamicSqlSupport.smsHomeNewProduct, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, SmsHomeNewProductDynamicSqlSupport.smsHomeNewProduct, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(SmsHomeNewProductDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(SmsHomeNewProduct record) {
        return MyBatis3Utils.insert(this::insert, record, SmsHomeNewProductDynamicSqlSupport.smsHomeNewProduct, c ->
            c.map(SmsHomeNewProductDynamicSqlSupport.productId).toProperty("productId")
            .map(SmsHomeNewProductDynamicSqlSupport.productName).toProperty("productName")
            .map(SmsHomeNewProductDynamicSqlSupport.recommendStatus).toProperty("recommendStatus")
            .map(SmsHomeNewProductDynamicSqlSupport.sort).toProperty("sort")
        );
    }

    default int insertSelective(SmsHomeNewProduct record) {
        return MyBatis3Utils.insert(this::insert, record, SmsHomeNewProductDynamicSqlSupport.smsHomeNewProduct, c ->
            c.map(SmsHomeNewProductDynamicSqlSupport.productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(SmsHomeNewProductDynamicSqlSupport.productName).toPropertyWhenPresent("productName", record::getProductName)
            .map(SmsHomeNewProductDynamicSqlSupport.recommendStatus).toPropertyWhenPresent("recommendStatus", record::getRecommendStatus)
            .map(SmsHomeNewProductDynamicSqlSupport.sort).toPropertyWhenPresent("sort", record::getSort)
        );
    }

    default Optional<SmsHomeNewProduct> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, SmsHomeNewProductDynamicSqlSupport.smsHomeNewProduct, completer);
    }

    default List<SmsHomeNewProduct> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, SmsHomeNewProductDynamicSqlSupport.smsHomeNewProduct, completer);
    }

    default List<SmsHomeNewProduct> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, SmsHomeNewProductDynamicSqlSupport.smsHomeNewProduct, completer);
    }

    default Optional<SmsHomeNewProduct> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(SmsHomeNewProductDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, SmsHomeNewProductDynamicSqlSupport.smsHomeNewProduct, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SmsHomeNewProduct record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsHomeNewProductDynamicSqlSupport.productId).equalTo(record::getProductId)
                .set(SmsHomeNewProductDynamicSqlSupport.productName).equalTo(record::getProductName)
                .set(SmsHomeNewProductDynamicSqlSupport.recommendStatus).equalTo(record::getRecommendStatus)
                .set(SmsHomeNewProductDynamicSqlSupport.sort).equalTo(record::getSort);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SmsHomeNewProduct record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(SmsHomeNewProductDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
                .set(SmsHomeNewProductDynamicSqlSupport.productName).equalToWhenPresent(record::getProductName)
                .set(SmsHomeNewProductDynamicSqlSupport.recommendStatus).equalToWhenPresent(record::getRecommendStatus)
                .set(SmsHomeNewProductDynamicSqlSupport.sort).equalToWhenPresent(record::getSort);
    }

    default int updateByPrimaryKey(SmsHomeNewProduct record) {
        return update(c ->
            c.set(SmsHomeNewProductDynamicSqlSupport.productId).equalTo(record::getProductId)
            .set(SmsHomeNewProductDynamicSqlSupport.productName).equalTo(record::getProductName)
            .set(SmsHomeNewProductDynamicSqlSupport.recommendStatus).equalTo(record::getRecommendStatus)
            .set(SmsHomeNewProductDynamicSqlSupport.sort).equalTo(record::getSort)
            .where(SmsHomeNewProductDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(SmsHomeNewProduct record) {
        return update(c ->
            c.set(SmsHomeNewProductDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
            .set(SmsHomeNewProductDynamicSqlSupport.productName).equalToWhenPresent(record::getProductName)
            .set(SmsHomeNewProductDynamicSqlSupport.recommendStatus).equalToWhenPresent(record::getRecommendStatus)
            .set(SmsHomeNewProductDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
            .where(SmsHomeNewProductDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}