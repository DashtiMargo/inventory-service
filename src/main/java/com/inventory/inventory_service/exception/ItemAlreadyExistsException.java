package com.inventory.inventory_service.exception;

public class ItemAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "Item already exists";

    public ItemAlreadyExistsException() {
        super(MESSAGE);
    }
}