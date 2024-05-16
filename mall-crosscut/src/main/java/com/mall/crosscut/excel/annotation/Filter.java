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
public @interface Filter {

    /**
     * the first row index
     *
     * @return the first row index
     */
    int firstRow() default 0;

    /**
     * the last row index
     *
     * @return the last row index
     */
    int lastRow() default -1;

    /**
     * the first column index
     *
     * @return the first column index
     */
    int firstCol() default 0;

    /**
     * the last column index
     *
     * @return the last column index
     */
    int lastCol();
}