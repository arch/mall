/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.CmsSubject;

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
public interface CmsSubjectMapper {
    BasicColumn[] selectList = BasicColumn.columnList(CmsSubjectDynamicSqlSupport.id, CmsSubjectDynamicSqlSupport.categoryId, CmsSubjectDynamicSqlSupport.title, CmsSubjectDynamicSqlSupport.pic, CmsSubjectDynamicSqlSupport.productCount, CmsSubjectDynamicSqlSupport.recommendStatus, CmsSubjectDynamicSqlSupport.collectCount, CmsSubjectDynamicSqlSupport.readCount, CmsSubjectDynamicSqlSupport.commentCount, CmsSubjectDynamicSqlSupport.albumPics, CmsSubjectDynamicSqlSupport.description, CmsSubjectDynamicSqlSupport.showStatus, CmsSubjectDynamicSqlSupport.forwardCount, CmsSubjectDynamicSqlSupport.categoryName, CmsSubjectDynamicSqlSupport.createTime, CmsSubjectDynamicSqlSupport.content);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CmsSubject> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CmsSubjectResult")
    Optional<CmsSubject> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CmsSubjectResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_count", property="productCount", jdbcType=JdbcType.INTEGER),
        @Result(column="recommend_status", property="recommendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="collect_count", property="collectCount", jdbcType=JdbcType.INTEGER),
        @Result(column="read_count", property="readCount", jdbcType=JdbcType.INTEGER),
        @Result(column="comment_count", property="commentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="album_pics", property="albumPics", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="forward_count", property="forwardCount", jdbcType=JdbcType.INTEGER),
        @Result(column="category_name", property="categoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<CmsSubject> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, CmsSubjectDynamicSqlSupport.cmsSubject, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, CmsSubjectDynamicSqlSupport.cmsSubject, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(CmsSubjectDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(CmsSubject record) {
        return MyBatis3Utils.insert(this::insert, record, CmsSubjectDynamicSqlSupport.cmsSubject, c ->
            c.map(CmsSubjectDynamicSqlSupport.categoryId).toProperty("categoryId")
            .map(CmsSubjectDynamicSqlSupport.title).toProperty("title")
            .map(CmsSubjectDynamicSqlSupport.pic).toProperty("pic")
            .map(CmsSubjectDynamicSqlSupport.productCount).toProperty("productCount")
            .map(CmsSubjectDynamicSqlSupport.recommendStatus).toProperty("recommendStatus")
            .map(CmsSubjectDynamicSqlSupport.collectCount).toProperty("collectCount")
            .map(CmsSubjectDynamicSqlSupport.readCount).toProperty("readCount")
            .map(CmsSubjectDynamicSqlSupport.commentCount).toProperty("commentCount")
            .map(CmsSubjectDynamicSqlSupport.albumPics).toProperty("albumPics")
            .map(CmsSubjectDynamicSqlSupport.description).toProperty("description")
            .map(CmsSubjectDynamicSqlSupport.showStatus).toProperty("showStatus")
            .map(CmsSubjectDynamicSqlSupport.forwardCount).toProperty("forwardCount")
            .map(CmsSubjectDynamicSqlSupport.categoryName).toProperty("categoryName")
            .map(CmsSubjectDynamicSqlSupport.createTime).toProperty("createTime")
            .map(CmsSubjectDynamicSqlSupport.content).toProperty("content")
        );
    }

    default int insertSelective(CmsSubject record) {
        return MyBatis3Utils.insert(this::insert, record, CmsSubjectDynamicSqlSupport.cmsSubject, c ->
            c.map(CmsSubjectDynamicSqlSupport.categoryId).toPropertyWhenPresent("categoryId", record::getCategoryId)
            .map(CmsSubjectDynamicSqlSupport.title).toPropertyWhenPresent("title", record::getTitle)
            .map(CmsSubjectDynamicSqlSupport.pic).toPropertyWhenPresent("pic", record::getPic)
            .map(CmsSubjectDynamicSqlSupport.productCount).toPropertyWhenPresent("productCount", record::getProductCount)
            .map(CmsSubjectDynamicSqlSupport.recommendStatus).toPropertyWhenPresent("recommendStatus", record::getRecommendStatus)
            .map(CmsSubjectDynamicSqlSupport.collectCount).toPropertyWhenPresent("collectCount", record::getCollectCount)
            .map(CmsSubjectDynamicSqlSupport.readCount).toPropertyWhenPresent("readCount", record::getReadCount)
            .map(CmsSubjectDynamicSqlSupport.commentCount).toPropertyWhenPresent("commentCount", record::getCommentCount)
            .map(CmsSubjectDynamicSqlSupport.albumPics).toPropertyWhenPresent("albumPics", record::getAlbumPics)
            .map(CmsSubjectDynamicSqlSupport.description).toPropertyWhenPresent("description", record::getDescription)
            .map(CmsSubjectDynamicSqlSupport.showStatus).toPropertyWhenPresent("showStatus", record::getShowStatus)
            .map(CmsSubjectDynamicSqlSupport.forwardCount).toPropertyWhenPresent("forwardCount", record::getForwardCount)
            .map(CmsSubjectDynamicSqlSupport.categoryName).toPropertyWhenPresent("categoryName", record::getCategoryName)
            .map(CmsSubjectDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(CmsSubjectDynamicSqlSupport.content).toPropertyWhenPresent("content", record::getContent)
        );
    }

    default Optional<CmsSubject> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, CmsSubjectDynamicSqlSupport.cmsSubject, completer);
    }

    default List<CmsSubject> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, CmsSubjectDynamicSqlSupport.cmsSubject, completer);
    }

    default List<CmsSubject> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, CmsSubjectDynamicSqlSupport.cmsSubject, completer);
    }

    default Optional<CmsSubject> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(CmsSubjectDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, CmsSubjectDynamicSqlSupport.cmsSubject, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(CmsSubject record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsSubjectDynamicSqlSupport.categoryId).equalTo(record::getCategoryId)
                .set(CmsSubjectDynamicSqlSupport.title).equalTo(record::getTitle)
                .set(CmsSubjectDynamicSqlSupport.pic).equalTo(record::getPic)
                .set(CmsSubjectDynamicSqlSupport.productCount).equalTo(record::getProductCount)
                .set(CmsSubjectDynamicSqlSupport.recommendStatus).equalTo(record::getRecommendStatus)
                .set(CmsSubjectDynamicSqlSupport.collectCount).equalTo(record::getCollectCount)
                .set(CmsSubjectDynamicSqlSupport.readCount).equalTo(record::getReadCount)
                .set(CmsSubjectDynamicSqlSupport.commentCount).equalTo(record::getCommentCount)
                .set(CmsSubjectDynamicSqlSupport.albumPics).equalTo(record::getAlbumPics)
                .set(CmsSubjectDynamicSqlSupport.description).equalTo(record::getDescription)
                .set(CmsSubjectDynamicSqlSupport.showStatus).equalTo(record::getShowStatus)
                .set(CmsSubjectDynamicSqlSupport.forwardCount).equalTo(record::getForwardCount)
                .set(CmsSubjectDynamicSqlSupport.categoryName).equalTo(record::getCategoryName)
                .set(CmsSubjectDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(CmsSubjectDynamicSqlSupport.content).equalTo(record::getContent);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(CmsSubject record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(CmsSubjectDynamicSqlSupport.categoryId).equalToWhenPresent(record::getCategoryId)
                .set(CmsSubjectDynamicSqlSupport.title).equalToWhenPresent(record::getTitle)
                .set(CmsSubjectDynamicSqlSupport.pic).equalToWhenPresent(record::getPic)
                .set(CmsSubjectDynamicSqlSupport.productCount).equalToWhenPresent(record::getProductCount)
                .set(CmsSubjectDynamicSqlSupport.recommendStatus).equalToWhenPresent(record::getRecommendStatus)
                .set(CmsSubjectDynamicSqlSupport.collectCount).equalToWhenPresent(record::getCollectCount)
                .set(CmsSubjectDynamicSqlSupport.readCount).equalToWhenPresent(record::getReadCount)
                .set(CmsSubjectDynamicSqlSupport.commentCount).equalToWhenPresent(record::getCommentCount)
                .set(CmsSubjectDynamicSqlSupport.albumPics).equalToWhenPresent(record::getAlbumPics)
                .set(CmsSubjectDynamicSqlSupport.description).equalToWhenPresent(record::getDescription)
                .set(CmsSubjectDynamicSqlSupport.showStatus).equalToWhenPresent(record::getShowStatus)
                .set(CmsSubjectDynamicSqlSupport.forwardCount).equalToWhenPresent(record::getForwardCount)
                .set(CmsSubjectDynamicSqlSupport.categoryName).equalToWhenPresent(record::getCategoryName)
                .set(CmsSubjectDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(CmsSubjectDynamicSqlSupport.content).equalToWhenPresent(record::getContent);
    }

    default int updateByPrimaryKey(CmsSubject record) {
        return update(c ->
            c.set(CmsSubjectDynamicSqlSupport.categoryId).equalTo(record::getCategoryId)
            .set(CmsSubjectDynamicSqlSupport.title).equalTo(record::getTitle)
            .set(CmsSubjectDynamicSqlSupport.pic).equalTo(record::getPic)
            .set(CmsSubjectDynamicSqlSupport.productCount).equalTo(record::getProductCount)
            .set(CmsSubjectDynamicSqlSupport.recommendStatus).equalTo(record::getRecommendStatus)
            .set(CmsSubjectDynamicSqlSupport.collectCount).equalTo(record::getCollectCount)
            .set(CmsSubjectDynamicSqlSupport.readCount).equalTo(record::getReadCount)
            .set(CmsSubjectDynamicSqlSupport.commentCount).equalTo(record::getCommentCount)
            .set(CmsSubjectDynamicSqlSupport.albumPics).equalTo(record::getAlbumPics)
            .set(CmsSubjectDynamicSqlSupport.description).equalTo(record::getDescription)
            .set(CmsSubjectDynamicSqlSupport.showStatus).equalTo(record::getShowStatus)
            .set(CmsSubjectDynamicSqlSupport.forwardCount).equalTo(record::getForwardCount)
            .set(CmsSubjectDynamicSqlSupport.categoryName).equalTo(record::getCategoryName)
            .set(CmsSubjectDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .set(CmsSubjectDynamicSqlSupport.content).equalTo(record::getContent)
            .where(CmsSubjectDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(CmsSubject record) {
        return update(c ->
            c.set(CmsSubjectDynamicSqlSupport.categoryId).equalToWhenPresent(record::getCategoryId)
            .set(CmsSubjectDynamicSqlSupport.title).equalToWhenPresent(record::getTitle)
            .set(CmsSubjectDynamicSqlSupport.pic).equalToWhenPresent(record::getPic)
            .set(CmsSubjectDynamicSqlSupport.productCount).equalToWhenPresent(record::getProductCount)
            .set(CmsSubjectDynamicSqlSupport.recommendStatus).equalToWhenPresent(record::getRecommendStatus)
            .set(CmsSubjectDynamicSqlSupport.collectCount).equalToWhenPresent(record::getCollectCount)
            .set(CmsSubjectDynamicSqlSupport.readCount).equalToWhenPresent(record::getReadCount)
            .set(CmsSubjectDynamicSqlSupport.commentCount).equalToWhenPresent(record::getCommentCount)
            .set(CmsSubjectDynamicSqlSupport.albumPics).equalToWhenPresent(record::getAlbumPics)
            .set(CmsSubjectDynamicSqlSupport.description).equalToWhenPresent(record::getDescription)
            .set(CmsSubjectDynamicSqlSupport.showStatus).equalToWhenPresent(record::getShowStatus)
            .set(CmsSubjectDynamicSqlSupport.forwardCount).equalToWhenPresent(record::getForwardCount)
            .set(CmsSubjectDynamicSqlSupport.categoryName).equalToWhenPresent(record::getCategoryName)
            .set(CmsSubjectDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .set(CmsSubjectDynamicSqlSupport.content).equalToWhenPresent(record::getContent)
            .where(CmsSubjectDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}