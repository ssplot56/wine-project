package com.project.wineshop.model.enums;

import lombok.Data;

@Data
public class ProductEvent {

    public enum Event {
        PARTY_WITH_FRIENDS,
        FOR_SPECIAL_EVENTS,
        FOR_PRESENT,
        FOR_LONELY_EVENING
    }

}
