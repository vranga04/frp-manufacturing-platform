package com.frp.orderservice;

import com.frp.contracts.events.MaterialReservationFailedEvent;
import com.frp.orderservice.event.NoOpInventoryEventConsumer;
import com.frp.orderservice.model.Order;
import com.frp.orderservice.model.OrderStatus;
import com.frp.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrderSagaSimulationTest {

    @Autowired
    private NoOpInventoryEventConsumer consumer;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    MaterialReservationFailedEvent event;

//    @Test
//    void shouldCancelOrder_whenInventoryFails() {
//
//        // Create order in CREATED state
//        Order order = orderRepository.save(
//                Order.builder()
//                        .id("ORD-SAGA-FAIL")
//                        .customerName("Test")
//                        .status(OrderStatus.CREATED)
//                        .build()
//        );
//
//
//        event.setOrderId(order.getId());
//        event.setReason("Insufficient inventory");
//
//        consumer.onMaterialReservationFailed(event);
//
//        Order updated = orderRepository.findById(order.getId()).get();
//        assertEquals(OrderStatus.CANCELLED, updated.getStatus());
//    }
}

