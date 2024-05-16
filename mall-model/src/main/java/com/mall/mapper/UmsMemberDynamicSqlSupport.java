/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsMemberDynamicSqlSupport {
    public static final UmsMember umsMember = new UmsMember();

    public static final SqlColumn<Long> id = umsMember.id;

    public static final SqlColumn<Long> levelId = umsMember.levelId;

    public static final SqlColumn<String> openid = umsMember.openid;

    public static final SqlColumn<String> username = umsMember.username;

    public static final SqlColumn<String> nickname = umsMember.nickname;

    public static final SqlColumn<String> phone = umsMember.phone;

    public static final SqlColumn<String> icon = umsMember.icon;

    public static final SqlColumn<Integer> gender = umsMember.gender;

    public static final SqlColumn<LocalDate> birthday = umsMember.birthday;

    public static final SqlColumn<String> city = umsMember.city;

    public static final SqlColumn<String> province = umsMember.province;

    public static final SqlColumn<String> country = umsMember.country;

    public static final SqlColumn<String> language = umsMember.language;

    public static final SqlColumn<String> job = umsMember.job;

    public static final SqlColumn<String> bio = umsMember.bio;

    public static final SqlColumn<Integer> status = umsMember.status;

    public static final SqlColumn<Integer> sourceType = umsMember.sourceType;

    public static final SqlColumn<Integer> integration = umsMember.integration;

    public static final SqlColumn<Integer> growth = umsMember.growth;

    public static final SqlColumn<Integer> luckyCount = umsMember.luckyCount;

    public static final SqlColumn<Integer> historyIntegration = umsMember.historyIntegration;

    public static final SqlColumn<LocalDateTime> createTime = umsMember.createTime;

    public static final class UmsMember extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> levelId = column("level_id", JDBCType.BIGINT);

        public final SqlColumn<String> openid = column("openid", JDBCType.VARCHAR);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> nickname = column("nickname", JDBCType.VARCHAR);

        public final SqlColumn<String> phone = column("phone", JDBCType.VARCHAR);

        public final SqlColumn<String> icon = column("icon", JDBCType.VARCHAR);

        public final SqlColumn<Integer> gender = column("gender", JDBCType.INTEGER);

        public final SqlColumn<LocalDate> birthday = column("birthday", JDBCType.DATE);

        public final SqlColumn<String> city = column("city", JDBCType.VARCHAR);

        public final SqlColumn<String> province = column("province", JDBCType.VARCHAR);

        public final SqlColumn<String> country = column("country", JDBCType.VARCHAR);

        public final SqlColumn<String> language = column("language", JDBCType.VARCHAR);

        public final SqlColumn<String> job = column("job", JDBCType.VARCHAR);

        public final SqlColumn<String> bio = column("bio", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.INTEGER);

        public final SqlColumn<Integer> sourceType = column("source_type", JDBCType.INTEGER);

        public final SqlColumn<Integer> integration = column("integration", JDBCType.INTEGER);

        public final SqlColumn<Integer> growth = column("growth", JDBCType.INTEGER);

        public final SqlColumn<Integer> luckyCount = column("lucky_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> historyIntegration = column("history_integration", JDBCType.INTEGER);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public UmsMember() {
            super("ums_member");
        }
    }
}