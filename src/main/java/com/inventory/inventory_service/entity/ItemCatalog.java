package com.inventory.inventory_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_catalog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCatalog extends BaseEntity {
    @Column
    private String itemCode;

    @Column
    private String itemName;

    @Column
    private String category;

    @Column
    private String unitOfMeasure;
}
