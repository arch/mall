/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.OmsOrderSetting;

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
public interface OmsOrderSettingMapper {
    BasicColumn[] selectList = BasicColumn.columnList(OmsOrderSettingDynamicSqlSupport.id, OmsOrderSettingDynamicSqlSupport.flashOrderOvertime, OmsOrderSettingDynamicSqlSupport.normalOrderOvertime, OmsOrderSettingDynamicSqlSupport.confirmOvertime, OmsOrderSettingDynamicSqlSupport.finishOvertime, OmsOrderSettingDynamicSqlSupport.commentOvertime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<OmsOrderSetting> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OmsOrderSettingResult")
    Optional<OmsOrderSetting> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OmsOrderSettingResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="flash_order_overtime", property="flashOrderOvertime", jdbcType=JdbcType.INTEGER),
        @Result(column="normal_order_overtime", property="normalOrderOvertime", jdbcType=JdbcType.INTEGER),
        @Result(column="confirm_overtime", property="confirmOvertime", jdbcType=JdbcType.INTEGER),
        @Result(column="finish_overtime", property="finishOvertime", jdbcType=JdbcType.INTEGER),
        @Result(column="comment_overtime", property="commentOvertime", jdbcType=JdbcType.INTEGER)
    })
    List<OmsOrderSetting> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, OmsOrderSettingDynamicSqlSupport.omsOrderSetting, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, OmsOrderSettingDynamicSqlSupport.omsOrderSetting, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(OmsOrderSettingDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(OmsOrderSetting record) {
        return MyBatis3Utils.insert(this::insert, record, OmsOrderSettingDynamicSqlSupport.omsOrderSetting, c ->
            c.map(OmsOrderSettingDynamicSqlSupport.flashOrderOvertime).toProperty("flashOrderOvertime")
            .map(OmsOrderSettingDynamicSqlSupport.normalOrderOvertime).toProperty("normalOrderOvertime")
            .map(OmsOrderSettingDynamicSqlSupport.confirmOvertime).toProperty("confirmOvertime")
            .map(OmsOrderSettingDynamicSqlSupport.finishOvertime).toProperty("finishOvertime")
            .map(OmsOrderSettingDynamicSqlSupport.commentOvertime).toProperty("commentOvertime")
        );
    }

    default int insertSelective(OmsOrderSetting record) {
        return MyBatis3Utils.insert(this::insert, record, OmsOrderSettingDynamicSqlSupport.omsOrderSetting, c ->
            c.map(OmsOrderSettingDynamicSqlSupport.flashOrderOvertime).toPropertyWhenPresent("flashOrderOvertime", record::getFlashOrderOvertime)
            .map(OmsOrderSettingDynamicSqlSupport.normalOrderOvertime).toPropertyWhenPresent("normalOrderOvertime", record::getNormalOrderOvertime)
            .map(OmsOrderSettingDynamicSqlSupport.confirmOvertime).toPropertyWhenPresent("confirmOvertime", record::getConfirmOvertime)
            .map(OmsOrderSettingDynamicSqlSupport.finishOvertime).toPropertyWhenPresent("finishOvertime", record::getFinishOvertime)
            .map(OmsOrderSettingDynamicSqlSupport.commentOvertime).toPropertyWhenPresent("commentOvertime", record::getCommentOvertime)
        );
    }

    default Optional<OmsOrderSetting> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, OmsOrderSettingDynamicSqlSupport.omsOrderSetting, completer);
    }

    default List<OmsOrderSetting> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, OmsOrderSettingDynamicSqlSupport.omsOrderSetting, completer);
    }

    default List<OmsOrderSetting> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, OmsOrderSettingDynamicSqlSupport.omsOrderSetting, completer);
    }

    default Optional<OmsOrderSetting> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(OmsOrderSettingDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, OmsOrderSettingDynamicSqlSupport.omsOrderSetting, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(OmsOrderSetting record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsOrderSettingDynamicSqlSupport.flashOrderOvertime).equalTo(record::getFlashOrderOvertime)
                .set(OmsOrderSettingDynamicSqlSupport.normalOrderOvertime).equalTo(record::getNormalOrderOvertime)
                .set(OmsOrderSettingDynamicSqlSupport.confirmOvertime).equalTo(record::getConfirmOvertime)
                .set(OmsOrderSettingDynamicSqlSupport.finishOvertime).equalTo(record::getFinishOvertime)
                .set(OmsOrderSettingDynamicSqlSupport.commentOvertime).equalTo(record::getCommentOvertime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(OmsOrderSetting record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsOrderSettingDynamicSqlSupport.flashOrderOvertime).equalToWhenPresent(record::getFlashOrderOvertime)
                .set(OmsOrderSettingDynamicSqlSupport.normalOrderOvertime).equalToWhenPresent(record::getNormalOrderOvertime)
                .set(OmsOrderSettingDynamicSqlSupport.confirmOvertime).equalToWhenPresent(record::getConfirmOvertime)
                .set(OmsOrderSettingDynamicSqlSupport.finishOvertime).equalToWhenPresent(record::getFinishOvertime)
                .set(OmsOrderSettingDynamicSqlSupport.commentOvertime).equalToWhenPresent(record::getCommentOvertime);
    }

    default int updateByPrimaryKey(OmsOrderSetting record) {
        return update(c ->
            c.set(OmsOrderSettingDynamicSqlSupport.flashOrderOvertime).equalTo(record::getFlashOrderOvertime)
            .set(OmsOrderSettingDynamicSqlSupport.normalOrderOvertime).equalTo(record::getNormalOrderOvertime)
            .set(OmsOrderSettingDynamicSqlSupport.confirmOvertime).equalTo(record::getConfirmOvertime)
            .set(OmsOrderSettingDynamicSqlSupport.finishOvertime).equalTo(record::getFinishOvertime)
            .set(OmsOrderSettingDynamicSqlSupport.commentOvertime).equalTo(record::getCommentOvertime)
            .where(OmsOrderSettingDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(OmsOrderSetting record) {
        return update(c ->
            c.set(OmsOrderSettingDynamicSqlSupport.flashOrderOvertime).equalToWhenPresent(record::getFlashOrderOvertime)
            .set(OmsOrderSettingDynamicSqlSupport.normalOrderOvertime).equalToWhenPresent(record::getNormalOrderOvertime)
            .set(OmsOrderSettingDynamicSqlSupport.confirmOvertime).equalToWhenPresent(record::getConfirmOvertime)
            .set(OmsOrderSettingDynamicSqlSupport.finishOvertime).equalToWhenPresent(record::getFinishOvertime)
            .set(OmsOrderSettingDynamicSqlSupport.commentOvertime).equalToWhenPresent(record::getCommentOvertime)
            .where(OmsOrderSettingDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}