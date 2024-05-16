/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.CmsTopicCategory;

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
public interface CmsTopicCategoryMapper {
    BasicColumn[] selectList = BasicColumn.columnList(CmsTopicCategoryDynamicSqlSupport.id, CmsTopicCategoryDynamicSqlSupport.name, CmsTopicCategoryDynamicSqlSupport.icon, CmsTopicCategoryDynamicSqlSupport.subjectCount, CmsTopicCategoryDynamicSqlSupport.showStatus, CmsTopicCategoryDynamicSqlSupport.sort);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CmsTopicCategory> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CmsTopicCategoryResult")
    Optional<CmsTopicCategory> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CmsTopicCategoryResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="subject_count", property="subjectCount", jdbcType=JdbcType.INTEGER),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<CmsTopicCategory> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, CmsTopicCategoryDynamicSqlSupport.cmsTopicCategory, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, CmsTopicCategoryDynamicSqlSupport.cmsTopicCategory, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(CmsTopicCategoryDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(CmsTopicCategory record) {
        return MyBatis3Utils.insert(this::insert, record, CmsTopicCategoryDynamicSqlSupport.cmsTopicCategory, c ->
            c.map(CmsTopicCategoryDynamicSqlSupport.name).toProperty("name")
            .map(CmsTopicCategoryDynamicSqlSupport.icon).toProperty("icon")
            .map(CmsTopicCategoryDynamicSqlSupport.subjectCount).toProperty("subjectCount")
            .map(CmsTopicCategoryDynamicSqlSupport.showStatus).toProperty("showStatus")
            .map(CmsTopicCategoryDynamicSqlSupport.sort).toProperty("sort")
        );
    }

    default int insertSelective(CmsTopicCategory record) {
        return MyBatis3Utils.insert(this::insert, record, CmsTopicCategoryDynamicSqlSupport.cmsTopicCategory, c ->
            c.map(CmsTopicCategoryDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(CmsTopicCategoryDynamicSqlSupport.icon).toPropertyWhenPresent("icon", record::getIcon)
            .map(CmsTopicCategoryDynamicSqlSupport.subjectCount).toPropertyWhenPresent("subjectCount", record::getSubjectCount)
            .map(CmsTopicCategoryDynamicSqlSupport.showStatus).toPropertyWhenPresent("showStatus", record::getShowStatus)
            .map(CmsTopicCategoryDynamicSqlSupport.sort).toPropertyWhenPresent("sort", record::getSort)
        );
    }

    default Optional<CmsTopicCategory> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, CmsTopicCategoryDynamicSqlSupport.cmsTopicCategory, completer);
    }

    default List<CmsTopicCategory> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, CmsTopicCategoryDynamicSqlSupport.cmsTopicCategory, completer);
    }

    default List<CmsTopicCategory> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, CmsTopicCategoryDynamicSqlSupport.cmsTopicCategory, completer);
    }

    default Optional<CmsTopicCategory> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(CmsTopicCategoryDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, CmsTopicCategoryDynamicSqlSupport.cmsTopicCategory, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(CmsTopicCategory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsTopicCategoryDynamicSqlSupport.name).equalTo(record::getName)
                .set(CmsTopicCategoryDynamicSqlSupport.icon).equalTo(record::getIcon)
                .set(CmsTopicCategoryDynamicSqlSupport.subjectCount).equalTo(record::getSubjectCount)
                .set(CmsTopicCategoryDynamicSqlSupport.showStatus).equalTo(record::getShowStatus)
                .set(CmsTopicCategoryDynamicSqlSupport.sort).equalTo(record::getSort);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(CmsTopicCategory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsTopicCategoryDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(CmsTopicCategoryDynamicSqlSupport.icon).equalToWhenPresent(record::getIcon)
                .set(CmsTopicCategoryDynamicSqlSupport.subjectCount).equalToWhenPresent(record::getSubjectCount)
                .set(CmsTopicCategoryDynamicSqlSupport.showStatus).equalToWhenPresent(record::getShowStatus)
                .set(CmsTopicCategoryDynamicSqlSupport.sort).equalToWhenPresent(record::getSort);
    }

    default int updateByPrimaryKey(CmsTopicCategory record) {
        return update(c ->
            c.set(CmsTopicCategoryDynamicSqlSupport.name).equalTo(record::getName)
            .set(CmsTopicCategoryDynamicSqlSupport.icon).equalTo(record::getIcon)
            .set(CmsTopicCategoryDynamicSqlSupport.subjectCount).equalTo(record::getSubjectCount)
            .set(CmsTopicCategoryDynamicSqlSupport.showStatus).equalTo(record::getShowStatus)
            .set(CmsTopicCategoryDynamicSqlSupport.sort).equalTo(record::getSort)
            .where(CmsTopicCategoryDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(CmsTopicCategory record) {
        return update(c ->
            c.set(CmsTopicCategoryDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(CmsTopicCategoryDynamicSqlSupport.icon).equalToWhenPresent(record::getIcon)
            .set(CmsTopicCategoryDynamicSqlSupport.subjectCount).equalToWhenPresent(record::getSubjectCount)
            .set(CmsTopicCategoryDynamicSqlSupport.showStatus).equalToWhenPresent(record::getShowStatus)
            .set(CmsTopicCategoryDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
            .where(CmsTopicCategoryDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}