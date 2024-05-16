/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsMemberProductCategoryRelation;

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
public interface UmsMemberProductCategoryRelationMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsMemberProductCategoryRelationDynamicSqlSupport.id, UmsMemberProductCategoryRelationDynamicSqlSupport.memberId, UmsMemberProductCategoryRelationDynamicSqlSupport.productCategoryId);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsMemberProductCategoryRelation> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsMemberProductCategoryRelationResult")
    Optional<UmsMemberProductCategoryRelation> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsMemberProductCategoryRelationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_category_id", property="productCategoryId", jdbcType=JdbcType.BIGINT)
    })
    List<UmsMemberProductCategoryRelation> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsMemberProductCategoryRelationDynamicSqlSupport.umsMemberProductCategoryRelation, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsMemberProductCategoryRelationDynamicSqlSupport.umsMemberProductCategoryRelation, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsMemberProductCategoryRelationDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsMemberProductCategoryRelation record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberProductCategoryRelationDynamicSqlSupport.umsMemberProductCategoryRelation, c ->
            c.map(UmsMemberProductCategoryRelationDynamicSqlSupport.memberId).toProperty("memberId")
            .map(UmsMemberProductCategoryRelationDynamicSqlSupport.productCategoryId).toProperty("productCategoryId")
        );
    }

    default int insertSelective(UmsMemberProductCategoryRelation record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberProductCategoryRelationDynamicSqlSupport.umsMemberProductCategoryRelation, c ->
            c.map(UmsMemberProductCategoryRelationDynamicSqlSupport.memberId).toPropertyWhenPresent("memberId", record::getMemberId)
            .map(UmsMemberProductCategoryRelationDynamicSqlSupport.productCategoryId).toPropertyWhenPresent("productCategoryId", record::getProductCategoryId)
        );
    }

    default Optional<UmsMemberProductCategoryRelation> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsMemberProductCategoryRelationDynamicSqlSupport.umsMemberProductCategoryRelation, completer);
    }

    default List<UmsMemberProductCategoryRelation> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsMemberProductCategoryRelationDynamicSqlSupport.umsMemberProductCategoryRelation, completer);
    }

    default List<UmsMemberProductCategoryRelation> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsMemberProductCategoryRelationDynamicSqlSupport.umsMemberProductCategoryRelation, completer);
    }

    default Optional<UmsMemberProductCategoryRelation> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsMemberProductCategoryRelationDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsMemberProductCategoryRelationDynamicSqlSupport.umsMemberProductCategoryRelation, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsMemberProductCategoryRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberProductCategoryRelationDynamicSqlSupport.memberId).equalTo(record::getMemberId)
                .set(UmsMemberProductCategoryRelationDynamicSqlSupport.productCategoryId).equalTo(record::getProductCategoryId);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsMemberProductCategoryRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberProductCategoryRelationDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
                .set(UmsMemberProductCategoryRelationDynamicSqlSupport.productCategoryId).equalToWhenPresent(record::getProductCategoryId);
    }

    default int updateByPrimaryKey(UmsMemberProductCategoryRelation record) {
        return update(c ->
            c.set(UmsMemberProductCategoryRelationDynamicSqlSupport.memberId).equalTo(record::getMemberId)
            .set(UmsMemberProductCategoryRelationDynamicSqlSupport.productCategoryId).equalTo(record::getProductCategoryId)
            .where(UmsMemberProductCategoryRelationDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsMemberProductCategoryRelation record) {
        return update(c ->
            c.set(UmsMemberProductCategoryRelationDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
            .set(UmsMemberProductCategoryRelationDynamicSqlSupport.productCategoryId).equalToWhenPresent(record::getProductCategoryId)
            .where(UmsMemberProductCategoryRelationDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}