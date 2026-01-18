package com.frp.inventoryservice.event;

import com.frp.contracts.events.MaterialReservationFailedEvent;
import com.frp.contracts.events.MaterialReservedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("Kafka")
@RequiredArgsConstructor
@Slf4j
public class KafkaInventoryEventPublisher implements InventoryEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishMaterialReserved(MaterialReservedEvent event) {
        log.info("Publishing MaterialReservedEvent for order {}", event.getOrderId());
        kafkaTemplate.send("inventory-events", String.valueOf(event.getOrderId()), event);
    }

    @Override
    public void publishReservationFailed(MaterialReservationFailedEvent event) {
        log.info("Publishing MaterialReservationFailedEvent for order {}", event.getOrderId());
        kafkaTemplate.send("inventory-events", String.valueOf(event.getOrderId()), event);
    }
}
