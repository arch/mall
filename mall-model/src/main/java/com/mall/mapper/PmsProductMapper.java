/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.mall.model.PmsProduct;

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
public interface PmsProductMapper {
    BasicColumn[] selectList = BasicColumn.columnList(PmsProductDynamicSqlSupport.id, PmsProductDynamicSqlSupport.brandId, PmsProductDynamicSqlSupport.categoryId, PmsProductDynamicSqlSupport.freightTemplateId, PmsProductDynamicSqlSupport.attributeCategoryId, PmsProductDynamicSqlSupport.name, PmsProductDynamicSqlSupport.pic, PmsProductDynamicSqlSupport.productSn, PmsProductDynamicSqlSupport.deleteStatus, PmsProductDynamicSqlSupport.publishStatus, PmsProductDynamicSqlSupport.newStatus, PmsProductDynamicSqlSupport.recommendStatus, PmsProductDynamicSqlSupport.verifyStatus, PmsProductDynamicSqlSupport.sort, PmsProductDynamicSqlSupport.sale, PmsProductDynamicSqlSupport.price, PmsProductDynamicSqlSupport.promotionPrice, PmsProductDynamicSqlSupport.giftGrowth, PmsProductDynamicSqlSupport.giftPoint, PmsProductDynamicSqlSupport.usePointLimit, PmsProductDynamicSqlSupport.subTitle, PmsProductDynamicSqlSupport.originalPrice, PmsProductDynamicSqlSupport.stock, PmsProductDynamicSqlSupport.lowStock, PmsProductDynamicSqlSupport.unit, PmsProductDynamicSqlSupport.weight, PmsProductDynamicSqlSupport.previewStatus, PmsProductDynamicSqlSupport.serviceIds, PmsProductDynamicSqlSupport.keywords, PmsProductDynamicSqlSupport.note, PmsProductDynamicSqlSupport.albumPics, PmsProductDynamicSqlSupport.detailTitle, PmsProductDynamicSqlSupport.promotionStartTime, PmsProductDynamicSqlSupport.promotionEndTime, PmsProductDynamicSqlSupport.promotionPerLimit, PmsProductDynamicSqlSupport.promotionType, PmsProductDynamicSqlSupport.brandName, PmsProductDynamicSqlSupport.productCategoryName, PmsProductDynamicSqlSupport.description, PmsProductDynamicSqlSupport.detailDesc, PmsProductDynamicSqlSupport.detailHtml, PmsProductDynamicSqlSupport.detailMobileHtml);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<PmsProduct> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PmsProductResult")
    Optional<PmsProduct> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PmsProductResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="brand_id", property="brandId", jdbcType=JdbcType.BIGINT),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="freight_template_id", property="freightTemplateId", jdbcType=JdbcType.BIGINT),
        @Result(column="attribute_category_id", property="attributeCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_sn", property="productSn", jdbcType=JdbcType.VARCHAR),
        @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="publish_status", property="publishStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="new_status", property="newStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="recommend_status", property="recommendStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="verify_status", property="verifyStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="sale", property="sale", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="promotion_price", property="promotionPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="gift_growth", property="giftGrowth", jdbcType=JdbcType.INTEGER),
        @Result(column="gift_point", property="giftPoint", jdbcType=JdbcType.INTEGER),
        @Result(column="use_point_limit", property="usePointLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="sub_title", property="subTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="original_price", property="originalPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="low_stock", property="lowStock", jdbcType=JdbcType.INTEGER),
        @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR),
        @Result(column="weight", property="weight", jdbcType=JdbcType.DECIMAL),
        @Result(column="preview_status", property="previewStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="service_ids", property="serviceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="album_pics", property="albumPics", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail_title", property="detailTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="promotion_start_time", property="promotionStartTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="promotion_end_time", property="promotionEndTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="promotion_per_limit", property="promotionPerLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="promotion_type", property="promotionType", jdbcType=JdbcType.INTEGER),
        @Result(column="brand_name", property="brandName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_category_name", property="productCategoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="detail_desc", property="detailDesc", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="detail_html", property="detailHtml", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="detail_mobile_html", property="detailMobileHtml", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<PmsProduct> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, PmsProductDynamicSqlSupport.pmsProduct, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, PmsProductDynamicSqlSupport.pmsProduct, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(PmsProductDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int insert(PmsProduct record) {
        return MyBatis3Utils.insert(this::insert, record, PmsProductDynamicSqlSupport.pmsProduct, c ->
            c.map(PmsProductDynamicSqlSupport.brandId).toProperty("brandId")
            .map(PmsProductDynamicSqlSupport.categoryId).toProperty("categoryId")
            .map(PmsProductDynamicSqlSupport.freightTemplateId).toProperty("freightTemplateId")
            .map(PmsProductDynamicSqlSupport.attributeCategoryId).toProperty("attributeCategoryId")
            .map(PmsProductDynamicSqlSupport.name).toProperty("name")
            .map(PmsProductDynamicSqlSupport.pic).toProperty("pic")
            .map(PmsProductDynamicSqlSupport.productSn).toProperty("productSn")
            .map(PmsProductDynamicSqlSupport.deleteStatus).toProperty("deleteStatus")
            .map(PmsProductDynamicSqlSupport.publishStatus).toProperty("publishStatus")
            .map(PmsProductDynamicSqlSupport.newStatus).toProperty("newStatus")
            .map(PmsProductDynamicSqlSupport.recommendStatus).toProperty("recommendStatus")
            .map(PmsProductDynamicSqlSupport.verifyStatus).toProperty("verifyStatus")
            .map(PmsProductDynamicSqlSupport.sort).toProperty("sort")
            .map(PmsProductDynamicSqlSupport.sale).toProperty("sale")
            .map(PmsProductDynamicSqlSupport.price).toProperty("price")
            .map(PmsProductDynamicSqlSupport.promotionPrice).toProperty("promotionPrice")
            .map(PmsProductDynamicSqlSupport.giftGrowth).toProperty("giftGrowth")
            .map(PmsProductDynamicSqlSupport.giftPoint).toProperty("giftPoint")
            .map(PmsProductDynamicSqlSupport.usePointLimit).toProperty("usePointLimit")
            .map(PmsProductDynamicSqlSupport.subTitle).toProperty("subTitle")
            .map(PmsProductDynamicSqlSupport.originalPrice).toProperty("originalPrice")
            .map(PmsProductDynamicSqlSupport.stock).toProperty("stock")
            .map(PmsProductDynamicSqlSupport.lowStock).toProperty("lowStock")
            .map(PmsProductDynamicSqlSupport.unit).toProperty("unit")
            .map(PmsProductDynamicSqlSupport.weight).toProperty("weight")
            .map(PmsProductDynamicSqlSupport.previewStatus).toProperty("previewStatus")
            .map(PmsProductDynamicSqlSupport.serviceIds).toProperty("serviceIds")
            .map(PmsProductDynamicSqlSupport.keywords).toProperty("keywords")
            .map(PmsProductDynamicSqlSupport.note).toProperty("note")
            .map(PmsProductDynamicSqlSupport.albumPics).toProperty("albumPics")
            .map(PmsProductDynamicSqlSupport.detailTitle).toProperty("detailTitle")
            .map(PmsProductDynamicSqlSupport.promotionStartTime).toProperty("promotionStartTime")
            .map(PmsProductDynamicSqlSupport.promotionEndTime).toProperty("promotionEndTime")
            .map(PmsProductDynamicSqlSupport.promotionPerLimit).toProperty("promotionPerLimit")
            .map(PmsProductDynamicSqlSupport.promotionType).toProperty("promotionType")
            .map(PmsProductDynamicSqlSupport.brandName).toProperty("brandName")
            .map(PmsProductDynamicSqlSupport.productCategoryName).toProperty("productCategoryName")
            .map(PmsProductDynamicSqlSupport.description).toProperty("description")
            .map(PmsProductDynamicSqlSupport.detailDesc).toProperty("detailDesc")
            .map(PmsProductDynamicSqlSupport.detailHtml).toProperty("detailHtml")
            .map(PmsProductDynamicSqlSupport.detailMobileHtml).toProperty("detailMobileHtml")
        );
    }

    default int insertSelective(PmsProduct record) {
        return MyBatis3Utils.insert(this::insert, record, PmsProductDynamicSqlSupport.pmsProduct, c ->
            c.map(PmsProductDynamicSqlSupport.brandId).toPropertyWhenPresent("brandId", record::getBrandId)
            .map(PmsProductDynamicSqlSupport.categoryId).toPropertyWhenPresent("categoryId", record::getCategoryId)
            .map(PmsProductDynamicSqlSupport.freightTemplateId).toPropertyWhenPresent("freightTemplateId", record::getFreightTemplateId)
            .map(PmsProductDynamicSqlSupport.attributeCategoryId).toPropertyWhenPresent("attributeCategoryId", record::getAttributeCategoryId)
            .map(PmsProductDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(PmsProductDynamicSqlSupport.pic).toPropertyWhenPresent("pic", record::getPic)
            .map(PmsProductDynamicSqlSupport.productSn).toPropertyWhenPresent("productSn", record::getProductSn)
            .map(PmsProductDynamicSqlSupport.deleteStatus).toPropertyWhenPresent("deleteStatus", record::getDeleteStatus)
            .map(PmsProductDynamicSqlSupport.publishStatus).toPropertyWhenPresent("publishStatus", record::getPublishStatus)
            .map(PmsProductDynamicSqlSupport.newStatus).toPropertyWhenPresent("newStatus", record::getNewStatus)
            .map(PmsProductDynamicSqlSupport.recommendStatus).toPropertyWhenPresent("recommendStatus", record::getRecommendStatus)
            .map(PmsProductDynamicSqlSupport.verifyStatus).toPropertyWhenPresent("verifyStatus", record::getVerifyStatus)
            .map(PmsProductDynamicSqlSupport.sort).toPropertyWhenPresent("sort", record::getSort)
            .map(PmsProductDynamicSqlSupport.sale).toPropertyWhenPresent("sale", record::getSale)
            .map(PmsProductDynamicSqlSupport.price).toPropertyWhenPresent("price", record::getPrice)
            .map(PmsProductDynamicSqlSupport.promotionPrice).toPropertyWhenPresent("promotionPrice", record::getPromotionPrice)
            .map(PmsProductDynamicSqlSupport.giftGrowth).toPropertyWhenPresent("giftGrowth", record::getGiftGrowth)
            .map(PmsProductDynamicSqlSupport.giftPoint).toPropertyWhenPresent("giftPoint", record::getGiftPoint)
            .map(PmsProductDynamicSqlSupport.usePointLimit).toPropertyWhenPresent("usePointLimit", record::getUsePointLimit)
            .map(PmsProductDynamicSqlSupport.subTitle).toPropertyWhenPresent("subTitle", record::getSubTitle)
            .map(PmsProductDynamicSqlSupport.originalPrice).toPropertyWhenPresent("originalPrice", record::getOriginalPrice)
            .map(PmsProductDynamicSqlSupport.stock).toPropertyWhenPresent("stock", record::getStock)
            .map(PmsProductDynamicSqlSupport.lowStock).toPropertyWhenPresent("lowStock", record::getLowStock)
            .map(PmsProductDynamicSqlSupport.unit).toPropertyWhenPresent("unit", record::getUnit)
            .map(PmsProductDynamicSqlSupport.weight).toPropertyWhenPresent("weight", record::getWeight)
            .map(PmsProductDynamicSqlSupport.previewStatus).toPropertyWhenPresent("previewStatus", record::getPreviewStatus)
            .map(PmsProductDynamicSqlSupport.serviceIds).toPropertyWhenPresent("serviceIds", record::getServiceIds)
            .map(PmsProductDynamicSqlSupport.keywords).toPropertyWhenPresent("keywords", record::getKeywords)
            .map(PmsProductDynamicSqlSupport.note).toPropertyWhenPresent("note", record::getNote)
            .map(PmsProductDynamicSqlSupport.albumPics).toPropertyWhenPresent("albumPics", record::getAlbumPics)
            .map(PmsProductDynamicSqlSupport.detailTitle).toPropertyWhenPresent("detailTitle", record::getDetailTitle)
            .map(PmsProductDynamicSqlSupport.promotionStartTime).toPropertyWhenPresent("promotionStartTime", record::getPromotionStartTime)
            .map(PmsProductDynamicSqlSupport.promotionEndTime).toPropertyWhenPresent("promotionEndTime", record::getPromotionEndTime)
            .map(PmsProductDynamicSqlSupport.promotionPerLimit).toPropertyWhenPresent("promotionPerLimit", record::getPromotionPerLimit)
            .map(PmsProductDynamicSqlSupport.promotionType).toPropertyWhenPresent("promotionType", record::getPromotionType)
            .map(PmsProductDynamicSqlSupport.brandName).toPropertyWhenPresent("brandName", record::getBrandName)
            .map(PmsProductDynamicSqlSupport.productCategoryName).toPropertyWhenPresent("productCategoryName", record::getProductCategoryName)
            .map(PmsProductDynamicSqlSupport.description).toPropertyWhenPresent("description", record::getDescription)
            .map(PmsProductDynamicSqlSupport.detailDesc).toPropertyWhenPresent("detailDesc", record::getDetailDesc)
            .map(PmsProductDynamicSqlSupport.detailHtml).toPropertyWhenPresent("detailHtml", record::getDetailHtml)
            .map(PmsProductDynamicSqlSupport.detailMobileHtml).toPropertyWhenPresent("detailMobileHtml", record::getDetailMobileHtml)
        );
    }

    default Optional<PmsProduct> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, PmsProductDynamicSqlSupport.pmsProduct, completer);
    }

    default List<PmsProduct> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, PmsProductDynamicSqlSupport.pmsProduct, completer);
    }

    default List<PmsProduct> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, PmsProductDynamicSqlSupport.pmsProduct, completer);
    }

    default Optional<PmsProduct> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(PmsProductDynamicSqlSupport.id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, PmsProductDynamicSqlSupport.pmsProduct, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(PmsProduct record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsProductDynamicSqlSupport.brandId).equalTo(record::getBrandId)
                .set(PmsProductDynamicSqlSupport.categoryId).equalTo(record::getCategoryId)
                .set(PmsProductDynamicSqlSupport.freightTemplateId).equalTo(record::getFreightTemplateId)
                .set(PmsProductDynamicSqlSupport.attributeCategoryId).equalTo(record::getAttributeCategoryId)
                .set(PmsProductDynamicSqlSupport.name).equalTo(record::getName)
                .set(PmsProductDynamicSqlSupport.pic).equalTo(record::getPic)
                .set(PmsProductDynamicSqlSupport.productSn).equalTo(record::getProductSn)
                .set(PmsProductDynamicSqlSupport.deleteStatus).equalTo(record::getDeleteStatus)
                .set(PmsProductDynamicSqlSupport.publishStatus).equalTo(record::getPublishStatus)
                .set(PmsProductDynamicSqlSupport.newStatus).equalTo(record::getNewStatus)
                .set(PmsProductDynamicSqlSupport.recommendStatus).equalTo(record::getRecommendStatus)
                .set(PmsProductDynamicSqlSupport.verifyStatus).equalTo(record::getVerifyStatus)
                .set(PmsProductDynamicSqlSupport.sort).equalTo(record::getSort)
                .set(PmsProductDynamicSqlSupport.sale).equalTo(record::getSale)
                .set(PmsProductDynamicSqlSupport.price).equalTo(record::getPrice)
                .set(PmsProductDynamicSqlSupport.promotionPrice).equalTo(record::getPromotionPrice)
                .set(PmsProductDynamicSqlSupport.giftGrowth).equalTo(record::getGiftGrowth)
                .set(PmsProductDynamicSqlSupport.giftPoint).equalTo(record::getGiftPoint)
                .set(PmsProductDynamicSqlSupport.usePointLimit).equalTo(record::getUsePointLimit)
                .set(PmsProductDynamicSqlSupport.subTitle).equalTo(record::getSubTitle)
                .set(PmsProductDynamicSqlSupport.originalPrice).equalTo(record::getOriginalPrice)
                .set(PmsProductDynamicSqlSupport.stock).equalTo(record::getStock)
                .set(PmsProductDynamicSqlSupport.lowStock).equalTo(record::getLowStock)
                .set(PmsProductDynamicSqlSupport.unit).equalTo(record::getUnit)
                .set(PmsProductDynamicSqlSupport.weight).equalTo(record::getWeight)
                .set(PmsProductDynamicSqlSupport.previewStatus).equalTo(record::getPreviewStatus)
                .set(PmsProductDynamicSqlSupport.serviceIds).equalTo(record::getServiceIds)
                .set(PmsProductDynamicSqlSupport.keywords).equalTo(record::getKeywords)
                .set(PmsProductDynamicSqlSupport.note).equalTo(record::getNote)
                .set(PmsProductDynamicSqlSupport.albumPics).equalTo(record::getAlbumPics)
                .set(PmsProductDynamicSqlSupport.detailTitle).equalTo(record::getDetailTitle)
                .set(PmsProductDynamicSqlSupport.promotionStartTime).equalTo(record::getPromotionStartTime)
                .set(PmsProductDynamicSqlSupport.promotionEndTime).equalTo(record::getPromotionEndTime)
                .set(PmsProductDynamicSqlSupport.promotionPerLimit).equalTo(record::getPromotionPerLimit)
                .set(PmsProductDynamicSqlSupport.promotionType).equalTo(record::getPromotionType)
                .set(PmsProductDynamicSqlSupport.brandName).equalTo(record::getBrandName)
                .set(PmsProductDynamicSqlSupport.productCategoryName).equalTo(record::getProductCategoryName)
                .set(PmsProductDynamicSqlSupport.description).equalTo(record::getDescription)
                .set(PmsProductDynamicSqlSupport.detailDesc).equalTo(record::getDetailDesc)
                .set(PmsProductDynamicSqlSupport.detailHtml).equalTo(record::getDetailHtml)
                .set(PmsProductDynamicSqlSupport.detailMobileHtml).equalTo(record::getDetailMobileHtml);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PmsProduct record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(PmsProductDynamicSqlSupport.brandId).equalToWhenPresent(record::getBrandId)
                .set(PmsProductDynamicSqlSupport.categoryId).equalToWhenPresent(record::getCategoryId)
                .set(PmsProductDynamicSqlSupport.freightTemplateId).equalToWhenPresent(record::getFreightTemplateId)
                .set(PmsProductDynamicSqlSupport.attributeCategoryId).equalToWhenPresent(record::getAttributeCategoryId)
                .set(PmsProductDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(PmsProductDynamicSqlSupport.pic).equalToWhenPresent(record::getPic)
                .set(PmsProductDynamicSqlSupport.productSn).equalToWhenPresent(record::getProductSn)
                .set(PmsProductDynamicSqlSupport.deleteStatus).equalToWhenPresent(record::getDeleteStatus)
                .set(PmsProductDynamicSqlSupport.publishStatus).equalToWhenPresent(record::getPublishStatus)
                .set(PmsProductDynamicSqlSupport.newStatus).equalToWhenPresent(record::getNewStatus)
                .set(PmsProductDynamicSqlSupport.recommendStatus).equalToWhenPresent(record::getRecommendStatus)
                .set(PmsProductDynamicSqlSupport.verifyStatus).equalToWhenPresent(record::getVerifyStatus)
                .set(PmsProductDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
                .set(PmsProductDynamicSqlSupport.sale).equalToWhenPresent(record::getSale)
                .set(PmsProductDynamicSqlSupport.price).equalToWhenPresent(record::getPrice)
                .set(PmsProductDynamicSqlSupport.promotionPrice).equalToWhenPresent(record::getPromotionPrice)
                .set(PmsProductDynamicSqlSupport.giftGrowth).equalToWhenPresent(record::getGiftGrowth)
                .set(PmsProductDynamicSqlSupport.giftPoint).equalToWhenPresent(record::getGiftPoint)
                .set(PmsProductDynamicSqlSupport.usePointLimit).equalToWhenPresent(record::getUsePointLimit)
                .set(PmsProductDynamicSqlSupport.subTitle).equalToWhenPresent(record::getSubTitle)
                .set(PmsProductDynamicSqlSupport.originalPrice).equalToWhenPresent(record::getOriginalPrice)
                .set(PmsProductDynamicSqlSupport.stock).equalToWhenPresent(record::getStock)
                .set(PmsProductDynamicSqlSupport.lowStock).equalToWhenPresent(record::getLowStock)
                .set(PmsProductDynamicSqlSupport.unit).equalToWhenPresent(record::getUnit)
                .set(PmsProductDynamicSqlSupport.weight).equalToWhenPresent(record::getWeight)
                .set(PmsProductDynamicSqlSupport.previewStatus).equalToWhenPresent(record::getPreviewStatus)
                .set(PmsProductDynamicSqlSupport.serviceIds).equalToWhenPresent(record::getServiceIds)
                .set(PmsProductDynamicSqlSupport.keywords).equalToWhenPresent(record::getKeywords)
                .set(PmsProductDynamicSqlSupport.note).equalToWhenPresent(record::getNote)
                .set(PmsProductDynamicSqlSupport.albumPics).equalToWhenPresent(record::getAlbumPics)
                .set(PmsProductDynamicSqlSupport.detailTitle).equalToWhenPresent(record::getDetailTitle)
                .set(PmsProductDynamicSqlSupport.promotionStartTime).equalToWhenPresent(record::getPromotionStartTime)
                .set(PmsProductDynamicSqlSupport.promotionEndTime).equalToWhenPresent(record::getPromotionEndTime)
                .set(PmsProductDynamicSqlSupport.promotionPerLimit).equalToWhenPresent(record::getPromotionPerLimit)
                .set(PmsProductDynamicSqlSupport.promotionType).equalToWhenPresent(record::getPromotionType)
                .set(PmsProductDynamicSqlSupport.brandName).equalToWhenPresent(record::getBrandName)
                .set(PmsProductDynamicSqlSupport.productCategoryName).equalToWhenPresent(record::getProductCategoryName)
                .set(PmsProductDynamicSqlSupport.description).equalToWhenPresent(record::getDescription)
                .set(PmsProductDynamicSqlSupport.detailDesc).equalToWhenPresent(record::getDetailDesc)
                .set(PmsProductDynamicSqlSupport.detailHtml).equalToWhenPresent(record::getDetailHtml)
                .set(PmsProductDynamicSqlSupport.detailMobileHtml).equalToWhenPresent(record::getDetailMobileHtml);
    }

    default int updateByPrimaryKey(PmsProduct record) {
        return update(c ->
            c.set(PmsProductDynamicSqlSupport.brandId).equalTo(record::getBrandId)
            .set(PmsProductDynamicSqlSupport.categoryId).equalTo(record::getCategoryId)
            .set(PmsProductDynamicSqlSupport.freightTemplateId).equalTo(record::getFreightTemplateId)
            .set(PmsProductDynamicSqlSupport.attributeCategoryId).equalTo(record::getAttributeCategoryId)
            .set(PmsProductDynamicSqlSupport.name).equalTo(record::getName)
            .set(PmsProductDynamicSqlSupport.pic).equalTo(record::getPic)
            .set(PmsProductDynamicSqlSupport.productSn).equalTo(record::getProductSn)
            .set(PmsProductDynamicSqlSupport.deleteStatus).equalTo(record::getDeleteStatus)
            .set(PmsProductDynamicSqlSupport.publishStatus).equalTo(record::getPublishStatus)
            .set(PmsProductDynamicSqlSupport.newStatus).equalTo(record::getNewStatus)
            .set(PmsProductDynamicSqlSupport.recommendStatus).equalTo(record::getRecommendStatus)
            .set(PmsProductDynamicSqlSupport.verifyStatus).equalTo(record::getVerifyStatus)
            .set(PmsProductDynamicSqlSupport.sort).equalTo(record::getSort)
            .set(PmsProductDynamicSqlSupport.sale).equalTo(record::getSale)
            .set(PmsProductDynamicSqlSupport.price).equalTo(record::getPrice)
            .set(PmsProductDynamicSqlSupport.promotionPrice).equalTo(record::getPromotionPrice)
            .set(PmsProductDynamicSqlSupport.giftGrowth).equalTo(record::getGiftGrowth)
            .set(PmsProductDynamicSqlSupport.giftPoint).equalTo(record::getGiftPoint)
            .set(PmsProductDynamicSqlSupport.usePointLimit).equalTo(record::getUsePointLimit)
            .set(PmsProductDynamicSqlSupport.subTitle).equalTo(record::getSubTitle)
            .set(PmsProductDynamicSqlSupport.originalPrice).equalTo(record::getOriginalPrice)
            .set(PmsProductDynamicSqlSupport.stock).equalTo(record::getStock)
            .set(PmsProductDynamicSqlSupport.lowStock).equalTo(record::getLowStock)
            .set(PmsProductDynamicSqlSupport.unit).equalTo(record::getUnit)
            .set(PmsProductDynamicSqlSupport.weight).equalTo(record::getWeight)
            .set(PmsProductDynamicSqlSupport.previewStatus).equalTo(record::getPreviewStatus)
            .set(PmsProductDynamicSqlSupport.serviceIds).equalTo(record::getServiceIds)
            .set(PmsProductDynamicSqlSupport.keywords).equalTo(record::getKeywords)
            .set(PmsProductDynamicSqlSupport.note).equalTo(record::getNote)
            .set(PmsProductDynamicSqlSupport.albumPics).equalTo(record::getAlbumPics)
            .set(PmsProductDynamicSqlSupport.detailTitle).equalTo(record::getDetailTitle)
            .set(PmsProductDynamicSqlSupport.promotionStartTime).equalTo(record::getPromotionStartTime)
            .set(PmsProductDynamicSqlSupport.promotionEndTime).equalTo(record::getPromotionEndTime)
            .set(PmsProductDynamicSqlSupport.promotionPerLimit).equalTo(record::getPromotionPerLimit)
            .set(PmsProductDynamicSqlSupport.promotionType).equalTo(record::getPromotionType)
            .set(PmsProductDynamicSqlSupport.brandName).equalTo(record::getBrandName)
            .set(PmsProductDynamicSqlSupport.productCategoryName).equalTo(record::getProductCategoryName)
            .set(PmsProductDynamicSqlSupport.description).equalTo(record::getDescription)
            .set(PmsProductDynamicSqlSupport.detailDesc).equalTo(record::getDetailDesc)
            .set(PmsProductDynamicSqlSupport.detailHtml).equalTo(record::getDetailHtml)
            .set(PmsProductDynamicSqlSupport.detailMobileHtml).equalTo(record::getDetailMobileHtml)
            .where(PmsProductDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PmsProduct record) {
        return update(c ->
            c.set(PmsProductDynamicSqlSupport.brandId).equalToWhenPresent(record::getBrandId)
            .set(PmsProductDynamicSqlSupport.categoryId).equalToWhenPresent(record::getCategoryId)
            .set(PmsProductDynamicSqlSupport.freightTemplateId).equalToWhenPresent(record::getFreightTemplateId)
            .set(PmsProductDynamicSqlSupport.attributeCategoryId).equalToWhenPresent(record::getAttributeCategoryId)
            .set(PmsProductDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(PmsProductDynamicSqlSupport.pic).equalToWhenPresent(record::getPic)
            .set(PmsProductDynamicSqlSupport.productSn).equalToWhenPresent(record::getProductSn)
            .set(PmsProductDynamicSqlSupport.deleteStatus).equalToWhenPresent(record::getDeleteStatus)
            .set(PmsProductDynamicSqlSupport.publishStatus).equalToWhenPresent(record::getPublishStatus)
            .set(PmsProductDynamicSqlSupport.newStatus).equalToWhenPresent(record::getNewStatus)
            .set(PmsProductDynamicSqlSupport.recommendStatus).equalToWhenPresent(record::getRecommendStatus)
            .set(PmsProductDynamicSqlSupport.verifyStatus).equalToWhenPresent(record::getVerifyStatus)
            .set(PmsProductDynamicSqlSupport.sort).equalToWhenPresent(record::getSort)
            .set(PmsProductDynamicSqlSupport.sale).equalToWhenPresent(record::getSale)
            .set(PmsProductDynamicSqlSupport.price).equalToWhenPresent(record::getPrice)
            .set(PmsProductDynamicSqlSupport.promotionPrice).equalToWhenPresent(record::getPromotionPrice)
            .set(PmsProductDynamicSqlSupport.giftGrowth).equalToWhenPresent(record::getGiftGrowth)
            .set(PmsProductDynamicSqlSupport.giftPoint).equalToWhenPresent(record::getGiftPoint)
            .set(PmsProductDynamicSqlSupport.usePointLimit).equalToWhenPresent(record::getUsePointLimit)
            .set(PmsProductDynamicSqlSupport.subTitle).equalToWhenPresent(record::getSubTitle)
            .set(PmsProductDynamicSqlSupport.originalPrice).equalToWhenPresent(record::getOriginalPrice)
            .set(PmsProductDynamicSqlSupport.stock).equalToWhenPresent(record::getStock)
            .set(PmsProductDynamicSqlSupport.lowStock).equalToWhenPresent(record::getLowStock)
            .set(PmsProductDynamicSqlSupport.unit).equalToWhenPresent(record::getUnit)
            .set(PmsProductDynamicSqlSupport.weight).equalToWhenPresent(record::getWeight)
            .set(PmsProductDynamicSqlSupport.previewStatus).equalToWhenPresent(record::getPreviewStatus)
            .set(PmsProductDynamicSqlSupport.serviceIds).equalToWhenPresent(record::getServiceIds)
            .set(PmsProductDynamicSqlSupport.keywords).equalToWhenPresent(record::getKeywords)
            .set(PmsProductDynamicSqlSupport.note).equalToWhenPresent(record::getNote)
            .set(PmsProductDynamicSqlSupport.albumPics).equalToWhenPresent(record::getAlbumPics)
            .set(PmsProductDynamicSqlSupport.detailTitle).equalToWhenPresent(record::getDetailTitle)
            .set(PmsProductDynamicSqlSupport.promotionStartTime).equalToWhenPresent(record::getPromotionStartTime)
            .set(PmsProductDynamicSqlSupport.promotionEndTime).equalToWhenPresent(record::getPromotionEndTime)
            .set(PmsProductDynamicSqlSupport.promotionPerLimit).equalToWhenPresent(record::getPromotionPerLimit)
            .set(PmsProductDynamicSqlSupport.promotionType).equalToWhenPresent(record::getPromotionType)
            .set(PmsProductDynamicSqlSupport.brandName).equalToWhenPresent(record::getBrandName)
            .set(PmsProductDynamicSqlSupport.productCategoryName).equalToWhenPresent(record::getProductCategoryName)
            .set(PmsProductDynamicSqlSupport.description).equalToWhenPresent(record::getDescription)
            .set(PmsProductDynamicSqlSupport.detailDesc).equalToWhenPresent(record::getDetailDesc)
            .set(PmsProductDynamicSqlSupport.detailHtml).equalToWhenPresent(record::getDetailHtml)
            .set(PmsProductDynamicSqlSupport.detailMobileHtml).equalToWhenPresent(record::getDetailMobileHtml)
            .where(PmsProductDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}