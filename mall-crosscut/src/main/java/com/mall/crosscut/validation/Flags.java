/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = FlagsValidator.class)
public @interface Flags {
    int[] value() default {};

    String message() default "flags is not found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}