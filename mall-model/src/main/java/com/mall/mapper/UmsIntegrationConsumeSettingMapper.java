/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static com.mall.mapper.UmsIntegrationConsumeSettingDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.UmsIntegrationConsumeSetting;
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
public interface UmsIntegrationConsumeSettingMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, deductionPerAmount, maxPercentPerOrder, useUnit, couponStatus);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UmsIntegrationConsumeSetting> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UmsIntegrationConsumeSettingResult")
    Optional<UmsIntegrationConsumeSetting> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UmsIntegrationConsumeSettingResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="deduction_per_amount", property="deductionPerAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="max_percent_per_order", property="maxPercentPerOrder", jdbcType=JdbcType.INTEGER),
        @Result(column="use_unit", property="useUnit", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_status", property="couponStatus", jdbcType=JdbcType.INTEGER)
    })
    List<UmsIntegrationConsumeSetting> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, umsIntegrationConsumeSetting, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, umsIntegrationConsumeSetting, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(UmsIntegrationConsumeSetting record) {
        return MyBatis3Utils.insert(this::insert, record, umsIntegrationConsumeSetting, c ->
            c.map(deductionPerAmount).toProperty("deductionPerAmount")
            .map(maxPercentPerOrder).toProperty("maxPercentPerOrder")
            .map(useUnit).toProperty("useUnit")
            .map(couponStatus).toProperty("couponStatus")
        );
    }

    default int insertSelective(UmsIntegrationConsumeSetting record) {
        return MyBatis3Utils.insert(this::insert, record, umsIntegrationConsumeSetting, c ->
            c.map(deductionPerAmount).toPropertyWhenPresent("deductionPerAmount", record::getDeductionPerAmount)
            .map(maxPercentPerOrder).toPropertyWhenPresent("maxPercentPerOrder", record::getMaxPercentPerOrder)
            .map(useUnit).toPropertyWhenPresent("useUnit", record::getUseUnit)
            .map(couponStatus).toPropertyWhenPresent("couponStatus", record::getCouponStatus)
        );
    }

    default Optional<UmsIntegrationConsumeSetting> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, umsIntegrationConsumeSetting, completer);
    }

    default List<UmsIntegrationConsumeSetting> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, umsIntegrationConsumeSetting, completer);
    }

    default List<UmsIntegrationConsumeSetting> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, umsIntegrationConsumeSetting, completer);
    }

    default Optional<UmsIntegrationConsumeSetting> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, umsIntegrationConsumeSetting, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UmsIntegrationConsumeSetting record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(deductionPerAmount).equalTo(record::getDeductionPerAmount)
                .set(maxPercentPerOrder).equalTo(record::getMaxPercentPerOrder)
                .set(useUnit).equalTo(record::getUseUnit)
                .set(couponStatus).equalTo(record::getCouponStatus);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UmsIntegrationConsumeSetting record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(deductionPerAmount).equalToWhenPresent(record::getDeductionPerAmount)
                .set(maxPercentPerOrder).equalToWhenPresent(record::getMaxPercentPerOrder)
                .set(useUnit).equalToWhenPresent(record::getUseUnit)
                .set(couponStatus).equalToWhenPresent(record::getCouponStatus);
    }

    default int updateByPrimaryKey(UmsIntegrationConsumeSetting record) {
        return update(c ->
            c.set(deductionPerAmount).equalTo(record::getDeductionPerAmount)
            .set(maxPercentPerOrder).equalTo(record::getMaxPercentPerOrder)
            .set(useUnit).equalTo(record::getUseUnit)
            .set(couponStatus).equalTo(record::getCouponStatus)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UmsIntegrationConsumeSetting record) {
        return update(c ->
            c.set(deductionPerAmount).equalToWhenPresent(record::getDeductionPerAmount)
            .set(maxPercentPerOrder).equalToWhenPresent(record::getMaxPercentPerOrder)
            .set(useUnit).equalToWhenPresent(record::getUseUnit)
            .set(couponStatus).equalToWhenPresent(record::getCouponStatus)
            .where(id, isEqualTo(record::getId))
        );
    }
}