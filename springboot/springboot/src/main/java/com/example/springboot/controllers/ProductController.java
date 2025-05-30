package com.example.springboot.controllers;

import com.example.springboot.client.ApiCurrencyClient;
import com.example.springboot.dtos.CurrencyDataDto;
import com.example.springboot.dtos.ExchangeResponseDto;
import com.example.springboot.dtos.ProductNewValuesDto;
import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.services.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ApiCurrencyClient apiCurrencyClient;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productRecordDto));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/currency")
    public ResponseEntity<List<ProductNewValuesDto>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllWithConvertedValues());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable UUID id) {
        var product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductRecordDto productRecordDto) {
        var updatedProduct = productService.update(id, productRecordDto);
        if (updatedProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id) {
        if (!productService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @PutMapping("/products/{id}/quantity/{quantity}")
    public ResponseEntity<Object> updateProductQuantity(@PathVariable UUID id, @PathVariable Integer quantity) {
        var productOptional = productService.findById(id);
        if (productOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productOptional.setQuantity(quantity);
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, new ProductRecordDto(
                productOptional.getName(),
                productOptional.getValue(),
                quantity
        )));
    }
}
