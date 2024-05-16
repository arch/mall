/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.CmsSubjectProductRelationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.CmsSubjectProductRelation;
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
public interface CmsSubjectProductRelationMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, subjectId, productId);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CmsSubjectProductRelation> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CmsSubjectProductRelationResult")
    Optional<CmsSubjectProductRelation> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CmsSubjectProductRelationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="subject_id", property="subjectId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT)
    })
    List<CmsSubjectProductRelation> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, cmsSubjectProductRelation, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, cmsSubjectProductRelation, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(CmsSubjectProductRelation record) {
        return MyBatis3Utils.insert(this::insert, record, cmsSubjectProductRelation, c ->
            c.map(subjectId).toProperty("subjectId")
            .map(productId).toProperty("productId")
        );
    }

    default int insertSelective(CmsSubjectProductRelation record) {
        return MyBatis3Utils.insert(this::insert, record, cmsSubjectProductRelation, c ->
            c.map(subjectId).toPropertyWhenPresent("subjectId", record::getSubjectId)
            .map(productId).toPropertyWhenPresent("productId", record::getProductId)
        );
    }

    default Optional<CmsSubjectProductRelation> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, cmsSubjectProductRelation, completer);
    }

    default List<CmsSubjectProductRelation> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, cmsSubjectProductRelation, completer);
    }

    default List<CmsSubjectProductRelation> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, cmsSubjectProductRelation, completer);
    }

    default Optional<CmsSubjectProductRelation> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, cmsSubjectProductRelation, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(CmsSubjectProductRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(subjectId).equalTo(record::getSubjectId)
                .set(productId).equalTo(record::getProductId);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(CmsSubjectProductRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(subjectId).equalToWhenPresent(record::getSubjectId)
                .set(productId).equalToWhenPresent(record::getProductId);
    }

    default int updateByPrimaryKey(CmsSubjectProductRelation record) {
        return update(c ->
            c.set(subjectId).equalTo(record::getSubjectId)
            .set(productId).equalTo(record::getProductId)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(CmsSubjectProductRelation record) {
        return update(c ->
            c.set(subjectId).equalToWhenPresent(record::getSubjectId)
            .set(productId).equalToWhenPresent(record::getProductId)
            .where(id, isEqualTo(record::getId))
        );
    }
}