/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FlagsValidator implements ConstraintValidator<Flags, Integer> {
    private int[] values;

    @Override
    public void initialize(Flags flags) {
        this.values = flags.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        for (int v : values) {
            if (value == v) {
                return true;
            }
        }

        return false;
    }
}