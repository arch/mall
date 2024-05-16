/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.CmsSubjectCommentDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.CmsSubjectComment;
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
public interface CmsSubjectCommentMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, subjectId, memberNickName, memberIcon, content, showStatus, createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CmsSubjectComment> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CmsSubjectCommentResult")
    Optional<CmsSubjectComment> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CmsSubjectCommentResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="subject_id", property="subjectId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_nick_name", property="memberNickName", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_icon", property="memberIcon", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CmsSubjectComment> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, cmsSubjectComment, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, cmsSubjectComment, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(CmsSubjectComment record) {
        return MyBatis3Utils.insert(this::insert, record, cmsSubjectComment, c ->
            c.map(subjectId).toProperty("subjectId")
            .map(memberNickName).toProperty("memberNickName")
            .map(memberIcon).toProperty("memberIcon")
            .map(content).toProperty("content")
            .map(showStatus).toProperty("showStatus")
            .map(createTime).toProperty("createTime")
        );
    }

    default int insertSelective(CmsSubjectComment record) {
        return MyBatis3Utils.insert(this::insert, record, cmsSubjectComment, c ->
            c.map(subjectId).toPropertyWhenPresent("subjectId", record::getSubjectId)
            .map(memberNickName).toPropertyWhenPresent("memberNickName", record::getMemberNickName)
            .map(memberIcon).toPropertyWhenPresent("memberIcon", record::getMemberIcon)
            .map(content).toPropertyWhenPresent("content", record::getContent)
            .map(showStatus).toPropertyWhenPresent("showStatus", record::getShowStatus)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<CmsSubjectComment> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, cmsSubjectComment, completer);
    }

    default List<CmsSubjectComment> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, cmsSubjectComment, completer);
    }

    default List<CmsSubjectComment> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, cmsSubjectComment, completer);
    }

    default Optional<CmsSubjectComment> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, cmsSubjectComment, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(CmsSubjectComment record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(subjectId).equalTo(record::getSubjectId)
                .set(memberNickName).equalTo(record::getMemberNickName)
                .set(memberIcon).equalTo(record::getMemberIcon)
                .set(content).equalTo(record::getContent)
                .set(showStatus).equalTo(record::getShowStatus)
                .set(createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(CmsSubjectComment record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(subjectId).equalToWhenPresent(record::getSubjectId)
                .set(memberNickName).equalToWhenPresent(record::getMemberNickName)
                .set(memberIcon).equalToWhenPresent(record::getMemberIcon)
                .set(content).equalToWhenPresent(record::getContent)
                .set(showStatus).equalToWhenPresent(record::getShowStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(CmsSubjectComment record) {
        return update(c ->
            c.set(subjectId).equalTo(record::getSubjectId)
            .set(memberNickName).equalTo(record::getMemberNickName)
            .set(memberIcon).equalTo(record::getMemberIcon)
            .set(content).equalTo(record::getContent)
            .set(showStatus).equalTo(record::getShowStatus)
            .set(createTime).equalTo(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(CmsSubjectComment record) {
        return update(c ->
            c.set(subjectId).equalToWhenPresent(record::getSubjectId)
            .set(memberNickName).equalToWhenPresent(record::getMemberNickName)
            .set(memberIcon).equalToWhenPresent(record::getMemberIcon)
            .set(content).equalToWhenPresent(record::getContent)
            .set(showStatus).equalToWhenPresent(record::getShowStatus)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}