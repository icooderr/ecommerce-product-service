package com.ecommerce.product.service;

import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.utils.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Product addProduct(ProductRequest productRequest) {
        Product product = productMapper.mapToProduct(productRequest);
        product.setCreatedDate(LocalDateTime.now());
        product.setUpdatedDate(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        return product;
    }

    public Product updateProduct(Long id, ProductRequest updateRequest) {
        Product product = getProductById(id);

        if(updateRequest.getName() != null && !updateRequest.getName().isEmpty()) {
            product.setName(updateRequest.getName());
        }

        if(updateRequest.getDescription() != null && !updateRequest.getDescription().isEmpty()) {
            product.setDescription(updateRequest.getDescription());
        }

        if(updateRequest.getPrice() != null) {
            product.setPrice(updateRequest.getPrice());
        }

        if(updateRequest.getQuantity() != null) {
            product.setQuantity(updateRequest.getQuantity());
        }

        if(updateRequest.getCategory() != null && !updateRequest.getCategory().isEmpty()) {
            product.setCategory(updateRequest.getCategory());
        }

        if(updateRequest.getImageUrl() != null && !updateRequest.getImageUrl().isEmpty()) {
            product.setImageUrl(updateRequest.getImageUrl());
        }

        if(updateRequest.getIsAvailable() != null) {
            product.setIsAvailable(updateRequest.getIsAvailable());
        }

        product.setUpdatedDate(LocalDateTime.now());
        Product updatedProduct = productRepository.save(product);

        return updatedProduct;
    }

    public String deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
        return "Product deleted successfully!";
    }
}
