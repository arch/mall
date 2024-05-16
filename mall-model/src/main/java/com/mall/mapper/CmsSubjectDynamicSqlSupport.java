/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CmsSubjectDynamicSqlSupport {
    public static final CmsSubject cmsSubject = new CmsSubject();

    public static final SqlColumn<Long> id = cmsSubject.id;

    public static final SqlColumn<Long> categoryId = cmsSubject.categoryId;

    public static final SqlColumn<String> title = cmsSubject.title;

    public static final SqlColumn<String> pic = cmsSubject.pic;

    public static final SqlColumn<Integer> productCount = cmsSubject.productCount;

    public static final SqlColumn<Integer> recommendStatus = cmsSubject.recommendStatus;

    public static final SqlColumn<Integer> collectCount = cmsSubject.collectCount;

    public static final SqlColumn<Integer> readCount = cmsSubject.readCount;

    public static final SqlColumn<Integer> commentCount = cmsSubject.commentCount;

    public static final SqlColumn<String> albumPics = cmsSubject.albumPics;

    public static final SqlColumn<String> description = cmsSubject.description;

    public static final SqlColumn<Integer> showStatus = cmsSubject.showStatus;

    public static final SqlColumn<Integer> forwardCount = cmsSubject.forwardCount;

    public static final SqlColumn<String> categoryName = cmsSubject.categoryName;

    public static final SqlColumn<LocalDateTime> createTime = cmsSubject.createTime;

    public static final SqlColumn<String> content = cmsSubject.content;

    public static final class CmsSubject extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> categoryId = column("category_id", JDBCType.BIGINT);

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);

        public final SqlColumn<String> pic = column("pic", JDBCType.VARCHAR);

        public final SqlColumn<Integer> productCount = column("product_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> recommendStatus = column("recommend_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> collectCount = column("collect_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> readCount = column("read_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> commentCount = column("comment_count", JDBCType.INTEGER);

        public final SqlColumn<String> albumPics = column("album_pics", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<Integer> showStatus = column("show_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> forwardCount = column("forward_count", JDBCType.INTEGER);

        public final SqlColumn<String> categoryName = column("category_name", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public CmsSubject() {
            super("cms_subject");
        }
    }
}