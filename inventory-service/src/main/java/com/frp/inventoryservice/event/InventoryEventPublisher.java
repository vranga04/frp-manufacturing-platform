package com.frp.inventoryservice.event;

import com.frp.contracts.events.MaterialReservationFailedEvent;
import com.frp.contracts.events.MaterialReservedEvent;

public interface InventoryEventPublisher {

    void publishMaterialReserved(MaterialReservedEvent event);

    void publishReservationFailed(MaterialReservationFailedEvent event);
}
