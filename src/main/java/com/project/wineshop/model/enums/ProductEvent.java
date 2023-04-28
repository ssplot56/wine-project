package com.project.wineshop.model.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product_event")
public class ProductEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductEvent.Event event;

    public enum Event {
        PARTY_WITH_FRIENDS,
        FOR_SPECIAL_EVENTS,
        FOR_PRESENT,
        FOR_LONELY_EVENING
    }

}
