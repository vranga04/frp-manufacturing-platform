package com.frp.orderservice.event;

import com.frp.contracts.events.MaterialReservationFailedEvent;
import com.frp.contracts.events.MaterialReservedEvent;
import com.frp.orderservice.service.OrderSagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Profile("kafka")
@RequiredArgsConstructor
public class KafkaInventoryEventConsumer {

    private final OrderSagaService sagaService;

    Logger log = Logger.getLogger(KafkaInventoryEventConsumer.class.getName());

    @KafkaListener(topics = "inventory-events", groupId = "order-service")
    public void consume(Object event) {

        if (event instanceof MaterialReservedEvent reserved) {
            log.info("Saga SUCCESS received for order {}");
            sagaService.handleMaterialReserved(reserved);
        }

        if (event instanceof MaterialReservationFailedEvent failed) {
            log.info("Saga FAILED received for order {}");
            sagaService.handleMaterialReservationFailed(failed);
        }
    }
}
