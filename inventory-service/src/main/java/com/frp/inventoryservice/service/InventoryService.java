package com.frp.inventoryservice.service;

import com.frp.contracts.events.MaterialReservationFailedEvent;
import com.frp.contracts.events.MaterialReservedEvent;
import com.frp.contracts.events.OrderCreatedEvent;
import com.frp.inventoryservice.event.*;
import com.frp.inventoryservice.model.InventoryItem;
import com.frp.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryEventPublisher eventPublisher;

    Logger log = Logger.getLogger(InventoryService.class.getName());

    /**
     * Simple business rule:
     * - FRP tank requires RESIN = capacityLiters * quantity * 1 unit
     */
    public void reserveMaterial(OrderCreatedEvent event) {

        log.info("InventoryService.reserveMaterial called for order {}");
        String material = "RESIN";
        int requiredQty = event.getCapacityLiters() * event.getQuantity();

        InventoryItem item = inventoryRepository
                .findByMaterialName(material)
                .orElse(null);

        if (item == null) {
            log.info("No inventory record found for material {}");
        } else {
            log.info("Available qty = {}, Required qty = {}");
        }

        if (item == null || item.getAvailableQuantity() < requiredQty) {
            log.info("INSUFFICIENT INVENTORY → triggering FAILURE saga");

            MaterialReservationFailedEvent failedEvent =
                    MaterialReservationFailedEvent.builder()
                            .eventId(UUID.randomUUID().toString())
                            .orderId(event.getOrderId())
                            .reason("Insufficient RESIN inventory")
                            .build();

            eventPublisher.publishReservationFailed(failedEvent);
            return;
        }

        log.info("SUFFICIENT INVENTORY → triggering SUCCESS saga");
        // Reserve inventory
        item.setAvailableQuantity(item.getAvailableQuantity() - requiredQty);
        inventoryRepository.save(item);

        MaterialReservedEvent successEvent =
                MaterialReservedEvent.builder()
                        .eventId(UUID.randomUUID().toString())
                        .orderId(event.getOrderId())
                        .materialName(material)
                        .reservedQuantity(requiredQty)
                        .build();

        eventPublisher.publishMaterialReserved(successEvent);
    }
}
