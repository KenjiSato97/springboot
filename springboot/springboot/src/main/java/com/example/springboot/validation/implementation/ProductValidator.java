package com.example.springboot.validation.implementation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class ProductValidator implements ConstraintValidator<OnlyWithCategory, String> {

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        if (Objects.nonNull(valor) && valor.contains("Notebook") || valor.contains("TV") || valor.contains("Smartphone")) {
            return true;
        }
        return false;
    }
}
