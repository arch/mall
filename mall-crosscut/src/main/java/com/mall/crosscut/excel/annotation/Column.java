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

@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Column {

    /**
     * the title of the column, if not set or null, will use field name as the excel column title.
     *
     * @return the title of the column
     */
    String title() default "";

    /**
     * excel cell index, if set to -1, import will try to auto discover the column index by the {@link Column#title()}
     *
     * @return the cell index
     */
    int index() default -1;

    /**
     * a value indicating whether allow merge the same value cells
     *
     * @return {@code true} if allow merge the same cells
     */
    boolean allowMerge() default true;

    /**
     * a value indicating whether this value of the field is ignored
     *
     * @return {@code true} if ignore this field/cell
     */
    boolean ignore() default false;

    /**
     * the formatter for formatting the value
     *
     * @return the format for formatting the value
     */
    String format() default "";
}