package com.inventory.inventory_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_balance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockBalance extends BaseEntity {

    @Column
    private String itemCode;

    @Column
    private String warehouseId;

    @Column
    private Integer quantity = 0;

    @UpdateTimestamp
    @Column
    private LocalDateTime lastUpdated;
}