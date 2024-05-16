/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.CmsHelpDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.CmsHelp;
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
public interface CmsHelpMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, categoryId, icon, title, showStatus, readCount, createTime, content);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CmsHelp> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CmsHelpResult")
    Optional<CmsHelp> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CmsHelpResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="read_count", property="readCount", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<CmsHelp> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, cmsHelp, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, cmsHelp, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(CmsHelp record) {
        return MyBatis3Utils.insert(this::insert, record, cmsHelp, c ->
            c.map(categoryId).toProperty("categoryId")
            .map(icon).toProperty("icon")
            .map(title).toProperty("title")
            .map(showStatus).toProperty("showStatus")
            .map(readCount).toProperty("readCount")
            .map(createTime).toProperty("createTime")
            .map(content).toProperty("content")
        );
    }

    default int insertSelective(CmsHelp record) {
        return MyBatis3Utils.insert(this::insert, record, cmsHelp, c ->
            c.map(categoryId).toPropertyWhenPresent("categoryId", record::getCategoryId)
            .map(icon).toPropertyWhenPresent("icon", record::getIcon)
            .map(title).toPropertyWhenPresent("title", record::getTitle)
            .map(showStatus).toPropertyWhenPresent("showStatus", record::getShowStatus)
            .map(readCount).toPropertyWhenPresent("readCount", record::getReadCount)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(content).toPropertyWhenPresent("content", record::getContent)
        );
    }

    default Optional<CmsHelp> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, cmsHelp, completer);
    }

    default List<CmsHelp> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, cmsHelp, completer);
    }

    default List<CmsHelp> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, cmsHelp, completer);
    }

    default Optional<CmsHelp> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, cmsHelp, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(CmsHelp record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(categoryId).equalTo(record::getCategoryId)
                .set(icon).equalTo(record::getIcon)
                .set(title).equalTo(record::getTitle)
                .set(showStatus).equalTo(record::getShowStatus)
                .set(readCount).equalTo(record::getReadCount)
                .set(createTime).equalTo(record::getCreateTime)
                .set(content).equalTo(record::getContent);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(CmsHelp record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(categoryId).equalToWhenPresent(record::getCategoryId)
                .set(icon).equalToWhenPresent(record::getIcon)
                .set(title).equalToWhenPresent(record::getTitle)
                .set(showStatus).equalToWhenPresent(record::getShowStatus)
                .set(readCount).equalToWhenPresent(record::getReadCount)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(content).equalToWhenPresent(record::getContent);
    }

    default int updateByPrimaryKey(CmsHelp record) {
        return update(c ->
            c.set(categoryId).equalTo(record::getCategoryId)
            .set(icon).equalTo(record::getIcon)
            .set(title).equalTo(record::getTitle)
            .set(showStatus).equalTo(record::getShowStatus)
            .set(readCount).equalTo(record::getReadCount)
            .set(createTime).equalTo(record::getCreateTime)
            .set(content).equalTo(record::getContent)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(CmsHelp record) {
        return update(c ->
            c.set(categoryId).equalToWhenPresent(record::getCategoryId)
            .set(icon).equalToWhenPresent(record::getIcon)
            .set(title).equalToWhenPresent(record::getTitle)
            .set(showStatus).equalToWhenPresent(record::getShowStatus)
            .set(readCount).equalToWhenPresent(record::getReadCount)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(content).equalToWhenPresent(record::getContent)
            .where(id, isEqualTo(record::getId))
        );
    }
}