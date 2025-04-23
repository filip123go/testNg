package com.example.demo.repository;

import com.example.demo.model.Product;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long id);
}