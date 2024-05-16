/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;

/**
 * https://www.baeldung.com/spring-beanpostprocessor
 */
public class GlobalEventBus {
    public static final String GLOBAL_EVENT_BUS_EXPRESSION
            = "T(com.sunrise.crosscut.eventbus.GlobalEventBus).getEventBus()";

    private static final String IDENTIFIER = "global-event-bus";

    private static final GlobalEventBus GLOBAL_EVENT_BUS = new GlobalEventBus();

    private final EventBus eventBus = new AsyncEventBus(IDENTIFIER, Executors.newCachedThreadPool());

    private GlobalEventBus() {
    }

    public static GlobalEventBus getInstance() {
        return GlobalEventBus.GLOBAL_EVENT_BUS;
    }

    public static EventBus getEventBus() {
        return GlobalEventBus.GLOBAL_EVENT_BUS.eventBus;
    }

    public static void subscribe(Object obj) {
        getEventBus().register(obj);
    }

    public static void unsubscribe(Object obj) {
        getEventBus().unregister(obj);
    }

    public static void post(Object event) {
        getEventBus().post(event);
    }
}