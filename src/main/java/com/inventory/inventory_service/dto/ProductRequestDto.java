package com.inventory.inventory_service.dto;

public record ProductRequestDto(
        String productCode,
        String productName,
        String category,
        String unitOfMeasure
) {
}
