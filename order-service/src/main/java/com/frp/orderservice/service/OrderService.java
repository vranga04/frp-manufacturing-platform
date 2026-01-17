package com.frp.orderservice.service;

import com.frp.contracts.events.OrderCreatedEvent;
import com.frp.orderservice.dto.CreateOrderRequest;
import com.frp.orderservice.dto.CreateOrderResponse;
import com.frp.orderservice.event.OrderEventPublisher;
import com.frp.orderservice.exception.OrderNotFoundException;
import com.frp.orderservice.model.Order;
import com.frp.orderservice.model.OrderStatus;
import com.frp.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    public Order createOrder(CreateOrderRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Order order = Order.builder()
                .id("ORD-" + UUID.randomUUID())
                .customerName(request.getCustomerName())
                .tankType(request.getTankType())
                .capacityLiters(request.getCapacityLiters())
                .quantity(request.getQuantity())
                .status(OrderStatus.CREATED)
                .build();

        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .orderId(savedOrder.getId())
                .customerName(savedOrder.getCustomerName())
                .tankType(savedOrder.getTankType())
                .capacityLiters(savedOrder.getCapacityLiters())
                .quantity(savedOrder.getQuantity())
                .createdAt(savedOrder.getCreatedAt())
                .build();

        orderEventPublisher.publishOrderCreatedEvent(event);

        return savedOrder;
    }

    public CreateOrderResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + orderId));

        return mapToResponse(order);
    }

    public Page<CreateOrderResponse> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    private CreateOrderResponse mapToResponse(Order order) {
        return CreateOrderResponse.builder()
                .orderId(order.getId())
                .customerName(order.getCustomerName())
                .tankType(order.getTankType())
                .capacityLiters(order.getCapacityLiters())
                .quantity(order.getQuantity())
                .status(order.getStatus().name())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
