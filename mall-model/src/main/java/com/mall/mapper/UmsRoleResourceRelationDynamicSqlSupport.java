/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsRoleResourceRelationDynamicSqlSupport {
    public static final UmsRoleResourceRelation umsRoleResourceRelation = new UmsRoleResourceRelation();

    public static final SqlColumn<Long> id = umsRoleResourceRelation.id;

    public static final SqlColumn<Long> roleId = umsRoleResourceRelation.roleId;

    public static final SqlColumn<Long> resourceId = umsRoleResourceRelation.resourceId;

    public static final class UmsRoleResourceRelation extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public final SqlColumn<Long> resourceId = column("resource_id", JDBCType.BIGINT);

        public UmsRoleResourceRelation() {
            super("ums_role_resource_relation");
        }
    }
}