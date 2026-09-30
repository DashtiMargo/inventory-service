package com.inventory.inventory_service.exception;

public class BadRequestException extends RuntimeException {
    private static final String MESSAGE = "Bad request!";

    public BadRequestException() {
        super(MESSAGE);
    }
}