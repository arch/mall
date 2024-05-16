/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OmsCompanyAddressDynamicSqlSupport {
    public static final OmsCompanyAddress omsCompanyAddress = new OmsCompanyAddress();

    public static final SqlColumn<Long> id = omsCompanyAddress.id;

    public static final SqlColumn<String> addressName = omsCompanyAddress.addressName;

    public static final SqlColumn<Integer> sendStatus = omsCompanyAddress.sendStatus;

    public static final SqlColumn<Integer> receiveStatus = omsCompanyAddress.receiveStatus;

    public static final SqlColumn<String> name = omsCompanyAddress.name;

    public static final SqlColumn<String> phone = omsCompanyAddress.phone;

    public static final SqlColumn<String> province = omsCompanyAddress.province;

    public static final SqlColumn<String> city = omsCompanyAddress.city;

    public static final SqlColumn<String> region = omsCompanyAddress.region;

    public static final SqlColumn<String> detailAddress = omsCompanyAddress.detailAddress;

    public static final class OmsCompanyAddress extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> addressName = column("address_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sendStatus = column("send_status", JDBCType.INTEGER);

        public final SqlColumn<Integer> receiveStatus = column("receive_status", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> phone = column("phone", JDBCType.VARCHAR);

        public final SqlColumn<String> province = column("province", JDBCType.VARCHAR);

        public final SqlColumn<String> city = column("city", JDBCType.VARCHAR);

        public final SqlColumn<String> region = column("region", JDBCType.VARCHAR);

        public final SqlColumn<String> detailAddress = column("detail_address", JDBCType.VARCHAR);

        public OmsCompanyAddress() {
            super("oms_company_address");
        }
    }
}