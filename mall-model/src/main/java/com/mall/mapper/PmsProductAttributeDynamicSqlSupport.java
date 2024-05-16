/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsProductAttributeDynamicSqlSupport {
    public static final PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();

    public static final SqlColumn<Long> id = pmsProductAttribute.id;

    public static final SqlColumn<Long> attributeCategoryId = pmsProductAttribute.attributeCategoryId;

    public static final SqlColumn<String> name = pmsProductAttribute.name;

    public static final SqlColumn<Integer> selectType = pmsProductAttribute.selectType;

    public static final SqlColumn<Integer> inputType = pmsProductAttribute.inputType;

    public static final SqlColumn<String> inputList = pmsProductAttribute.inputList;

    public static final SqlColumn<Integer> sort = pmsProductAttribute.sort;

    public static final SqlColumn<Integer> filterType = pmsProductAttribute.filterType;

    public static final SqlColumn<Integer> searchType = pmsProductAttribute.searchType;

    public static final SqlColumn<Integer> relatedStatus = pmsProductAttribute.relatedStatus;

    public static final SqlColumn<Integer> handAddStatus = pmsProductAttribute.handAddStatus;

    public static final SqlColumn<Integer> type = pmsProductAttribute.type;

    public static final class PmsProductAttribute extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> attributeCategoryId = column("attribute_category_id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> selectType = column("select_type", JDBCType.INTEGER);

        public final SqlColumn<Integer> inputType = column("input_type", JDBCType.INTEGER);

        public final SqlColumn<String> inputList = column("input_list", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<Integer> filterType = column("filter_type", JDBCType.INTEGER);

        public final SqlColumn<Integer> searchType = column("search_type", JDBCType.INTEGER);

        public final SqlColumn<Integer> relatedStatus = column("related_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> handAddStatus = column("hand_add_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> type = column("type", JDBCType.INTEGER);

        public PmsProductAttribute() {
            super("pms_product_attribute");
        }
    }
}