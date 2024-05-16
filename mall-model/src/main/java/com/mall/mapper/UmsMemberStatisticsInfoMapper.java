/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsMemberStatisticsInfo;

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
public interface UmsMemberStatisticsInfoMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsMemberStatisticsInfoDynamicSqlSupport.id, UmsMemberStatisticsInfoDynamicSqlSupport.memberId, UmsMemberStatisticsInfoDynamicSqlSupport.consumeAmount, UmsMemberStatisticsInfoDynamicSqlSupport.orderCount, UmsMemberStatisticsInfoDynamicSqlSupport.couponCount, UmsMemberStatisticsInfoDynamicSqlSupport.commentCount, UmsMemberStatisticsInfoDynamicSqlSupport.returnOrderCount, UmsMemberStatisticsInfoDynamicSqlSupport.loginCount, UmsMemberStatisticsInfoDynamicSqlSupport.attendCount, UmsMemberStatisticsInfoDynamicSqlSupport.fansCount, UmsMemberStatisticsInfoDynamicSqlSupport.collectProductCount, UmsMemberStatisticsInfoDynamicSqlSupport.collectSubjectCount, UmsMemberStatisticsInfoDynamicSqlSupport.collectTopicCount, UmsMemberStatisticsInfoDynamicSqlSupport.collectCommentCount, UmsMemberStatisticsInfoDynamicSqlSupport.inviteFriendCount, UmsMemberStatisticsInfoDynamicSqlSupport.recentOrderTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsMemberStatisticsInfo> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsMemberStatisticsInfoResult")
    Optional<UmsMemberStatisticsInfo> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsMemberStatisticsInfoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.BIGINT),
        @Result(column="consume_amount", property="consumeAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="order_count", property="orderCount", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_count", property="couponCount", jdbcType=JdbcType.INTEGER),
        @Result(column="comment_count", property="commentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="return_order_count", property="returnOrderCount", jdbcType=JdbcType.INTEGER),
        @Result(column="login_count", property="loginCount", jdbcType=JdbcType.INTEGER),
        @Result(column="attend_count", property="attendCount", jdbcType=JdbcType.INTEGER),
        @Result(column="fans_count", property="fansCount", jdbcType=JdbcType.INTEGER),
        @Result(column="collect_product_count", property="collectProductCount", jdbcType=JdbcType.INTEGER),
        @Result(column="collect_subject_count", property="collectSubjectCount", jdbcType=JdbcType.INTEGER),
        @Result(column="collect_topic_count", property="collectTopicCount", jdbcType=JdbcType.INTEGER),
        @Result(column="collect_comment_count", property="collectCommentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="invite_friend_count", property="inviteFriendCount", jdbcType=JdbcType.INTEGER),
        @Result(column="recent_order_time", property="recentOrderTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UmsMemberStatisticsInfo> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsMemberStatisticsInfoDynamicSqlSupport.umsMemberStatisticsInfo, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsMemberStatisticsInfoDynamicSqlSupport.umsMemberStatisticsInfo, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsMemberStatisticsInfoDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsMemberStatisticsInfo record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberStatisticsInfoDynamicSqlSupport.umsMemberStatisticsInfo, c ->
            c.map(UmsMemberStatisticsInfoDynamicSqlSupport.memberId).toProperty("memberId")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.consumeAmount).toProperty("consumeAmount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.orderCount).toProperty("orderCount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.couponCount).toProperty("couponCount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.commentCount).toProperty("commentCount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.returnOrderCount).toProperty("returnOrderCount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.loginCount).toProperty("loginCount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.attendCount).toProperty("attendCount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.fansCount).toProperty("fansCount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.collectProductCount).toProperty("collectProductCount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.collectSubjectCount).toProperty("collectSubjectCount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.collectTopicCount).toProperty("collectTopicCount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.collectCommentCount).toProperty("collectCommentCount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.inviteFriendCount).toProperty("inviteFriendCount")
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.recentOrderTime).toProperty("recentOrderTime")
        );
    }

    default int insertSelective(UmsMemberStatisticsInfo record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberStatisticsInfoDynamicSqlSupport.umsMemberStatisticsInfo, c ->
            c.map(UmsMemberStatisticsInfoDynamicSqlSupport.memberId).toPropertyWhenPresent("memberId", record::getMemberId)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.consumeAmount).toPropertyWhenPresent("consumeAmount", record::getConsumeAmount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.orderCount).toPropertyWhenPresent("orderCount", record::getOrderCount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.couponCount).toPropertyWhenPresent("couponCount", record::getCouponCount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.commentCount).toPropertyWhenPresent("commentCount", record::getCommentCount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.returnOrderCount).toPropertyWhenPresent("returnOrderCount", record::getReturnOrderCount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.loginCount).toPropertyWhenPresent("loginCount", record::getLoginCount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.attendCount).toPropertyWhenPresent("attendCount", record::getAttendCount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.fansCount).toPropertyWhenPresent("fansCount", record::getFansCount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.collectProductCount).toPropertyWhenPresent("collectProductCount", record::getCollectProductCount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.collectSubjectCount).toPropertyWhenPresent("collectSubjectCount", record::getCollectSubjectCount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.collectTopicCount).toPropertyWhenPresent("collectTopicCount", record::getCollectTopicCount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.collectCommentCount).toPropertyWhenPresent("collectCommentCount", record::getCollectCommentCount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.inviteFriendCount).toPropertyWhenPresent("inviteFriendCount", record::getInviteFriendCount)
            .map(UmsMemberStatisticsInfoDynamicSqlSupport.recentOrderTime).toPropertyWhenPresent("recentOrderTime", record::getRecentOrderTime)
        );
    }

    default Optional<UmsMemberStatisticsInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsMemberStatisticsInfoDynamicSqlSupport.umsMemberStatisticsInfo, completer);
    }

    default List<UmsMemberStatisticsInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsMemberStatisticsInfoDynamicSqlSupport.umsMemberStatisticsInfo, completer);
    }

    default List<UmsMemberStatisticsInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsMemberStatisticsInfoDynamicSqlSupport.umsMemberStatisticsInfo, completer);
    }

    default Optional<UmsMemberStatisticsInfo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsMemberStatisticsInfoDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsMemberStatisticsInfoDynamicSqlSupport.umsMemberStatisticsInfo, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsMemberStatisticsInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberStatisticsInfoDynamicSqlSupport.memberId).equalTo(record::getMemberId)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.consumeAmount).equalTo(record::getConsumeAmount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.orderCount).equalTo(record::getOrderCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.couponCount).equalTo(record::getCouponCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.commentCount).equalTo(record::getCommentCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.returnOrderCount).equalTo(record::getReturnOrderCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.loginCount).equalTo(record::getLoginCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.attendCount).equalTo(record::getAttendCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.fansCount).equalTo(record::getFansCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectProductCount).equalTo(record::getCollectProductCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectSubjectCount).equalTo(record::getCollectSubjectCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectTopicCount).equalTo(record::getCollectTopicCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectCommentCount).equalTo(record::getCollectCommentCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.inviteFriendCount).equalTo(record::getInviteFriendCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.recentOrderTime).equalTo(record::getRecentOrderTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsMemberStatisticsInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberStatisticsInfoDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.consumeAmount).equalToWhenPresent(record::getConsumeAmount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.orderCount).equalToWhenPresent(record::getOrderCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.couponCount).equalToWhenPresent(record::getCouponCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.commentCount).equalToWhenPresent(record::getCommentCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.returnOrderCount).equalToWhenPresent(record::getReturnOrderCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.loginCount).equalToWhenPresent(record::getLoginCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.attendCount).equalToWhenPresent(record::getAttendCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.fansCount).equalToWhenPresent(record::getFansCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectProductCount).equalToWhenPresent(record::getCollectProductCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectSubjectCount).equalToWhenPresent(record::getCollectSubjectCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectTopicCount).equalToWhenPresent(record::getCollectTopicCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectCommentCount).equalToWhenPresent(record::getCollectCommentCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.inviteFriendCount).equalToWhenPresent(record::getInviteFriendCount)
                .set(UmsMemberStatisticsInfoDynamicSqlSupport.recentOrderTime).equalToWhenPresent(record::getRecentOrderTime);
    }

    default int updateByPrimaryKey(UmsMemberStatisticsInfo record) {
        return update(c ->
            c.set(UmsMemberStatisticsInfoDynamicSqlSupport.memberId).equalTo(record::getMemberId)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.consumeAmount).equalTo(record::getConsumeAmount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.orderCount).equalTo(record::getOrderCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.couponCount).equalTo(record::getCouponCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.commentCount).equalTo(record::getCommentCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.returnOrderCount).equalTo(record::getReturnOrderCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.loginCount).equalTo(record::getLoginCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.attendCount).equalTo(record::getAttendCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.fansCount).equalTo(record::getFansCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectProductCount).equalTo(record::getCollectProductCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectSubjectCount).equalTo(record::getCollectSubjectCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectTopicCount).equalTo(record::getCollectTopicCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectCommentCount).equalTo(record::getCollectCommentCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.inviteFriendCount).equalTo(record::getInviteFriendCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.recentOrderTime).equalTo(record::getRecentOrderTime)
            .where(UmsMemberStatisticsInfoDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsMemberStatisticsInfo record) {
        return update(c ->
            c.set(UmsMemberStatisticsInfoDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.consumeAmount).equalToWhenPresent(record::getConsumeAmount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.orderCount).equalToWhenPresent(record::getOrderCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.couponCount).equalToWhenPresent(record::getCouponCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.commentCount).equalToWhenPresent(record::getCommentCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.returnOrderCount).equalToWhenPresent(record::getReturnOrderCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.loginCount).equalToWhenPresent(record::getLoginCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.attendCount).equalToWhenPresent(record::getAttendCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.fansCount).equalToWhenPresent(record::getFansCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectProductCount).equalToWhenPresent(record::getCollectProductCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectSubjectCount).equalToWhenPresent(record::getCollectSubjectCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectTopicCount).equalToWhenPresent(record::getCollectTopicCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.collectCommentCount).equalToWhenPresent(record::getCollectCommentCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.inviteFriendCount).equalToWhenPresent(record::getInviteFriendCount)
            .set(UmsMemberStatisticsInfoDynamicSqlSupport.recentOrderTime).equalToWhenPresent(record::getRecentOrderTime)
            .where(UmsMemberStatisticsInfoDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}