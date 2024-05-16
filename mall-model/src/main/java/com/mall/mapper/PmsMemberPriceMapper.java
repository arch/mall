/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsMemberPrice;

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
public interface PmsMemberPriceMapper {
    BasicColumn[] selectList = BasicColumn.columnList(PmsMemberPriceDynamicSqlSupport.id, PmsMemberPriceDynamicSqlSupport.productId, PmsMemberPriceDynamicSqlSupport.memberLevelId, PmsMemberPriceDynamicSqlSupport.memberPrice, PmsMemberPriceDynamicSqlSupport.memberLevelName);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsMemberPrice> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsMemberPriceResult")
    Optional<PmsMemberPrice> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsMemberPriceResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_level_id", property="memberLevelId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_price", property="memberPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="member_level_name", property="memberLevelName", jdbcType=JdbcType.VARCHAR)
    })
    List<PmsMemberPrice> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, PmsMemberPriceDynamicSqlSupport.pmsMemberPrice, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, PmsMemberPriceDynamicSqlSupport.pmsMemberPrice, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(PmsMemberPriceDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(PmsMemberPrice record) {
        return MyBatis3Utils.insert(this::insert, record, PmsMemberPriceDynamicSqlSupport.pmsMemberPrice, c ->
            c.map(PmsMemberPriceDynamicSqlSupport.productId).toProperty("productId")
            .map(PmsMemberPriceDynamicSqlSupport.memberLevelId).toProperty("memberLevelId")
            .map(PmsMemberPriceDynamicSqlSupport.memberPrice).toProperty("memberPrice")
            .map(PmsMemberPriceDynamicSqlSupport.memberLevelName).toProperty("memberLevelName")
        );
    }

    default int insertSelective(PmsMemberPrice record) {
        return MyBatis3Utils.insert(this::insert, record, PmsMemberPriceDynamicSqlSupport.pmsMemberPrice, c ->
            c.map(PmsMemberPriceDynamicSqlSupport.productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(PmsMemberPriceDynamicSqlSupport.memberLevelId).toPropertyWhenPresent("memberLevelId", record::getMemberLevelId)
            .map(PmsMemberPriceDynamicSqlSupport.memberPrice).toPropertyWhenPresent("memberPrice", record::getMemberPrice)
            .map(PmsMemberPriceDynamicSqlSupport.memberLevelName).toPropertyWhenPresent("memberLevelName", record::getMemberLevelName)
        );
    }

    default Optional<PmsMemberPrice> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, PmsMemberPriceDynamicSqlSupport.pmsMemberPrice, completer);
    }

    default List<PmsMemberPrice> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, PmsMemberPriceDynamicSqlSupport.pmsMemberPrice, completer);
    }

    default List<PmsMemberPrice> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, PmsMemberPriceDynamicSqlSupport.pmsMemberPrice, completer);
    }

    default Optional<PmsMemberPrice> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(PmsMemberPriceDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, PmsMemberPriceDynamicSqlSupport.pmsMemberPrice, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsMemberPrice record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsMemberPriceDynamicSqlSupport.productId).equalTo(record::getProductId)
                .set(PmsMemberPriceDynamicSqlSupport.memberLevelId).equalTo(record::getMemberLevelId)
                .set(PmsMemberPriceDynamicSqlSupport.memberPrice).equalTo(record::getMemberPrice)
                .set(PmsMemberPriceDynamicSqlSupport.memberLevelName).equalTo(record::getMemberLevelName);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsMemberPrice record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsMemberPriceDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
                .set(PmsMemberPriceDynamicSqlSupport.memberLevelId).equalToWhenPresent(record::getMemberLevelId)
                .set(PmsMemberPriceDynamicSqlSupport.memberPrice).equalToWhenPresent(record::getMemberPrice)
                .set(PmsMemberPriceDynamicSqlSupport.memberLevelName).equalToWhenPresent(record::getMemberLevelName);
    }

    default int updateByPrimaryKey(PmsMemberPrice record) {
        return update(c ->
            c.set(PmsMemberPriceDynamicSqlSupport.productId).equalTo(record::getProductId)
            .set(PmsMemberPriceDynamicSqlSupport.memberLevelId).equalTo(record::getMemberLevelId)
            .set(PmsMemberPriceDynamicSqlSupport.memberPrice).equalTo(record::getMemberPrice)
            .set(PmsMemberPriceDynamicSqlSupport.memberLevelName).equalTo(record::getMemberLevelName)
            .where(PmsMemberPriceDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsMemberPrice record) {
        return update(c ->
            c.set(PmsMemberPriceDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
            .set(PmsMemberPriceDynamicSqlSupport.memberLevelId).equalToWhenPresent(record::getMemberLevelId)
            .set(PmsMemberPriceDynamicSqlSupport.memberPrice).equalToWhenPresent(record::getMemberPrice)
            .set(PmsMemberPriceDynamicSqlSupport.memberLevelName).equalToWhenPresent(record::getMemberLevelName)
            .where(PmsMemberPriceDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}