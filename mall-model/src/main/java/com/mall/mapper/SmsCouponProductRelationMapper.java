/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.SmsCouponProductRelationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.SmsCouponProductRelation;
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
public interface SmsCouponProductRelationMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, couponId, productId, productName, productSn);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SmsCouponProductRelation> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SmsCouponProductRelationResult")
    Optional<SmsCouponProductRelation> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SmsCouponProductRelationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="coupon_id", property="couponId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_sn", property="productSn", jdbcType=JdbcType.VARCHAR)
    })
    List<SmsCouponProductRelation> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, smsCouponProductRelation, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, smsCouponProductRelation, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(SmsCouponProductRelation record) {
        return MyBatis3Utils.insert(this::insert, record, smsCouponProductRelation, c ->
            c.map(couponId).toProperty("couponId")
            .map(productId).toProperty("productId")
            .map(productName).toProperty("productName")
            .map(productSn).toProperty("productSn")
        );
    }

    default int insertSelective(SmsCouponProductRelation record) {
        return MyBatis3Utils.insert(this::insert, record, smsCouponProductRelation, c ->
            c.map(couponId).toPropertyWhenPresent("couponId", record::getCouponId)
            .map(productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(productName).toPropertyWhenPresent("productName", record::getProductName)
            .map(productSn).toPropertyWhenPresent("productSn", record::getProductSn)
        );
    }

    default Optional<SmsCouponProductRelation> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, smsCouponProductRelation, completer);
    }

    default List<SmsCouponProductRelation> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, smsCouponProductRelation, completer);
    }

    default List<SmsCouponProductRelation> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, smsCouponProductRelation, completer);
    }

    default Optional<SmsCouponProductRelation> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, smsCouponProductRelation, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SmsCouponProductRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(couponId).equalTo(record::getCouponId)
                .set(productId).equalTo(record::getProductId)
                .set(productName).equalTo(record::getProductName)
                .set(productSn).equalTo(record::getProductSn);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SmsCouponProductRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(couponId).equalToWhenPresent(record::getCouponId)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(productName).equalToWhenPresent(record::getProductName)
                .set(productSn).equalToWhenPresent(record::getProductSn);
    }

    default int updateByPrimaryKey(SmsCouponProductRelation record) {
        return update(c ->
            c.set(couponId).equalTo(record::getCouponId)
            .set(productId).equalTo(record::getProductId)
            .set(productName).equalTo(record::getProductName)
            .set(productSn).equalTo(record::getProductSn)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(SmsCouponProductRelation record) {
        return update(c ->
            c.set(couponId).equalToWhenPresent(record::getCouponId)
            .set(productId).equalToWhenPresent(record::getProductId)
            .set(productName).equalToWhenPresent(record::getProductName)
            .set(productSn).equalToWhenPresent(record::getProductSn)
            .where(id, isEqualTo(record::getId))
        );
    }
}