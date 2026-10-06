package com.inventory.inventory_service.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record StockJournalResponseDto(
        UUID id,
        String productCode,
        String warehouseId,
        String operationType,
        Integer quantity,
        String documentId,
        OffsetDateTime createdAt
) {
}