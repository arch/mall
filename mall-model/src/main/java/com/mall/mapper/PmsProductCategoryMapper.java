/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.PmsProductCategoryDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsProductCategory;
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
public interface PmsProductCategoryMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, parentId, name, level, productCount, productUnit, navStatus, showStatus, sort, icon, keywords, description);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsProductCategory> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsProductCategoryResult")
    Optional<PmsProductCategory> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsProductCategoryResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="product_count", property="productCount", jdbcType=JdbcType.INTEGER),
        @Result(column="product_unit", property="productUnit", jdbcType=JdbcType.VARCHAR),
        @Result(column="nav_status", property="navStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<PmsProductCategory> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, pmsProductCategory, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, pmsProductCategory, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(PmsProductCategory record) {
        return MyBatis3Utils.insert(this::insert, record, pmsProductCategory, c ->
            c.map(parentId).toProperty("parentId")
            .map(name).toProperty("name")
            .map(level).toProperty("level")
            .map(productCount).toProperty("productCount")
            .map(productUnit).toProperty("productUnit")
            .map(navStatus).toProperty("navStatus")
            .map(showStatus).toProperty("showStatus")
            .map(sort).toProperty("sort")
            .map(icon).toProperty("icon")
            .map(keywords).toProperty("keywords")
            .map(description).toProperty("description")
        );
    }

    default int insertSelective(PmsProductCategory record) {
        return MyBatis3Utils.insert(this::insert, record, pmsProductCategory, c ->
            c.map(parentId).toPropertyWhenPresent("parentId", record::getParentId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(level).toPropertyWhenPresent("level", record::getLevel)
            .map(productCount).toPropertyWhenPresent("productCount", record::getProductCount)
            .map(productUnit).toPropertyWhenPresent("productUnit", record::getProductUnit)
            .map(navStatus).toPropertyWhenPresent("navStatus", record::getNavStatus)
            .map(showStatus).toPropertyWhenPresent("showStatus", record::getShowStatus)
            .map(sort).toPropertyWhenPresent("sort", record::getSort)
            .map(icon).toPropertyWhenPresent("icon", record::getIcon)
            .map(keywords).toPropertyWhenPresent("keywords", record::getKeywords)
            .map(description).toPropertyWhenPresent("description", record::getDescription)
        );
    }

    default Optional<PmsProductCategory> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, pmsProductCategory, completer);
    }

    default List<PmsProductCategory> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, pmsProductCategory, completer);
    }

    default List<PmsProductCategory> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, pmsProductCategory, completer);
    }

    default Optional<PmsProductCategory> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, pmsProductCategory, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsProductCategory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(parentId).equalTo(record::getParentId)
                .set(name).equalTo(record::getName)
                .set(level).equalTo(record::getLevel)
                .set(productCount).equalTo(record::getProductCount)
                .set(productUnit).equalTo(record::getProductUnit)
                .set(navStatus).equalTo(record::getNavStatus)
                .set(showStatus).equalTo(record::getShowStatus)
                .set(sort).equalTo(record::getSort)
                .set(icon).equalTo(record::getIcon)
                .set(keywords).equalTo(record::getKeywords)
                .set(description).equalTo(record::getDescription);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsProductCategory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(parentId).equalToWhenPresent(record::getParentId)
                .set(name).equalToWhenPresent(record::getName)
                .set(level).equalToWhenPresent(record::getLevel)
                .set(productCount).equalToWhenPresent(record::getProductCount)
                .set(productUnit).equalToWhenPresent(record::getProductUnit)
                .set(navStatus).equalToWhenPresent(record::getNavStatus)
                .set(showStatus).equalToWhenPresent(record::getShowStatus)
                .set(sort).equalToWhenPresent(record::getSort)
                .set(icon).equalToWhenPresent(record::getIcon)
                .set(keywords).equalToWhenPresent(record::getKeywords)
                .set(description).equalToWhenPresent(record::getDescription);
    }

    default int updateByPrimaryKey(PmsProductCategory record) {
        return update(c ->
            c.set(parentId).equalTo(record::getParentId)
            .set(name).equalTo(record::getName)
            .set(level).equalTo(record::getLevel)
            .set(productCount).equalTo(record::getProductCount)
            .set(productUnit).equalTo(record::getProductUnit)
            .set(navStatus).equalTo(record::getNavStatus)
            .set(showStatus).equalTo(record::getShowStatus)
            .set(sort).equalTo(record::getSort)
            .set(icon).equalTo(record::getIcon)
            .set(keywords).equalTo(record::getKeywords)
            .set(description).equalTo(record::getDescription)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsProductCategory record) {
        return update(c ->
            c.set(parentId).equalToWhenPresent(record::getParentId)
            .set(name).equalToWhenPresent(record::getName)
            .set(level).equalToWhenPresent(record::getLevel)
            .set(productCount).equalToWhenPresent(record::getProductCount)
            .set(productUnit).equalToWhenPresent(record::getProductUnit)
            .set(navStatus).equalToWhenPresent(record::getNavStatus)
            .set(showStatus).equalToWhenPresent(record::getShowStatus)
            .set(sort).equalToWhenPresent(record::getSort)
            .set(icon).equalToWhenPresent(record::getIcon)
            .set(keywords).equalToWhenPresent(record::getKeywords)
            .set(description).equalToWhenPresent(record::getDescription)
            .where(id, isEqualTo(record::getId))
        );
    }
}