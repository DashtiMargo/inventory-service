package com.inventory.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private Long id;
    private String itemCode;
    private String itemName;
    private String category;
    private String unitOfMeasure;
    private LocalDateTime createdAt;
}
