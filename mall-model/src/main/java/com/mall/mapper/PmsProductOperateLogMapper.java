/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsProductOperateLog;

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
public interface PmsProductOperateLogMapper {
    BasicColumn[] selectList = BasicColumn.columnList(PmsProductOperateLogDynamicSqlSupport.id, PmsProductOperateLogDynamicSqlSupport.productId, PmsProductOperateLogDynamicSqlSupport.priceOld, PmsProductOperateLogDynamicSqlSupport.priceNew, PmsProductOperateLogDynamicSqlSupport.salePriceOld, PmsProductOperateLogDynamicSqlSupport.salePriceNew, PmsProductOperateLogDynamicSqlSupport.giftPointOld, PmsProductOperateLogDynamicSqlSupport.giftPointNew, PmsProductOperateLogDynamicSqlSupport.usePointLimitOld, PmsProductOperateLogDynamicSqlSupport.usePointLimitNew, PmsProductOperateLogDynamicSqlSupport.operateMan, PmsProductOperateLogDynamicSqlSupport.createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsProductOperateLog> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsProductOperateLogResult")
    Optional<PmsProductOperateLog> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsProductOperateLogResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="price_old", property="priceOld", jdbcType=JdbcType.DECIMAL),
        @Result(column="price_new", property="priceNew", jdbcType=JdbcType.DECIMAL),
        @Result(column="sale_price_old", property="salePriceOld", jdbcType=JdbcType.DECIMAL),
        @Result(column="sale_price_new", property="salePriceNew", jdbcType=JdbcType.DECIMAL),
        @Result(column="gift_point_old", property="giftPointOld", jdbcType=JdbcType.INTEGER),
        @Result(column="gift_point_new", property="giftPointNew", jdbcType=JdbcType.INTEGER),
        @Result(column="use_point_limit_old", property="usePointLimitOld", jdbcType=JdbcType.INTEGER),
        @Result(column="use_point_limit_new", property="usePointLimitNew", jdbcType=JdbcType.INTEGER),
        @Result(column="operate_man", property="operateMan", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<PmsProductOperateLog> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, PmsProductOperateLogDynamicSqlSupport.pmsProductOperateLog, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, PmsProductOperateLogDynamicSqlSupport.pmsProductOperateLog, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(PmsProductOperateLogDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(PmsProductOperateLog record) {
        return MyBatis3Utils.insert(this::insert, record, PmsProductOperateLogDynamicSqlSupport.pmsProductOperateLog, c ->
            c.map(PmsProductOperateLogDynamicSqlSupport.productId).toProperty("productId")
            .map(PmsProductOperateLogDynamicSqlSupport.priceOld).toProperty("priceOld")
            .map(PmsProductOperateLogDynamicSqlSupport.priceNew).toProperty("priceNew")
            .map(PmsProductOperateLogDynamicSqlSupport.salePriceOld).toProperty("salePriceOld")
            .map(PmsProductOperateLogDynamicSqlSupport.salePriceNew).toProperty("salePriceNew")
            .map(PmsProductOperateLogDynamicSqlSupport.giftPointOld).toProperty("giftPointOld")
            .map(PmsProductOperateLogDynamicSqlSupport.giftPointNew).toProperty("giftPointNew")
            .map(PmsProductOperateLogDynamicSqlSupport.usePointLimitOld).toProperty("usePointLimitOld")
            .map(PmsProductOperateLogDynamicSqlSupport.usePointLimitNew).toProperty("usePointLimitNew")
            .map(PmsProductOperateLogDynamicSqlSupport.operateMan).toProperty("operateMan")
            .map(PmsProductOperateLogDynamicSqlSupport.createTime).toProperty("createTime")
        );
    }

    default int insertSelective(PmsProductOperateLog record) {
        return MyBatis3Utils.insert(this::insert, record, PmsProductOperateLogDynamicSqlSupport.pmsProductOperateLog, c ->
            c.map(PmsProductOperateLogDynamicSqlSupport.productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(PmsProductOperateLogDynamicSqlSupport.priceOld).toPropertyWhenPresent("priceOld", record::getPriceOld)
            .map(PmsProductOperateLogDynamicSqlSupport.priceNew).toPropertyWhenPresent("priceNew", record::getPriceNew)
            .map(PmsProductOperateLogDynamicSqlSupport.salePriceOld).toPropertyWhenPresent("salePriceOld", record::getSalePriceOld)
            .map(PmsProductOperateLogDynamicSqlSupport.salePriceNew).toPropertyWhenPresent("salePriceNew", record::getSalePriceNew)
            .map(PmsProductOperateLogDynamicSqlSupport.giftPointOld).toPropertyWhenPresent("giftPointOld", record::getGiftPointOld)
            .map(PmsProductOperateLogDynamicSqlSupport.giftPointNew).toPropertyWhenPresent("giftPointNew", record::getGiftPointNew)
            .map(PmsProductOperateLogDynamicSqlSupport.usePointLimitOld).toPropertyWhenPresent("usePointLimitOld", record::getUsePointLimitOld)
            .map(PmsProductOperateLogDynamicSqlSupport.usePointLimitNew).toPropertyWhenPresent("usePointLimitNew", record::getUsePointLimitNew)
            .map(PmsProductOperateLogDynamicSqlSupport.operateMan).toPropertyWhenPresent("operateMan", record::getOperateMan)
            .map(PmsProductOperateLogDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<PmsProductOperateLog> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, PmsProductOperateLogDynamicSqlSupport.pmsProductOperateLog, completer);
    }

    default List<PmsProductOperateLog> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, PmsProductOperateLogDynamicSqlSupport.pmsProductOperateLog, completer);
    }

    default List<PmsProductOperateLog> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, PmsProductOperateLogDynamicSqlSupport.pmsProductOperateLog, completer);
    }

