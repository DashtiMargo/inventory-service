package com.inventory.inventory_service.mapper;

import com.inventory.inventory_service.dto.ProductRequestDto;
import com.inventory.inventory_service.dto.ProductResponseDto;
import com.inventory.inventory_service.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto toResponse(Product entity);

    Product toEntity(ProductRequestDto request);

    void updateFromDto(ProductRequestDto request, @MappingTarget Product entity);
}