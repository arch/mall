/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.CmsSubjectCategory;

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
public interface CmsSubjectCategoryMapper {
    BasicColumn[] selectList = BasicColumn.columnList(CmsSubjectCategoryDynamicSqlSupport.id, CmsSubjectCategoryDynamicSqlSupport.name, CmsSubjectCategoryDynamicSqlSupport.icon, CmsSubjectCategoryDynamicSqlSupport.subjectCount, CmsSubjectCategoryDynamicSqlSupport.showStatus, CmsSubjectCategoryDynamicSqlSupport.sort);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CmsSubjectCategory> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CmsSubjectCategoryResult")
    Optional<CmsSubjectCategory> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CmsSubjectCategoryResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="subject_count", property="subjectCount", jdbcType=JdbcType.INTEGER),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<CmsSubjectCategory> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, CmsSubjectCategoryDynamicSqlSupport.cmsSubjectCategory, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, CmsSubjectCategoryDynamicSqlSupport.cmsSubjectCategory, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(CmsSubjectCategoryDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(CmsSubjectCategory record) {
        return MyBatis3Utils.insert(this::insert, record, CmsSubjectCategoryDynamicSqlSupport.cmsSubjectCategory, c ->
            c.map(CmsSubjectCategoryDynamicSqlSupport.name).toProperty("name")
            .map(CmsSubjectCategoryDynamicSqlSupport.icon).toProperty("icon")
            .map(CmsSubjectCategoryDynamicSqlSupport.subjectCount).toProperty("subjectCount")
            .map(CmsSubjectCategoryDynamicSqlSupport.showStatus).toProperty("showStatus")
            .map(CmsSubjectCategoryDynamicSqlSupport.sort).toProperty("sort")
        );
    }

    default int insertSelective(CmsSubjectCategory record) {
        return MyBatis3Utils.insert(this::insert, record, CmsSubjectCategoryDynamicSqlSupport.cmsSubjectCategory, c ->
            c.map(CmsSubjectCategoryDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(CmsSubjectCategoryDynamicSqlSupport.icon).toPropertyWhenPresent("icon", record::getIcon)
            .map(CmsSubjectCategoryDynamicSqlSupport.subjectCount).toPropertyWhenPresent("subjectCount", record::getSubjectCount)
            .map(CmsSubjectCategoryDynamicSqlSupport.showStatus).toPropertyWhenPresent("showStatus", record::getShowStatus)
            .map(CmsSubjectCategoryDynamicSqlSupport.sort).toPropertyWhenPresent("sort", record::getSort)
        );
    }

    default Optional<CmsSubjectCategory> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, CmsSubjectCategoryDynamicSqlSupport.cmsSubjectCategory, completer);
    }

    default List<CmsSubjectCategory> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, CmsSubjectCategoryDynamicSqlSupport.cmsSubjectCategory, completer);
    }

    default List<CmsSubjectCategory> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, CmsSubjectCategoryDynamicSqlSupport.cmsSubjectCategory, completer);
    }

    default Optional<CmsSubjectCategory> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(CmsSubjectCategoryDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, CmsSubjectCategoryDynamicSqlSupport.cmsSubjectCategory, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(CmsSubjectCategory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsSubjectCategoryDynamicSqlSupport.name).equalTo(record::getName)
                .set(CmsSubjectCategoryDynamicSqlSupport.icon).equalTo(record::getIcon)
                .set(CmsSubjectCategoryDynamicSqlSupport.subjectCount).equalTo(record::getSubjectCount)
                .set(CmsSubjectCategoryDynamicSqlSupport.showStatus).equalTo(record::getShowStatus)
                .set(CmsSubjectCategoryDynamicSqlSupport.sort).equalTo(record::getSort);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(CmsSubjectCategory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsSubjectCategoryDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(CmsSubjectCategoryDynamicSqlSupport.icon).equalToWhenPresent(record::getIcon)
                .set(CmsSubjectCategoryDynamicSqlSupport.subjectCount).equalToWhenPresent(record::getSubjectCount)
                .set(CmsSubjectCategoryDynamicSqlSupport.showStatus).equalToWhenPresent(record::getShowStatus)
                .set(CmsSubjectCategoryDynamicSqlSupport.sort).equalToWhenPresent(record::getSort);
    }

    default int updateByPrimaryKey(CmsSubjectCategory record) {
        return update(c ->
            c.set(CmsSubjectCategoryDynamicSqlSupport.name).equalTo(record::getName)
            .set(CmsSubjectCategoryDynamicSqlSupport.icon).equalTo(record::getIcon)
            .set(CmsSubjectCategoryDynamicSqlSupport.subjectCount).equalTo(record::getSubjectCount)
            .set(CmsSubjectCategoryDynamicSqlSupport.showStatus).equalTo(record::getShowStatus)
            .set(CmsSubjectCategoryDynamicSqlSupport.sort).equalTo(record::getSort)
            .where(CmsSubjectCategoryDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(CmsSubjectCategory record) {
        return update(c ->
            c.set(CmsSubjectCategoryDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(CmsSubjectCategoryDynamicSqlSupport.icon).equalToWhenPresent(record::getIcon)
            .set(CmsSubjectCategoryDynamicSqlSupport.subjectCount).equalToWhenPresent(record::getSubjectCount)
            .set(CmsSubjectCategoryDynamicSqlSupport.showStatus).equalToWhenPresent(record::getShowStatus)
            .set(CmsSubjectCategoryDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
            .where(CmsSubjectCategoryDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}