package com.ecommerce.product.controller;

import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.service.ProductService;
import com.ecommerce.product.utils.ProductRequest;
import com.ecommerce.product.utils.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.addProduct(productRequest);
        ProductResponse productResponse = productMapper.mapToProductResponse(product);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        List<ProductResponse> productResponseList = allProducts.stream()
                .map(productMapper::mapToProductResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(productResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        ProductResponse productResponse = productMapper.mapToProductResponse(product);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest updateRequest) {
        Product updatedProduct = productService.updateProduct(id, updateRequest);
        ProductResponse productResponse = productMapper.mapToProductResponse(updatedProduct);
        return new ResponseEntity<>(productResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        String update = productService.deleteProduct(id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
}
