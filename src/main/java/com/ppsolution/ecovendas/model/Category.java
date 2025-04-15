package com.ppsolution.ecovendas.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "tbl_category")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "stockQuantity")
    private Integer stock_quantity;
    @Column(name = "active")
    private Integer active;
    @Column(name = "createdAt")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
