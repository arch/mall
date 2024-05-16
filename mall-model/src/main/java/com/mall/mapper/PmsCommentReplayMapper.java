/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsCommentReplay;

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
public interface PmsCommentReplayMapper {
    BasicColumn[] selectList = BasicColumn.columnList(PmsCommentReplayDynamicSqlSupport.id, PmsCommentReplayDynamicSqlSupport.commentId, PmsCommentReplayDynamicSqlSupport.memberNickName, PmsCommentReplayDynamicSqlSupport.memberIcon, PmsCommentReplayDynamicSqlSupport.content, PmsCommentReplayDynamicSqlSupport.type, PmsCommentReplayDynamicSqlSupport.createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsCommentReplay> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsCommentReplayResult")
    Optional<PmsCommentReplay> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsCommentReplayResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_nick_name", property="memberNickName", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_icon", property="memberIcon", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<PmsCommentReplay> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, PmsCommentReplayDynamicSqlSupport.pmsCommentReplay, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, PmsCommentReplayDynamicSqlSupport.pmsCommentReplay, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(PmsCommentReplayDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(PmsCommentReplay record) {
        return MyBatis3Utils.insert(this::insert, record, PmsCommentReplayDynamicSqlSupport.pmsCommentReplay, c ->
            c.map(PmsCommentReplayDynamicSqlSupport.commentId).toProperty("commentId")
            .map(PmsCommentReplayDynamicSqlSupport.memberNickName).toProperty("memberNickName")
            .map(PmsCommentReplayDynamicSqlSupport.memberIcon).toProperty("memberIcon")
            .map(PmsCommentReplayDynamicSqlSupport.content).toProperty("content")
            .map(PmsCommentReplayDynamicSqlSupport.type).toProperty("type")
            .map(PmsCommentReplayDynamicSqlSupport.createTime).toProperty("createTime")
        );
    }

    default int insertSelective(PmsCommentReplay record) {
        return MyBatis3Utils.insert(this::insert, record, PmsCommentReplayDynamicSqlSupport.pmsCommentReplay, c ->
            c.map(PmsCommentReplayDynamicSqlSupport.commentId).toPropertyWhenPresent("commentId", record::getCommentId)
            .map(PmsCommentReplayDynamicSqlSupport.memberNickName).toPropertyWhenPresent("memberNickName", record::getMemberNickName)
            .map(PmsCommentReplayDynamicSqlSupport.memberIcon).toPropertyWhenPresent("memberIcon", record::getMemberIcon)
            .map(PmsCommentReplayDynamicSqlSupport.content).toPropertyWhenPresent("content", record::getContent)
            .map(PmsCommentReplayDynamicSqlSupport.type).toPropertyWhenPresent("type", record::getType)
            .map(PmsCommentReplayDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<PmsCommentReplay> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, PmsCommentReplayDynamicSqlSupport.pmsCommentReplay, completer);
    }

    default List<PmsCommentReplay> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, PmsCommentReplayDynamicSqlSupport.pmsCommentReplay, completer);
    }

    default List<PmsCommentReplay> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, PmsCommentReplayDynamicSqlSupport.pmsCommentReplay, completer);
    }

    default Optional<PmsCommentReplay> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(PmsCommentReplayDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, PmsCommentReplayDynamicSqlSupport.pmsCommentReplay, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsCommentReplay record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsCommentReplayDynamicSqlSupport.commentId).equalTo(record::getCommentId)
                .set(PmsCommentReplayDynamicSqlSupport.memberNickName).equalTo(record::getMemberNickName)
                .set(PmsCommentReplayDynamicSqlSupport.memberIcon).equalTo(record::getMemberIcon)
                .set(PmsCommentReplayDynamicSqlSupport.content).equalTo(record::getContent)
                .set(PmsCommentReplayDynamicSqlSupport.type).equalTo(record::getType)
                .set(PmsCommentReplayDynamicSqlSupport.createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsCommentReplay record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsCommentReplayDynamicSqlSupport.commentId).equalToWhenPresent(record::getCommentId)
                .set(PmsCommentReplayDynamicSqlSupport.memberNickName).equalToWhenPresent(record::getMemberNickName)
                .set(PmsCommentReplayDynamicSqlSupport.memberIcon).equalToWhenPresent(record::getMemberIcon)
                .set(PmsCommentReplayDynamicSqlSupport.content).equalToWhenPresent(record::getContent)
                .set(PmsCommentReplayDynamicSqlSupport.type).equalToWhenPresent(record::getType)
                .set(PmsCommentReplayDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(PmsCommentReplay record) {
        return update(c ->
            c.set(PmsCommentReplayDynamicSqlSupport.commentId).equalTo(record::getCommentId)
            .set(PmsCommentReplayDynamicSqlSupport.memberNickName).equalTo(record::getMemberNickName)
            .set(PmsCommentReplayDynamicSqlSupport.memberIcon).equalTo(record::getMemberIcon)
            .set(PmsCommentReplayDynamicSqlSupport.content).equalTo(record::getContent)
            .set(PmsCommentReplayDynamicSqlSupport.type).equalTo(record::getType)
            .set(PmsCommentReplayDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .where(PmsCommentReplayDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsCommentReplay record) {
        return update(c ->
            c.set(PmsCommentReplayDynamicSqlSupport.commentId).equalToWhenPresent(record::getCommentId)
            .set(PmsCommentReplayDynamicSqlSupport.memberNickName).equalToWhenPresent(record::getMemberNickName)
            .set(PmsCommentReplayDynamicSqlSupport.memberIcon).equalToWhenPresent(record::getMemberIcon)
            .set(PmsCommentReplayDynamicSqlSupport.content).equalToWhenPresent(record::getContent)
            .set(PmsCommentReplayDynamicSqlSupport.type).equalToWhenPresent(record::getType)
            .set(PmsCommentReplayDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .where(PmsCommentReplayDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}