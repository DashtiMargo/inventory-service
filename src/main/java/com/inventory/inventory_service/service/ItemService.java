package com.inventory.inventory_service.service;

import com.inventory.inventory_service.entity.ItemCatalog;
import com.inventory.inventory_service.repository.ItemCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemCatalogRepository itemCatalogRepository;

    @Transactional
    public ItemCatalog save(ItemCatalog item) {
        return itemCatalogRepository.save(item);
    }

    @Transactional(readOnly = true)
    public Optional<ItemCatalog> find(Long id) {
        return itemCatalogRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<ItemCatalog> findByCode(String itemCode) {
        return itemCatalogRepository.findByItemCode(itemCode);
    }

    @Transactional(readOnly = true)
    public List<ItemCatalog> findAll() {
        return itemCatalogRepository.findAll();
    }

    @Transactional
    public Optional<ItemCatalog> merge(Long id, ItemCatalog item) {
        return itemCatalogRepository.findById(id).map(existing -> {
            existing.setItemName(item.getItemName());
            existing.setCategory(item.getCategory());
            existing.setUnitOfMeasure(item.getUnitOfMeasure());
            return itemCatalogRepository.save(existing);
        });
    }

    @Transactional
    public Optional<Boolean> remove(Long id) {
        return itemCatalogRepository.findById(id).map(item -> {
            itemCatalogRepository.delete(item);
            return true;
        });
    }

    @Transactional(readOnly = true)
    public boolean existsByCode(String itemCode) {
        return itemCatalogRepository.existsByItemCode(itemCode);
    }
}