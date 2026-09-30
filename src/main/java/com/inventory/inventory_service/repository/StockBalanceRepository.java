package com.inventory.inventory_service.repository;

import com.inventory.inventory_service.entity.StockBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockBalanceRepository extends JpaRepository<StockBalance, Long> {

    Optional<StockBalance> findByProductCodeAndWarehouseId(String productCode, String warehouseId);
}