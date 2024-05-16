/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.jdbc;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.NonNull;

import java.util.Map;

public class JdbcRoutingDataSource extends AbstractRoutingDataSource implements AutoCloseable {
    private Map<Object, Object> targetDataSources;

    @Override
    public void setTargetDataSources(@NonNull Map<Object, Object> targetDataSources) {
        this.targetDataSources = targetDataSources;
        super.setTargetDataSources(targetDataSources);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return JdbcRouterHolder.get();
    }

    @Override
    public void close() throws Exception {
        for (Map.Entry<Object, Object> entry : targetDataSources.entrySet()) {
            Object dataSource = entry.getValue();
            if (AutoCloseable.class.isAssignableFrom(dataSource.getClass())) {
                AutoCloseable closeable = (AutoCloseable) dataSource;
                closeable.close();
            }
        }
    }
}