package com.frp.inventoryservice.repository;

import com.frp.inventoryservice.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    Optional<InventoryItem> findByMaterialName(String materialName);
}
