/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Async {
    // logging into file with runAsync catalog
    Logger logger = LoggerFactory.getLogger("runAsync");

    // cached thread pool, (will new & reclaim automatic)
    ExecutorService threadPool = Executors.newCachedThreadPool();

    static void runAsync(Runnable action) {
        // ensure exception be throw
        threadPool.submit(() -> {
            try {
                action.run();
            } catch (Throwable cause) {
                // logging into file
                logger.error(cause.getMessage(), cause);
            }
        });
    }

    static void runAsync(Runnable action, boolean condition) {
        if (condition) {
            runAsync(action);
        }
    }
}