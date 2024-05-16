/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsMemberLevel;

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
public interface UmsMemberLevelMapper {
    BasicColumn[] selectList = BasicColumn.columnList(UmsMemberLevelDynamicSqlSupport.id, UmsMemberLevelDynamicSqlSupport.name, UmsMemberLevelDynamicSqlSupport.growthPoint, UmsMemberLevelDynamicSqlSupport.defaultStatus, UmsMemberLevelDynamicSqlSupport.freeFreightPoint, UmsMemberLevelDynamicSqlSupport.commentGrowthPoint, UmsMemberLevelDynamicSqlSupport.privilegeFreeFreight, UmsMemberLevelDynamicSqlSupport.privilegeSignIn, UmsMemberLevelDynamicSqlSupport.privilegeComment, UmsMemberLevelDynamicSqlSupport.privilegePromotion, UmsMemberLevelDynamicSqlSupport.privilegeMemberPrice, UmsMemberLevelDynamicSqlSupport.privilegeBirthday, UmsMemberLevelDynamicSqlSupport.note);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsMemberLevel> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsMemberLevelResult")
    Optional<UmsMemberLevel> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsMemberLevelResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="growth_point", property="growthPoint", jdbcType=JdbcType.INTEGER),
        @Result(column="default_status", property="defaultStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="free_freight_point", property="freeFreightPoint", jdbcType=JdbcType.DECIMAL),
        @Result(column="comment_growth_point", property="commentGrowthPoint", jdbcType=JdbcType.INTEGER),
        @Result(column="privilege_free_freight", property="privilegeFreeFreight", jdbcType=JdbcType.INTEGER),
        @Result(column="privilege_sign_in", property="privilegeSignIn", jdbcType=JdbcType.INTEGER),
        @Result(column="privilege_comment", property="privilegeComment", jdbcType=JdbcType.INTEGER),
        @Result(column="privilege_promotion", property="privilegePromotion", jdbcType=JdbcType.INTEGER),
        @Result(column="privilege_member_price", property="privilegeMemberPrice", jdbcType=JdbcType.INTEGER),
        @Result(column="privilege_birthday", property="privilegeBirthday", jdbcType=JdbcType.INTEGER),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR)
    })
    List<UmsMemberLevel> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UmsMemberLevelDynamicSqlSupport.umsMemberLevel, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UmsMemberLevelDynamicSqlSupport.umsMemberLevel, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(UmsMemberLevelDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(UmsMemberLevel record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberLevelDynamicSqlSupport.umsMemberLevel, c ->
            c.map(UmsMemberLevelDynamicSqlSupport.name).toProperty("name")
            .map(UmsMemberLevelDynamicSqlSupport.growthPoint).toProperty("growthPoint")
            .map(UmsMemberLevelDynamicSqlSupport.defaultStatus).toProperty("defaultStatus")
            .map(UmsMemberLevelDynamicSqlSupport.freeFreightPoint).toProperty("freeFreightPoint")
            .map(UmsMemberLevelDynamicSqlSupport.commentGrowthPoint).toProperty("commentGrowthPoint")
            .map(UmsMemberLevelDynamicSqlSupport.privilegeFreeFreight).toProperty("privilegeFreeFreight")
            .map(UmsMemberLevelDynamicSqlSupport.privilegeSignIn).toProperty("privilegeSignIn")
            .map(UmsMemberLevelDynamicSqlSupport.privilegeComment).toProperty("privilegeComment")
            .map(UmsMemberLevelDynamicSqlSupport.privilegePromotion).toProperty("privilegePromotion")
            .map(UmsMemberLevelDynamicSqlSupport.privilegeMemberPrice).toProperty("privilegeMemberPrice")
            .map(UmsMemberLevelDynamicSqlSupport.privilegeBirthday).toProperty("privilegeBirthday")
            .map(UmsMemberLevelDynamicSqlSupport.note).toProperty("note")
        );
    }

    default int insertSelective(UmsMemberLevel record) {
        return MyBatis3Utils.insert(this::insert, record, UmsMemberLevelDynamicSqlSupport.umsMemberLevel, c ->
            c.map(UmsMemberLevelDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(UmsMemberLevelDynamicSqlSupport.growthPoint).toPropertyWhenPresent("growthPoint", record::getGrowthPoint)
            .map(UmsMemberLevelDynamicSqlSupport.defaultStatus).toPropertyWhenPresent("defaultStatus", record::getDefaultStatus)
            .map(UmsMemberLevelDynamicSqlSupport.freeFreightPoint).toPropertyWhenPresent("freeFreightPoint", record::getFreeFreightPoint)
            .map(UmsMemberLevelDynamicSqlSupport.commentGrowthPoint).toPropertyWhenPresent("commentGrowthPoint", record::getCommentGrowthPoint)
            .map(UmsMemberLevelDynamicSqlSupport.privilegeFreeFreight).toPropertyWhenPresent("privilegeFreeFreight", record::getPrivilegeFreeFreight)
            .map(UmsMemberLevelDynamicSqlSupport.privilegeSignIn).toPropertyWhenPresent("privilegeSignIn", record::getPrivilegeSignIn)
            .map(UmsMemberLevelDynamicSqlSupport.privilegeComment).toPropertyWhenPresent("privilegeComment", record::getPrivilegeComment)
            .map(UmsMemberLevelDynamicSqlSupport.privilegePromotion).toPropertyWhenPresent("privilegePromotion", record::getPrivilegePromotion)
            .map(UmsMemberLevelDynamicSqlSupport.privilegeMemberPrice).toPropertyWhenPresent("privilegeMemberPrice", record::getPrivilegeMemberPrice)
            .map(UmsMemberLevelDynamicSqlSupport.privilegeBirthday).toPropertyWhenPresent("privilegeBirthday", record::getPrivilegeBirthday)
            .map(UmsMemberLevelDynamicSqlSupport.note).toPropertyWhenPresent("note", record::getNote)
        );
    }

    default Optional<UmsMemberLevel> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UmsMemberLevelDynamicSqlSupport.umsMemberLevel, completer);
    }

    default List<UmsMemberLevel> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UmsMemberLevelDynamicSqlSupport.umsMemberLevel, completer);
    }

    default List<UmsMemberLevel> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UmsMemberLevelDynamicSqlSupport.umsMemberLevel, completer);
    }

    default Optional<UmsMemberLevel> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(UmsMemberLevelDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UmsMemberLevelDynamicSqlSupport.umsMemberLevel, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsMemberLevel record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberLevelDynamicSqlSupport.name).equalTo(record::getName)
                .set(UmsMemberLevelDynamicSqlSupport.growthPoint).equalTo(record::getGrowthPoint)
                .set(UmsMemberLevelDynamicSqlSupport.defaultStatus).equalTo(record::getDefaultStatus)
                .set(UmsMemberLevelDynamicSqlSupport.freeFreightPoint).equalTo(record::getFreeFreightPoint)
                .set(UmsMemberLevelDynamicSqlSupport.commentGrowthPoint).equalTo(record::getCommentGrowthPoint)
                .set(UmsMemberLevelDynamicSqlSupport.privilegeFreeFreight).equalTo(record::getPrivilegeFreeFreight)
                .set(UmsMemberLevelDynamicSqlSupport.privilegeSignIn).equalTo(record::getPrivilegeSignIn)
                .set(UmsMemberLevelDynamicSqlSupport.privilegeComment).equalTo(record::getPrivilegeComment)
                .set(UmsMemberLevelDynamicSqlSupport.privilegePromotion).equalTo(record::getPrivilegePromotion)
                .set(UmsMemberLevelDynamicSqlSupport.privilegeMemberPrice).equalTo(record::getPrivilegeMemberPrice)
                .set(UmsMemberLevelDynamicSqlSupport.privilegeBirthday).equalTo(record::getPrivilegeBirthday)
                .set(UmsMemberLevelDynamicSqlSupport.note).equalTo(record::getNote);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsMemberLevel record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UmsMemberLevelDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(UmsMemberLevelDynamicSqlSupport.growthPoint).equalToWhenPresent(record::getGrowthPoint)
                .set(UmsMemberLevelDynamicSqlSupport.defaultStatus).equalToWhenPresent(record::getDefaultStatus)
                .set(UmsMemberLevelDynamicSqlSupport.freeFreightPoint).equalToWhenPresent(record::getFreeFreightPoint)
                .set(UmsMemberLevelDynamicSqlSupport.commentGrowthPoint).equalToWhenPresent(record::getCommentGrowthPoint)
                .set(UmsMemberLevelDynamicSqlSupport.privilegeFreeFreight).equalToWhenPresent(record::getPrivilegeFreeFreight)
                .set(UmsMemberLevelDynamicSqlSupport.privilegeSignIn).equalToWhenPresent(record::getPrivilegeSignIn)
                .set(UmsMemberLevelDynamicSqlSupport.privilegeComment).equalToWhenPresent(record::getPrivilegeComment)
                .set(UmsMemberLevelDynamicSqlSupport.privilegePromotion).equalToWhenPresent(record::getPrivilegePromotion)
                .set(UmsMemberLevelDynamicSqlSupport.privilegeMemberPrice).equalToWhenPresent(record::getPrivilegeMemberPrice)
                .set(UmsMemberLevelDynamicSqlSupport.privilegeBirthday).equalToWhenPresent(record::getPrivilegeBirthday)
                .set(UmsMemberLevelDynamicSqlSupport.note).equalToWhenPresent(record::getNote);
    }

    default int updateByPrimaryKey(UmsMemberLevel record) {
        return update(c ->
            c.set(UmsMemberLevelDynamicSqlSupport.name).equalTo(record::getName)
            .set(UmsMemberLevelDynamicSqlSupport.growthPoint).equalTo(record::getGrowthPoint)
            .set(UmsMemberLevelDynamicSqlSupport.defaultStatus).equalTo(record::getDefaultStatus)
            .set(UmsMemberLevelDynamicSqlSupport.freeFreightPoint).equalTo(record::getFreeFreightPoint)
            .set(UmsMemberLevelDynamicSqlSupport.commentGrowthPoint).equalTo(record::getCommentGrowthPoint)
            .set(UmsMemberLevelDynamicSqlSupport.privilegeFreeFreight).equalTo(record::getPrivilegeFreeFreight)
            .set(UmsMemberLevelDynamicSqlSupport.privilegeSignIn).equalTo(record::getPrivilegeSignIn)
            .set(UmsMemberLevelDynamicSqlSupport.privilegeComment).equalTo(record::getPrivilegeComment)
            .set(UmsMemberLevelDynamicSqlSupport.privilegePromotion).equalTo(record::getPrivilegePromotion)
            .set(UmsMemberLevelDynamicSqlSupport.privilegeMemberPrice).equalTo(record::getPrivilegeMemberPrice)
            .set(UmsMemberLevelDynamicSqlSupport.privilegeBirthday).equalTo(record::getPrivilegeBirthday)
            .set(UmsMemberLevelDynamicSqlSupport.note).equalTo(record::getNote)
            .where(UmsMemberLevelDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsMemberLevel record) {
        return update(c ->
            c.set(UmsMemberLevelDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(UmsMemberLevelDynamicSqlSupport.growthPoint).equalToWhenPresent(record::getGrowthPoint)
            .set(UmsMemberLevelDynamicSqlSupport.defaultStatus).equalToWhenPresent(record::getDefaultStatus)
            .set(UmsMemberLevelDynamicSqlSupport.freeFreightPoint).equalToWhenPresent(record::getFreeFreightPoint)
            .set(UmsMemberLevelDynamicSqlSupport.commentGrowthPoint).equalToWhenPresent(record::getCommentGrowthPoint)
            .set(UmsMemberLevelDynamicSqlSupport.privilegeFreeFreight).equalToWhenPresent(record::getPrivilegeFreeFreight)
            .set(UmsMemberLevelDynamicSqlSupport.privilegeSignIn).equalToWhenPresent(record::getPrivilegeSignIn)
            .set(UmsMemberLevelDynamicSqlSupport.privilegeComment).equalToWhenPresent(record::getPrivilegeComment)
            .set(UmsMemberLevelDynamicSqlSupport.privilegePromotion).equalToWhenPresent(record::getPrivilegePromotion)
            .set(UmsMemberLevelDynamicSqlSupport.privilegeMemberPrice).equalToWhenPresent(record::getPrivilegeMemberPrice)
            .set(UmsMemberLevelDynamicSqlSupport.privilegeBirthday).equalToWhenPresent(record::getPrivilegeBirthday)
            .set(UmsMemberLevelDynamicSqlSupport.note).equalToWhenPresent(record::getNote)
            .where(UmsMemberLevelDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}