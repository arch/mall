/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsBrandDynamicSqlSupport {
    public static final PmsBrand pmsBrand = new PmsBrand();

    public static final SqlColumn<Long> id = pmsBrand.id;

    public static final SqlColumn<String> name = pmsBrand.name;

    public static final SqlColumn<String> firstLetter = pmsBrand.firstLetter;

    public static final SqlColumn<Integer> sort = pmsBrand.sort;

    public static final SqlColumn<Integer> factoryStatus = pmsBrand.factoryStatus;

    public static final SqlColumn<Integer> showStatus = pmsBrand.showStatus;

    public static final SqlColumn<Integer> productCount = pmsBrand.productCount;

    public static final SqlColumn<Integer> productCommentCount = pmsBrand.productCommentCount;

    public static final SqlColumn<String> logo = pmsBrand.logo;

    public static final SqlColumn<String> bigPic = pmsBrand.bigPic;

    public static final SqlColumn<String> brandStory = pmsBrand.brandStory;

    public static final class PmsBrand extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> firstLetter = column("first_letter", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<Integer> factoryStatus = column("factory_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> showStatus = column("show_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> productCount = column("product_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> productCommentCount = column("product_comment_count", JDBCType.INTEGER);

        public final SqlColumn<String> logo = column("logo", JDBCType.VARCHAR);

        public final SqlColumn<String> bigPic = column("big_pic", JDBCType.VARCHAR);

        public final SqlColumn<String> brandStory = column("brand_story", JDBCType.LONGVARCHAR);

        public PmsBrand() {
            super("pms_brand");
        }
    }
}