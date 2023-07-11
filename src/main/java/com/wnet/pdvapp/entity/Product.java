package com.wnet.pdvapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(length = 20, precision = 20, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

}
