package com.inventory.inventory_service.service;

import com.inventory.inventory_service.dto.ProductRequest;
import com.inventory.inventory_service.dto.ProductResponse;
import com.inventory.inventory_service.entity.Product;
import com.inventory.inventory_service.exception.ProductAlreadyExistsException;
import com.inventory.inventory_service.exception.ProductNotFoundException;
import com.inventory.inventory_service.mapper.ProductMapper;
import com.inventory.inventory_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.inventory.inventory_service.CacheName.PRODUCTS;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @CacheEvict(value = PRODUCTS, allEntries = true)
    @Transactional
    public ProductResponse save(ProductRequest request) {
        if (productRepository.existsByProductCode(request.getProductCode())) {
            throw new ProductAlreadyExistsException();
        }
        Product saved = productRepository.save(productMapper.toEntity(request));
        return productMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public ProductResponse find(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        return productMapper.toResponse(product);
    }

    @Cacheable(value = PRODUCTS)
    @Transactional(readOnly = true)
    public ProductResponse findByCode(String productCode) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(ProductNotFoundException::new);
        return productMapper.toResponse(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @CacheEvict(value = PRODUCTS, allEntries = true)
    @Transactional
    public ProductResponse merge(Long id, ProductRequest request) {
        Product existing = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        productMapper.updateFromDto(request, existing);
        Product updated = productRepository.save(existing);
        return productMapper.toResponse(updated);
    }

    @CacheEvict(value = PRODUCTS, allEntries = true)
    @Transactional
    public void remove(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }
}