/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UmsMemberReceiveAddressDynamicSqlSupport {
    public static final UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();

    public static final SqlColumn<Long> id = umsMemberReceiveAddress.id;

    public static final SqlColumn<Long> memberId = umsMemberReceiveAddress.memberId;

    public static final SqlColumn<String> name = umsMemberReceiveAddress.name;

    public static final SqlColumn<String> phoneNumber = umsMemberReceiveAddress.phoneNumber;

    public static final SqlColumn<Integer> defaultStatus = umsMemberReceiveAddress.defaultStatus;

    public static final SqlColumn<String> postCode = umsMemberReceiveAddress.postCode;

    public static final SqlColumn<String> province = umsMemberReceiveAddress.province;

    public static final SqlColumn<String> city = umsMemberReceiveAddress.city;

    public static final SqlColumn<String> region = umsMemberReceiveAddress.region;

    public static final SqlColumn<String> detailAddress = umsMemberReceiveAddress.detailAddress;

    public static final class UmsMemberReceiveAddress extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> memberId = column("member_id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> phoneNumber = column("phone_number", JDBCType.VARCHAR);

        public final SqlColumn<Integer> defaultStatus = column("default_status", JDBCType.INTEGER);

        public final SqlColumn<String> postCode = column("post_code", JDBCType.VARCHAR);

        public final SqlColumn<String> province = column("province", JDBCType.VARCHAR);

        public final SqlColumn<String> city = column("city", JDBCType.VARCHAR);

        public final SqlColumn<String> region = column("region", JDBCType.VARCHAR);

        public final SqlColumn<String> detailAddress = column("detail_address", JDBCType.VARCHAR);

        public UmsMemberReceiveAddress() {
            super("ums_member_receive_address");
        }
    }
}