package com.frp.inventoryservice.event;

import com.frp.contracts.events.OrderCreatedEvent;
import com.frp.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!kafka")
@RequiredArgsConstructor
public class NoOpOrderEventConsumer {

    private final InventoryService inventoryService;

    public void simulate(OrderCreatedEvent event) {
        inventoryService.reserveMaterial(event);
    }
}
