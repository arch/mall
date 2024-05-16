/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsMemberMemberTagRelationDynamicSqlSupport {
    public static final UmsMemberMemberTagRelation umsMemberMemberTagRelation = new UmsMemberMemberTagRelation();

    public static final SqlColumn<Long> id = umsMemberMemberTagRelation.id;

    public static final SqlColumn<Long> memberId = umsMemberMemberTagRelation.memberId;

    public static final SqlColumn<Long> tagId = umsMemberMemberTagRelation.tagId;

    public static final class UmsMemberMemberTagRelation extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> memberId = column("member_id", JDBCType.BIGINT);

        public final SqlColumn<Long> tagId = column("tag_id", JDBCType.BIGINT);

        public UmsMemberMemberTagRelation() {
            super("ums_member_member_tag_relation");
        }
    }
}