package com.inventory.inventory_service.exception;

public class ProductAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "Item already exists";

    public ProductAlreadyExistsException() {
        super(MESSAGE);
    }
}