package com.inventory.inventory_service.repository;

import com.inventory.inventory_service.entity.StockJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockJournalRepository extends JpaRepository<StockJournal, Long> {

    List<StockJournal> findByProductCode(String productCode);

    List<StockJournal> findByWarehouseId(String warehouseId);
}