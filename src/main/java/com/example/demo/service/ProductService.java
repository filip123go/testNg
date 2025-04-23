package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product getProductById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        return repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
    }
}