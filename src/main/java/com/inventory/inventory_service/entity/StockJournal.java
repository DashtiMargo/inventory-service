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

    @Column
    private String itemCode;

    @Column
    private String warehouseId;

    @Enumerated(EnumType.STRING)
    @Column
    private OperationType  operationType;

    @Column
    private Integer quantity;

    @Column
    private String documentId;
}
