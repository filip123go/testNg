package com.example.demo.service;

import com.example.demo.repository.ProductRepository;
import com.example.demo.service.exception.ProductNotFoundException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class ProductServiceEdgeCasesTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetProductById_NullId() {
        productService.getProductById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetProductById_NegativeId() {
        productService.getProductById(-5L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetProductById_ZeroId() {
        productService.getProductById(0L);
    }

    @Test(expectedExceptions = ProductNotFoundException.class)
    public void testGetProductById_NonExistentId() {
        when(productRepository.findById(Long.MAX_VALUE))
                .thenReturn(Optional.empty());
        productService.getProductById(Long.MAX_VALUE);
    }
}