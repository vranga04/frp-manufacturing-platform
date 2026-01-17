package com.frp.orderservice.event;

import com.frp.contracts.events.OrderCreatedEvent;

public interface OrderEventPublisher {
    void publishOrderCreatedEvent(OrderCreatedEvent event);
}
