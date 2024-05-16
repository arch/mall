/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CmsTopicDynamicSqlSupport {
    public static final CmsTopic cmsTopic = new CmsTopic();

    public static final SqlColumn<Long> id = cmsTopic.id;

    public static final SqlColumn<Long> categoryId = cmsTopic.categoryId;

    public static final SqlColumn<String> name = cmsTopic.name;

    public static final SqlColumn<LocalDateTime> startTime = cmsTopic.startTime;

    public static final SqlColumn<LocalDateTime> endTime = cmsTopic.endTime;

    public static final SqlColumn<Integer> attendCount = cmsTopic.attendCount;

    public static final SqlColumn<Integer> attentionCount = cmsTopic.attentionCount;

    public static final SqlColumn<Integer> readCount = cmsTopic.readCount;

    public static final SqlColumn<String> awardName = cmsTopic.awardName;

    public static final SqlColumn<String> attendType = cmsTopic.attendType;

    public static final SqlColumn<LocalDateTime> createTime = cmsTopic.createTime;

    public static final SqlColumn<String> content = cmsTopic.content;

    public static final class CmsTopic extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> categoryId = column("category_id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> startTime = column("start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<LocalDateTime> endTime = column("end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> attendCount = column("attend_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> attentionCount = column("attention_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> readCount = column("read_count", JDBCType.INTEGER);

        public final SqlColumn<String> awardName = column("award_name", JDBCType.VARCHAR);

        public final SqlColumn<String> attendType = column("attend_type", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public CmsTopic() {
            super("cms_topic");
        }
    }
}