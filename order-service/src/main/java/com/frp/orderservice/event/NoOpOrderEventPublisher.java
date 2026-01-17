package com.frp.orderservice.event;

import com.frp.contracts.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!kafka")
public class NoOpOrderEventPublisher implements OrderEventPublisher {

    @Override
    public void publishOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Kafka disabled. Skipping event publish: {}", event);
    }
}
