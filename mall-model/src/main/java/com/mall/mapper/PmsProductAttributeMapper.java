/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.PmsProductAttributeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsProductAttribute;
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
public interface PmsProductAttributeMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, attributeCategoryId, name, selectType, inputType, inputList, sort, filterType, searchType, relatedStatus, handAddStatus, type);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsProductAttribute> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsProductAttributeResult")
    Optional<PmsProductAttribute> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsProductAttributeResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="attribute_category_id", property="attributeCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="select_type", property="selectType", jdbcType=JdbcType.INTEGER),
        @Result(column="input_type", property="inputType", jdbcType=JdbcType.INTEGER),
        @Result(column="input_list", property="inputList", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="filter_type", property="filterType", jdbcType=JdbcType.INTEGER),
        @Result(column="search_type", property="searchType", jdbcType=JdbcType.INTEGER),
        @Result(column="related_status", property="relatedStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="hand_add_status", property="handAddStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    List<PmsProductAttribute> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, pmsProductAttribute, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, pmsProductAttribute, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(PmsProductAttribute record) {
        return MyBatis3Utils.insert(this::insert, record, pmsProductAttribute, c ->
            c.map(attributeCategoryId).toProperty("attributeCategoryId")
            .map(name).toProperty("name")
            .map(selectType).toProperty("selectType")
            .map(inputType).toProperty("inputType")
            .map(inputList).toProperty("inputList")
            .map(sort).toProperty("sort")
            .map(filterType).toProperty("filterType")
            .map(searchType).toProperty("searchType")
            .map(relatedStatus).toProperty("relatedStatus")
            .map(handAddStatus).toProperty("handAddStatus")
            .map(type).toProperty("type")
        );
    }

    default int insertSelective(PmsProductAttribute record) {
        return MyBatis3Utils.insert(this::insert, record, pmsProductAttribute, c ->
            c.map(attributeCategoryId).toPropertyWhenPresent("attributeCategoryId", record::getAttributeCategoryId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(selectType).toPropertyWhenPresent("selectType", record::getSelectType)
            .map(inputType).toPropertyWhenPresent("inputType", record::getInputType)
            .map(inputList).toPropertyWhenPresent("inputList", record::getInputList)
            .map(sort).toPropertyWhenPresent("sort", record::getSort)
            .map(filterType).toPropertyWhenPresent("filterType", record::getFilterType)
            .map(searchType).toPropertyWhenPresent("searchType", record::getSearchType)
            .map(relatedStatus).toPropertyWhenPresent("relatedStatus", record::getRelatedStatus)
            .map(handAddStatus).toPropertyWhenPresent("handAddStatus", record::getHandAddStatus)
            .map(type).toPropertyWhenPresent("type", record::getType)
        );
    }

    default Optional<PmsProductAttribute> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, pmsProductAttribute, completer);
    }

    default List<PmsProductAttribute> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, pmsProductAttribute, completer);
    }

    default List<PmsProductAttribute> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, pmsProductAttribute, completer);
    }

    default Optional<PmsProductAttribute> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, pmsProductAttribute, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsProductAttribute record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(attributeCategoryId).equalTo(record::getAttributeCategoryId)
                .set(name).equalTo(record::getName)
                .set(selectType).equalTo(record::getSelectType)
                .set(inputType).equalTo(record::getInputType)
                .set(inputList).equalTo(record::getInputList)
                .set(sort).equalTo(record::getSort)
                .set(filterType).equalTo(record::getFilterType)
                .set(searchType).equalTo(record::getSearchType)
                .set(relatedStatus).equalTo(record::getRelatedStatus)
                .set(handAddStatus).equalTo(record::getHandAddStatus)
                .set(type).equalTo(record::getType);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsProductAttribute record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(attributeCategoryId).equalToWhenPresent(record::getAttributeCategoryId)
                .set(name).equalToWhenPresent(record::getName)
                .set(selectType).equalToWhenPresent(record::getSelectType)
                .set(inputType).equalToWhenPresent(record::getInputType)
                .set(inputList).equalToWhenPresent(record::getInputList)
                .set(sort).equalToWhenPresent(record::getSort)
                .set(filterType).equalToWhenPresent(record::getFilterType)
                .set(searchType).equalToWhenPresent(record::getSearchType)
                .set(relatedStatus).equalToWhenPresent(record::getRelatedStatus)
                .set(handAddStatus).equalToWhenPresent(record::getHandAddStatus)
                .set(type).equalToWhenPresent(record::getType);
    }

    default int updateByPrimaryKey(PmsProductAttribute record) {
        return update(c ->
            c.set(attributeCategoryId).equalTo(record::getAttributeCategoryId)
            .set(name).equalTo(record::getName)
            .set(selectType).equalTo(record::getSelectType)
            .set(inputType).equalTo(record::getInputType)
            .set(inputList).equalTo(record::getInputList)
            .set(sort).equalTo(record::getSort)
            .set(filterType).equalTo(record::getFilterType)
            .set(searchType).equalTo(record::getSearchType)
            .set(relatedStatus).equalTo(record::getRelatedStatus)
            .set(handAddStatus).equalTo(record::getHandAddStatus)
            .set(type).equalTo(record::getType)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsProductAttribute record) {
        return update(c ->
            c.set(attributeCategoryId).equalToWhenPresent(record::getAttributeCategoryId)
            .set(name).equalToWhenPresent(record::getName)
            .set(selectType).equalToWhenPresent(record::getSelectType)
            .set(inputType).equalToWhenPresent(record::getInputType)
            .set(inputList).equalToWhenPresent(record::getInputList)
            .set(sort).equalToWhenPresent(record::getSort)
            .set(filterType).equalToWhenPresent(record::getFilterType)
            .set(searchType).equalToWhenPresent(record::getSearchType)
            .set(relatedStatus).equalToWhenPresent(record::getRelatedStatus)
            .set(handAddStatus).equalToWhenPresent(record::getHandAddStatus)
            .set(type).equalToWhenPresent(record::getType)
            .where(id, isEqualTo(record::getId))
        );
    }
}