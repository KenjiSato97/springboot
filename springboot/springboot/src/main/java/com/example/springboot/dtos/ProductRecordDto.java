package com.example.springboot.dtos;

import com.example.springboot.validation.implementation.OnlyWithCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRecordDto(@NotBlank @OnlyWithCategory String name, @NotNull BigDecimal value, @NotNull Integer quantity) {
}
