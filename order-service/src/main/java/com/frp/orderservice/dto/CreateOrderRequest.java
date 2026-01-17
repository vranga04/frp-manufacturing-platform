package com.frp.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateOrderRequest {

    @NotBlank
    private String customerName;

    @NotBlank
    private String tankType;

    @Positive
    private int capacityLiters;

    @Positive
    private int quantity;
}
