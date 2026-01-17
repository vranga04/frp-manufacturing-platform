package com.frp.inventoryservice.event;

import com.frp.contracts.events.OrderCreatedEvent;

public interface OrderEventConsumer {
    void onOrderCreated(OrderCreatedEvent event);
}
