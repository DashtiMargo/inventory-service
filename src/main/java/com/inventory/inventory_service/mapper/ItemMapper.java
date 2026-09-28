package com.inventory.inventory_service.mapper;

import com.inventory.inventory_service.dto.ItemRequest;
import com.inventory.inventory_service.dto.ItemResponse;
import com.inventory.inventory_service.entity.ItemCatalog;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemResponse toResponse(ItemCatalog entity);

    ItemCatalog toEntity(ItemRequest request);

    void updateFromDto(ItemRequest request, @MappingTarget ItemCatalog entity);
}