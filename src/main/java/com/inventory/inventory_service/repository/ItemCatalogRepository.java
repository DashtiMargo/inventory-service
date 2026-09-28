package com.inventory.inventory_service.repository;

import com.inventory.inventory_service.entity.ItemCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemCatalogRepository extends JpaRepository<ItemCatalog, Long> {

    Optional<ItemCatalog> findByItemCode(String itemCode);

    boolean existsByItemCode(String itemCode);
}