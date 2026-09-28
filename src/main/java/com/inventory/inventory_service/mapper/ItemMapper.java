package com.inventory.inventory_service.mapper;

import com.inventory.inventory_service.dto.ItemRequest;
import com.inventory.inventory_service.dto.ItemResponse;
import com.inventory.inventory_service.entity.ItemCatalog;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemCatalog toEntity(ItemRequest request) {
        ItemCatalog item = new ItemCatalog();
        item.setItemCode(request.getItemCode());
        item.setItemName(request.getItemName());
        item.setCategory(request.getCategory());
        item.setUnitOfMeasure(request.getUnitOfMeasure());
        return item;
    }

    public ItemResponse toResponse(ItemCatalog entity) {
        ItemResponse response = new ItemResponse();
        response.setId(entity.getId());
        response.setItemCode(entity.getItemCode());
        response.setItemName(entity.getItemName());
        response.setCategory(entity.getCategory());
        response.setUnitOfMeasure(entity.getUnitOfMeasure());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }

    public void updateFromDto(ItemRequest request, ItemCatalog entity) {
        entity.setItemName(request.getItemName());
        entity.setCategory(request.getCategory());
        entity.setUnitOfMeasure(request.getUnitOfMeasure());
    }
}