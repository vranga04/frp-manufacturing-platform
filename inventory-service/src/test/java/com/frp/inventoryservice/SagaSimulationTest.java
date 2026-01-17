package com.frp.inventoryservice;

import com.frp.contracts.events.OrderCreatedEvent;
import com.frp.inventoryservice.event.NoOpOrderEventConsumer;
import com.frp.inventoryservice.model.InventoryItem;
import com.frp.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.UUID;

@SpringBootTest
class SagaSimulationTest {

    @Autowired
    private NoOpOrderEventConsumer consumer;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    OrderCreatedEvent event;

//    @Test
//    void shouldReserveMaterialSuccessfully() {
//
//        event.setEventId(UUID.randomUUID().toString());
//        event.setOrderId("ORD-TEST-1");
//        event.setTankType("FRP");
//        event.setCapacityLiters(100);
//        event.setQuantity(2);
//        event.setCreatedAt(Instant.now());
//
//        consumer.simulate(event);
//    }

//    @Test
//    void shouldFailMaterialReservation_whenInventoryIsInsufficient() {
//
//        // 🔻 Force LOW inventory
//        inventoryRepository.deleteAll();
//
//        inventoryRepository.save(
//                InventoryItem.builder()
//                        .materialName("RESIN")
//                        .availableQuantity(10) // deliberately low
//                        .build()
//        );
//
//        // 🔥 Create order that needs MORE than available
//        event.setEventId(UUID.randomUUID().toString());
//        event.setOrderId("ORD-FAIL-1");
//        event.setTankType("FRP");
//        event.setCapacityLiters(100);
//        event.setQuantity(2); // requires 200
//        event.setCreatedAt(Instant.now());
//
//        consumer.simulate(event);
//    }
}
