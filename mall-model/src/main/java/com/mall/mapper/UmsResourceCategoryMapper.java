/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsResourceCategory;

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
public interface UmsResourceCategoryMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsResourceCategoryDynamicSqlSupport.id, UmsResourceCategoryDynamicSqlSupport.name, UmsResourceCategoryDynamicSqlSupport.sort, UmsResourceCategoryDynamicSqlSupport.createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsResourceCategory> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsResourceCategoryResult")
    Optional<UmsResourceCategory> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsResourceCategoryResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UmsResourceCategory> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsResourceCategoryDynamicSqlSupport.umsResourceCategory, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsResourceCategoryDynamicSqlSupport.umsResourceCategory, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsResourceCategoryDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsResourceCategory record) {
        return MyBatis3Utils.insert(this::insert, record, UmsResourceCategoryDynamicSqlSupport.umsResourceCategory, c ->
            c.map(UmsResourceCategoryDynamicSqlSupport.name).toProperty("name")
            .map(UmsResourceCategoryDynamicSqlSupport.sort).toProperty("sort")
            .map(UmsResourceCategoryDynamicSqlSupport.createTime).toProperty("createTime")
        );
    }

    default int insertSelective(UmsResourceCategory record) {
        return MyBatis3Utils.insert(this::insert, record, UmsResourceCategoryDynamicSqlSupport.umsResourceCategory, c ->
            c.map(UmsResourceCategoryDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(UmsResourceCategoryDynamicSqlSupport.sort).toPropertyWhenPresent("sort", record::getSort)
            .map(UmsResourceCategoryDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<UmsResourceCategory> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsResourceCategoryDynamicSqlSupport.umsResourceCategory, completer);
    }

    default List<UmsResourceCategory> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsResourceCategoryDynamicSqlSupport.umsResourceCategory, completer);
    }

    default List<UmsResourceCategory> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsResourceCategoryDynamicSqlSupport.umsResourceCategory, completer);
    }

    default Optional<UmsResourceCategory> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsResourceCategoryDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsResourceCategoryDynamicSqlSupport.umsResourceCategory, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsResourceCategory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsResourceCategoryDynamicSqlSupport.name).equalTo(record::getName)
                .set(UmsResourceCategoryDynamicSqlSupport.sort).equalTo(record::getSort)
                .set(UmsResourceCategoryDynamicSqlSupport.createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsResourceCategory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsResourceCategoryDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(UmsResourceCategoryDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
                .set(UmsResourceCategoryDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(UmsResourceCategory record) {
        return update(c ->
            c.set(UmsResourceCategoryDynamicSqlSupport.name).equalTo(record::getName)
            .set(UmsResourceCategoryDynamicSqlSupport.sort).equalTo(record::getSort)
            .set(UmsResourceCategoryDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .where(UmsResourceCategoryDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsResourceCategory record) {
        return update(c ->
            c.set(UmsResourceCategoryDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(UmsResourceCategoryDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
            .set(UmsResourceCategoryDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .where(UmsResourceCategoryDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}