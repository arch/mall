/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsAdminRoleRelationDynamicSqlSupport {
    public static final UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();

    public static final SqlColumn<Long> id = umsAdminRoleRelation.id;

    public static final SqlColumn<Long> adminId = umsAdminRoleRelation.adminId;

    public static final SqlColumn<Long> roleId = umsAdminRoleRelation.roleId;

    public static final class UmsAdminRoleRelation extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> adminId = column("admin_id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public UmsAdminRoleRelation() {
            super("ums_admin_role_relation");
        }
    }
}