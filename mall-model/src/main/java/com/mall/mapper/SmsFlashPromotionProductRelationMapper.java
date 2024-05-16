/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.SmsFlashPromotionProductRelationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.SmsFlashPromotionProductRelation;
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
public interface SmsFlashPromotionProductRelationMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, flashPromotionId, flashPromotionSessionId, productId, flashPromotionPrice, flashPromotionCount, flashPromotionLimit, sort);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SmsFlashPromotionProductRelation> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SmsFlashPromotionProductRelationResult")
    Optional<SmsFlashPromotionProductRelation> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SmsFlashPromotionProductRelationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="flash_promotion_id", property="flashPromotionId", jdbcType=JdbcType.BIGINT),
        @Result(column="flash_promotion_session_id", property="flashPromotionSessionId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="flash_promotion_price", property="flashPromotionPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="flash_promotion_count", property="flashPromotionCount", jdbcType=JdbcType.INTEGER),
        @Result(column="flash_promotion_limit", property="flashPromotionLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<SmsFlashPromotionProductRelation> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, smsFlashPromotionProductRelation, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, smsFlashPromotionProductRelation, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(SmsFlashPromotionProductRelation record) {
        return MyBatis3Utils.insert(this::insert, record, smsFlashPromotionProductRelation, c ->
            c.map(flashPromotionId).toProperty("flashPromotionId")
            .map(flashPromotionSessionId).toProperty("flashPromotionSessionId")
            .map(productId).toProperty("productId")
            .map(flashPromotionPrice).toProperty("flashPromotionPrice")
            .map(flashPromotionCount).toProperty("flashPromotionCount")
            .map(flashPromotionLimit).toProperty("flashPromotionLimit")
            .map(sort).toProperty("sort")
        );
    }

    default int insertSelective(SmsFlashPromotionProductRelation record) {
        return MyBatis3Utils.insert(this::insert, record, smsFlashPromotionProductRelation, c ->
            c.map(flashPromotionId).toPropertyWhenPresent("flashPromotionId", record::getFlashPromotionId)
            .map(flashPromotionSessionId).toPropertyWhenPresent("flashPromotionSessionId", record::getFlashPromotionSessionId)
            .map(productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(flashPromotionPrice).toPropertyWhenPresent("flashPromotionPrice", record::getFlashPromotionPrice)
            .map(flashPromotionCount).toPropertyWhenPresent("flashPromotionCount", record::getFlashPromotionCount)
            .map(flashPromotionLimit).toPropertyWhenPresent("flashPromotionLimit", record::getFlashPromotionLimit)
            .map(sort).toPropertyWhenPresent("sort", record::getSort)
        );
    }

    default Optional<SmsFlashPromotionProductRelation> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, smsFlashPromotionProductRelation, completer);
    }

    default List<SmsFlashPromotionProductRelation> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, smsFlashPromotionProductRelation, completer);
    }

    default List<SmsFlashPromotionProductRelation> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, smsFlashPromotionProductRelation, completer);
    }

    default Optional<SmsFlashPromotionProductRelation> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, smsFlashPromotionProductRelation, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SmsFlashPromotionProductRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(flashPromotionId).equalTo(record::getFlashPromotionId)
                .set(flashPromotionSessionId).equalTo(record::getFlashPromotionSessionId)
                .set(productId).equalTo(record::getProductId)
                .set(flashPromotionPrice).equalTo(record::getFlashPromotionPrice)
                .set(flashPromotionCount).equalTo(record::getFlashPromotionCount)
                .set(flashPromotionLimit).equalTo(record::getFlashPromotionLimit)
                .set(sort).equalTo(record::getSort);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SmsFlashPromotionProductRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(flashPromotionId).equalToWhenPresent(record::getFlashPromotionId)
                .set(flashPromotionSessionId).equalToWhenPresent(record::getFlashPromotionSessionId)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(flashPromotionPrice).equalToWhenPresent(record::getFlashPromotionPrice)
                .set(flashPromotionCount).equalToWhenPresent(record::getFlashPromotionCount)
                .set(flashPromotionLimit).equalToWhenPresent(record::getFlashPromotionLimit)
                .set(sort).equalToWhenPresent(record::getSort);
    }

    default int updateByPrimaryKey(SmsFlashPromotionProductRelation record) {
        return update(c ->
            c.set(flashPromotionId).equalTo(record::getFlashPromotionId)
            .set(flashPromotionSessionId).equalTo(record::getFlashPromotionSessionId)
            .set(productId).equalTo(record::getProductId)
            .set(flashPromotionPrice).equalTo(record::getFlashPromotionPrice)
            .set(flashPromotionCount).equalTo(record::getFlashPromotionCount)
            .set(flashPromotionLimit).equalTo(record::getFlashPromotionLimit)
            .set(sort).equalTo(record::getSort)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(SmsFlashPromotionProductRelation record) {
        return update(c ->
            c.set(flashPromotionId).equalToWhenPresent(record::getFlashPromotionId)
            .set(flashPromotionSessionId).equalToWhenPresent(record::getFlashPromotionSessionId)
            .set(productId).equalToWhenPresent(record::getProductId)
            .set(flashPromotionPrice).equalToWhenPresent(record::getFlashPromotionPrice)
            .set(flashPromotionCount).equalToWhenPresent(record::getFlashPromotionCount)
            .set(flashPromotionLimit).equalToWhenPresent(record::getFlashPromotionLimit)
            .set(sort).equalToWhenPresent(record::getSort)
            .where(id, isEqualTo(record::getId))
        );
    }
}