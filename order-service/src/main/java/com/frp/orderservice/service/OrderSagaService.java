package com.frp.orderservice.service;

import com.frp.contracts.events.MaterialReservationFailedEvent;
import com.frp.contracts.events.MaterialReservedEvent;
import com.frp.orderservice.model.Order;
import com.frp.orderservice.model.OrderStatus;
import com.frp.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSagaService {

    private final OrderRepository orderRepository;

    public void handleMaterialReserved(MaterialReservedEvent event) {

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.MATERIAL_RESERVED);
        orderRepository.save(order);
    }

    public void handleMaterialReservationFailed(MaterialReservationFailedEvent event) {

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

        // (Optional next step)
        // emit OrderCancelledEvent
    }
}
