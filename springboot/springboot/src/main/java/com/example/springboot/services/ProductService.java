package com.example.springboot.services;

import com.example.springboot.dtos.ProductNewValuesDto;
import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductModel create(ProductRecordDto productDto);
    List<ProductModel> findAll();
    ProductModel findById(UUID id);
    boolean existsById(UUID id);
    ProductModel update(UUID id, ProductRecordDto productDto);
    void delete(UUID id);
    List<ProductNewValuesDto> getAllWithConvertedValues();
}