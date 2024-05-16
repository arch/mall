/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsComment;

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
public interface PmsCommentMapper {
    BasicColumn[] selectList = BasicColumn.columnList(PmsCommentDynamicSqlSupport.id, PmsCommentDynamicSqlSupport.productId, PmsCommentDynamicSqlSupport.memberNickName, PmsCommentDynamicSqlSupport.productName, PmsCommentDynamicSqlSupport.star, PmsCommentDynamicSqlSupport.memberIp, PmsCommentDynamicSqlSupport.showStatus, PmsCommentDynamicSqlSupport.productAttribute, PmsCommentDynamicSqlSupport.collectCount, PmsCommentDynamicSqlSupport.readCount, PmsCommentDynamicSqlSupport.pics, PmsCommentDynamicSqlSupport.memberIcon, PmsCommentDynamicSqlSupport.replayCount, PmsCommentDynamicSqlSupport.createTime, PmsCommentDynamicSqlSupport.content);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsComment> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsCommentResult")
    Optional<PmsComment> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsCommentResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_nick_name", property="memberNickName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="star", property="star", jdbcType=JdbcType.INTEGER),
        @Result(column="member_ip", property="memberIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="product_attribute", property="productAttribute", jdbcType=JdbcType.VARCHAR),
        @Result(column="collect_count", property="collectCount", jdbcType=JdbcType.INTEGER),
        @Result(column="read_count", property="readCount", jdbcType=JdbcType.INTEGER),
        @Result(column="pics", property="pics", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_icon", property="memberIcon", jdbcType=JdbcType.VARCHAR),
        @Result(column="replay_count", property="replayCount", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<PmsComment> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, PmsCommentDynamicSqlSupport.pmsComment, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, PmsCommentDynamicSqlSupport.pmsComment, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(PmsCommentDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(PmsComment record) {
        return MyBatis3Utils.insert(this::insert, record, PmsCommentDynamicSqlSupport.pmsComment, c ->
            c.map(PmsCommentDynamicSqlSupport.productId).toProperty("productId")
            .map(PmsCommentDynamicSqlSupport.memberNickName).toProperty("memberNickName")
            .map(PmsCommentDynamicSqlSupport.productName).toProperty("productName")
            .map(PmsCommentDynamicSqlSupport.star).toProperty("star")
            .map(PmsCommentDynamicSqlSupport.memberIp).toProperty("memberIp")
            .map(PmsCommentDynamicSqlSupport.showStatus).toProperty("showStatus")
            .map(PmsCommentDynamicSqlSupport.productAttribute).toProperty("productAttribute")
            .map(PmsCommentDynamicSqlSupport.collectCount).toProperty("collectCount")
            .map(PmsCommentDynamicSqlSupport.readCount).toProperty("readCount")
            .map(PmsCommentDynamicSqlSupport.pics).toProperty("pics")
            .map(PmsCommentDynamicSqlSupport.memberIcon).toProperty("memberIcon")
            .map(PmsCommentDynamicSqlSupport.replayCount).toProperty("replayCount")
            .map(PmsCommentDynamicSqlSupport.createTime).toProperty("createTime")
            .map(PmsCommentDynamicSqlSupport.content).toProperty("content")
        );
    }

    default int insertSelective(PmsComment record) {
        return MyBatis3Utils.insert(this::insert, record, PmsCommentDynamicSqlSupport.pmsComment, c ->
            c.map(PmsCommentDynamicSqlSupport.productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(PmsCommentDynamicSqlSupport.memberNickName).toPropertyWhenPresent("memberNickName", record::getMemberNickName)
            .map(PmsCommentDynamicSqlSupport.productName).toPropertyWhenPresent("productName", record::getProductName)
            .map(PmsCommentDynamicSqlSupport.star).toPropertyWhenPresent("star", record::getStar)
            .map(PmsCommentDynamicSqlSupport.memberIp).toPropertyWhenPresent("memberIp", record::getMemberIp)
            .map(PmsCommentDynamicSqlSupport.showStatus).toPropertyWhenPresent("showStatus", record::getShowStatus)
            .map(PmsCommentDynamicSqlSupport.productAttribute).toPropertyWhenPresent("productAttribute", record::getProductAttribute)
            .map(PmsCommentDynamicSqlSupport.collectCount).toPropertyWhenPresent("collectCount", record::getCollectCount)
            .map(PmsCommentDynamicSqlSupport.readCount).toPropertyWhenPresent("readCount", record::getReadCount)
            .map(PmsCommentDynamicSqlSupport.pics).toPropertyWhenPresent("pics", record::getPics)
            .map(PmsCommentDynamicSqlSupport.memberIcon).toPropertyWhenPresent("memberIcon", record::getMemberIcon)
            .map(PmsCommentDynamicSqlSupport.replayCount).toPropertyWhenPresent("replayCount", record::getReplayCount)
            .map(PmsCommentDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(PmsCommentDynamicSqlSupport.content).toPropertyWhenPresent("content", record::getContent)
        );
    }

    default Optional<PmsComment> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, PmsCommentDynamicSqlSupport.pmsComment, completer);
    }

    default List<PmsComment> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, PmsCommentDynamicSqlSupport.pmsComment, completer);
    }

    default List<PmsComment> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, PmsCommentDynamicSqlSupport.pmsComment, completer);
    }

    default Optional<PmsComment> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(PmsCommentDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, PmsCommentDynamicSqlSupport.pmsComment, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsComment record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsCommentDynamicSqlSupport.productId).equalTo(record::getProductId)
                .set(PmsCommentDynamicSqlSupport.memberNickName).equalTo(record::getMemberNickName)
                .set(PmsCommentDynamicSqlSupport.productName).equalTo(record::getProductName)
                .set(PmsCommentDynamicSqlSupport.star).equalTo(record::getStar)
                .set(PmsCommentDynamicSqlSupport.memberIp).equalTo(record::getMemberIp)
                .set(PmsCommentDynamicSqlSupport.showStatus).equalTo(record::getShowStatus)
                .set(PmsCommentDynamicSqlSupport.productAttribute).equalTo(record::getProductAttribute)
                .set(PmsCommentDynamicSqlSupport.collectCount).equalTo(record::getCollectCount)
                .set(PmsCommentDynamicSqlSupport.readCount).equalTo(record::getReadCount)
                .set(PmsCommentDynamicSqlSupport.pics).equalTo(record::getPics)
                .set(PmsCommentDynamicSqlSupport.memberIcon).equalTo(record::getMemberIcon)
                .set(PmsCommentDynamicSqlSupport.replayCount).equalTo(record::getReplayCount)
                .set(PmsCommentDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(PmsCommentDynamicSqlSupport.content).equalTo(record::getContent);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsComment record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsCommentDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
                .set(PmsCommentDynamicSqlSupport.memberNickName).equalToWhenPresent(record::getMemberNickName)
                .set(PmsCommentDynamicSqlSupport.productName).equalToWhenPresent(record::getProductName)
                .set(PmsCommentDynamicSqlSupport.star).equalToWhenPresent(record::getStar)
                .set(PmsCommentDynamicSqlSupport.memberIp).equalToWhenPresent(record::getMemberIp)
                .set(PmsCommentDynamicSqlSupport.showStatus).equalToWhenPresent(record::getShowStatus)
                .set(PmsCommentDynamicSqlSupport.productAttribute).equalToWhenPresent(record::getProductAttribute)
                .set(PmsCommentDynamicSqlSupport.collectCount).equalToWhenPresent(record::getCollectCount)
                .set(PmsCommentDynamicSqlSupport.readCount).equalToWhenPresent(record::getReadCount)
                .set(PmsCommentDynamicSqlSupport.pics).equalToWhenPresent(record::getPics)
                .set(PmsCommentDynamicSqlSupport.memberIcon).equalToWhenPresent(record::getMemberIcon)
                .set(PmsCommentDynamicSqlSupport.replayCount).equalToWhenPresent(record::getReplayCount)
                .set(PmsCommentDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(PmsCommentDynamicSqlSupport.content).equalToWhenPresent(record::getContent);
    }

    default int updateByPrimaryKey(PmsComment record) {
        return update(c ->
            c.set(PmsCommentDynamicSqlSupport.productId).equalTo(record::getProductId)
            .set(PmsCommentDynamicSqlSupport.memberNickName).equalTo(record::getMemberNickName)
            .set(PmsCommentDynamicSqlSupport.productName).equalTo(record::getProductName)
            .set(PmsCommentDynamicSqlSupport.star).equalTo(record::getStar)
            .set(PmsCommentDynamicSqlSupport.memberIp).equalTo(record::getMemberIp)
            .set(PmsCommentDynamicSqlSupport.showStatus).equalTo(record::getShowStatus)
            .set(PmsCommentDynamicSqlSupport.productAttribute).equalTo(record::getProductAttribute)
            .set(PmsCommentDynamicSqlSupport.collectCount).equalTo(record::getCollectCount)
            .set(PmsCommentDynamicSqlSupport.readCount).equalTo(record::getReadCount)
            .set(PmsCommentDynamicSqlSupport.pics).equalTo(record::getPics)
            .set(PmsCommentDynamicSqlSupport.memberIcon).equalTo(record::getMemberIcon)
            .set(PmsCommentDynamicSqlSupport.replayCount).equalTo(record::getReplayCount)
            .set(PmsCommentDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .set(PmsCommentDynamicSqlSupport.content).equalTo(record::getContent)
            .where(PmsCommentDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsComment record) {
        return update(c ->
            c.set(PmsCommentDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
            .set(PmsCommentDynamicSqlSupport.memberNickName).equalToWhenPresent(record::getMemberNickName)
            .set(PmsCommentDynamicSqlSupport.productName).equalToWhenPresent(record::getProductName)
            .set(PmsCommentDynamicSqlSupport.star).equalToWhenPresent(record::getStar)
            .set(PmsCommentDynamicSqlSupport.memberIp).equalToWhenPresent(record::getMemberIp)
            .set(PmsCommentDynamicSqlSupport.showStatus).equalToWhenPresent(record::getShowStatus)
            .set(PmsCommentDynamicSqlSupport.productAttribute).equalToWhenPresent(record::getProductAttribute)
            .set(PmsCommentDynamicSqlSupport.collectCount).equalToWhenPresent(record::getCollectCount)
            .set(PmsCommentDynamicSqlSupport.readCount).equalToWhenPresent(record::getReadCount)
            .set(PmsCommentDynamicSqlSupport.pics).equalToWhenPresent(record::getPics)
            .set(PmsCommentDynamicSqlSupport.memberIcon).equalToWhenPresent(record::getMemberIcon)
            .set(PmsCommentDynamicSqlSupport.replayCount).equalToWhenPresent(record::getReplayCount)
            .set(PmsCommentDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .set(PmsCommentDynamicSqlSupport.content).equalToWhenPresent(record::getContent)
            .where(PmsCommentDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}