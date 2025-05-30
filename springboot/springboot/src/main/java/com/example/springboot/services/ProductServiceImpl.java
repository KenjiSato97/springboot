package com.example.springboot.services;

import com.example.springboot.client.ApiCurrencyClient;
import com.example.springboot.dtos.CurrencyDataDto;
import com.example.springboot.dtos.ExchangeResponseDto;
import com.example.springboot.dtos.ProductNewValuesDto;
import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import com.example.springboot.utils.ProductMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ApiCurrencyClient apiCurrencyClient;

    public ProductServiceImpl(ProductRepository productRepository, ApiCurrencyClient apiCurrencyClient) {
        this.productRepository = productRepository;
        this.apiCurrencyClient = apiCurrencyClient;
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
    public List<ProductNewValuesDto> getAllWithConvertedValues() {
        List<ProductModel> products = productRepository.findAll();
        ExchangeResponseDto exchange = apiCurrencyClient.getCurrencyDataDto();

        BigDecimal usdHigh = new BigDecimal(exchange.USDBRL().high());
        BigDecimal eurHigh = new BigDecimal(exchange.EURBRL().high());
        BigDecimal jpyHigh = new BigDecimal(exchange.JPYBRL().high());

        return products.stream()
            .map(p -> ProductMapper.toDto(p, usdHigh, eurHigh, jpyHigh))
            .collect(Collectors.toList());
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