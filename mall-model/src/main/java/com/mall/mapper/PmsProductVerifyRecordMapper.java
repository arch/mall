/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsProductVerifyRecord;

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
public interface PmsProductVerifyRecordMapper {
    BasicColumn[] selectList = BasicColumn.columnList(PmsProductVerifyRecordDynamicSqlSupport.id, PmsProductVerifyRecordDynamicSqlSupport.productId, PmsProductVerifyRecordDynamicSqlSupport.createTime, PmsProductVerifyRecordDynamicSqlSupport.verifyMan, PmsProductVerifyRecordDynamicSqlSupport.status, PmsProductVerifyRecordDynamicSqlSupport.detail);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsProductVerifyRecord> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsProductVerifyRecordResult")
    Optional<PmsProductVerifyRecord> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsProductVerifyRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="verify_man", property="verifyMan", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="detail", property="detail", jdbcType=JdbcType.VARCHAR)
    })
    List<PmsProductVerifyRecord> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, PmsProductVerifyRecordDynamicSqlSupport.pmsProductVerifyRecord, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, PmsProductVerifyRecordDynamicSqlSupport.pmsProductVerifyRecord, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(PmsProductVerifyRecordDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(PmsProductVerifyRecord record) {
        return MyBatis3Utils.insert(this::insert, record, PmsProductVerifyRecordDynamicSqlSupport.pmsProductVerifyRecord, c ->
            c.map(PmsProductVerifyRecordDynamicSqlSupport.productId).toProperty("productId")
            .map(PmsProductVerifyRecordDynamicSqlSupport.createTime).toProperty("createTime")
            .map(PmsProductVerifyRecordDynamicSqlSupport.verifyMan).toProperty("verifyMan")
            .map(PmsProductVerifyRecordDynamicSqlSupport.status).toProperty("status")
            .map(PmsProductVerifyRecordDynamicSqlSupport.detail).toProperty("detail")
        );
    }

    default int insertSelective(PmsProductVerifyRecord record) {
        return MyBatis3Utils.insert(this::insert, record, PmsProductVerifyRecordDynamicSqlSupport.pmsProductVerifyRecord, c ->
            c.map(PmsProductVerifyRecordDynamicSqlSupport.productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(PmsProductVerifyRecordDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(PmsProductVerifyRecordDynamicSqlSupport.verifyMan).toPropertyWhenPresent("verifyMan", record::getVerifyMan)
            .map(PmsProductVerifyRecordDynamicSqlSupport.status).toPropertyWhenPresent("status", record::getStatus)
            .map(PmsProductVerifyRecordDynamicSqlSupport.detail).toPropertyWhenPresent("detail", record::getDetail)
        );
    }

    default Optional<PmsProductVerifyRecord> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, PmsProductVerifyRecordDynamicSqlSupport.pmsProductVerifyRecord, completer);
    }

    default List<PmsProductVerifyRecord> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, PmsProductVerifyRecordDynamicSqlSupport.pmsProductVerifyRecord, completer);
    }

    default List<PmsProductVerifyRecord> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, PmsProductVerifyRecordDynamicSqlSupport.pmsProductVerifyRecord, completer);
    }

    default Optional<PmsProductVerifyRecord> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(PmsProductVerifyRecordDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, PmsProductVerifyRecordDynamicSqlSupport.pmsProductVerifyRecord, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsProductVerifyRecord record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsProductVerifyRecordDynamicSqlSupport.productId).equalTo(record::getProductId)
                .set(PmsProductVerifyRecordDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(PmsProductVerifyRecordDynamicSqlSupport.verifyMan).equalTo(record::getVerifyMan)
                .set(PmsProductVerifyRecordDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(PmsProductVerifyRecordDynamicSqlSupport.detail).equalTo(record::getDetail);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsProductVerifyRecord record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsProductVerifyRecordDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
                .set(PmsProductVerifyRecordDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(PmsProductVerifyRecordDynamicSqlSupport.verifyMan).equalToWhenPresent(record::getVerifyMan)
                .set(PmsProductVerifyRecordDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(PmsProductVerifyRecordDynamicSqlSupport.detail).equalToWhenPresent(record::getDetail);
    }

    default int updateByPrimaryKey(PmsProductVerifyRecord record) {
        return update(c ->
            c.set(PmsProductVerifyRecordDynamicSqlSupport.productId).equalTo(record::getProductId)
            .set(PmsProductVerifyRecordDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .set(PmsProductVerifyRecordDynamicSqlSupport.verifyMan).equalTo(record::getVerifyMan)
            .set(PmsProductVerifyRecordDynamicSqlSupport.status).equalTo(record::getStatus)
            .set(PmsProductVerifyRecordDynamicSqlSupport.detail).equalTo(record::getDetail)
            .where(PmsProductVerifyRecordDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsProductVerifyRecord record) {
        return update(c ->
            c.set(PmsProductVerifyRecordDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
            .set(PmsProductVerifyRecordDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .set(PmsProductVerifyRecordDynamicSqlSupport.verifyMan).equalToWhenPresent(record::getVerifyMan)
            .set(PmsProductVerifyRecordDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
            .set(PmsProductVerifyRecordDynamicSqlSupport.detail).equalToWhenPresent(record::getDetail)
            .where(PmsProductVerifyRecordDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}