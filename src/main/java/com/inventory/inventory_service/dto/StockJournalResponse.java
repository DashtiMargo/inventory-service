package com.inventory.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockJournalResponse {
    private Long id;
    private String ProductCode;
    private String warehouseId;
    private String operationType;
    private Integer quantity;
    private String documentId;
    private LocalDateTime createdAt;
}