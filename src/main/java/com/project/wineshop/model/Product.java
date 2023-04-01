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

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product {//equals & hashcode Lombok
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ProductColor.Color color;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ProductType.Type type;

    @Column(length = 4)
    private Integer vintage;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    private String pairing;

    private String grape;

    private String taste;

    private Byte temperature;
}
