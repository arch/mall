/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsProductCategoryAttributeRelation;

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
public interface PmsProductCategoryAttributeRelationMapper {
    BasicColumn[] selectList = BasicColumn.columnList(PmsProductCategoryAttributeRelationDynamicSqlSupport.id, PmsProductCategoryAttributeRelationDynamicSqlSupport.categoryId, PmsProductCategoryAttributeRelationDynamicSqlSupport.attributeId);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsProductCategoryAttributeRelation> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsProductCategoryAttributeRelationResult")
    Optional<PmsProductCategoryAttributeRelation> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsProductCategoryAttributeRelationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="attribute_id", property="attributeId", jdbcType=JdbcType.BIGINT)
    })
    List<PmsProductCategoryAttributeRelation> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, PmsProductCategoryAttributeRelationDynamicSqlSupport.pmsProductCategoryAttributeRelation, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, PmsProductCategoryAttributeRelationDynamicSqlSupport.pmsProductCategoryAttributeRelation, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(PmsProductCategoryAttributeRelationDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(PmsProductCategoryAttributeRelation record) {
        return MyBatis3Utils.insert(this::insert, record, PmsProductCategoryAttributeRelationDynamicSqlSupport.pmsProductCategoryAttributeRelation, c ->
            c.map(PmsProductCategoryAttributeRelationDynamicSqlSupport.categoryId).toProperty("categoryId")
            .map(PmsProductCategoryAttributeRelationDynamicSqlSupport.attributeId).toProperty("attributeId")
        );
    }

    default int insertSelective(PmsProductCategoryAttributeRelation record) {
        return MyBatis3Utils.insert(this::insert, record, PmsProductCategoryAttributeRelationDynamicSqlSupport.pmsProductCategoryAttributeRelation, c ->
            c.map(PmsProductCategoryAttributeRelationDynamicSqlSupport.categoryId).toPropertyWhenPresent("categoryId", record::getCategoryId)
            .map(PmsProductCategoryAttributeRelationDynamicSqlSupport.attributeId).toPropertyWhenPresent("attributeId", record::getAttributeId)
        );
    }

    default Optional<PmsProductCategoryAttributeRelation> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, PmsProductCategoryAttributeRelationDynamicSqlSupport.pmsProductCategoryAttributeRelation, completer);
    }

    default List<PmsProductCategoryAttributeRelation> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, PmsProductCategoryAttributeRelationDynamicSqlSupport.pmsProductCategoryAttributeRelation, completer);
    }

    default List<PmsProductCategoryAttributeRelation> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, PmsProductCategoryAttributeRelationDynamicSqlSupport.pmsProductCategoryAttributeRelation, completer);
    }

    default Optional<PmsProductCategoryAttributeRelation> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(PmsProductCategoryAttributeRelationDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, PmsProductCategoryAttributeRelationDynamicSqlSupport.pmsProductCategoryAttributeRelation, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsProductCategoryAttributeRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsProductCategoryAttributeRelationDynamicSqlSupport.categoryId).equalTo(record::getCategoryId)
                .set(PmsProductCategoryAttributeRelationDynamicSqlSupport.attributeId).equalTo(record::getAttributeId);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsProductCategoryAttributeRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsProductCategoryAttributeRelationDynamicSqlSupport.categoryId).equalToWhenPresent(record::getCategoryId)
                .set(PmsProductCategoryAttributeRelationDynamicSqlSupport.attributeId).equalToWhenPresent(record::getAttributeId);
    }

    default int updateByPrimaryKey(PmsProductCategoryAttributeRelation record) {
        return update(c ->
            c.set(PmsProductCategoryAttributeRelationDynamicSqlSupport.categoryId).equalTo(record::getCategoryId)
            .set(PmsProductCategoryAttributeRelationDynamicSqlSupport.attributeId).equalTo(record::getAttributeId)
            .where(PmsProductCategoryAttributeRelationDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsProductCategoryAttributeRelation record) {
        return update(c ->
            c.set(PmsProductCategoryAttributeRelationDynamicSqlSupport.categoryId).equalToWhenPresent(record::getCategoryId)
            .set(PmsProductCategoryAttributeRelationDynamicSqlSupport.attributeId).equalToWhenPresent(record::getAttributeId)
            .where(PmsProductCategoryAttributeRelationDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}