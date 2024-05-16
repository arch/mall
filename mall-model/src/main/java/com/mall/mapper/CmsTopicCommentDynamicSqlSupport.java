/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CmsTopicCommentDynamicSqlSupport {
    public static final CmsTopicComment cmsTopicComment = new CmsTopicComment();

    public static final SqlColumn<Long> id = cmsTopicComment.id;

    public static final SqlColumn<String> memberNickName = cmsTopicComment.memberNickName;

    public static final SqlColumn<Long> topicId = cmsTopicComment.topicId;

    public static final SqlColumn<String> memberIcon = cmsTopicComment.memberIcon;

    public static final SqlColumn<String> content = cmsTopicComment.content;

    public static final SqlColumn<Integer> showStatus = cmsTopicComment.showStatus;

    public static final SqlColumn<LocalDateTime> createTime = cmsTopicComment.createTime;

    public static final class CmsTopicComment extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> memberNickName = column("member_nick_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> topicId = column("topic_id", JDBCType.BIGINT);

        public final SqlColumn<String> memberIcon = column("member_icon", JDBCType.VARCHAR);

        public final SqlColumn<String> content = column("content", JDBCType.VARCHAR);

        public final SqlColumn<Integer> showStatus = column("show_status", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public CmsTopicComment() {
            super("cms_topic_comment");
        }
    }
}