/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
@Import({CrosscutConfiguration.class, PropertiesConfiguration.class})
@Configuration
public @interface EnableCrosscut {
    String corsPathPattern() default "/api/**";

    String deployRoot() default "deployRoot";

    String[] apiResultDisabledURI() default {};

    String datePattern() default "yyyy-MM-dd HH:mm:ss";
}