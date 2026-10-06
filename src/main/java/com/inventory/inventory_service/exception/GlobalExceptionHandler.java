package com.inventory.inventory_service.exception;

import com.inventory.inventory_service.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleNotFound(ProductNotFoundException ex) {
        return new ErrorResponseDto(ex.getMessage());
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handleAlreadyExists(ProductAlreadyExistsException ex) {
        return new ErrorResponseDto(ex.getMessage());
    }

    @ExceptionHandler(StockNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleStockNotFound(StockNotFoundException ex) {
        return new ErrorResponseDto(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleBadRequest(BadRequestException ex) {
        return new ErrorResponseDto(ex.getMessage());
    }

    @ExceptionHandler(NotEnoughStockException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handleNotEnoughStock(NotEnoughStockException ex) {
        return new ErrorResponseDto(ex.getMessage());
    }
}