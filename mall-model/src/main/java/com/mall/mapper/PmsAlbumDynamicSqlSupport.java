/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsAlbumDynamicSqlSupport {
    public static final PmsAlbum pmsAlbum = new PmsAlbum();

    public static final SqlColumn<Long> id = pmsAlbum.id;

    public static final SqlColumn<String> name = pmsAlbum.name;

    public static final SqlColumn<String> coverPic = pmsAlbum.coverPic;

    public static final SqlColumn<Integer> picCount = pmsAlbum.picCount;

    public static final SqlColumn<Integer> sort = pmsAlbum.sort;

    public static final SqlColumn<String> description = pmsAlbum.description;

    public static final class PmsAlbum extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> coverPic = column("cover_pic", JDBCType.VARCHAR);

        public final SqlColumn<Integer> picCount = column("pic_count", JDBCType.INTEGER);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public PmsAlbum() {
            super("pms_album");
        }
    }
}