package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final Map<Long, Product> db = new ConcurrentHashMap<>();

    public InMemoryProductRepository() {
        db.put(1L, new Product(1L, "Widget", 19.99));
        db.put(2L, new Product(2L, "Gadget", 29.99));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }
}

