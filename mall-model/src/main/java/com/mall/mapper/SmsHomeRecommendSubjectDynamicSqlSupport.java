/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SmsHomeRecommendSubjectDynamicSqlSupport {
    public static final SmsHomeRecommendSubject smsHomeRecommendSubject = new SmsHomeRecommendSubject();

    public static final SqlColumn<Long> id = smsHomeRecommendSubject.id;

    public static final SqlColumn<Long> subjectId = smsHomeRecommendSubject.subjectId;

    public static final SqlColumn<String> subjectName = smsHomeRecommendSubject.subjectName;

    public static final SqlColumn<Integer> recommendStatus = smsHomeRecommendSubject.recommendStatus;

    public static final SqlColumn<Integer> sort = smsHomeRecommendSubject.sort;

    public static final class SmsHomeRecommendSubject extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> subjectId = column("subject_id", JDBCType.BIGINT);

        public final SqlColumn<String> subjectName = column("subject_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> recommendStatus = column("recommend_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public SmsHomeRecommendSubject() {
            super("sms_home_recommend_subject");
        }
    }
}