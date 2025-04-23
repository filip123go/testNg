package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.exception.ProductNotFoundException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class ProductServiceTest {

    @Mock private ProductRepository repo;
    @InjectMocks private ProductService service;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductById_Found() {
        Product p = new Product(1L, "Widget", 19.99);
        when(repo.findById(1L)).thenReturn(Optional.of(p));

        Product result = service.getProductById(1L);
        assertEquals(result.getName(), "Widget");
        assertEquals(result.getPrice(), 19.99);
    }

    @Test(expectedExceptions = ProductNotFoundException.class)
    public void testGetProductById_NotFound() {
        when(repo.findById(42L)).thenReturn(Optional.empty());
        service.getProductById(42L);
    }
}