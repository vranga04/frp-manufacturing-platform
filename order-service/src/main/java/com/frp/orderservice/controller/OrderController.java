package com.frp.orderservice.controller;

import com.frp.orderservice.dto.CreateOrderRequest;
import com.frp.orderservice.dto.CreateOrderResponse;
import com.frp.orderservice.dto.CreateOrderResponse;
import com.frp.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(
            @Valid @RequestBody CreateOrderRequest request) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        var order = orderService.createOrder(request);

        return CreateOrderResponse.builder()
                .orderId(order.getId())
                .status(order.getStatus().name())
                .build();
    }

    @GetMapping("/{orderId}")
    public CreateOrderResponse getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping
    public Page<CreateOrderResponse> getOrders(Pageable pageable) {
        return orderService.getOrders(pageable);
    }
}
