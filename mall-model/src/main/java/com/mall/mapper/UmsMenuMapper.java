/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsMenu;

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
public interface UmsMenuMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsMenuDynamicSqlSupport.id, UmsMenuDynamicSqlSupport.parentId, UmsMenuDynamicSqlSupport.title, UmsMenuDynamicSqlSupport.level, UmsMenuDynamicSqlSupport.sort, UmsMenuDynamicSqlSupport.name, UmsMenuDynamicSqlSupport.icon, UmsMenuDynamicSqlSupport.hidden, UmsMenuDynamicSqlSupport.createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsMenu> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsMenuResult")
    Optional<UmsMenu> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsMenuResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="hidden", property="hidden", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UmsMenu> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsMenuDynamicSqlSupport.umsMenu, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsMenuDynamicSqlSupport.umsMenu, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsMenuDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsMenu record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMenuDynamicSqlSupport.umsMenu, c ->
            c.map(UmsMenuDynamicSqlSupport.parentId).toProperty("parentId")
            .map(UmsMenuDynamicSqlSupport.title).toProperty("title")
            .map(UmsMenuDynamicSqlSupport.level).toProperty("level")
            .map(UmsMenuDynamicSqlSupport.sort).toProperty("sort")
            .map(UmsMenuDynamicSqlSupport.name).toProperty("name")
            .map(UmsMenuDynamicSqlSupport.icon).toProperty("icon")
            .map(UmsMenuDynamicSqlSupport.hidden).toProperty("hidden")
            .map(UmsMenuDynamicSqlSupport.createTime).toProperty("createTime")
        );
    }

    default int insertSelective(UmsMenu record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMenuDynamicSqlSupport.umsMenu, c ->
            c.map(UmsMenuDynamicSqlSupport.parentId).toPropertyWhenPresent("parentId", record::getParentId)
            .map(UmsMenuDynamicSqlSupport.title).toPropertyWhenPresent("title", record::getTitle)
            .map(UmsMenuDynamicSqlSupport.level).toPropertyWhenPresent("level", record::getLevel)
            .map(UmsMenuDynamicSqlSupport.sort).toPropertyWhenPresent("sort", record::getSort)
            .map(UmsMenuDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(UmsMenuDynamicSqlSupport.icon).toPropertyWhenPresent("icon", record::getIcon)
            .map(UmsMenuDynamicSqlSupport.hidden).toPropertyWhenPresent("hidden", record::getHidden)
            .map(UmsMenuDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<UmsMenu> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsMenuDynamicSqlSupport.umsMenu, completer);
    }

    default List<UmsMenu> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsMenuDynamicSqlSupport.umsMenu, completer);
    }

    default List<UmsMenu> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsMenuDynamicSqlSupport.umsMenu, completer);
    }

    default Optional<UmsMenu> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsMenuDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsMenuDynamicSqlSupport.umsMenu, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsMenu record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMenuDynamicSqlSupport.parentId).equalTo(record::getParentId)
                .set(UmsMenuDynamicSqlSupport.title).equalTo(record::getTitle)
                .set(UmsMenuDynamicSqlSupport.level).equalTo(record::getLevel)
                .set(UmsMenuDynamicSqlSupport.sort).equalTo(record::getSort)
                .set(UmsMenuDynamicSqlSupport.name).equalTo(record::getName)
                .set(UmsMenuDynamicSqlSupport.icon).equalTo(record::getIcon)
                .set(UmsMenuDynamicSqlSupport.hidden).equalTo(record::getHidden)
                .set(UmsMenuDynamicSqlSupport.createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsMenu record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMenuDynamicSqlSupport.parentId).equalToWhenPresent(record::getParentId)
                .set(UmsMenuDynamicSqlSupport.title).equalToWhenPresent(record::getTitle)
                .set(UmsMenuDynamicSqlSupport.level).equalToWhenPresent(record::getLevel)
                .set(UmsMenuDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
                .set(UmsMenuDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(UmsMenuDynamicSqlSupport.icon).equalToWhenPresent(record::getIcon)
                .set(UmsMenuDynamicSqlSupport.hidden).equalToWhenPresent(record::getHidden)
                .set(UmsMenuDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(UmsMenu record) {
        return update(c ->
            c.set(UmsMenuDynamicSqlSupport.parentId).equalTo(record::getParentId)
            .set(UmsMenuDynamicSqlSupport.title).equalTo(record::getTitle)
            .set(UmsMenuDynamicSqlSupport.level).equalTo(record::getLevel)
            .set(UmsMenuDynamicSqlSupport.sort).equalTo(record::getSort)
            .set(UmsMenuDynamicSqlSupport.name).equalTo(record::getName)
            .set(UmsMenuDynamicSqlSupport.icon).equalTo(record::getIcon)
            .set(UmsMenuDynamicSqlSupport.hidden).equalTo(record::getHidden)
            .set(UmsMenuDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .where(UmsMenuDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsMenu record) {
        return update(c ->
            c.set(UmsMenuDynamicSqlSupport.parentId).equalToWhenPresent(record::getParentId)
            .set(UmsMenuDynamicSqlSupport.title).equalToWhenPresent(record::getTitle)
            .set(UmsMenuDynamicSqlSupport.level).equalToWhenPresent(record::getLevel)
            .set(UmsMenuDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
            .set(UmsMenuDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(UmsMenuDynamicSqlSupport.icon).equalToWhenPresent(record::getIcon)
            .set(UmsMenuDynamicSqlSupport.hidden).equalToWhenPresent(record::getHidden)
            .set(UmsMenuDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .where(UmsMenuDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}