/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.jdbc;

import org.springframework.util.Assert;

public class JdbcRouterHolder {
    private static final ThreadLocal<JdbcRouter> holder = new ThreadLocal<>();

    public static JdbcRouter get() {
        return holder.get();
    }

    public static void set(JdbcRouter router) {
        Assert.notNull(router, "router cannot be null");

        holder.set(router);
    }

    public static void clear() {
        holder.remove();
    }
}