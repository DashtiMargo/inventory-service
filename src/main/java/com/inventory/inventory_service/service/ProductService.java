package com.inventory.inventory_service.service;

import com.inventory.inventory_service.dto.ProductRequestDto;
import com.inventory.inventory_service.dto.ProductResponseDto;
import com.inventory.inventory_service.entity.Product;
import com.inventory.inventory_service.exception.ProductAlreadyExistsException;
import com.inventory.inventory_service.exception.ProductNotFoundException;
import com.inventory.inventory_service.mapper.ProductMapper;
import com.inventory.inventory_service.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.inventory.inventory_service.util.CacheName.PRODUCTS;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

     ProductRepository productRepository;
     ProductMapper productMapper;

    @CacheEvict(value = PRODUCTS, allEntries = true)
    @Transactional
    public ProductResponseDto save(ProductRequestDto request) {
        if (productRepository.existsByProductCode(request.productCode())) {
            throw new ProductAlreadyExistsException();
        }
        Product saved = productRepository.save(productMapper.toEntity(request));
        return productMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto findById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        return productMapper.toResponse(product);
    }

    @Cacheable(value = PRODUCTS)
    @Transactional(readOnly = true)
    public ProductResponseDto findByCode(String productCode) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(ProductNotFoundException::new);
        return productMapper.toResponse(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @CacheEvict(value = PRODUCTS, allEntries = true)
    @Transactional
    public ProductResponseDto merge(UUID id, ProductRequestDto request) {
        Product existing = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        productMapper.updateFromDto(request, existing);
        return productMapper.toResponse(productRepository.save(existing));
    }

    @CacheEvict(value = PRODUCTS, allEntries = true)
    @Transactional
    public void remove(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }
}