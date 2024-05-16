/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsProductDynamicSqlSupport {
    public static final PmsProduct pmsProduct = new PmsProduct();

    public static final SqlColumn<Long> id = pmsProduct.id;

    public static final SqlColumn<Long> brandId = pmsProduct.brandId;

    public static final SqlColumn<Long> categoryId = pmsProduct.categoryId;

    public static final SqlColumn<Long> freightTemplateId = pmsProduct.freightTemplateId;

    public static final SqlColumn<Long> attributeCategoryId = pmsProduct.attributeCategoryId;

    public static final SqlColumn<String> name = pmsProduct.name;

    public static final SqlColumn<String> pic = pmsProduct.pic;

    public static final SqlColumn<String> productSn = pmsProduct.productSn;

    public static final SqlColumn<Integer> deleteStatus = pmsProduct.deleteStatus;

    public static final SqlColumn<Integer> publishStatus = pmsProduct.publishStatus;

    public static final SqlColumn<Integer> newStatus = pmsProduct.newStatus;

    public static final SqlColumn<Integer> recommendStatus = pmsProduct.recommendStatus;

    public static final SqlColumn<Integer> verifyStatus = pmsProduct.verifyStatus;

    public static final SqlColumn<Integer> sort = pmsProduct.sort;

    public static final SqlColumn<Integer> sale = pmsProduct.sale;

    public static final SqlColumn<BigDecimal> price = pmsProduct.price;

    public static final SqlColumn<BigDecimal> promotionPrice = pmsProduct.promotionPrice;

    public static final SqlColumn<Integer> giftGrowth = pmsProduct.giftGrowth;

    public static final SqlColumn<Integer> giftPoint = pmsProduct.giftPoint;

    public static final SqlColumn<Integer> usePointLimit = pmsProduct.usePointLimit;

    public static final SqlColumn<String> subTitle = pmsProduct.subTitle;

    public static final SqlColumn<BigDecimal> originalPrice = pmsProduct.originalPrice;

    public static final SqlColumn<Integer> stock = pmsProduct.stock;

    public static final SqlColumn<Integer> lowStock = pmsProduct.lowStock;

    public static final SqlColumn<String> unit = pmsProduct.unit;

    public static final SqlColumn<BigDecimal> weight = pmsProduct.weight;

    public static final SqlColumn<Integer> previewStatus = pmsProduct.previewStatus;

    public static final SqlColumn<String> serviceIds = pmsProduct.serviceIds;

    public static final SqlColumn<String> keywords = pmsProduct.keywords;

    public static final SqlColumn<String> note = pmsProduct.note;

    public static final SqlColumn<String> albumPics = pmsProduct.albumPics;

    public static final SqlColumn<String> detailTitle = pmsProduct.detailTitle;

    public static final SqlColumn<LocalDateTime> promotionStartTime = pmsProduct.promotionStartTime;

    public static final SqlColumn<LocalDateTime> promotionEndTime = pmsProduct.promotionEndTime;

    public static final SqlColumn<Integer> promotionPerLimit = pmsProduct.promotionPerLimit;

    public static final SqlColumn<Integer> promotionType = pmsProduct.promotionType;

    public static final SqlColumn<String> brandName = pmsProduct.brandName;

    public static final SqlColumn<String> productCategoryName = pmsProduct.productCategoryName;

    public static final SqlColumn<String> description = pmsProduct.description;

    public static final SqlColumn<String> detailDesc = pmsProduct.detailDesc;

    public static final SqlColumn<String> detailHtml = pmsProduct.detailHtml;

    public static final SqlColumn<String> detailMobileHtml = pmsProduct.detailMobileHtml;

    public static final class PmsProduct extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> brandId = column("brand_id", JDBCType.BIGINT);

        public final SqlColumn<Long> categoryId = column("category_id", JDBCType.BIGINT);

        public final SqlColumn<Long> freightTemplateId = column("freight_template_id", JDBCType.BIGINT);

        public final SqlColumn<Long> attributeCategoryId = column("attribute_category_id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> pic = column("pic", JDBCType.VARCHAR);

        public final SqlColumn<String> productSn = column("product_sn", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deleteStatus = column("delete_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> publishStatus = column("publish_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> newStatus = column("new_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> recommendStatus = column("recommend_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> verifyStatus = column("verify_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<Integer> sale = column("sale", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> price = column("price", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> promotionPrice = column("promotion_price", JDBCType.DECIMAL);

        public final SqlColumn<Integer> giftGrowth = column("gift_growth", JDBCType.INTEGER);

        public final SqlColumn<Integer> giftPoint = column("gift_point", JDBCType.INTEGER);

        public final SqlColumn<Integer> usePointLimit = column("use_point_limit", JDBCType.INTEGER);

        public final SqlColumn<String> subTitle = column("sub_title", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> originalPrice = column("original_price", JDBCType.DECIMAL);

        public final SqlColumn<Integer> stock = column("stock", JDBCType.INTEGER);

        public final SqlColumn<Integer> lowStock = column("low_stock", JDBCType.INTEGER);

        public final SqlColumn<String> unit = column("unit", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> weight = column("weight", JDBCType.DECIMAL);

        public final SqlColumn<Integer> previewStatus = column("preview_status", JDBCType.INTEGER);

        public final SqlColumn<String> serviceIds = column("service_ids", JDBCType.VARCHAR);

        public final SqlColumn<String> keywords = column("keywords", JDBCType.VARCHAR);

        public final SqlColumn<String> note = column("note", JDBCType.VARCHAR);

        public final SqlColumn<String> albumPics = column("album_pics", JDBCType.VARCHAR);

        public final SqlColumn<String> detailTitle = column("detail_title", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> promotionStartTime = column("promotion_start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> promotionEndTime = column("promotion_end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> promotionPerLimit = column("promotion_per_limit", JDBCType.INTEGER);

        public final SqlColumn<Integer> promotionType = column("promotion_type", JDBCType.INTEGER);

        public final SqlColumn<String> brandName = column("brand_name", JDBCType.VARCHAR);

        public final SqlColumn<String> productCategoryName = column("product_category_name", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.LONGVARCHAR);

        public final SqlColumn<String> detailDesc = column("detail_desc", JDBCType.LONGVARCHAR);

        public final SqlColumn<String> detailHtml = column("detail_html", JDBCType.LONGVARCHAR);

        public final SqlColumn<String> detailMobileHtml = column("detail_mobile_html", JDBCType.LONGVARCHAR);

        public PmsProduct() {
            super("pms_product");
        }
    }
}