package com.project.wineshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Color color;
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Type type;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
    private Boolean inStock;
    private String grape;
    private String taste;

    public enum Color {
        RED,
        WHITE,
        PINK
    }

    public enum Type {
        DRY,
        SEMIDRY,
        SEMISWEET,
        SWEET,
        BRUT,
        SPARCLING
    }
}
