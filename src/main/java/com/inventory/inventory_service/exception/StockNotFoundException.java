package com.inventory.inventory_service.exception;

public class StockNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Stock not found!";

    public StockNotFoundException() {
        super(MESSAGE);
    }
}