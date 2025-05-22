package com.example.springboot.services;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductModel create(ProductRecordDto productDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        return productRepository.save(productModel);
    }

    @Override
    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductModel findById(UUID id) {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        return productOptional.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public boolean existsById(UUID id) {
        return productRepository.existsById(id);
    }

    @Override
    public ProductModel update(UUID id, ProductRecordDto productDto) {
        var existingProduct = findById(id);
        BeanUtils.copyProperties(productDto, existingProduct);
        return productRepository.save(existingProduct);
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }
}