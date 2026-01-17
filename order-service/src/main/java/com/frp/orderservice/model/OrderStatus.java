package com.frp.orderservice.model;

public enum OrderStatus {
    CREATED,
    MATERIAL_RESERVED,
    PRODUCTION_STARTED,
    PRODUCTION_COMPLETED,
    QUALITY_PASSED,
    DISPATCHED,
    COMPLETED,
    CANCELLED,
    FAILED
}
