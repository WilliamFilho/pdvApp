package com.wnet.pdvapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    private boolean isEnable;

    @OneToMany(mappedBy = "user")
    private List<Sale> sales;
}
