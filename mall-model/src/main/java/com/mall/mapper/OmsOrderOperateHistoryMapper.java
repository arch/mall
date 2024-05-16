/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.OmsOrderOperateHistoryDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.OmsOrderOperateHistory;
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
public interface OmsOrderOperateHistoryMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, orderId, operateMan, orderStatus, note, createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<OmsOrderOperateHistory> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OmsOrderOperateHistoryResult")
    Optional<OmsOrderOperateHistory> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OmsOrderOperateHistoryResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="operate_man", property="operateMan", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<OmsOrderOperateHistory> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, omsOrderOperateHistory, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, omsOrderOperateHistory, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(OmsOrderOperateHistory record) {
        return MyBatis3Utils.insert(this::insert, record, omsOrderOperateHistory, c ->
            c.map(orderId).toProperty("orderId")
            .map(operateMan).toProperty("operateMan")
            .map(orderStatus).toProperty("orderStatus")
            .map(note).toProperty("note")
            .map(createTime).toProperty("createTime")
        );
    }

    default int insertSelective(OmsOrderOperateHistory record) {
        return MyBatis3Utils.insert(this::insert, record, omsOrderOperateHistory, c ->
            c.map(orderId).toPropertyWhenPresent("orderId", record::getOrderId)
            .map(operateMan).toPropertyWhenPresent("operateMan", record::getOperateMan)
            .map(orderStatus).toPropertyWhenPresent("orderStatus", record::getOrderStatus)
            .map(note).toPropertyWhenPresent("note", record::getNote)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<OmsOrderOperateHistory> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, omsOrderOperateHistory, completer);
    }

    default List<OmsOrderOperateHistory> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, omsOrderOperateHistory, completer);
    }

    default List<OmsOrderOperateHistory> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, omsOrderOperateHistory, completer);
    }

    default Optional<OmsOrderOperateHistory> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, omsOrderOperateHistory, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(OmsOrderOperateHistory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(orderId).equalTo(record::getOrderId)
                .set(operateMan).equalTo(record::getOperateMan)
                .set(orderStatus).equalTo(record::getOrderStatus)
                .set(note).equalTo(record::getNote)
                .set(createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(OmsOrderOperateHistory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(orderId).equalToWhenPresent(record::getOrderId)
                .set(operateMan).equalToWhenPresent(record::getOperateMan)
                .set(orderStatus).equalToWhenPresent(record::getOrderStatus)
                .set(note).equalToWhenPresent(record::getNote)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(OmsOrderOperateHistory record) {
        return update(c ->
            c.set(orderId).equalTo(record::getOrderId)
            .set(operateMan).equalTo(record::getOperateMan)
            .set(orderStatus).equalTo(record::getOrderStatus)
            .set(note).equalTo(record::getNote)
            .set(createTime).equalTo(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(OmsOrderOperateHistory record) {
        return update(c ->
            c.set(orderId).equalToWhenPresent(record::getOrderId)
            .set(operateMan).equalToWhenPresent(record::getOperateMan)
            .set(orderStatus).equalToWhenPresent(record::getOrderStatus)
            .set(note).equalToWhenPresent(record::getNote)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}