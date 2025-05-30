package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductNewValuesDto(@NotBlank String name, @NotNull BigDecimal value,BigDecimal valueusd, BigDecimal valueeur, BigDecimal valuejpy, @NotNull Integer quantity) {
}
