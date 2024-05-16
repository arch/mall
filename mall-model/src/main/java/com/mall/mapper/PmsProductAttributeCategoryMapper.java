/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.PmsProductAttributeCategoryDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsProductAttributeCategory;
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
public interface PmsProductAttributeCategoryMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, name, attributeCount, paramCount);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsProductAttributeCategory> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsProductAttributeCategoryResult")
    Optional<PmsProductAttributeCategory> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsProductAttributeCategoryResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="attribute_count", property="attributeCount", jdbcType=JdbcType.INTEGER),
        @Result(column="param_count", property="paramCount", jdbcType=JdbcType.INTEGER)
    })
    List<PmsProductAttributeCategory> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, pmsProductAttributeCategory, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, pmsProductAttributeCategory, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(PmsProductAttributeCategory record) {
        return MyBatis3Utils.insert(this::insert, record, pmsProductAttributeCategory, c ->
            c.map(name).toProperty("name")
            .map(attributeCount).toProperty("attributeCount")
            .map(paramCount).toProperty("paramCount")
        );
    }

    default int insertSelective(PmsProductAttributeCategory record) {
        return MyBatis3Utils.insert(this::insert, record, pmsProductAttributeCategory, c ->
            c.map(name).toPropertyWhenPresent("name", record::getName)
            .map(attributeCount).toPropertyWhenPresent("attributeCount", record::getAttributeCount)
            .map(paramCount).toPropertyWhenPresent("paramCount", record::getParamCount)
        );
    }

    default Optional<PmsProductAttributeCategory> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, pmsProductAttributeCategory, completer);
    }

    default List<PmsProductAttributeCategory> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, pmsProductAttributeCategory, completer);
    }

    default List<PmsProductAttributeCategory> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, pmsProductAttributeCategory, completer);
    }

    default Optional<PmsProductAttributeCategory> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, pmsProductAttributeCategory, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsProductAttributeCategory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalTo(record::getName)
                .set(attributeCount).equalTo(record::getAttributeCount)
                .set(paramCount).equalTo(record::getParamCount);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsProductAttributeCategory record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalToWhenPresent(record::getName)
                .set(attributeCount).equalToWhenPresent(record::getAttributeCount)
                .set(paramCount).equalToWhenPresent(record::getParamCount);
    }

    default int updateByPrimaryKey(PmsProductAttributeCategory record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(attributeCount).equalTo(record::getAttributeCount)
            .set(paramCount).equalTo(record::getParamCount)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsProductAttributeCategory record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(attributeCount).equalToWhenPresent(record::getAttributeCount)
            .set(paramCount).equalToWhenPresent(record::getParamCount)
            .where(id, isEqualTo(record::getId))
        );
    }
}