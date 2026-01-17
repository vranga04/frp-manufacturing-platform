package com.frp.orderservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CreateOrderResponse {

    private String orderId;
    private String customerName;
    private String tankType;
    private int capacityLiters;
    private int quantity;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}
