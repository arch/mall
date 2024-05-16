/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsMember;

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
public interface UmsMemberMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsMemberDynamicSqlSupport.id, UmsMemberDynamicSqlSupport.levelId, UmsMemberDynamicSqlSupport.openid, UmsMemberDynamicSqlSupport.username, UmsMemberDynamicSqlSupport.nickname, UmsMemberDynamicSqlSupport.phone, UmsMemberDynamicSqlSupport.icon, UmsMemberDynamicSqlSupport.gender, UmsMemberDynamicSqlSupport.birthday, UmsMemberDynamicSqlSupport.city, UmsMemberDynamicSqlSupport.province, UmsMemberDynamicSqlSupport.country, UmsMemberDynamicSqlSupport.language, UmsMemberDynamicSqlSupport.job, UmsMemberDynamicSqlSupport.bio, UmsMemberDynamicSqlSupport.status, UmsMemberDynamicSqlSupport.sourceType, UmsMemberDynamicSqlSupport.integration, UmsMemberDynamicSqlSupport.growth, UmsMemberDynamicSqlSupport.luckyCount, UmsMemberDynamicSqlSupport.historyIntegration, UmsMemberDynamicSqlSupport.createTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsMember> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsMemberResult")
    Optional<UmsMember> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsMemberResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="level_id", property="levelId", jdbcType=JdbcType.BIGINT),
        @Result(column="openid", property="openid", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.INTEGER),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="country", property="country", jdbcType=JdbcType.VARCHAR),
        @Result(column="language", property="language", jdbcType=JdbcType.VARCHAR),
        @Result(column="job", property="job", jdbcType=JdbcType.VARCHAR),
        @Result(column="bio", property="bio", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="source_type", property="sourceType", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.INTEGER),
        @Result(column="growth", property="growth", jdbcType=JdbcType.INTEGER),
        @Result(column="lucky_count", property="luckyCount", jdbcType=JdbcType.INTEGER),
        @Result(column="history_integration", property="historyIntegration", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UmsMember> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsMemberDynamicSqlSupport.umsMember, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsMemberDynamicSqlSupport.umsMember, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsMemberDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsMember record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberDynamicSqlSupport.umsMember, c ->
            c.map(UmsMemberDynamicSqlSupport.levelId).toProperty("levelId")
            .map(UmsMemberDynamicSqlSupport.openid).toProperty("openid")
            .map(UmsMemberDynamicSqlSupport.username).toProperty("username")
            .map(UmsMemberDynamicSqlSupport.nickname).toProperty("nickname")
            .map(UmsMemberDynamicSqlSupport.phone).toProperty("phone")
            .map(UmsMemberDynamicSqlSupport.icon).toProperty("icon")
            .map(UmsMemberDynamicSqlSupport.gender).toProperty("gender")
            .map(UmsMemberDynamicSqlSupport.birthday).toProperty("birthday")
            .map(UmsMemberDynamicSqlSupport.city).toProperty("city")
            .map(UmsMemberDynamicSqlSupport.province).toProperty("province")
            .map(UmsMemberDynamicSqlSupport.country).toProperty("country")
            .map(UmsMemberDynamicSqlSupport.language).toProperty("language")
            .map(UmsMemberDynamicSqlSupport.job).toProperty("job")
            .map(UmsMemberDynamicSqlSupport.bio).toProperty("bio")
            .map(UmsMemberDynamicSqlSupport.status).toProperty("status")
            .map(UmsMemberDynamicSqlSupport.sourceType).toProperty("sourceType")
            .map(UmsMemberDynamicSqlSupport.integration).toProperty("integration")
            .map(UmsMemberDynamicSqlSupport.growth).toProperty("growth")
            .map(UmsMemberDynamicSqlSupport.luckyCount).toProperty("luckyCount")
            .map(UmsMemberDynamicSqlSupport.historyIntegration).toProperty("historyIntegration")
            .map(UmsMemberDynamicSqlSupport.createTime).toProperty("createTime")
        );
    }

    default int insertSelective(UmsMember record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberDynamicSqlSupport.umsMember, c ->
            c.map(UmsMemberDynamicSqlSupport.levelId).toPropertyWhenPresent("levelId", record::getLevelId)
            .map(UmsMemberDynamicSqlSupport.openid).toPropertyWhenPresent("openid", record::getOpenid)
            .map(UmsMemberDynamicSqlSupport.username).toPropertyWhenPresent("username", record::getUsername)
            .map(UmsMemberDynamicSqlSupport.nickname).toPropertyWhenPresent("nickname", record::getNickname)
            .map(UmsMemberDynamicSqlSupport.phone).toPropertyWhenPresent("phone", record::getPhone)
            .map(UmsMemberDynamicSqlSupport.icon).toPropertyWhenPresent("icon", record::getIcon)
            .map(UmsMemberDynamicSqlSupport.gender).toPropertyWhenPresent("gender", record::getGender)
            .map(UmsMemberDynamicSqlSupport.birthday).toPropertyWhenPresent("birthday", record::getBirthday)
            .map(UmsMemberDynamicSqlSupport.city).toPropertyWhenPresent("city", record::getCity)
            .map(UmsMemberDynamicSqlSupport.province).toPropertyWhenPresent("province", record::getProvince)
            .map(UmsMemberDynamicSqlSupport.country).toPropertyWhenPresent("country", record::getCountry)
            .map(UmsMemberDynamicSqlSupport.language).toPropertyWhenPresent("language", record::getLanguage)
            .map(UmsMemberDynamicSqlSupport.job).toPropertyWhenPresent("job", record::getJob)
            .map(UmsMemberDynamicSqlSupport.bio).toPropertyWhenPresent("bio", record::getBio)
            .map(UmsMemberDynamicSqlSupport.status).toPropertyWhenPresent("status", record::getStatus)
            .map(UmsMemberDynamicSqlSupport.sourceType).toPropertyWhenPresent("sourceType", record::getSourceType)
            .map(UmsMemberDynamicSqlSupport.integration).toPropertyWhenPresent("integration", record::getIntegration)
            .map(UmsMemberDynamicSqlSupport.growth).toPropertyWhenPresent("growth", record::getGrowth)
            .map(UmsMemberDynamicSqlSupport.luckyCount).toPropertyWhenPresent("luckyCount", record::getLuckyCount)
            .map(UmsMemberDynamicSqlSupport.historyIntegration).toPropertyWhenPresent("historyIntegration", record::getHistoryIntegration)
            .map(UmsMemberDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    default Optional<UmsMember> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsMemberDynamicSqlSupport.umsMember, completer);
    }

    default List<UmsMember> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsMemberDynamicSqlSupport.umsMember, completer);
    }

    default List<UmsMember> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsMemberDynamicSqlSupport.umsMember, completer);
    }

    default Optional<UmsMember> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsMemberDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsMemberDynamicSqlSupport.umsMember, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsMember record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberDynamicSqlSupport.levelId).equalTo(record::getLevelId)
                .set(UmsMemberDynamicSqlSupport.openid).equalTo(record::getOpenid)
                .set(UmsMemberDynamicSqlSupport.username).equalTo(record::getUsername)
                .set(UmsMemberDynamicSqlSupport.nickname).equalTo(record::getNickname)
                .set(UmsMemberDynamicSqlSupport.phone).equalTo(record::getPhone)
                .set(UmsMemberDynamicSqlSupport.icon).equalTo(record::getIcon)
                .set(UmsMemberDynamicSqlSupport.gender).equalTo(record::getGender)
                .set(UmsMemberDynamicSqlSupport.birthday).equalTo(record::getBirthday)
                .set(UmsMemberDynamicSqlSupport.city).equalTo(record::getCity)
                .set(UmsMemberDynamicSqlSupport.province).equalTo(record::getProvince)
                .set(UmsMemberDynamicSqlSupport.country).equalTo(record::getCountry)
                .set(UmsMemberDynamicSqlSupport.language).equalTo(record::getLanguage)
                .set(UmsMemberDynamicSqlSupport.job).equalTo(record::getJob)
                .set(UmsMemberDynamicSqlSupport.bio).equalTo(record::getBio)
                .set(UmsMemberDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(UmsMemberDynamicSqlSupport.sourceType).equalTo(record::getSourceType)
                .set(UmsMemberDynamicSqlSupport.integration).equalTo(record::getIntegration)
                .set(UmsMemberDynamicSqlSupport.growth).equalTo(record::getGrowth)
                .set(UmsMemberDynamicSqlSupport.luckyCount).equalTo(record::getLuckyCount)
                .set(UmsMemberDynamicSqlSupport.historyIntegration).equalTo(record::getHistoryIntegration)
                .set(UmsMemberDynamicSqlSupport.createTime).equalTo(record::getCreateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsMember record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberDynamicSqlSupport.levelId).equalToWhenPresent(record::getLevelId)
                .set(UmsMemberDynamicSqlSupport.openid).equalToWhenPresent(record::getOpenid)
                .set(UmsMemberDynamicSqlSupport.username).equalToWhenPresent(record::getUsername)
                .set(UmsMemberDynamicSqlSupport.nickname).equalToWhenPresent(record::getNickname)
                .set(UmsMemberDynamicSqlSupport.phone).equalToWhenPresent(record::getPhone)
                .set(UmsMemberDynamicSqlSupport.icon).equalToWhenPresent(record::getIcon)
                .set(UmsMemberDynamicSqlSupport.gender).equalToWhenPresent(record::getGender)
                .set(UmsMemberDynamicSqlSupport.birthday).equalToWhenPresent(record::getBirthday)
                .set(UmsMemberDynamicSqlSupport.city).equalToWhenPresent(record::getCity)
                .set(UmsMemberDynamicSqlSupport.province).equalToWhenPresent(record::getProvince)
                .set(UmsMemberDynamicSqlSupport.country).equalToWhenPresent(record::getCountry)
                .set(UmsMemberDynamicSqlSupport.language).equalToWhenPresent(record::getLanguage)
                .set(UmsMemberDynamicSqlSupport.job).equalToWhenPresent(record::getJob)
                .set(UmsMemberDynamicSqlSupport.bio).equalToWhenPresent(record::getBio)
                .set(UmsMemberDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(UmsMemberDynamicSqlSupport.sourceType).equalToWhenPresent(record::getSourceType)
                .set(UmsMemberDynamicSqlSupport.integration).equalToWhenPresent(record::getIntegration)
                .set(UmsMemberDynamicSqlSupport.growth).equalToWhenPresent(record::getGrowth)
                .set(UmsMemberDynamicSqlSupport.luckyCount).equalToWhenPresent(record::getLuckyCount)
                .set(UmsMemberDynamicSqlSupport.historyIntegration).equalToWhenPresent(record::getHistoryIntegration)
                .set(UmsMemberDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime);
    }

    default int updateByPrimaryKey(UmsMember record) {
        return update(c ->
            c.set(UmsMemberDynamicSqlSupport.levelId).equalTo(record::getLevelId)
            .set(UmsMemberDynamicSqlSupport.openid).equalTo(record::getOpenid)
            .set(UmsMemberDynamicSqlSupport.username).equalTo(record::getUsername)
            .set(UmsMemberDynamicSqlSupport.nickname).equalTo(record::getNickname)
            .set(UmsMemberDynamicSqlSupport.phone).equalTo(record::getPhone)
            .set(UmsMemberDynamicSqlSupport.icon).equalTo(record::getIcon)
            .set(UmsMemberDynamicSqlSupport.gender).equalTo(record::getGender)
            .set(UmsMemberDynamicSqlSupport.birthday).equalTo(record::getBirthday)
            .set(UmsMemberDynamicSqlSupport.city).equalTo(record::getCity)
            .set(UmsMemberDynamicSqlSupport.province).equalTo(record::getProvince)
            .set(UmsMemberDynamicSqlSupport.country).equalTo(record::getCountry)
            .set(UmsMemberDynamicSqlSupport.language).equalTo(record::getLanguage)
            .set(UmsMemberDynamicSqlSupport.job).equalTo(record::getJob)
            .set(UmsMemberDynamicSqlSupport.bio).equalTo(record::getBio)
            .set(UmsMemberDynamicSqlSupport.status).equalTo(record::getStatus)
            .set(UmsMemberDynamicSqlSupport.sourceType).equalTo(record::getSourceType)
            .set(UmsMemberDynamicSqlSupport.integration).equalTo(record::getIntegration)
            .set(UmsMemberDynamicSqlSupport.growth).equalTo(record::getGrowth)
            .set(UmsMemberDynamicSqlSupport.luckyCount).equalTo(record::getLuckyCount)
            .set(UmsMemberDynamicSqlSupport.historyIntegration).equalTo(record::getHistoryIntegration)
            .set(UmsMemberDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
            .where(UmsMemberDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsMember record) {
        return update(c ->
            c.set(UmsMemberDynamicSqlSupport.levelId).equalToWhenPresent(record::getLevelId)
            .set(UmsMemberDynamicSqlSupport.openid).equalToWhenPresent(record::getOpenid)
            .set(UmsMemberDynamicSqlSupport.username).equalToWhenPresent(record::getUsername)
            .set(UmsMemberDynamicSqlSupport.nickname).equalToWhenPresent(record::getNickname)
            .set(UmsMemberDynamicSqlSupport.phone).equalToWhenPresent(record::getPhone)
            .set(UmsMemberDynamicSqlSupport.icon).equalToWhenPresent(record::getIcon)
            .set(UmsMemberDynamicSqlSupport.gender).equalToWhenPresent(record::getGender)
            .set(UmsMemberDynamicSqlSupport.birthday).equalToWhenPresent(record::getBirthday)
            .set(UmsMemberDynamicSqlSupport.city).equalToWhenPresent(record::getCity)
            .set(UmsMemberDynamicSqlSupport.province).equalToWhenPresent(record::getProvince)
            .set(UmsMemberDynamicSqlSupport.country).equalToWhenPresent(record::getCountry)
            .set(UmsMemberDynamicSqlSupport.language).equalToWhenPresent(record::getLanguage)
            .set(UmsMemberDynamicSqlSupport.job).equalToWhenPresent(record::getJob)
            .set(UmsMemberDynamicSqlSupport.bio).equalToWhenPresent(record::getBio)
            .set(UmsMemberDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
            .set(UmsMemberDynamicSqlSupport.sourceType).equalToWhenPresent(record::getSourceType)
            .set(UmsMemberDynamicSqlSupport.integration).equalToWhenPresent(record::getIntegration)
            .set(UmsMemberDynamicSqlSupport.growth).equalToWhenPresent(record::getGrowth)
            .set(UmsMemberDynamicSqlSupport.luckyCount).equalToWhenPresent(record::getLuckyCount)
            .set(UmsMemberDynamicSqlSupport.historyIntegration).equalToWhenPresent(record::getHistoryIntegration)
            .set(UmsMemberDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
            .where(UmsMemberDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}