package com.project.wineshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shipping_details")
@NoArgsConstructor
public class ShippingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String region;
    private String city;
    private String deliveryService;
    private String warehouse;

    public ShippingDetails(String region, String city,
                           String deliveryService, String warehouse) {
        this.region = region;
        this.city = city;
        this.deliveryService = deliveryService;
        this.warehouse = warehouse;
    }
}
