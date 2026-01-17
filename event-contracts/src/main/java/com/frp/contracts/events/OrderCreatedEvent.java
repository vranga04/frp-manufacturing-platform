package com.frp.contracts.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent {

    private String eventId;
    private String orderId;
    private String customerName;
    private String tankType;
    private int capacityLiters;
    private int quantity;
    private Instant createdAt;
}
