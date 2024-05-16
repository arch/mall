/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.PmsSkuStockDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsSkuStock;
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
public interface PmsSkuStockMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, productId, skuCode, price, stock, lowStock, pic, sale, promotionPrice, lockStock, spData);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsSkuStock> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsSkuStockResult")
    Optional<PmsSkuStock> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsSkuStockResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="sku_code", property="skuCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="low_stock", property="lowStock", jdbcType=JdbcType.INTEGER),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="sale", property="sale", jdbcType=JdbcType.INTEGER),
        @Result(column="promotion_price", property="promotionPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="lock_stock", property="lockStock", jdbcType=JdbcType.INTEGER),
        @Result(column="sp_data", property="spData", jdbcType=JdbcType.VARCHAR)
    })
    List<PmsSkuStock> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, pmsSkuStock, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, pmsSkuStock, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(PmsSkuStock record) {
        return MyBatis3Utils.insert(this::insert, record, pmsSkuStock, c ->
            c.map(productId).toProperty("productId")
            .map(skuCode).toProperty("skuCode")
            .map(price).toProperty("price")
            .map(stock).toProperty("stock")
            .map(lowStock).toProperty("lowStock")
            .map(pic).toProperty("pic")
            .map(sale).toProperty("sale")
            .map(promotionPrice).toProperty("promotionPrice")
            .map(lockStock).toProperty("lockStock")
            .map(spData).toProperty("spData")
        );
    }

    default int insertSelective(PmsSkuStock record) {
        return MyBatis3Utils.insert(this::insert, record, pmsSkuStock, c ->
            c.map(productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(skuCode).toPropertyWhenPresent("skuCode", record::getSkuCode)
            .map(price).toPropertyWhenPresent("price", record::getPrice)
            .map(stock).toPropertyWhenPresent("stock", record::getStock)
            .map(lowStock).toPropertyWhenPresent("lowStock", record::getLowStock)
            .map(pic).toPropertyWhenPresent("pic", record::getPic)
            .map(sale).toPropertyWhenPresent("sale", record::getSale)
            .map(promotionPrice).toPropertyWhenPresent("promotionPrice", record::getPromotionPrice)
            .map(lockStock).toPropertyWhenPresent("lockStock", record::getLockStock)
            .map(spData).toPropertyWhenPresent("spData", record::getSpData)
        );
    }

    default Optional<PmsSkuStock> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, pmsSkuStock, completer);
    }

    default List<PmsSkuStock> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, pmsSkuStock, completer);
    }

    default List<PmsSkuStock> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, pmsSkuStock, completer);
    }

    default Optional<PmsSkuStock> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, pmsSkuStock, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsSkuStock record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(productId).equalTo(record::getProductId)
                .set(skuCode).equalTo(record::getSkuCode)
                .set(price).equalTo(record::getPrice)
                .set(stock).equalTo(record::getStock)
                .set(lowStock).equalTo(record::getLowStock)
                .set(pic).equalTo(record::getPic)
                .set(sale).equalTo(record::getSale)
                .set(promotionPrice).equalTo(record::getPromotionPrice)
                .set(lockStock).equalTo(record::getLockStock)
                .set(spData).equalTo(record::getSpData);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsSkuStock record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(productId).equalToWhenPresent(record::getProductId)
                .set(skuCode).equalToWhenPresent(record::getSkuCode)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(stock).equalToWhenPresent(record::getStock)
                .set(lowStock).equalToWhenPresent(record::getLowStock)
                .set(pic).equalToWhenPresent(record::getPic)
                .set(sale).equalToWhenPresent(record::getSale)
                .set(promotionPrice).equalToWhenPresent(record::getPromotionPrice)
                .set(lockStock).equalToWhenPresent(record::getLockStock)
                .set(spData).equalToWhenPresent(record::getSpData);
    }

    default int updateByPrimaryKey(PmsSkuStock record) {
        return update(c ->
            c.set(productId).equalTo(record::getProductId)
            .set(skuCode).equalTo(record::getSkuCode)
            .set(price).equalTo(record::getPrice)
            .set(stock).equalTo(record::getStock)
            .set(lowStock).equalTo(record::getLowStock)
            .set(pic).equalTo(record::getPic)
            .set(sale).equalTo(record::getSale)
            .set(promotionPrice).equalTo(record::getPromotionPrice)
            .set(lockStock).equalTo(record::getLockStock)
            .set(spData).equalTo(record::getSpData)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsSkuStock record) {
        return update(c ->
            c.set(productId).equalToWhenPresent(record::getProductId)
            .set(skuCode).equalToWhenPresent(record::getSkuCode)
            .set(price).equalToWhenPresent(record::getPrice)
            .set(stock).equalToWhenPresent(record::getStock)
            .set(lowStock).equalToWhenPresent(record::getLowStock)
            .set(pic).equalToWhenPresent(record::getPic)
            .set(sale).equalToWhenPresent(record::getSale)
            .set(promotionPrice).equalToWhenPresent(record::getPromotionPrice)
            .set(lockStock).equalToWhenPresent(record::getLockStock)
            .set(spData).equalToWhenPresent(record::getSpData)
            .where(id, isEqualTo(record::getId))
        );
    }
}