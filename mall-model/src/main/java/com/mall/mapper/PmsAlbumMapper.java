/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.PmsAlbumDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsAlbum;
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
public interface PmsAlbumMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, name, coverPic, picCount, sort, description);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsAlbum> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsAlbumResult")
    Optional<PmsAlbum> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsAlbumResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="cover_pic", property="coverPic", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic_count", property="picCount", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    List<PmsAlbum> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, pmsAlbum, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, pmsAlbum, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(PmsAlbum record) {
        return MyBatis3Utils.insert(this::insert, record, pmsAlbum, c ->
            c.map(name).toProperty("name")
            .map(coverPic).toProperty("coverPic")
            .map(picCount).toProperty("picCount")
            .map(sort).toProperty("sort")
            .map(description).toProperty("description")
        );
    }

    default int insertSelective(PmsAlbum record) {
        return MyBatis3Utils.insert(this::insert, record, pmsAlbum, c ->
            c.map(name).toPropertyWhenPresent("name", record::getName)
            .map(coverPic).toPropertyWhenPresent("coverPic", record::getCoverPic)
            .map(picCount).toPropertyWhenPresent("picCount", record::getPicCount)
            .map(sort).toPropertyWhenPresent("sort", record::getSort)
            .map(description).toPropertyWhenPresent("description", record::getDescription)
        );
    }

    default Optional<PmsAlbum> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, pmsAlbum, completer);
    }

    default List<PmsAlbum> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, pmsAlbum, completer);
    }

    default List<PmsAlbum> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, pmsAlbum, completer);
    }

    default Optional<PmsAlbum> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, pmsAlbum, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsAlbum record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalTo(record::getName)
                .set(coverPic).equalTo(record::getCoverPic)
                .set(picCount).equalTo(record::getPicCount)
                .set(sort).equalTo(record::getSort)
                .set(description).equalTo(record::getDescription);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsAlbum record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalToWhenPresent(record::getName)
                .set(coverPic).equalToWhenPresent(record::getCoverPic)
                .set(picCount).equalToWhenPresent(record::getPicCount)
                .set(sort).equalToWhenPresent(record::getSort)
                .set(description).equalToWhenPresent(record::getDescription);
    }

    default int updateByPrimaryKey(PmsAlbum record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(coverPic).equalTo(record::getCoverPic)
            .set(picCount).equalTo(record::getPicCount)
            .set(sort).equalTo(record::getSort)
            .set(description).equalTo(record::getDescription)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsAlbum record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(coverPic).equalToWhenPresent(record::getCoverPic)
            .set(picCount).equalToWhenPresent(record::getPicCount)
            .set(sort).equalToWhenPresent(record::getSort)
            .set(description).equalToWhenPresent(record::getDescription)
            .where(id, isEqualTo(record::getId))
        );
    }
}