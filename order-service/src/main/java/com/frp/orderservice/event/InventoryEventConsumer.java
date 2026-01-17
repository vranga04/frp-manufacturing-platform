package com.frp.orderservice.event;

import com.frp.contracts.events.MaterialReservationFailedEvent;
import com.frp.contracts.events.MaterialReservedEvent;

public interface InventoryEventConsumer {

    void onMaterialReserved(MaterialReservedEvent event);

    void onMaterialReservationFailed(MaterialReservationFailedEvent event);
}
