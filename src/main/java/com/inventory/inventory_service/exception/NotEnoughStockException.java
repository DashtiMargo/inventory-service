package com.inventory.inventory_service.exception;

public class NotEnoughStockException extends RuntimeException {
    private static final String MESSAGE = "Not enough stock on warehouse";

    public NotEnoughStockException() {
        super(MESSAGE);
    }
}