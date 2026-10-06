package com.inventory.inventory_service.dto;

import java.time.OffsetDateTime;

public record StockBalanceResponseDto(
        Long id,
        String productCode,
        String warehouseId,
        Integer quantity,
        OffsetDateTime updatedAt
) {
}