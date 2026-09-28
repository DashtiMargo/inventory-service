package com.inventory.inventory_service.service;

import com.inventory.inventory_service.dto.ItemRequest;
import com.inventory.inventory_service.dto.ItemResponse;
import com.inventory.inventory_service.entity.ItemCatalog;
import com.inventory.inventory_service.exception.ItemAlreadyExistsException;
import com.inventory.inventory_service.exception.ItemNotFoundException;
import com.inventory.inventory_service.mapper.ItemMapper;
import com.inventory.inventory_service.repository.ItemCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemCatalogRepository itemCatalogRepository;
    private final ItemMapper itemMapper;

    @Transactional
    public ItemResponse save(ItemRequest request) {
        if (itemCatalogRepository.existsByItemCode(request.getItemCode())) {
            throw new ItemAlreadyExistsException();
        }
        ItemCatalog saved = itemCatalogRepository.save(itemMapper.toEntity(request));
        return itemMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public ItemResponse find(Long id) {
        ItemCatalog item = itemCatalogRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);
        return itemMapper.toResponse(item);
    }

    @Transactional(readOnly = true)
    public ItemResponse findByCode(String itemCode) {
        ItemCatalog item = itemCatalogRepository.findByItemCode(itemCode)
                .orElseThrow(ItemNotFoundException::new);
        return itemMapper.toResponse(item);
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> findAll() {
        return itemCatalogRepository.findAll().stream()
                .map(itemMapper::toResponse)
                .toList();
    }

    @Transactional
    public ItemResponse merge(Long id, ItemRequest request) {
        ItemCatalog existing = itemCatalogRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);
        itemMapper.updateFromDto(request, existing);
        ItemCatalog updated = itemCatalogRepository.save(existing);
        return itemMapper.toResponse(updated);
    }

    @Transactional
    public void remove(Long id) {
        ItemCatalog item = itemCatalogRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);
        itemCatalogRepository.delete(item);
    }
}