package com.project.wineshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40)
    private String name;

    private BigDecimal price;

    @Column(length = 20)
    private String type;

    @Column(length = 20)
    private String color;

    @Column(length = 60)
    private String event;

    @Column(length = 200)
    private String pairing;

    @ManyToMany
    private Set<Dish> dishes;

    @Column(length = 4)
    private Integer vintage;

    private String country;

    private String region;

    private String grape;

    @Column(length = 600)
    private String taste;

    private String temperature;

    @Column(length = 2000)
    private String imageLink;
}
