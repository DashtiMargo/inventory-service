package com.inventory.inventory_service.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Item not found";

    public ProductNotFoundException() {
        super(MESSAGE);
    }
}