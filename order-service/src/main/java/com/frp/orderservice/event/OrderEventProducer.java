package com.frp.orderservice.event;

import com.frp.contracts.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
@RequiredArgsConstructor
public class OrderEventProducer implements OrderEventPublisher {

    private static final String TOPIC = "order-events";

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Override
    public void publishOrderCreatedEvent(OrderCreatedEvent event) {
        kafkaTemplate.send(TOPIC, event.getOrderId(), event);
    }
}
