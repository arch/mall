/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.validation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Represents a validator proxy which will delegating it's checking to the specified {@link AbstractChecker
 * AbstractChecker}
 */
public class DelegatingValidatorProxy implements ConstraintValidator<EnsureChecked, Object>, ApplicationContextAware {
    private EnsureChecked ensureChecked;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(EnsureChecked ensureChecked) {
        this.ensureChecked = ensureChecked;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return ensureChecked.isNullable();
        }

        try {
            //AbstractChecker validator = applicationContext.getBean(ensureChecked.checkBy());
            AbstractChecker validator = ensureChecked.checkBy().getConstructor().newInstance();
            return validator.isValid(value, context);
        } catch (Exception e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();

            return false;
        }
    }
}