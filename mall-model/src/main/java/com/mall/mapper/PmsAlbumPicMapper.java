/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.PmsAlbumPicDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsAlbumPic;
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
public interface PmsAlbumPicMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, albumId, pic);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsAlbumPic> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsAlbumPicResult")
    Optional<PmsAlbumPic> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsAlbumPicResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="album_id", property="albumId", jdbcType=JdbcType.BIGINT),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR)
    })
    List<PmsAlbumPic> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, pmsAlbumPic, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, pmsAlbumPic, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(PmsAlbumPic record) {
        return MyBatis3Utils.insert(this::insert, record, pmsAlbumPic, c ->
            c.map(albumId).toProperty("albumId")
            .map(pic).toProperty("pic")
        );
    }

    default int insertSelective(PmsAlbumPic record) {
        return MyBatis3Utils.insert(this::insert, record, pmsAlbumPic, c ->
            c.map(albumId).toPropertyWhenPresent("albumId", record::getAlbumId)
            .map(pic).toPropertyWhenPresent("pic", record::getPic)
        );
    }

    default Optional<PmsAlbumPic> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, pmsAlbumPic, completer);
    }

    default List<PmsAlbumPic> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, pmsAlbumPic, completer);
    }

    default List<PmsAlbumPic> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, pmsAlbumPic, completer);
    }

    default Optional<PmsAlbumPic> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, pmsAlbumPic, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsAlbumPic record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(albumId).equalTo(record::getAlbumId)
                .set(pic).equalTo(record::getPic);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsAlbumPic record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(albumId).equalToWhenPresent(record::getAlbumId)
                .set(pic).equalToWhenPresent(record::getPic);
    }

    default int updateByPrimaryKey(PmsAlbumPic record) {
        return update(c ->
            c.set(albumId).equalTo(record::getAlbumId)
            .set(pic).equalTo(record::getPic)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsAlbumPic record) {
        return update(c ->
            c.set(albumId).equalToWhenPresent(record::getAlbumId)
            .set(pic).equalToWhenPresent(record::getPic)
            .where(id, isEqualTo(record::getId))
        );
    }
}