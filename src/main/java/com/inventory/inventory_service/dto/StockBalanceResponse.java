package com.inventory.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockBalanceResponse {
    private Long id;
    private String itemCode;
    private String warehouseId;
    private Integer quantity;
    private LocalDateTime lastUpdated;
}