package com.inventory.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockOperationRequest {
    private String itemCode;
    private String warehouseId;
    private Integer quantity;
}