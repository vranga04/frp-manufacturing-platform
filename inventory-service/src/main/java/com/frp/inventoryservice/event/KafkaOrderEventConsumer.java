package com.frp.inventoryservice.event;

import com.frp.contracts.events.OrderCreatedEvent;
import com.frp.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
@RequiredArgsConstructor
public class KafkaOrderEventConsumer {

    private final InventoryService inventoryService;

    @KafkaListener(topics = "order-events", groupId = "inventory-service")
    public void consume(OrderCreatedEvent event) {
        inventoryService.reserveMaterial(event);
    }
}
