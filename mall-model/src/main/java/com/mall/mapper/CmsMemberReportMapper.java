/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.CmsMemberReportDynamicSqlSupport.*;

import com.mall.model.CmsMemberReport;
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
public interface CmsMemberReportMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, reportType, reportMemberName, reportObject, reportStatus, handleStatus, note, createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CmsMemberReport> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CmsMemberReportResult")
    Optional<CmsMemberReport> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CmsMemberReportResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="report_type", property="reportType", jdbcType=JdbcType.INTEGER),
        @Result(column="report_member_name", property="reportMemberName", jdbcType=JdbcType.VARCHAR),
        @Result(column="report_object", property="reportObject", jdbcType=JdbcType.VARCHAR),
        @Result(column="report_status", property="reportStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="handle_status", property="handleStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CmsMemberReport> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, cmsMemberReport, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, cmsMemberReport, completer);
    }

    default int insert(CmsMemberReport record) {
        return MyBatis3Utils.insert(this::insert, record, cmsMemberReport, c ->
            c.map(reportType).toProperty("reportType")
            .map(reportMemberName).toProperty("reportMemberName")
            .map(reportObject).toProperty("reportObject")
            .map(reportStatus).toProperty("reportStatus")
            .map(handleStatus).toProperty("handleStatus")
            .map(note).toProperty("note")
            .map(createTime).toProperty("createTime")
        );
    }

    default int insertSelective(CmsMemberReport record) {
        return MyBatis3Utils.insert(this::insert, record, cmsMemberReport, c ->
            c.map(reportType).toPropertyWhenPresent("reportType", record::getReportType)
            .map(reportMemberName).toPropertyWhenPresent("reportMemberName", record::getReportMemberName)
            .map(reportObject).toPropertyWhenPresent("reportObject", record::getReportObject)
            .map(reportStatus).toPropertyWhenPresent("reportStatus", record::getReportStatus)
            .map(handleStatus).toPropertyWhenPresent("handleStatus", record::getHandleStatus)
            .map(note).toPropertyWhenPresent("note", record::getNote)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<CmsMemberReport> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, cmsMemberReport, completer);
    }

    default List<CmsMemberReport> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, cmsMemberReport, completer);
    }

    default List<CmsMemberReport> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, cmsMemberReport, completer);
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, cmsMemberReport, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(CmsMemberReport record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(reportType).equalTo(record::getReportType)
                .set(reportMemberName).equalTo(record::getReportMemberName)
                .set(reportObject).equalTo(record::getReportObject)
                .set(reportStatus).equalTo(record::getReportStatus)
                .set(handleStatus).equalTo(record::getHandleStatus)
                .set(note).equalTo(record::getNote)
                .set(createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(CmsMemberReport record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(reportType).equalToWhenPresent(record::getReportType)
                .set(reportMemberName).equalToWhenPresent(record::getReportMemberName)
                .set(reportObject).equalToWhenPresent(record::getReportObject)
                .set(reportStatus).equalToWhenPresent(record::getReportStatus)
                .set(handleStatus).equalToWhenPresent(record::getHandleStatus)
                .set(note).equalToWhenPresent(record::getNote)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }
}