/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.CmsTopicCommentDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.CmsTopicComment;
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
public interface CmsTopicCommentMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, memberNickName, topicId, memberIcon, content, showStatus, createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CmsTopicComment> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CmsTopicCommentResult")
    Optional<CmsTopicComment> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CmsTopicCommentResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="member_nick_name", property="memberNickName", jdbcType=JdbcType.VARCHAR),
        @Result(column="topic_id", property="topicId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_icon", property="memberIcon", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CmsTopicComment> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, cmsTopicComment, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, cmsTopicComment, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(CmsTopicComment record) {
        return MyBatis3Utils.insert(this::insert, record, cmsTopicComment, c ->
            c.map(memberNickName).toProperty("memberNickName")
            .map(topicId).toProperty("topicId")
            .map(memberIcon).toProperty("memberIcon")
            .map(content).toProperty("content")
            .map(showStatus).toProperty("showStatus")
            .map(createTime).toProperty("createTime")
        );
    }

    default int insertSelective(CmsTopicComment record) {
        return MyBatis3Utils.insert(this::insert, record, cmsTopicComment, c ->
            c.map(memberNickName).toPropertyWhenPresent("memberNickName", record::getMemberNickName)
            .map(topicId).toPropertyWhenPresent("topicId", record::getTopicId)
            .map(memberIcon).toPropertyWhenPresent("memberIcon", record::getMemberIcon)
            .map(content).toPropertyWhenPresent("content", record::getContent)
            .map(showStatus).toPropertyWhenPresent("showStatus", record::getShowStatus)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<CmsTopicComment> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, cmsTopicComment, completer);
    }

    default List<CmsTopicComment> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, cmsTopicComment, completer);
    }

    default List<CmsTopicComment> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, cmsTopicComment, completer);
    }

    default Optional<CmsTopicComment> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, cmsTopicComment, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(CmsTopicComment record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(memberNickName).equalTo(record::getMemberNickName)
                .set(topicId).equalTo(record::getTopicId)
                .set(memberIcon).equalTo(record::getMemberIcon)
                .set(content).equalTo(record::getContent)
                .set(showStatus).equalTo(record::getShowStatus)
                .set(createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(CmsTopicComment record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(memberNickName).equalToWhenPresent(record::getMemberNickName)
                .set(topicId).equalToWhenPresent(record::getTopicId)
                .set(memberIcon).equalToWhenPresent(record::getMemberIcon)
                .set(content).equalToWhenPresent(record::getContent)
                .set(showStatus).equalToWhenPresent(record::getShowStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(CmsTopicComment record) {
        return update(c ->
            c.set(memberNickName).equalTo(record::getMemberNickName)
            .set(topicId).equalTo(record::getTopicId)
            .set(memberIcon).equalTo(record::getMemberIcon)
            .set(content).equalTo(record::getContent)
            .set(showStatus).equalTo(record::getShowStatus)
            .set(createTime).equalTo(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(CmsTopicComment record) {
        return update(c ->
            c.set(memberNickName).equalToWhenPresent(record::getMemberNickName)
            .set(topicId).equalToWhenPresent(record::getTopicId)
            .set(memberIcon).equalToWhenPresent(record::getMemberIcon)
            .set(content).equalToWhenPresent(record::getContent)
            .set(showStatus).equalToWhenPresent(record::getShowStatus)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}