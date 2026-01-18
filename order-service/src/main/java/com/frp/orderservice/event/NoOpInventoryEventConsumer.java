package com.frp.orderservice.event;

import com.frp.contracts.events.MaterialReservationFailedEvent;
import com.frp.contracts.events.MaterialReservedEvent;
import com.frp.orderservice.service.OrderSagaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!Kafka")
@RequiredArgsConstructor
public class NoOpInventoryEventConsumer implements InventoryEventConsumer {

    private final OrderSagaService sagaService;

    @Override
    public void onMaterialReserved(MaterialReservedEvent event) {
//        log.info("Saga SUCCESS received: {}", event);
        sagaService.handleMaterialReserved(event);
    }

    @Override
    public void onMaterialReservationFailed(MaterialReservationFailedEvent event) {
//        log.info("Saga FAILURE received: {}", event);
        sagaService.handleMaterialReservationFailed(event);
    }
}
