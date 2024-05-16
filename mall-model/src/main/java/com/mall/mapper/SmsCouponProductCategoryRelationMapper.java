/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.SmsCouponProductCategoryRelationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.SmsCouponProductCategoryRelation;
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
public interface SmsCouponProductCategoryRelationMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, couponId, productCategoryId, productCategoryName, parentCategoryName);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SmsCouponProductCategoryRelation> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SmsCouponProductCategoryRelationResult")
    Optional<SmsCouponProductCategoryRelation> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SmsCouponProductCategoryRelationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="coupon_id", property="couponId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_category_id", property="productCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_category_name", property="productCategoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_category_name", property="parentCategoryName", jdbcType=JdbcType.VARCHAR)
    })
    List<SmsCouponProductCategoryRelation> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, smsCouponProductCategoryRelation, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, smsCouponProductCategoryRelation, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(SmsCouponProductCategoryRelation record) {
        return MyBatis3Utils.insert(this::insert, record, smsCouponProductCategoryRelation, c ->
            c.map(couponId).toProperty("couponId")
            .map(productCategoryId).toProperty("productCategoryId")
            .map(productCategoryName).toProperty("productCategoryName")
            .map(parentCategoryName).toProperty("parentCategoryName")
        );
    }

    default int insertSelective(SmsCouponProductCategoryRelation record) {
        return MyBatis3Utils.insert(this::insert, record, smsCouponProductCategoryRelation, c ->
            c.map(couponId).toPropertyWhenPresent("couponId", record::getCouponId)
            .map(productCategoryId).toPropertyWhenPresent("productCategoryId", record::getProductCategoryId)
            .map(productCategoryName).toPropertyWhenPresent("productCategoryName", record::getProductCategoryName)
            .map(parentCategoryName).toPropertyWhenPresent("parentCategoryName", record::getParentCategoryName)
        );
    }

    default Optional<SmsCouponProductCategoryRelation> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, smsCouponProductCategoryRelation, completer);
    }

    default List<SmsCouponProductCategoryRelation> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, smsCouponProductCategoryRelation, completer);
    }

    default List<SmsCouponProductCategoryRelation> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, smsCouponProductCategoryRelation, completer);
    }

    default Optional<SmsCouponProductCategoryRelation> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, smsCouponProductCategoryRelation, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SmsCouponProductCategoryRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(couponId).equalTo(record::getCouponId)
                .set(productCategoryId).equalTo(record::getProductCategoryId)
                .set(productCategoryName).equalTo(record::getProductCategoryName)
                .set(parentCategoryName).equalTo(record::getParentCategoryName);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SmsCouponProductCategoryRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(couponId).equalToWhenPresent(record::getCouponId)
                .set(productCategoryId).equalToWhenPresent(record::getProductCategoryId)
                .set(productCategoryName).equalToWhenPresent(record::getProductCategoryName)
                .set(parentCategoryName).equalToWhenPresent(record::getParentCategoryName);
    }

    default int updateByPrimaryKey(SmsCouponProductCategoryRelation record) {
        return update(c ->
            c.set(couponId).equalTo(record::getCouponId)
            .set(productCategoryId).equalTo(record::getProductCategoryId)
            .set(productCategoryName).equalTo(record::getProductCategoryName)
            .set(parentCategoryName).equalTo(record::getParentCategoryName)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(SmsCouponProductCategoryRelation record) {
        return update(c ->
            c.set(couponId).equalToWhenPresent(record::getCouponId)
            .set(productCategoryId).equalToWhenPresent(record::getProductCategoryId)
            .set(productCategoryName).equalToWhenPresent(record::getProductCategoryName)
            .set(parentCategoryName).equalToWhenPresent(record::getParentCategoryName)
            .where(id, isEqualTo(record::getId))
        );
    }
}