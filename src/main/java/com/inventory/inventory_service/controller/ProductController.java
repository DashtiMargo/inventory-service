package com.inventory.inventory_service.controller;

import com.inventory.inventory_service.dto.ProductRequestDto;
import com.inventory.inventory_service.dto.ProductResponseDto;
import com.inventory.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@RequestBody ProductRequestDto request) {
        return ResponseEntity.ok(productService.save(request));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable UUID productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping("/by-code/{productCode}")
    public ResponseEntity<ProductResponseDto> findByCode(@PathVariable String productCode) {
        return ResponseEntity.ok(productService.findByCode(productCode));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> merge(@PathVariable UUID productId,
                                                    @RequestBody ProductRequestDto request) {
        return ResponseEntity.ok(productService.merge(productId, request));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> remove(@PathVariable UUID productId) {
        productService.remove(productId);
        return ResponseEntity.noContent().build();
    }
}