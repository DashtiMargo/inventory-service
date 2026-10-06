package com.inventory.inventory_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stock_journal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockJournal extends BaseEntity {

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "warehouse_id")
    private String warehouseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operationType;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "document_id")
    private String documentId;
}
