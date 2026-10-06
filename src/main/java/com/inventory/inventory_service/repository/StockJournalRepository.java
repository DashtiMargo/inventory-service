package com.inventory.inventory_service.repository;

import com.inventory.inventory_service.entity.StockJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockJournalRepository extends JpaRepository<StockJournal, UUID> {

    List<StockJournal> findByProductCode(String productCode);

    List<StockJournal> findByWarehouseId(String warehouseId);
}