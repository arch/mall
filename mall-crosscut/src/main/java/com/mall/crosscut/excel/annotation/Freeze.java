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
public @interface Freeze {

    /**
     * the column to split
     *
     * @return the column to split
     */
    int colSplit();

    /**
     * the row to split
     *
     * @return the row to split
     */
    int rowSplit();

    /**
     * the left most column index
     *
     * @return the left most column index
     */
    int leftMostCol();

    /**
     * the top most row index
     *
     * @return the top most row index
     */
    int topRow();
}