package com.example.springboot.utils;

import com.example.springboot.dtos.ExchangeResponseDto;
import com.example.springboot.dtos.ProductNewValuesDto;
import com.example.springboot.models.ProductModel;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductMapper {

    public static ProductNewValuesDto toDto(ProductModel model,
                                          BigDecimal usdHigh,
                                          BigDecimal eurHigh,
                                          BigDecimal jpyHigh) {
        ProductNewValuesDto dto = new ProductNewValuesDto(model.getName(), model.getValue(),
                model.getValue().divide(usdHigh, 2, RoundingMode.HALF_UP),
                model.getValue().divide(eurHigh, 2, RoundingMode.HALF_UP),
                model.getValue().divide(jpyHigh, 2, RoundingMode.HALF_UP),
                model.getQuantity());

        return dto;
    }
}