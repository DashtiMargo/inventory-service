package com.inventory.inventory_service.repository;

import com.inventory.inventory_service.entity.OperationType;
import com.inventory.inventory_service.entity.StockJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockJournalRepository extends JpaRepository<StockJournal, Long> {

    List<StockJournal> findByItemCode(String itemCode);

    List<StockJournal> findByWarehouseId(String warehouseId);

    List<StockJournal> findByOperationType(OperationType operationType);

    List<StockJournal> findByItemCodeAndWarehouseId(String itemCode, String warehouseId);
}