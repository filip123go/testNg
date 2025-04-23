package com.example.demo.service.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String msg) { super(msg); }
}
