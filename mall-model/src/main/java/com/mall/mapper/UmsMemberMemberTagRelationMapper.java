/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.UmsMemberMemberTagRelationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsMemberMemberTagRelation;
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
public interface UmsMemberMemberTagRelationMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, memberId, tagId);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsMemberMemberTagRelation> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsMemberMemberTagRelationResult")
    Optional<UmsMemberMemberTagRelation> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsMemberMemberTagRelationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.BIGINT),
        @Result(column="tag_id", property="tagId", jdbcType=JdbcType.BIGINT)
    })
    List<UmsMemberMemberTagRelation> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, umsMemberMemberTagRelation, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, umsMemberMemberTagRelation, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(UmsMemberMemberTagRelation record) {
        return MyBatis3Utils.insert(this::insert, record, umsMemberMemberTagRelation, c ->
            c.map(memberId).toProperty("memberId")
            .map(tagId).toProperty("tagId")
        );
    }

    default int insertSelective(UmsMemberMemberTagRelation record) {
        return MyBatis3Utils.insert(this::insert, record, umsMemberMemberTagRelation, c ->
            c.map(memberId).toPropertyWhenPresent("memberId", record::getMemberId)
            .map(tagId).toPropertyWhenPresent("tagId", record::getTagId)
        );
    }

    default Optional<UmsMemberMemberTagRelation> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, umsMemberMemberTagRelation, completer);
    }

    default List<UmsMemberMemberTagRelation> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, umsMemberMemberTagRelation, completer);
    }

    default List<UmsMemberMemberTagRelation> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, umsMemberMemberTagRelation, completer);
    }

    default Optional<UmsMemberMemberTagRelation> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, umsMemberMemberTagRelation, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsMemberMemberTagRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(memberId).equalTo(record::getMemberId)
                .set(tagId).equalTo(record::getTagId);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsMemberMemberTagRelation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(memberId).equalToWhenPresent(record::getMemberId)
                .set(tagId).equalToWhenPresent(record::getTagId);
    }

    default int updateByPrimaryKey(UmsMemberMemberTagRelation record) {
        return update(c ->
            c.set(memberId).equalTo(record::getMemberId)
            .set(tagId).equalTo(record::getTagId)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsMemberMemberTagRelation record) {
        return update(c ->
            c.set(memberId).equalToWhenPresent(record::getMemberId)
            .set(tagId).equalToWhenPresent(record::getTagId)
            .where(id, isEqualTo(record::getId))
        );
    }
}