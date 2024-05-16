/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PmsAlbumPicDynamicSqlSupport {
    public static final PmsAlbumPic pmsAlbumPic = new PmsAlbumPic();

    public static final SqlColumn<Long> id = pmsAlbumPic.id;

    public static final SqlColumn<Long> albumId = pmsAlbumPic.albumId;

    public static final SqlColumn<String> pic = pmsAlbumPic.pic;

    public static final class PmsAlbumPic extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> albumId = column("album_id", JDBCType.BIGINT);

        public final SqlColumn<String> pic = column("pic", JDBCType.VARCHAR);

        public PmsAlbumPic() {
            super("pms_album_pic");
        }
    }
}