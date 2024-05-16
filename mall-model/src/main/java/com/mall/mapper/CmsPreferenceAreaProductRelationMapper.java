/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.CmsPreferenceAreaProductRelation;

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
public interface CmsPreferenceAreaProductRelationMapper {
    BasicColumn[] selectList = BasicColumn.columnList(CmsPreferenceAreaProductRelationDynamicSqlSupport.id, CmsPreferenceAreaProductRelationDynamicSqlSupport.preferenceAreaId, CmsPreferenceAreaProductRelationDynamicSqlSupport.productId);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CmsPreferenceAreaProductRelation> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CmsPreferenceAreaProductRelationResult")
    Optional<CmsPreferenceAreaProductRelation> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CmsPreferenceAreaProductRelationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="preference_area_id", property="preferenceAreaId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT)
    })
    List<CmsPreferenceAreaProductRelation> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, CmsPreferenceAreaProductRelationDynamicSqlSupport.cmsPreferenceAreaProductRelation, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, CmsPreferenceAreaProductRelationDynamicSqlSupport.cmsPreferenceAreaProductRelation, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(CmsPreferenceAreaProductRelationDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(CmsPreferenceAreaProductRelation record) {
        return MyBatis3Utils.insert(this::insert, record, CmsPreferenceAreaProductRelationDynamicSqlSupport.cmsPreferenceAreaProductRelation, c ->
            c.map(CmsPreferenceAreaProductRelationDynamicSqlSupport.preferenceAreaId).toProperty("preferenceAreaId")
            .map(CmsPreferenceAreaProductRelationDynamicSqlSupport.productId).toProperty("productId")
        );
    }

    default int insertSelective(CmsPreferenceAreaProductRelation record) {
        return MyBatis3Utils.insert(this::insert, record, CmsPreferenceAreaProductRelationDynamicSqlSupport.cmsPreferenceAreaProductRelation, c ->
            c.map(CmsPreferenceAreaProductRelationDynamicSqlSupport.preferenceAreaId).toPropertyWhenPresent("preferenceAreaId", record::getPreferenceAreaId)
            .map(CmsPreferenceAreaProductRelationDynamicSqlSupport.productId).toPropertyWhenPresent("productId", record::getProductId)
        );
    }

    default Optional<CmsPreferenceAreaProductRelation> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, CmsPreferenceAreaProductRelationDynamicSqlSupport.cmsPreferenceAreaProductRelation, completer);
    }

    default List<CmsPreferenceAreaProductRelation> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, CmsPreferenceAreaProductRelationDynamicSqlSupport.cmsPreferenceAreaProductRelation, completer);
    }

    default List<CmsPreferenceAreaProductRelation> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, CmsPreferenceAreaProductRelationDynamicSqlSupport.cmsPreferenceAreaProductRelation, completer);
    }

    default Optional<CmsPreferenceAreaProductRelation> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(CmsPreferenceAreaProductRelationDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, CmsPreferenceAreaProductRelationDynamicSqlSupport.cmsPreferenceAreaProductRelation, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(CmsPreferenceAreaProductRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsPreferenceAreaProductRelationDynamicSqlSupport.preferenceAreaId).equalTo(record::getPreferenceAreaId)
                .set(CmsPreferenceAreaProductRelationDynamicSqlSupport.productId).equalTo(record::getProductId);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(CmsPreferenceAreaProductRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsPreferenceAreaProductRelationDynamicSqlSupport.preferenceAreaId).equalToWhenPresent(record::getPreferenceAreaId)
                .set(CmsPreferenceAreaProductRelationDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId);
    }

    default int updateByPrimaryKey(CmsPreferenceAreaProductRelation record) {
        return update(c ->
            c.set(CmsPreferenceAreaProductRelationDynamicSqlSupport.preferenceAreaId).equalTo(record::getPreferenceAreaId)
            .set(CmsPreferenceAreaProductRelationDynamicSqlSupport.productId).equalTo(record::getProductId)
            .where(CmsPreferenceAreaProductRelationDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(CmsPreferenceAreaProductRelation record) {
        return update(c ->
            c.set(CmsPreferenceAreaProductRelationDynamicSqlSupport.preferenceAreaId).equalToWhenPresent(record::getPreferenceAreaId)
            .set(CmsPreferenceAreaProductRelationDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
            .where(CmsPreferenceAreaProductRelationDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}