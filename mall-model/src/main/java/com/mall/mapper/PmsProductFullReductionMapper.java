/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.PmsProductFullReductionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsProductFullReduction;
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
public interface PmsProductFullReductionMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, productId, fullPrice, reducePrice);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsProductFullReduction> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsProductFullReductionResult")
    Optional<PmsProductFullReduction> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsProductFullReductionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="full_price", property="fullPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="reduce_price", property="reducePrice", jdbcType=JdbcType.DECIMAL)
    })
    List<PmsProductFullReduction> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, pmsProductFullReduction, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, pmsProductFullReduction, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(PmsProductFullReduction record) {
        return MyBatis3Utils.insert(this::insert, record, pmsProductFullReduction, c ->
            c.map(productId).toProperty("productId")
            .map(fullPrice).toProperty("fullPrice")
            .map(reducePrice).toProperty("reducePrice")
        );
    }

    default int insertSelective(PmsProductFullReduction record) {
        return MyBatis3Utils.insert(this::insert, record, pmsProductFullReduction, c ->
            c.map(productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(fullPrice).toPropertyWhenPresent("fullPrice", record::getFullPrice)
            .map(reducePrice).toPropertyWhenPresent("reducePrice", record::getReducePrice)
        );
    }

    default Optional<PmsProductFullReduction> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, pmsProductFullReduction, completer);
    }

    default List<PmsProductFullReduction> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, pmsProductFullReduction, completer);
    }

    default List<PmsProductFullReduction> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, pmsProductFullReduction, completer);
    }

    default Optional<PmsProductFullReduction> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, pmsProductFullReduction, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsProductFullReduction record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(productId).equalTo(record::getProductId)
                .set(fullPrice).equalTo(record::getFullPrice)
                .set(reducePrice).equalTo(record::getReducePrice);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsProductFullReduction record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(productId).equalToWhenPresent(record::getProductId)
                .set(fullPrice).equalToWhenPresent(record::getFullPrice)
                .set(reducePrice).equalToWhenPresent(record::getReducePrice);
    }

    default int updateByPrimaryKey(PmsProductFullReduction record) {
        return update(c ->
            c.set(productId).equalTo(record::getProductId)
            .set(fullPrice).equalTo(record::getFullPrice)
            .set(reducePrice).equalTo(record::getReducePrice)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsProductFullReduction record) {
        return update(c ->
            c.set(productId).equalToWhenPresent(record::getProductId)
            .set(fullPrice).equalToWhenPresent(record::getFullPrice)
            .set(reducePrice).equalToWhenPresent(record::getReducePrice)
            .where(id, isEqualTo(record::getId))
        );
    }
}