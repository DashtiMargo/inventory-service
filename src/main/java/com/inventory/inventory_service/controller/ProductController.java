package com.inventory.inventory_service.controller;

import com.inventory.inventory_service.dto.ProductRequest;
import com.inventory.inventory_service.dto.ProductResponse;
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

@RestController
@RequestMapping("/api/v1/inventory/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.save(request));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> find(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.find(productId));
    }

    @GetMapping("/by-code/{productCode}")
    public ResponseEntity<ProductResponse> findByCode(@PathVariable String productCode) {
        return ResponseEntity.ok(productService.findByCode(productCode));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> merge(@PathVariable Long productId,
                                                 @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.merge(productId, request));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> remove(@PathVariable Long productId) {
        productService.remove(productId);
        return ResponseEntity.noContent().build();
    }
}