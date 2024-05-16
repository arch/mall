/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.CmsHelpCategory;

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
public interface CmsHelpCategoryMapper {
    BasicColumn[] selectList = BasicColumn.columnList(CmsHelpCategoryDynamicSqlSupport.id, CmsHelpCategoryDynamicSqlSupport.name, CmsHelpCategoryDynamicSqlSupport.icon, CmsHelpCategoryDynamicSqlSupport.helpCount, CmsHelpCategoryDynamicSqlSupport.showStatus, CmsHelpCategoryDynamicSqlSupport.sort);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CmsHelpCategory> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CmsHelpCategoryResult")
    Optional<CmsHelpCategory> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CmsHelpCategoryResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="help_count", property="helpCount", jdbcType=JdbcType.INTEGER),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<CmsHelpCategory> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, CmsHelpCategoryDynamicSqlSupport.cmsHelpCategory, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, CmsHelpCategoryDynamicSqlSupport.cmsHelpCategory, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(CmsHelpCategoryDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(CmsHelpCategory record) {
        return MyBatis3Utils.insert(this::insert, record, CmsHelpCategoryDynamicSqlSupport.cmsHelpCategory, c ->
            c.map(CmsHelpCategoryDynamicSqlSupport.name).toProperty("name")
            .map(CmsHelpCategoryDynamicSqlSupport.icon).toProperty("icon")
            .map(CmsHelpCategoryDynamicSqlSupport.helpCount).toProperty("helpCount")
            .map(CmsHelpCategoryDynamicSqlSupport.showStatus).toProperty("showStatus")
            .map(CmsHelpCategoryDynamicSqlSupport.sort).toProperty("sort")
        );
    }

    default int insertSelective(CmsHelpCategory record) {
        return MyBatis3Utils.insert(this::insert, record, CmsHelpCategoryDynamicSqlSupport.cmsHelpCategory, c ->
            c.map(CmsHelpCategoryDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(CmsHelpCategoryDynamicSqlSupport.icon).toPropertyWhenPresent("icon", record::getIcon)
            .map(CmsHelpCategoryDynamicSqlSupport.helpCount).toPropertyWhenPresent("helpCount", record::getHelpCount)
            .map(CmsHelpCategoryDynamicSqlSupport.showStatus).toPropertyWhenPresent("showStatus", record::getShowStatus)
            .map(CmsHelpCategoryDynamicSqlSupport.sort).toPropertyWhenPresent("sort", record::getSort)
        );
    }

    default Optional<CmsHelpCategory> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, CmsHelpCategoryDynamicSqlSupport.cmsHelpCategory, completer);
    }

    default List<CmsHelpCategory> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, CmsHelpCategoryDynamicSqlSupport.cmsHelpCategory, completer);
    }

    default List<CmsHelpCategory> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, CmsHelpCategoryDynamicSqlSupport.cmsHelpCategory, completer);
    }

    default Optional<CmsHelpCategory> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(CmsHelpCategoryDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, CmsHelpCategoryDynamicSqlSupport.cmsHelpCategory, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(CmsHelpCategory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsHelpCategoryDynamicSqlSupport.name).equalTo(record::getName)
                .set(CmsHelpCategoryDynamicSqlSupport.icon).equalTo(record::getIcon)
                .set(CmsHelpCategoryDynamicSqlSupport.helpCount).equalTo(record::getHelpCount)
                .set(CmsHelpCategoryDynamicSqlSupport.showStatus).equalTo(record::getShowStatus)
                .set(CmsHelpCategoryDynamicSqlSupport.sort).equalTo(record::getSort);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(CmsHelpCategory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsHelpCategoryDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(CmsHelpCategoryDynamicSqlSupport.icon).equalToWhenPresent(record::getIcon)
                .set(CmsHelpCategoryDynamicSqlSupport.helpCount).equalToWhenPresent(record::getHelpCount)
                .set(CmsHelpCategoryDynamicSqlSupport.showStatus).equalToWhenPresent(record::getShowStatus)
                .set(CmsHelpCategoryDynamicSqlSupport.sort).equalToWhenPresent(record::getSort);
    }

    default int updateByPrimaryKey(CmsHelpCategory record) {
        return update(c ->
            c.set(CmsHelpCategoryDynamicSqlSupport.name).equalTo(record::getName)
            .set(CmsHelpCategoryDynamicSqlSupport.icon).equalTo(record::getIcon)
            .set(CmsHelpCategoryDynamicSqlSupport.helpCount).equalTo(record::getHelpCount)
            .set(CmsHelpCategoryDynamicSqlSupport.showStatus).equalTo(record::getShowStatus)
            .set(CmsHelpCategoryDynamicSqlSupport.sort).equalTo(record::getSort)
            .where(CmsHelpCategoryDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(CmsHelpCategory record) {
        return update(c ->
            c.set(CmsHelpCategoryDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(CmsHelpCategoryDynamicSqlSupport.icon).equalToWhenPresent(record::getIcon)
            .set(CmsHelpCategoryDynamicSqlSupport.helpCount).equalToWhenPresent(record::getHelpCount)
            .set(CmsHelpCategoryDynamicSqlSupport.showStatus).equalToWhenPresent(record::getShowStatus)
            .set(CmsHelpCategoryDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
            .where(CmsHelpCategoryDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}