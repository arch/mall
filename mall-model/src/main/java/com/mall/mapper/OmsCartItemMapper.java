/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.OmsCartItem;

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
public interface OmsCartItemMapper {
    BasicColumn[] selectList = BasicColumn.columnList(OmsCartItemDynamicSqlSupport.id, OmsCartItemDynamicSqlSupport.productId, OmsCartItemDynamicSqlSupport.productSkuId, OmsCartItemDynamicSqlSupport.memberId, OmsCartItemDynamicSqlSupport.memberNickname, OmsCartItemDynamicSqlSupport.quantity, OmsCartItemDynamicSqlSupport.price, OmsCartItemDynamicSqlSupport.productPic, OmsCartItemDynamicSqlSupport.productName, OmsCartItemDynamicSqlSupport.productSubTitle, OmsCartItemDynamicSqlSupport.productSkuCode, OmsCartItemDynamicSqlSupport.deleteStatus, OmsCartItemDynamicSqlSupport.productCategoryId, OmsCartItemDynamicSqlSupport.productBrand, OmsCartItemDynamicSqlSupport.productSn, OmsCartItemDynamicSqlSupport.productAttr, OmsCartItemDynamicSqlSupport.createDate, OmsCartItemDynamicSqlSupport.modifyDate);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<OmsCartItem> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OmsCartItemResult")
    Optional<OmsCartItem> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OmsCartItemResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_sku_id", property="productSkuId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.BIGINT),
        @Result(column="member_nickname", property="memberNickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="product_pic", property="productPic", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_sub_title", property="productSubTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_sku_code", property="productSkuCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="product_category_id", property="productCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_brand", property="productBrand", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_sn", property="productSn", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_attr", property="productAttr", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_date", property="modifyDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<OmsCartItem> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, OmsCartItemDynamicSqlSupport.omsCartItem, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, OmsCartItemDynamicSqlSupport.omsCartItem, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(OmsCartItemDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(OmsCartItem record) {
        return MyBatis3Utils.insert(this::insert, record, OmsCartItemDynamicSqlSupport.omsCartItem, c ->
            c.map(OmsCartItemDynamicSqlSupport.productId).toProperty("productId")
            .map(OmsCartItemDynamicSqlSupport.productSkuId).toProperty("productSkuId")
            .map(OmsCartItemDynamicSqlSupport.memberId).toProperty("memberId")
            .map(OmsCartItemDynamicSqlSupport.memberNickname).toProperty("memberNickname")
            .map(OmsCartItemDynamicSqlSupport.quantity).toProperty("quantity")
            .map(OmsCartItemDynamicSqlSupport.price).toProperty("price")
            .map(OmsCartItemDynamicSqlSupport.productPic).toProperty("productPic")
            .map(OmsCartItemDynamicSqlSupport.productName).toProperty("productName")
            .map(OmsCartItemDynamicSqlSupport.productSubTitle).toProperty("productSubTitle")
            .map(OmsCartItemDynamicSqlSupport.productSkuCode).toProperty("productSkuCode")
            .map(OmsCartItemDynamicSqlSupport.deleteStatus).toProperty("deleteStatus")
            .map(OmsCartItemDynamicSqlSupport.productCategoryId).toProperty("productCategoryId")
            .map(OmsCartItemDynamicSqlSupport.productBrand).toProperty("productBrand")
            .map(OmsCartItemDynamicSqlSupport.productSn).toProperty("productSn")
            .map(OmsCartItemDynamicSqlSupport.productAttr).toProperty("productAttr")
            .map(OmsCartItemDynamicSqlSupport.createDate).toProperty("createDate")
            .map(OmsCartItemDynamicSqlSupport.modifyDate).toProperty("modifyDate")
        );
    }

    default int insertSelective(OmsCartItem record) {
        return MyBatis3Utils.insert(this::insert, record, OmsCartItemDynamicSqlSupport.omsCartItem, c ->
            c.map(OmsCartItemDynamicSqlSupport.productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(OmsCartItemDynamicSqlSupport.productSkuId).toPropertyWhenPresent("productSkuId", record::getProductSkuId)
            .map(OmsCartItemDynamicSqlSupport.memberId).toPropertyWhenPresent("memberId", record::getMemberId)
            .map(OmsCartItemDynamicSqlSupport.memberNickname).toPropertyWhenPresent("memberNickname", record::getMemberNickname)
            .map(OmsCartItemDynamicSqlSupport.quantity).toPropertyWhenPresent("quantity", record::getQuantity)
            .map(OmsCartItemDynamicSqlSupport.price).toPropertyWhenPresent("price", record::getPrice)
            .map(OmsCartItemDynamicSqlSupport.productPic).toPropertyWhenPresent("productPic", record::getProductPic)
            .map(OmsCartItemDynamicSqlSupport.productName).toPropertyWhenPresent("productName", record::getProductName)
            .map(OmsCartItemDynamicSqlSupport.productSubTitle).toPropertyWhenPresent("productSubTitle", record::getProductSubTitle)
            .map(OmsCartItemDynamicSqlSupport.productSkuCode).toPropertyWhenPresent("productSkuCode", record::getProductSkuCode)
            .map(OmsCartItemDynamicSqlSupport.deleteStatus).toPropertyWhenPresent("deleteStatus", record::getDeleteStatus)
            .map(OmsCartItemDynamicSqlSupport.productCategoryId).toPropertyWhenPresent("productCategoryId", record::getProductCategoryId)
            .map(OmsCartItemDynamicSqlSupport.productBrand).toPropertyWhenPresent("productBrand", record::getProductBrand)
            .map(OmsCartItemDynamicSqlSupport.productSn).toPropertyWhenPresent("productSn", record::getProductSn)
            .map(OmsCartItemDynamicSqlSupport.productAttr).toPropertyWhenPresent("productAttr", record::getProductAttr)
            .map(OmsCartItemDynamicSqlSupport.createDate).toPropertyWhenPresent("createDate", record::getCreateDate)
            .map(OmsCartItemDynamicSqlSupport.modifyDate).toPropertyWhenPresent("modifyDate", record::getModifyDate)
        );
    }

    default Optional<OmsCartItem> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, OmsCartItemDynamicSqlSupport.omsCartItem, completer);
    }

    default List<OmsCartItem> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, OmsCartItemDynamicSqlSupport.omsCartItem, completer);
    }

    default List<OmsCartItem> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, OmsCartItemDynamicSqlSupport.omsCartItem, completer);
    }

    default Optional<OmsCartItem> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(OmsCartItemDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, OmsCartItemDynamicSqlSupport.omsCartItem, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(OmsCartItem record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsCartItemDynamicSqlSupport.productId).equalTo(record::getProductId)
                .set(OmsCartItemDynamicSqlSupport.productSkuId).equalTo(record::getProductSkuId)
                .set(OmsCartItemDynamicSqlSupport.memberId).equalTo(record::getMemberId)
                .set(OmsCartItemDynamicSqlSupport.memberNickname).equalTo(record::getMemberNickname)
                .set(OmsCartItemDynamicSqlSupport.quantity).equalTo(record::getQuantity)
                .set(OmsCartItemDynamicSqlSupport.price).equalTo(record::getPrice)
                .set(OmsCartItemDynamicSqlSupport.productPic).equalTo(record::getProductPic)
                .set(OmsCartItemDynamicSqlSupport.productName).equalTo(record::getProductName)
                .set(OmsCartItemDynamicSqlSupport.productSubTitle).equalTo(record::getProductSubTitle)
                .set(OmsCartItemDynamicSqlSupport.productSkuCode).equalTo(record::getProductSkuCode)
                .set(OmsCartItemDynamicSqlSupport.deleteStatus).equalTo(record::getDeleteStatus)
                .set(OmsCartItemDynamicSqlSupport.productCategoryId).equalTo(record::getProductCategoryId)
                .set(OmsCartItemDynamicSqlSupport.productBrand).equalTo(record::getProductBrand)
                .set(OmsCartItemDynamicSqlSupport.productSn).equalTo(record::getProductSn)
                .set(OmsCartItemDynamicSqlSupport.productAttr).equalTo(record::getProductAttr)
                .set(OmsCartItemDynamicSqlSupport.createDate).equalTo(record::getCreateDate)
                .set(OmsCartItemDynamicSqlSupport.modifyDate).equalTo(record::getModifyDate);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(OmsCartItem record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsCartItemDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
                .set(OmsCartItemDynamicSqlSupport.productSkuId).equalToWhenPresent(record::getProductSkuId)
                .set(OmsCartItemDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
                .set(OmsCartItemDynamicSqlSupport.memberNickname).equalToWhenPresent(record::getMemberNickname)
                .set(OmsCartItemDynamicSqlSupport.quantity).equalToWhenPresent(record::getQuantity)
                .set(OmsCartItemDynamicSqlSupport.price).equalToWhenPresent(record::getPrice)
                .set(OmsCartItemDynamicSqlSupport.productPic).equalToWhenPresent(record::getProductPic)
                .set(OmsCartItemDynamicSqlSupport.productName).equalToWhenPresent(record::getProductName)
                .set(OmsCartItemDynamicSqlSupport.productSubTitle).equalToWhenPresent(record::getProductSubTitle)
                .set(OmsCartItemDynamicSqlSupport.productSkuCode).equalToWhenPresent(record::getProductSkuCode)
                .set(OmsCartItemDynamicSqlSupport.deleteStatus).equalToWhenPresent(record::getDeleteStatus)
                .set(OmsCartItemDynamicSqlSupport.productCategoryId).equalToWhenPresent(record::getProductCategoryId)
                .set(OmsCartItemDynamicSqlSupport.productBrand).equalToWhenPresent(record::getProductBrand)
                .set(OmsCartItemDynamicSqlSupport.productSn).equalToWhenPresent(record::getProductSn)
                .set(OmsCartItemDynamicSqlSupport.productAttr).equalToWhenPresent(record::getProductAttr)
                .set(OmsCartItemDynamicSqlSupport.createDate).equalToWhenPresent(record::getCreateDate)
                .set(OmsCartItemDynamicSqlSupport.modifyDate).equalToWhenPresent(record::getModifyDate);
    }

    default int updateByPrimaryKey(OmsCartItem record) {
        return update(c ->
            c.set(OmsCartItemDynamicSqlSupport.productId).equalTo(record::getProductId)
            .set(OmsCartItemDynamicSqlSupport.productSkuId).equalTo(record::getProductSkuId)
            .set(OmsCartItemDynamicSqlSupport.memberId).equalTo(record::getMemberId)
            .set(OmsCartItemDynamicSqlSupport.memberNickname).equalTo(record::getMemberNickname)
            .set(OmsCartItemDynamicSqlSupport.quantity).equalTo(record::getQuantity)
            .set(OmsCartItemDynamicSqlSupport.price).equalTo(record::getPrice)
            .set(OmsCartItemDynamicSqlSupport.productPic).equalTo(record::getProductPic)
            .set(OmsCartItemDynamicSqlSupport.productName).equalTo(record::getProductName)
            .set(OmsCartItemDynamicSqlSupport.productSubTitle).equalTo(record::getProductSubTitle)
            .set(OmsCartItemDynamicSqlSupport.productSkuCode).equalTo(record::getProductSkuCode)
            .set(OmsCartItemDynamicSqlSupport.deleteStatus).equalTo(record::getDeleteStatus)
            .set(OmsCartItemDynamicSqlSupport.productCategoryId).equalTo(record::getProductCategoryId)
            .set(OmsCartItemDynamicSqlSupport.productBrand).equalTo(record::getProductBrand)
            .set(OmsCartItemDynamicSqlSupport.productSn).equalTo(record::getProductSn)
            .set(OmsCartItemDynamicSqlSupport.productAttr).equalTo(record::getProductAttr)
            .set(OmsCartItemDynamicSqlSupport.createDate).equalTo(record::getCreateDate)
            .set(OmsCartItemDynamicSqlSupport.modifyDate).equalTo(record::getModifyDate)
            .where(OmsCartItemDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(OmsCartItem record) {
        return update(c ->
            c.set(OmsCartItemDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
            .set(OmsCartItemDynamicSqlSupport.productSkuId).equalToWhenPresent(record::getProductSkuId)
            .set(OmsCartItemDynamicSqlSupport.memberId).equalToWhenPresent(record::getMemberId)
            .set(OmsCartItemDynamicSqlSupport.memberNickname).equalToWhenPresent(record::getMemberNickname)
            .set(OmsCartItemDynamicSqlSupport.quantity).equalToWhenPresent(record::getQuantity)
            .set(OmsCartItemDynamicSqlSupport.price).equalToWhenPresent(record::getPrice)
            .set(OmsCartItemDynamicSqlSupport.productPic).equalToWhenPresent(record::getProductPic)
            .set(OmsCartItemDynamicSqlSupport.productName).equalToWhenPresent(record::getProductName)
            .set(OmsCartItemDynamicSqlSupport.productSubTitle).equalToWhenPresent(record::getProductSubTitle)
            .set(OmsCartItemDynamicSqlSupport.productSkuCode).equalToWhenPresent(record::getProductSkuCode)
            .set(OmsCartItemDynamicSqlSupport.deleteStatus).equalToWhenPresent(record::getDeleteStatus)
            .set(OmsCartItemDynamicSqlSupport.productCategoryId).equalToWhenPresent(record::getProductCategoryId)
            .set(OmsCartItemDynamicSqlSupport.productBrand).equalToWhenPresent(record::getProductBrand)
            .set(OmsCartItemDynamicSqlSupport.productSn).equalToWhenPresent(record::getProductSn)
            .set(OmsCartItemDynamicSqlSupport.productAttr).equalToWhenPresent(record::getProductAttr)
            .set(OmsCartItemDynamicSqlSupport.createDate).equalToWhenPresent(record::getCreateDate)
            .set(OmsCartItemDynamicSqlSupport.modifyDate).equalToWhenPresent(record::getModifyDate)
            .where(OmsCartItemDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}