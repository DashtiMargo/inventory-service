package com.inventory.inventory_service.exception;

public class ItemNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Item not found";

    public ItemNotFoundException() {
        super(MESSAGE);
    }
}