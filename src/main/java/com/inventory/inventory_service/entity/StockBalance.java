package com.inventory.inventory_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stock_balance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockBalance extends BaseEntity {

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "warehouse_id")
    private String warehouseId;

    @Column
    private Integer quantity;
}