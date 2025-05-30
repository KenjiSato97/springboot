package com.example.springboot.validation.implementation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductValidator.class)
public @interface OnlyWithCategory {
    String message() default "A categoria do produto deve ser especificada no nome do produto";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
