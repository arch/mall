/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.PmsBrandDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsBrand;
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
public interface PmsBrandMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, name, firstLetter, sort, factoryStatus, showStatus, productCount, productCommentCount, logo, bigPic, brandStory);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsBrand> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsBrandResult")
    Optional<PmsBrand> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsBrandResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_letter", property="firstLetter", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="factory_status", property="factoryStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="product_count", property="productCount", jdbcType=JdbcType.INTEGER),
        @Result(column="product_comment_count", property="productCommentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
        @Result(column="big_pic", property="bigPic", jdbcType=JdbcType.VARCHAR),
        @Result(column="brand_story", property="brandStory", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<PmsBrand> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, pmsBrand, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, pmsBrand, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(PmsBrand record) {
        return MyBatis3Utils.insert(this::insert, record, pmsBrand, c ->
            c.map(name).toProperty("name")
            .map(firstLetter).toProperty("firstLetter")
            .map(sort).toProperty("sort")
            .map(factoryStatus).toProperty("factoryStatus")
            .map(showStatus).toProperty("showStatus")
            .map(productCount).toProperty("productCount")
            .map(productCommentCount).toProperty("productCommentCount")
            .map(logo).toProperty("logo")
            .map(bigPic).toProperty("bigPic")
            .map(brandStory).toProperty("brandStory")
        );
    }

    default int insertSelective(PmsBrand record) {
        return MyBatis3Utils.insert(this::insert, record, pmsBrand, c ->
            c.map(name).toPropertyWhenPresent("name", record::getName)
            .map(firstLetter).toPropertyWhenPresent("firstLetter", record::getFirstLetter)
            .map(sort).toPropertyWhenPresent("sort", record::getSort)
            .map(factoryStatus).toPropertyWhenPresent("factoryStatus", record::getFactoryStatus)
            .map(showStatus).toPropertyWhenPresent("showStatus", record::getShowStatus)
            .map(productCount).toPropertyWhenPresent("productCount", record::getProductCount)
            .map(productCommentCount).toPropertyWhenPresent("productCommentCount", record::getProductCommentCount)
            .map(logo).toPropertyWhenPresent("logo", record::getLogo)
            .map(bigPic).toPropertyWhenPresent("bigPic", record::getBigPic)
            .map(brandStory).toPropertyWhenPresent("brandStory", record::getBrandStory)
        );
    }

    default Optional<PmsBrand> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, pmsBrand, completer);
    }

    default List<PmsBrand> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, pmsBrand, completer);
    }

    default List<PmsBrand> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, pmsBrand, completer);
    }

    default Optional<PmsBrand> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, pmsBrand, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsBrand record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalTo(record::getName)
                .set(firstLetter).equalTo(record::getFirstLetter)
                .set(sort).equalTo(record::getSort)
                .set(factoryStatus).equalTo(record::getFactoryStatus)
                .set(showStatus).equalTo(record::getShowStatus)
                .set(productCount).equalTo(record::getProductCount)
                .set(productCommentCount).equalTo(record::getProductCommentCount)
                .set(logo).equalTo(record::getLogo)
                .set(bigPic).equalTo(record::getBigPic)
                .set(brandStory).equalTo(record::getBrandStory);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsBrand record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalToWhenPresent(record::getName)
                .set(firstLetter).equalToWhenPresent(record::getFirstLetter)
                .set(sort).equalToWhenPresent(record::getSort)
                .set(factoryStatus).equalToWhenPresent(record::getFactoryStatus)
                .set(showStatus).equalToWhenPresent(record::getShowStatus)
                .set(productCount).equalToWhenPresent(record::getProductCount)
                .set(productCommentCount).equalToWhenPresent(record::getProductCommentCount)
                .set(logo).equalToWhenPresent(record::getLogo)
                .set(bigPic).equalToWhenPresent(record::getBigPic)
                .set(brandStory).equalToWhenPresent(record::getBrandStory);
    }

    default int updateByPrimaryKey(PmsBrand record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(firstLetter).equalTo(record::getFirstLetter)
            .set(sort).equalTo(record::getSort)
            .set(factoryStatus).equalTo(record::getFactoryStatus)
            .set(showStatus).equalTo(record::getShowStatus)
            .set(productCount).equalTo(record::getProductCount)
            .set(productCommentCount).equalTo(record::getProductCommentCount)
            .set(logo).equalTo(record::getLogo)
            .set(bigPic).equalTo(record::getBigPic)
            .set(brandStory).equalTo(record::getBrandStory)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsBrand record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(firstLetter).equalToWhenPresent(record::getFirstLetter)
            .set(sort).equalToWhenPresent(record::getSort)
            .set(factoryStatus).equalToWhenPresent(record::getFactoryStatus)
            .set(showStatus).equalToWhenPresent(record::getShowStatus)
            .set(productCount).equalToWhenPresent(record::getProductCount)
            .set(productCommentCount).equalToWhenPresent(record::getProductCommentCount)
            .set(logo).equalToWhenPresent(record::getLogo)
            .set(bigPic).equalToWhenPresent(record::getBigPic)
            .set(brandStory).equalToWhenPresent(record::getBrandStory)
            .where(id, isEqualTo(record::getId))
        );
    }
}