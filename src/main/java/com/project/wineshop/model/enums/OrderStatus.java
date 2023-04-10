package com.project.wineshop.model.enums;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;

   public enum Status{
        CREATED, FULFILLED
    }
}
