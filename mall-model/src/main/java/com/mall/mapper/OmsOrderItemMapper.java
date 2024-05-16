/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.OmsOrderItem;

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
public interface OmsOrderItemMapper {
    BasicColumn[] selectList = BasicColumn.columnList(OmsOrderItemDynamicSqlSupport.id, OmsOrderItemDynamicSqlSupport.orderId, OmsOrderItemDynamicSqlSupport.orderSn, OmsOrderItemDynamicSqlSupport.returnId, OmsOrderItemDynamicSqlSupport.productId, OmsOrderItemDynamicSqlSupport.productPic, OmsOrderItemDynamicSqlSupport.productName, OmsOrderItemDynamicSqlSupport.productBrand, OmsOrderItemDynamicSqlSupport.productSn, OmsOrderItemDynamicSqlSupport.productPrice, OmsOrderItemDynamicSqlSupport.productQuantity, OmsOrderItemDynamicSqlSupport.productSkuId, OmsOrderItemDynamicSqlSupport.productSkuCode, OmsOrderItemDynamicSqlSupport.productCategoryId, OmsOrderItemDynamicSqlSupport.promotionName, OmsOrderItemDynamicSqlSupport.promotionAmount, OmsOrderItemDynamicSqlSupport.couponAmount, OmsOrderItemDynamicSqlSupport.integrationAmount, OmsOrderItemDynamicSqlSupport.realAmount, OmsOrderItemDynamicSqlSupport.giftIntegration, OmsOrderItemDynamicSqlSupport.giftGrowth, OmsOrderItemDynamicSqlSupport.productAttr);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<OmsOrderItem> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OmsOrderItemResult")
    Optional<OmsOrderItem> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OmsOrderItemResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.BIGINT),
        @Result(column="order_sn", property="orderSn", jdbcType=JdbcType.VARCHAR),
        @Result(column="return_id", property="returnId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_pic", property="productPic", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_brand", property="productBrand", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_sn", property="productSn", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_price", property="productPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="product_quantity", property="productQuantity", jdbcType=JdbcType.INTEGER),
        @Result(column="product_sku_id", property="productSkuId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_sku_code", property="productSkuCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_category_id", property="productCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="promotion_name", property="promotionName", jdbcType=JdbcType.VARCHAR),
        @Result(column="promotion_amount", property="promotionAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="coupon_amount", property="couponAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="integration_amount", property="integrationAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="real_amount", property="realAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="gift_integration", property="giftIntegration", jdbcType=JdbcType.INTEGER),
        @Result(column="gift_growth", property="giftGrowth", jdbcType=JdbcType.INTEGER),
        @Result(column="product_attr", property="productAttr", jdbcType=JdbcType.VARCHAR)
    })
    List<OmsOrderItem> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, OmsOrderItemDynamicSqlSupport.omsOrderItem, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, OmsOrderItemDynamicSqlSupport.omsOrderItem, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(OmsOrderItemDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(OmsOrderItem record) {
        return MyBatis3Utils.insert(this::insert, record, OmsOrderItemDynamicSqlSupport.omsOrderItem, c ->
            c.map(OmsOrderItemDynamicSqlSupport.orderId).toProperty("orderId")
            .map(OmsOrderItemDynamicSqlSupport.orderSn).toProperty("orderSn")
            .map(OmsOrderItemDynamicSqlSupport.returnId).toProperty("returnId")
            .map(OmsOrderItemDynamicSqlSupport.productId).toProperty("productId")
            .map(OmsOrderItemDynamicSqlSupport.productPic).toProperty("productPic")
            .map(OmsOrderItemDynamicSqlSupport.productName).toProperty("productName")
            .map(OmsOrderItemDynamicSqlSupport.productBrand).toProperty("productBrand")
            .map(OmsOrderItemDynamicSqlSupport.productSn).toProperty("productSn")
            .map(OmsOrderItemDynamicSqlSupport.productPrice).toProperty("productPrice")
            .map(OmsOrderItemDynamicSqlSupport.productQuantity).toProperty("productQuantity")
            .map(OmsOrderItemDynamicSqlSupport.productSkuId).toProperty("productSkuId")
            .map(OmsOrderItemDynamicSqlSupport.productSkuCode).toProperty("productSkuCode")
            .map(OmsOrderItemDynamicSqlSupport.productCategoryId).toProperty("productCategoryId")
            .map(OmsOrderItemDynamicSqlSupport.promotionName).toProperty("promotionName")
            .map(OmsOrderItemDynamicSqlSupport.promotionAmount).toProperty("promotionAmount")
            .map(OmsOrderItemDynamicSqlSupport.couponAmount).toProperty("couponAmount")
            .map(OmsOrderItemDynamicSqlSupport.integrationAmount).toProperty("integrationAmount")
            .map(OmsOrderItemDynamicSqlSupport.realAmount).toProperty("realAmount")
            .map(OmsOrderItemDynamicSqlSupport.giftIntegration).toProperty("giftIntegration")
            .map(OmsOrderItemDynamicSqlSupport.giftGrowth).toProperty("giftGrowth")
            .map(OmsOrderItemDynamicSqlSupport.productAttr).toProperty("productAttr")
        );
    }

    default int insertSelective(OmsOrderItem record) {
        return MyBatis3Utils.insert(this::insert, record, OmsOrderItemDynamicSqlSupport.omsOrderItem, c ->
            c.map(OmsOrderItemDynamicSqlSupport.orderId).toPropertyWhenPresent("orderId", record::getOrderId)
            .map(OmsOrderItemDynamicSqlSupport.orderSn).toPropertyWhenPresent("orderSn", record::getOrderSn)
            .map(OmsOrderItemDynamicSqlSupport.returnId).toPropertyWhenPresent("returnId", record::getReturnId)
            .map(OmsOrderItemDynamicSqlSupport.productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(OmsOrderItemDynamicSqlSupport.productPic).toPropertyWhenPresent("productPic", record::getProductPic)
            .map(OmsOrderItemDynamicSqlSupport.productName).toPropertyWhenPresent("productName", record::getProductName)
            .map(OmsOrderItemDynamicSqlSupport.productBrand).toPropertyWhenPresent("productBrand", record::getProductBrand)
            .map(OmsOrderItemDynamicSqlSupport.productSn).toPropertyWhenPresent("productSn", record::getProductSn)
            .map(OmsOrderItemDynamicSqlSupport.productPrice).toPropertyWhenPresent("productPrice", record::getProductPrice)
            .map(OmsOrderItemDynamicSqlSupport.productQuantity).toPropertyWhenPresent("productQuantity", record::getProductQuantity)
            .map(OmsOrderItemDynamicSqlSupport.productSkuId).toPropertyWhenPresent("productSkuId", record::getProductSkuId)
            .map(OmsOrderItemDynamicSqlSupport.productSkuCode).toPropertyWhenPresent("productSkuCode", record::getProductSkuCode)
            .map(OmsOrderItemDynamicSqlSupport.productCategoryId).toPropertyWhenPresent("productCategoryId", record::getProductCategoryId)
            .map(OmsOrderItemDynamicSqlSupport.promotionName).toPropertyWhenPresent("promotionName", record::getPromotionName)
            .map(OmsOrderItemDynamicSqlSupport.promotionAmount).toPropertyWhenPresent("promotionAmount", record::getPromotionAmount)
            .map(OmsOrderItemDynamicSqlSupport.couponAmount).toPropertyWhenPresent("couponAmount", record::getCouponAmount)
            .map(OmsOrderItemDynamicSqlSupport.integrationAmount).toPropertyWhenPresent("integrationAmount", record::getIntegrationAmount)
            .map(OmsOrderItemDynamicSqlSupport.realAmount).toPropertyWhenPresent("realAmount", record::getRealAmount)
            .map(OmsOrderItemDynamicSqlSupport.giftIntegration).toPropertyWhenPresent("giftIntegration", record::getGiftIntegration)
            .map(OmsOrderItemDynamicSqlSupport.giftGrowth).toPropertyWhenPresent("giftGrowth", record::getGiftGrowth)
            .map(OmsOrderItemDynamicSqlSupport.productAttr).toPropertyWhenPresent("productAttr", record::getProductAttr)
        );
    }

    default Optional<OmsOrderItem> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, OmsOrderItemDynamicSqlSupport.omsOrderItem, completer);
    }

    default List<OmsOrderItem> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, OmsOrderItemDynamicSqlSupport.omsOrderItem, completer);
    }

    default List<OmsOrderItem> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, OmsOrderItemDynamicSqlSupport.omsOrderItem, completer);
    }

    default Optional<OmsOrderItem> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(OmsOrderItemDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, OmsOrderItemDynamicSqlSupport.omsOrderItem, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(OmsOrderItem record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsOrderItemDynamicSqlSupport.orderId).equalTo(record::getOrderId)
                .set(OmsOrderItemDynamicSqlSupport.orderSn).equalTo(record::getOrderSn)
                .set(OmsOrderItemDynamicSqlSupport.returnId).equalTo(record::getReturnId)
                .set(OmsOrderItemDynamicSqlSupport.productId).equalTo(record::getProductId)
                .set(OmsOrderItemDynamicSqlSupport.productPic).equalTo(record::getProductPic)
                .set(OmsOrderItemDynamicSqlSupport.productName).equalTo(record::getProductName)
                .set(OmsOrderItemDynamicSqlSupport.productBrand).equalTo(record::getProductBrand)
                .set(OmsOrderItemDynamicSqlSupport.productSn).equalTo(record::getProductSn)
                .set(OmsOrderItemDynamicSqlSupport.productPrice).equalTo(record::getProductPrice)
                .set(OmsOrderItemDynamicSqlSupport.productQuantity).equalTo(record::getProductQuantity)
                .set(OmsOrderItemDynamicSqlSupport.productSkuId).equalTo(record::getProductSkuId)
                .set(OmsOrderItemDynamicSqlSupport.productSkuCode).equalTo(record::getProductSkuCode)
                .set(OmsOrderItemDynamicSqlSupport.productCategoryId).equalTo(record::getProductCategoryId)
                .set(OmsOrderItemDynamicSqlSupport.promotionName).equalTo(record::getPromotionName)
                .set(OmsOrderItemDynamicSqlSupport.promotionAmount).equalTo(record::getPromotionAmount)
                .set(OmsOrderItemDynamicSqlSupport.couponAmount).equalTo(record::getCouponAmount)
                .set(OmsOrderItemDynamicSqlSupport.integrationAmount).equalTo(record::getIntegrationAmount)
                .set(OmsOrderItemDynamicSqlSupport.realAmount).equalTo(record::getRealAmount)
                .set(OmsOrderItemDynamicSqlSupport.giftIntegration).equalTo(record::getGiftIntegration)
                .set(OmsOrderItemDynamicSqlSupport.giftGrowth).equalTo(record::getGiftGrowth)
                .set(OmsOrderItemDynamicSqlSupport.productAttr).equalTo(record::getProductAttr);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(OmsOrderItem record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(OmsOrderItemDynamicSqlSupport.orderId).equalToWhenPresent(record::getOrderId)
                .set(OmsOrderItemDynamicSqlSupport.orderSn).equalToWhenPresent(record::getOrderSn)
                .set(OmsOrderItemDynamicSqlSupport.returnId).equalToWhenPresent(record::getReturnId)
                .set(OmsOrderItemDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
                .set(OmsOrderItemDynamicSqlSupport.productPic).equalToWhenPresent(record::getProductPic)
                .set(OmsOrderItemDynamicSqlSupport.productName).equalToWhenPresent(record::getProductName)
                .set(OmsOrderItemDynamicSqlSupport.productBrand).equalToWhenPresent(record::getProductBrand)
                .set(OmsOrderItemDynamicSqlSupport.productSn).equalToWhenPresent(record::getProductSn)
                .set(OmsOrderItemDynamicSqlSupport.productPrice).equalToWhenPresent(record::getProductPrice)
                .set(OmsOrderItemDynamicSqlSupport.productQuantity).equalToWhenPresent(record::getProductQuantity)
                .set(OmsOrderItemDynamicSqlSupport.productSkuId).equalToWhenPresent(record::getProductSkuId)
                .set(OmsOrderItemDynamicSqlSupport.productSkuCode).equalToWhenPresent(record::getProductSkuCode)
                .set(OmsOrderItemDynamicSqlSupport.productCategoryId).equalToWhenPresent(record::getProductCategoryId)
                .set(OmsOrderItemDynamicSqlSupport.promotionName).equalToWhenPresent(record::getPromotionName)
                .set(OmsOrderItemDynamicSqlSupport.promotionAmount).equalToWhenPresent(record::getPromotionAmount)
                .set(OmsOrderItemDynamicSqlSupport.couponAmount).equalToWhenPresent(record::getCouponAmount)
                .set(OmsOrderItemDynamicSqlSupport.integrationAmount).equalToWhenPresent(record::getIntegrationAmount)
                .set(OmsOrderItemDynamicSqlSupport.realAmount).equalToWhenPresent(record::getRealAmount)
                .set(OmsOrderItemDynamicSqlSupport.giftIntegration).equalToWhenPresent(record::getGiftIntegration)
                .set(OmsOrderItemDynamicSqlSupport.giftGrowth).equalToWhenPresent(record::getGiftGrowth)
                .set(OmsOrderItemDynamicSqlSupport.productAttr).equalToWhenPresent(record::getProductAttr);
    }

    default int updateByPrimaryKey(OmsOrderItem record) {
        return update(c ->
            c.set(OmsOrderItemDynamicSqlSupport.orderId).equalTo(record::getOrderId)
            .set(OmsOrderItemDynamicSqlSupport.orderSn).equalTo(record::getOrderSn)
            .set(OmsOrderItemDynamicSqlSupport.returnId).equalTo(record::getReturnId)
            .set(OmsOrderItemDynamicSqlSupport.productId).equalTo(record::getProductId)
            .set(OmsOrderItemDynamicSqlSupport.productPic).equalTo(record::getProductPic)
            .set(OmsOrderItemDynamicSqlSupport.productName).equalTo(record::getProductName)
            .set(OmsOrderItemDynamicSqlSupport.productBrand).equalTo(record::getProductBrand)
            .set(OmsOrderItemDynamicSqlSupport.productSn).equalTo(record::getProductSn)
            .set(OmsOrderItemDynamicSqlSupport.productPrice).equalTo(record::getProductPrice)
            .set(OmsOrderItemDynamicSqlSupport.productQuantity).equalTo(record::getProductQuantity)
            .set(OmsOrderItemDynamicSqlSupport.productSkuId).equalTo(record::getProductSkuId)
            .set(OmsOrderItemDynamicSqlSupport.productSkuCode).equalTo(record::getProductSkuCode)
            .set(OmsOrderItemDynamicSqlSupport.productCategoryId).equalTo(record::getProductCategoryId)
            .set(OmsOrderItemDynamicSqlSupport.promotionName).equalTo(record::getPromotionName)
            .set(OmsOrderItemDynamicSqlSupport.promotionAmount).equalTo(record::getPromotionAmount)
            .set(OmsOrderItemDynamicSqlSupport.couponAmount).equalTo(record::getCouponAmount)
            .set(OmsOrderItemDynamicSqlSupport.integrationAmount).equalTo(record::getIntegrationAmount)
            .set(OmsOrderItemDynamicSqlSupport.realAmount).equalTo(record::getRealAmount)
            .set(OmsOrderItemDynamicSqlSupport.giftIntegration).equalTo(record::getGiftIntegration)
            .set(OmsOrderItemDynamicSqlSupport.giftGrowth).equalTo(record::getGiftGrowth)
            .set(OmsOrderItemDynamicSqlSupport.productAttr).equalTo(record::getProductAttr)
            .where(OmsOrderItemDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(OmsOrderItem record) {
        return update(c ->
            c.set(OmsOrderItemDynamicSqlSupport.orderId).equalToWhenPresent(record::getOrderId)
            .set(OmsOrderItemDynamicSqlSupport.orderSn).equalToWhenPresent(record::getOrderSn)
            .set(OmsOrderItemDynamicSqlSupport.returnId).equalToWhenPresent(record::getReturnId)
            .set(OmsOrderItemDynamicSqlSupport.productId).equalToWhenPresent(record::getProductId)
            .set(OmsOrderItemDynamicSqlSupport.productPic).equalToWhenPresent(record::getProductPic)
            .set(OmsOrderItemDynamicSqlSupport.productName).equalToWhenPresent(record::getProductName)
            .set(OmsOrderItemDynamicSqlSupport.productBrand).equalToWhenPresent(record::getProductBrand)
            .set(OmsOrderItemDynamicSqlSupport.productSn).equalToWhenPresent(record::getProductSn)
            .set(OmsOrderItemDynamicSqlSupport.productPrice).equalToWhenPresent(record::getProductPrice)
            .set(OmsOrderItemDynamicSqlSupport.productQuantity).equalToWhenPresent(record::getProductQuantity)
            .set(OmsOrderItemDynamicSqlSupport.productSkuId).equalToWhenPresent(record::getProductSkuId)
            .set(OmsOrderItemDynamicSqlSupport.productSkuCode).equalToWhenPresent(record::getProductSkuCode)
            .set(OmsOrderItemDynamicSqlSupport.productCategoryId).equalToWhenPresent(record::getProductCategoryId)
            .set(OmsOrderItemDynamicSqlSupport.promotionName).equalToWhenPresent(record::getPromotionName)
            .set(OmsOrderItemDynamicSqlSupport.promotionAmount).equalToWhenPresent(record::getPromotionAmount)
            .set(OmsOrderItemDynamicSqlSupport.couponAmount).equalToWhenPresent(record::getCouponAmount)
            .set(OmsOrderItemDynamicSqlSupport.integrationAmount).equalToWhenPresent(record::getIntegrationAmount)
            .set(OmsOrderItemDynamicSqlSupport.realAmount).equalToWhenPresent(record::getRealAmount)
            .set(OmsOrderItemDynamicSqlSupport.giftIntegration).equalToWhenPresent(record::getGiftIntegration)
            .set(OmsOrderItemDynamicSqlSupport.giftGrowth).equalToWhenPresent(record::getGiftGrowth)
            .set(OmsOrderItemDynamicSqlSupport.productAttr).equalToWhenPresent(record::getProductAttr)
            .where(OmsOrderItemDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}