package com.inventory.inventory_service.controller;

import com.inventory.inventory_service.dto.ItemRequest;
import com.inventory.inventory_service.dto.ItemResponse;
import com.inventory.inventory_service.entity.ItemCatalog;
import com.inventory.inventory_service.exception.ItemAlreadyExistsException;
import com.inventory.inventory_service.exception.ItemNotFoundException;
import com.inventory.inventory_service.mapper.ItemMapper;
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
@RequestMapping("items")//придумать путь
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @PostMapping("/save")
    public ResponseEntity<ItemResponse> save(@RequestBody ItemRequest request) {
        if (itemService.existsByCode(request.getItemCode())) {
            throw new ItemAlreadyExistsException();
        }
        ItemCatalog saved = itemService.save(itemMapper.toEntity(request));
        return ResponseEntity.ok(itemMapper.toResponse(saved));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ItemResponse> find(@PathVariable Long id) {
        ItemCatalog item = itemService.find(id)
                .orElseThrow(ItemNotFoundException::new);
        return ResponseEntity.ok(itemMapper.toResponse(item));
    }

    @GetMapping("/find-by-code/{itemCode}")
    public ResponseEntity<ItemResponse> findByCode(@PathVariable String itemCode) {
        ItemCatalog item = itemService.findByCode(itemCode)
                .orElseThrow(ItemNotFoundException::new);
        return ResponseEntity.ok(itemMapper.toResponse(item));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemResponse>> findAll() {
        List<ItemResponse> responses = itemService.findAll().stream()
                .map(itemMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/merge/{id}")
    public ResponseEntity<ItemResponse> merge(@PathVariable Long id,
                                              @RequestBody ItemRequest request) {
        ItemCatalog merged = itemService.merge(id, itemMapper.toEntity(request))
                .orElseThrow(ItemNotFoundException::new);
        return ResponseEntity.ok(itemMapper.toResponse(merged));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> remove(@PathVariable Long id) {
        itemService.remove(id).orElseThrow(ItemNotFoundException::new);
        return ResponseEntity.noContent().build();
    }
}