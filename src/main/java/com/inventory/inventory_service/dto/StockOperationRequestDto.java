package com.inventory.inventory_service.dto;

public record StockOperationRequestDto(
        String productCode,
        String warehouseId,
        Integer quantity,
        String documentId
) {
}