    default Optional<PmsProductOperateLog> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(PmsProductOperateLogDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, PmsProductOperateLogDynamicSqlSupport.pmsProductOperateLog, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsProductOperateLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsProductOperateLogDynamicSqlSupport.productId).equalTo(record::getProductId)
                .set(PmsProductOperateLogDynamicSqlSupport.priceOld).equalTo(record::getPriceOld)
                .set(PmsProductOperateLogDynamicSqlSupport.priceNew).equalTo(record::getPriceNew)
                .set(PmsProductOperateLogDynamicSqlSupport.salePriceOld).equalTo(record::getSalePriceOld)
                .set(PmsProductOperateLogDynamicSqlSupport.salePriceNew).equalTo(record::getSalePriceNew)
                .set(PmsProductOperateLogDynamicSqlSupport.giftPointOld).equalTo(record::getGiftPointOld)
                .set(PmsProductOperateLogDynamicSqlSupport.giftPointNew).equalTo(record::getGiftPointNew)
                .set(PmsProductOperateLogDynamicSqlSupport.usePointLimitOld).equalTo(record::getUsePointLimitOld)
                .set(PmsProductOperateLogDynamicSqlSupport.usePointLimitNew).equalTo(record::getUsePointLimitNew)
                .set(PmsProductOperateLogDynamicSqlSupport.operateMan).equalTo(record::getOperateMan)
                .set(PmsProductOperateLogDynamicSqlSupport.createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsProductOperateLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsProductOperateLogDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
                .set(PmsProductOperateLogDynamicSqlSupport.priceOld).equalToWhenPresent(record::getPriceOld)
                .set(PmsProductOperateLogDynamicSqlSupport.priceNew).equalToWhenPresent(record::getPriceNew)
                .set(PmsProductOperateLogDynamicSqlSupport.salePriceOld).equalToWhenPresent(record::getSalePriceOld)
                .set(PmsProductOperateLogDynamicSqlSupport.salePriceNew).equalToWhenPresent(record::getSalePriceNew)
                .set(PmsProductOperateLogDynamicSqlSupport.giftPointOld).equalToWhenPresent(record::getGiftPointOld)
                .set(PmsProductOperateLogDynamicSqlSupport.giftPointNew).equalToWhenPresent(record::getGiftPointNew)
                .set(PmsProductOperateLogDynamicSqlSupport.usePointLimitOld).equalToWhenPresent(record::getUsePointLimitOld)
                .set(PmsProductOperateLogDynamicSqlSupport.usePointLimitNew).equalToWhenPresent(record::getUsePointLimitNew)
                .set(PmsProductOperateLogDynamicSqlSupport.operateMan).equalToWhenPresent(record::getOperateMan)
                .set(PmsProductOperateLogDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(PmsProductOperateLog record) {
        return update(c ->
            c.set(PmsProductOperateLogDynamicSqlSupport.productId).equalTo(record::getProductId)
            .set(PmsProductOperateLogDynamicSqlSupport.priceOld).equalTo(record::getPriceOld)
            .set(PmsProductOperateLogDynamicSqlSupport.priceNew).equalTo(record::getPriceNew)
            .set(PmsProductOperateLogDynamicSqlSupport.salePriceOld).equalTo(record::getSalePriceOld)
            .set(PmsProductOperateLogDynamicSqlSupport.salePriceNew).equalTo(record::getSalePriceNew)
            .set(PmsProductOperateLogDynamicSqlSupport.giftPointOld).equalTo(record::getGiftPointOld)
            .set(PmsProductOperateLogDynamicSqlSupport.giftPointNew).equalTo(record::getGiftPointNew)
            .set(PmsProductOperateLogDynamicSqlSupport.usePointLimitOld).equalTo(record::getUsePointLimitOld)
            .set(PmsProductOperateLogDynamicSqlSupport.usePointLimitNew).equalTo(record::getUsePointLimitNew)
            .set(PmsProductOperateLogDynamicSqlSupport.operateMan).equalTo(record::getOperateMan)
            .set(PmsProductOperateLogDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .where(PmsProductOperateLogDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsProductOperateLog record) {
        return update(c ->
            c.set(PmsProductOperateLogDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
            .set(PmsProductOperateLogDynamicSqlSupport.priceOld).equalToWhenPresent(record::getPriceOld)
            .set(PmsProductOperateLogDynamicSqlSupport.priceNew).equalToWhenPresent(record::getPriceNew)
            .set(PmsProductOperateLogDynamicSqlSupport.salePriceOld).equalToWhenPresent(record::getSalePriceOld)
            .set(PmsProductOperateLogDynamicSqlSupport.salePriceNew).equalToWhenPresent(record::getSalePriceNew)
            .set(PmsProductOperateLogDynamicSqlSupport.giftPointOld).equalToWhenPresent(record::getGiftPointOld)
            .set(PmsProductOperateLogDynamicSqlSupport.giftPointNew).equalToWhenPresent(record::getGiftPointNew)
            .set(PmsProductOperateLogDynamicSqlSupport.usePointLimitOld).equalToWhenPresent(record::getUsePointLimitOld)
            .set(PmsProductOperateLogDynamicSqlSupport.usePointLimitNew).equalToWhenPresent(record::getUsePointLimitNew)
            .set(PmsProductOperateLogDynamicSqlSupport.operateMan).equalToWhenPresent(record::getOperateMan)
            .set(PmsProductOperateLogDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .where(PmsProductOperateLogDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}