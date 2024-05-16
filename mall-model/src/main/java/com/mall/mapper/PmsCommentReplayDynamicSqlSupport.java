/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsCommentReplayDynamicSqlSupport {
    public static final PmsCommentReplay pmsCommentReplay = new PmsCommentReplay();

    public static final SqlColumn<Long> id = pmsCommentReplay.id;

    public static final SqlColumn<Long> commentId = pmsCommentReplay.commentId;

    public static final SqlColumn<String> memberNickName = pmsCommentReplay.memberNickName;

    public static final SqlColumn<String> memberIcon = pmsCommentReplay.memberIcon;

    public static final SqlColumn<String> content = pmsCommentReplay.content;

    public static final SqlColumn<Integer> type = pmsCommentReplay.type;

    public static final SqlColumn<LocalDateTime> createTime = pmsCommentReplay.createTime;

    public static final class PmsCommentReplay extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> commentId = column("comment_id", JDBCType.BIGINT);

        public final SqlColumn<String> memberNickName = column("member_nick_name", JDBCType.VARCHAR);

        public final SqlColumn<String> memberIcon = column("member_icon", JDBCType.VARCHAR);

        public final SqlColumn<String> content = column("content", JDBCType.VARCHAR);

        public final SqlColumn<Integer> type = column("type", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public PmsCommentReplay() {
            super("pms_comment_replay");
        }
    }
}