package com.inventory.inventory_service.repository;

import com.inventory.inventory_service.entity.StockBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockBalanceRepository extends JpaRepository<StockBalance, Long> {

    Optional<StockBalance> findByItemCodeAndWarehouseId(String itemCode, String warehouseId);

    List<StockBalance> findByWarehouseId(String warehouseId);

    List<StockBalance> findByItemCode(String itemCode);

    boolean existsByItemCodeAndWarehouseId(String itemCode, String warehouseId);
}