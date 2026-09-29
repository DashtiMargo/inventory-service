package com.inventory.inventory_service.mapper;

import com.inventory.inventory_service.dto.ProductRequest;
import com.inventory.inventory_service.dto.ProductResponse;
import com.inventory.inventory_service.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product entity);

    Product toEntity(ProductRequest request);

    void updateFromDto(ProductRequest request, @MappingTarget Product entity);
}