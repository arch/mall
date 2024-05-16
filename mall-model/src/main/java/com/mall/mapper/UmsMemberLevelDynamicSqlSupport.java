/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsMemberLevelDynamicSqlSupport {
    public static final UmsMemberLevel umsMemberLevel = new UmsMemberLevel();

    public static final SqlColumn<Long> id = umsMemberLevel.id;

    public static final SqlColumn<String> name = umsMemberLevel.name;

    public static final SqlColumn<Integer> growthPoint = umsMemberLevel.growthPoint;

    public static final SqlColumn<Integer> defaultStatus = umsMemberLevel.defaultStatus;

    public static final SqlColumn<BigDecimal> freeFreightPoint = umsMemberLevel.freeFreightPoint;

    public static final SqlColumn<Integer> commentGrowthPoint = umsMemberLevel.commentGrowthPoint;

    public static final SqlColumn<Integer> privilegeFreeFreight = umsMemberLevel.privilegeFreeFreight;

    public static final SqlColumn<Integer> privilegeSignIn = umsMemberLevel.privilegeSignIn;

    public static final SqlColumn<Integer> privilegeComment = umsMemberLevel.privilegeComment;

    public static final SqlColumn<Integer> privilegePromotion = umsMemberLevel.privilegePromotion;

    public static final SqlColumn<Integer> privilegeMemberPrice = umsMemberLevel.privilegeMemberPrice;

    public static final SqlColumn<Integer> privilegeBirthday = umsMemberLevel.privilegeBirthday;

    public static final SqlColumn<String> note = umsMemberLevel.note;

    public static final class UmsMemberLevel extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> growthPoint = column("growth_point", JDBCType.INTEGER);

        public final SqlColumn<Integer> defaultStatus = column("default_status", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> freeFreightPoint = column("free_freight_point", JDBCType.DECIMAL);

        public final SqlColumn<Integer> commentGrowthPoint = column("comment_growth_point", JDBCType.INTEGER);

        public final SqlColumn<Integer> privilegeFreeFreight = column("privilege_free_freight", JDBCType.INTEGER);

        public final SqlColumn<Integer> privilegeSignIn = column("privilege_sign_in", JDBCType.INTEGER);

        public final SqlColumn<Integer> privilegeComment = column("privilege_comment", JDBCType.INTEGER);

        public final SqlColumn<Integer> privilegePromotion = column("privilege_promotion", JDBCType.INTEGER);

        public final SqlColumn<Integer> privilegeMemberPrice = column("privilege_member_price", JDBCType.INTEGER);

        public final SqlColumn<Integer> privilegeBirthday = column("privilege_birthday", JDBCType.INTEGER);

        public final SqlColumn<String> note = column("note", JDBCType.VARCHAR);

        public UmsMemberLevel() {
            super("ums_member_level");
        }
    }
}