/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.validation;

import javax.validation.ConstraintValidatorContext;

/**
 * Represents an abstract checker for he specified type.
 *
 * @param <T> The type of model should be checking.
 */
@CrossConstraint
public abstract class AbstractChecker<T> {
    public boolean isValid(T value, ConstraintValidatorContext context) {
        return true;
    }

    protected boolean invalid(ConstraintValidatorContext context, String parameterName, String promptMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(promptMessage)
               .addPropertyNode(parameterName)
               .addConstraintViolation();

        return false;
    }
}