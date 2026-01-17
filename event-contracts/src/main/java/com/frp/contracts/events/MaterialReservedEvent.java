package com.frp.contracts.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialReservedEvent {
    private String eventId;
    private String orderId;
    private String materialName;
    private int reservedQuantity;
}
