/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CmsSubjectCommentDynamicSqlSupport {
    public static final CmsSubjectComment cmsSubjectComment = new CmsSubjectComment();

    public static final SqlColumn<Long> id = cmsSubjectComment.id;

    public static final SqlColumn<Long> subjectId = cmsSubjectComment.subjectId;

    public static final SqlColumn<String> memberNickName = cmsSubjectComment.memberNickName;

    public static final SqlColumn<String> memberIcon = cmsSubjectComment.memberIcon;

    public static final SqlColumn<String> content = cmsSubjectComment.content;

    public static final SqlColumn<Integer> showStatus = cmsSubjectComment.showStatus;

    public static final SqlColumn<LocalDateTime> createTime = cmsSubjectComment.createTime;

    public static final class CmsSubjectComment extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> subjectId = column("subject_id", JDBCType.BIGINT);

        public final SqlColumn<String> memberNickName = column("member_nick_name", JDBCType.VARCHAR);

        public final SqlColumn<String> memberIcon = column("member_icon", JDBCType.VARCHAR);

        public final SqlColumn<String> content = column("content", JDBCType.VARCHAR);

        public final SqlColumn<Integer> showStatus = column("show_status", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public CmsSubjectComment() {
            super("cms_subject_comment");
        }
    }
}