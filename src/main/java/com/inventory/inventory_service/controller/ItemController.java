package com.inventory.inventory_service.controller;

import com.inventory.inventory_service.dto.ItemRequest;
import com.inventory.inventory_service.dto.ItemResponse;
import com.inventory.inventory_service.service.ItemService;
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
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<ItemResponse> save(@RequestBody ItemRequest request) {
        return ResponseEntity.ok(itemService.save(request));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ItemResponse> find(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.find(id));
    }

    @GetMapping("/find-by-code/{itemCode}")
    public ResponseEntity<ItemResponse> findByCode(@PathVariable String itemCode) {
        return ResponseEntity.ok(itemService.findByCode(itemCode));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemResponse>> findAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @PutMapping("/merge/{id}")
    public ResponseEntity<ItemResponse> merge(@PathVariable Long id,
                                              @RequestBody ItemRequest request) {
        return ResponseEntity.ok(itemService.merge(id, request));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        itemService.remove(id);
        return ResponseEntity.noContent().build();
    }
}