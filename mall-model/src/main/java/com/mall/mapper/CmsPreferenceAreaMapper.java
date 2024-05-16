/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.CmsPreferenceArea;

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
public interface CmsPreferenceAreaMapper {
    BasicColumn[] selectList = BasicColumn.columnList(CmsPreferenceAreaDynamicSqlSupport.id, CmsPreferenceAreaDynamicSqlSupport.name, CmsPreferenceAreaDynamicSqlSupport.subTitle, CmsPreferenceAreaDynamicSqlSupport.sort, CmsPreferenceAreaDynamicSqlSupport.showStatus, CmsPreferenceAreaDynamicSqlSupport.pic);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CmsPreferenceArea> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CmsPreferenceAreaResult")
    Optional<CmsPreferenceArea> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CmsPreferenceAreaResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sub_title", property="subTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARBINARY)
    })
    List<CmsPreferenceArea> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, CmsPreferenceAreaDynamicSqlSupport.cmsPreferenceArea, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, CmsPreferenceAreaDynamicSqlSupport.cmsPreferenceArea, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(CmsPreferenceAreaDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(CmsPreferenceArea record) {
        return MyBatis3Utils.insert(this::insert, record, CmsPreferenceAreaDynamicSqlSupport.cmsPreferenceArea, c ->
            c.map(CmsPreferenceAreaDynamicSqlSupport.name).toProperty("name")
            .map(CmsPreferenceAreaDynamicSqlSupport.subTitle).toProperty("subTitle")
            .map(CmsPreferenceAreaDynamicSqlSupport.sort).toProperty("sort")
            .map(CmsPreferenceAreaDynamicSqlSupport.showStatus).toProperty("showStatus")
            .map(CmsPreferenceAreaDynamicSqlSupport.pic).toProperty("pic")
        );
    }

    default int insertSelective(CmsPreferenceArea record) {
        return MyBatis3Utils.insert(this::insert, record, CmsPreferenceAreaDynamicSqlSupport.cmsPreferenceArea, c ->
            c.map(CmsPreferenceAreaDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(CmsPreferenceAreaDynamicSqlSupport.subTitle).toPropertyWhenPresent("subTitle", record::getSubTitle)
            .map(CmsPreferenceAreaDynamicSqlSupport.sort).toPropertyWhenPresent("sort", record::getSort)
            .map(CmsPreferenceAreaDynamicSqlSupport.showStatus).toPropertyWhenPresent("showStatus", record::getShowStatus)
            .map(CmsPreferenceAreaDynamicSqlSupport.pic).toPropertyWhenPresent("pic", record::getPic)
        );
    }

    default Optional<CmsPreferenceArea> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, CmsPreferenceAreaDynamicSqlSupport.cmsPreferenceArea, completer);
    }

    default List<CmsPreferenceArea> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, CmsPreferenceAreaDynamicSqlSupport.cmsPreferenceArea, completer);
    }

    default List<CmsPreferenceArea> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, CmsPreferenceAreaDynamicSqlSupport.cmsPreferenceArea, completer);
    }

    default Optional<CmsPreferenceArea> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(CmsPreferenceAreaDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, CmsPreferenceAreaDynamicSqlSupport.cmsPreferenceArea, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(CmsPreferenceArea record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsPreferenceAreaDynamicSqlSupport.name).equalTo(record::getName)
                .set(CmsPreferenceAreaDynamicSqlSupport.subTitle).equalTo(record::getSubTitle)
                .set(CmsPreferenceAreaDynamicSqlSupport.sort).equalTo(record::getSort)
                .set(CmsPreferenceAreaDynamicSqlSupport.showStatus).equalTo(record::getShowStatus)
                .set(CmsPreferenceAreaDynamicSqlSupport.pic).equalTo(record::getPic);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(CmsPreferenceArea record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsPreferenceAreaDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(CmsPreferenceAreaDynamicSqlSupport.subTitle).equalToWhenPresent(record::getSubTitle)
                .set(CmsPreferenceAreaDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
                .set(CmsPreferenceAreaDynamicSqlSupport.showStatus).equalToWhenPresent(record::getShowStatus)
                .set(CmsPreferenceAreaDynamicSqlSupport.pic).equalToWhenPresent(record::getPic);
    }

    default int updateByPrimaryKey(CmsPreferenceArea record) {
        return update(c ->
            c.set(CmsPreferenceAreaDynamicSqlSupport.name).equalTo(record::getName)
            .set(CmsPreferenceAreaDynamicSqlSupport.subTitle).equalTo(record::getSubTitle)
            .set(CmsPreferenceAreaDynamicSqlSupport.sort).equalTo(record::getSort)
            .set(CmsPreferenceAreaDynamicSqlSupport.showStatus).equalTo(record::getShowStatus)
            .set(CmsPreferenceAreaDynamicSqlSupport.pic).equalTo(record::getPic)
            .where(CmsPreferenceAreaDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(CmsPreferenceArea record) {
        return update(c ->
            c.set(CmsPreferenceAreaDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(CmsPreferenceAreaDynamicSqlSupport.subTitle).equalToWhenPresent(record::getSubTitle)
            .set(CmsPreferenceAreaDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
            .set(CmsPreferenceAreaDynamicSqlSupport.showStatus).equalToWhenPresent(record::getShowStatus)
            .set(CmsPreferenceAreaDynamicSqlSupport.pic).equalToWhenPresent(record::getPic)
            .where(CmsPreferenceAreaDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}