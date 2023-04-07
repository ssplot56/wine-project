package com.project.wineshop.dto.request;

import com.project.wineshop.model.enums.OrderPayment;
import lombok.Data;

import java.util.Map;

@Data
public class OrderRequestDto {

    private Map<Long,Integer> products;

    private Long userId;

    private Long paymentId;

    private Boolean isGift;
}
