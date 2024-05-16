/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsProductLadder;

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
public interface PmsProductLadderMapper {
    BasicColumn[] selectList = BasicColumn.columnList(PmsProductLadderDynamicSqlSupport.id, PmsProductLadderDynamicSqlSupport.productId, PmsProductLadderDynamicSqlSupport.count, PmsProductLadderDynamicSqlSupport.discount, PmsProductLadderDynamicSqlSupport.price);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsProductLadder> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsProductLadderResult")
    Optional<PmsProductLadder> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsProductLadderResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="discount", property="discount", jdbcType=JdbcType.DECIMAL),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL)
    })
    List<PmsProductLadder> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, PmsProductLadderDynamicSqlSupport.pmsProductLadder, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, PmsProductLadderDynamicSqlSupport.pmsProductLadder, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(PmsProductLadderDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(PmsProductLadder record) {
        return MyBatis3Utils.insert(this::insert, record, PmsProductLadderDynamicSqlSupport.pmsProductLadder, c ->
            c.map(PmsProductLadderDynamicSqlSupport.productId).toProperty("productId")
            .map(PmsProductLadderDynamicSqlSupport.count).toProperty("count")
            .map(PmsProductLadderDynamicSqlSupport.discount).toProperty("discount")
            .map(PmsProductLadderDynamicSqlSupport.price).toProperty("price")
        );
    }

    default int insertSelective(PmsProductLadder record) {
        return MyBatis3Utils.insert(this::insert, record, PmsProductLadderDynamicSqlSupport.pmsProductLadder, c ->
            c.map(PmsProductLadderDynamicSqlSupport.productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(PmsProductLadderDynamicSqlSupport.count).toPropertyWhenPresent("count", record::getCount)
            .map(PmsProductLadderDynamicSqlSupport.discount).toPropertyWhenPresent("discount", record::getDiscount)
            .map(PmsProductLadderDynamicSqlSupport.price).toPropertyWhenPresent("price", record::getPrice)
        );
    }

    default Optional<PmsProductLadder> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, PmsProductLadderDynamicSqlSupport.pmsProductLadder, completer);
    }

    default List<PmsProductLadder> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, PmsProductLadderDynamicSqlSupport.pmsProductLadder, completer);
    }

    default List<PmsProductLadder> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, PmsProductLadderDynamicSqlSupport.pmsProductLadder, completer);
    }

    default Optional<PmsProductLadder> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(PmsProductLadderDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, PmsProductLadderDynamicSqlSupport.pmsProductLadder, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsProductLadder record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsProductLadderDynamicSqlSupport.productId).equalTo(record::getProductId)
                .set(PmsProductLadderDynamicSqlSupport.count).equalTo(record::getCount)
                .set(PmsProductLadderDynamicSqlSupport.discount).equalTo(record::getDiscount)
                .set(PmsProductLadderDynamicSqlSupport.price).equalTo(record::getPrice);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsProductLadder record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsProductLadderDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
                .set(PmsProductLadderDynamicSqlSupport.count).equalToWhenPresent(record::getCount)
                .set(PmsProductLadderDynamicSqlSupport.discount).equalToWhenPresent(record::getDiscount)
                .set(PmsProductLadderDynamicSqlSupport.price).equalToWhenPresent(record::getPrice);
    }

    default int updateByPrimaryKey(PmsProductLadder record) {
        return update(c ->
            c.set(PmsProductLadderDynamicSqlSupport.productId).equalTo(record::getProductId)
            .set(PmsProductLadderDynamicSqlSupport.count).equalTo(record::getCount)
            .set(PmsProductLadderDynamicSqlSupport.discount).equalTo(record::getDiscount)
            .set(PmsProductLadderDynamicSqlSupport.price).equalTo(record::getPrice)
            .where(PmsProductLadderDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsProductLadder record) {
        return update(c ->
            c.set(PmsProductLadderDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
            .set(PmsProductLadderDynamicSqlSupport.count).equalToWhenPresent(record::getCount)
            .set(PmsProductLadderDynamicSqlSupport.discount).equalToWhenPresent(record::getDiscount)
            .set(PmsProductLadderDynamicSqlSupport.price).equalToWhenPresent(record::getPrice)
            .where(PmsProductLadderDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}