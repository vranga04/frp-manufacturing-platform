package com.frp.inventoryservice.event;

import com.frp.contracts.events.MaterialReservationFailedEvent;
import com.frp.contracts.events.MaterialReservedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!kafka")
public class NoOpInventoryEventPublisher implements InventoryEventPublisher {

    @Override
    public void publishMaterialReserved(MaterialReservedEvent event) {
        log.info("Saga SUCCESS → Material reserved: {}", event);
    }

    @Override
    public void publishReservationFailed(MaterialReservationFailedEvent event) {
        log.info("Saga FAILURE → Reservation failed: {}", event);
    }
}
