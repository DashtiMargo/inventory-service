package com.inventory.inventory_service.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ProductResponseDto(
        UUID id,
        String productCode,
        String productName,
        String category,
        String unitOfMeasure,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}