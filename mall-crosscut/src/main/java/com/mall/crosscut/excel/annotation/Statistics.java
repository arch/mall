/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.excel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Statistics {

    /**
     * the statistics name. e.g. Total
     *
     * @return the statistics name
     */
    String name();

    /**
     * the cell formula, such as SUM, AVERAGE and so on, which for vertical statistics
     *
     * @return the cell formula
     */
    String formula();

    /**
     * column indexes for statistics.
     *
     * If the {@link Statistics#formula()} if SUM, and the columns is [1,3], the column No. 1 and 3
     * will be SUM from first to last row
     *
     * @return the statistics column indexes
     */
    int[] columns();
}