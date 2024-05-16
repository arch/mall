/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.UmsResourceDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsResource;
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
public interface UmsResourceMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, name, url, description, categoryId, createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsResource> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsResourceResult")
    Optional<UmsResource> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsResourceResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UmsResource> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, umsResource, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, umsResource, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(UmsResource record) {
        return MyBatis3Utils.insert(this::insert, record, umsResource, c ->
            c.map(name).toProperty("name")
            .map(url).toProperty("url")
            .map(description).toProperty("description")
            .map(categoryId).toProperty("categoryId")
            .map(createTime).toProperty("createTime")
        );
    }

    default int insertSelective(UmsResource record) {
        return MyBatis3Utils.insert(this::insert, record, umsResource, c ->
            c.map(name).toPropertyWhenPresent("name", record::getName)
            .map(url).toPropertyWhenPresent("url", record::getUrl)
            .map(description).toPropertyWhenPresent("description", record::getDescription)
            .map(categoryId).toPropertyWhenPresent("categoryId", record::getCategoryId)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<UmsResource> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, umsResource, completer);
    }

    default List<UmsResource> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, umsResource, completer);
    }

    default List<UmsResource> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, umsResource, completer);
    }

    default Optional<UmsResource> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, umsResource, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsResource record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalTo(record::getName)
                .set(url).equalTo(record::getUrl)
                .set(description).equalTo(record::getDescription)
                .set(categoryId).equalTo(record::getCategoryId)
                .set(createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsResource record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalToWhenPresent(record::getName)
                .set(url).equalToWhenPresent(record::getUrl)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(categoryId).equalToWhenPresent(record::getCategoryId)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(UmsResource record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(url).equalTo(record::getUrl)
            .set(description).equalTo(record::getDescription)
            .set(categoryId).equalTo(record::getCategoryId)
            .set(createTime).equalTo(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsResource record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(url).equalToWhenPresent(record::getUrl)
            .set(description).equalToWhenPresent(record::getDescription)
            .set(categoryId).equalToWhenPresent(record::getCategoryId)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}