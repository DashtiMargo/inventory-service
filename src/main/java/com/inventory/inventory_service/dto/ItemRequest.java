package com.inventory.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {
    private String itemCode;
    private String itemName;
    private String category;
    private String unitOfMeasure;
}