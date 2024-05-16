/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsCommentDynamicSqlSupport {
    public static final PmsComment pmsComment = new PmsComment();

    public static final SqlColumn<Long> id = pmsComment.id;

    public static final SqlColumn<Long> productId = pmsComment.productId;

    public static final SqlColumn<String> memberNickName = pmsComment.memberNickName;

    public static final SqlColumn<String> productName = pmsComment.productName;

    public static final SqlColumn<Integer> star = pmsComment.star;

    public static final SqlColumn<String> memberIp = pmsComment.memberIp;

    public static final SqlColumn<Integer> showStatus = pmsComment.showStatus;

    public static final SqlColumn<String> productAttribute = pmsComment.productAttribute;

    public static final SqlColumn<Integer> collectCount = pmsComment.collectCount;

    public static final SqlColumn<Integer> readCount = pmsComment.readCount;

    public static final SqlColumn<String> pics = pmsComment.pics;

    public static final SqlColumn<String> memberIcon = pmsComment.memberIcon;

    public static final SqlColumn<Integer> replayCount = pmsComment.replayCount;

    public static final SqlColumn<LocalDateTime> createTime = pmsComment.createTime;

    public static final SqlColumn<String> content = pmsComment.content;

    public static final class PmsComment extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<String> memberNickName = column("member_nick_name", JDBCType.VARCHAR);

        public final SqlColumn<String> productName = column("product_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> star = column("star", JDBCType.INTEGER);

        public final SqlColumn<String> memberIp = column("member_ip", JDBCType.VARCHAR);

        public final SqlColumn<Integer> showStatus = column("show_status", JDBCType.INTEGER);

        public final SqlColumn<String> productAttribute = column("product_attribute", JDBCType.VARCHAR);

        public final SqlColumn<Integer> collectCount = column("collect_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> readCount = column("read_count", JDBCType.INTEGER);

        public final SqlColumn<String> pics = column("pics", JDBCType.VARCHAR);

        public final SqlColumn<String> memberIcon = column("member_icon", JDBCType.VARCHAR);

        public final SqlColumn<Integer> replayCount = column("replay_count", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public PmsComment() {
            super("pms_comment");
        }
    }
}