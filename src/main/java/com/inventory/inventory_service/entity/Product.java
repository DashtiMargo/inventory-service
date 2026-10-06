package com.inventory.inventory_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "product_code")
    private String itemCode;

    @Column(name = "product_name")
    private String itemName;

    @Column
    private String category;

    @Column(name = "unit_of_measure")
    private String unitOfMeasure;
}
