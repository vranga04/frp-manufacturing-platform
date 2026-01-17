package com.frp.inventoryservice;

import com.frp.inventoryservice.model.InventoryItem;
import com.frp.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner seedInventory(InventoryRepository repo) {
        return args -> {
            repo.save(
                    InventoryItem.builder()
                            .materialName("RESIN")
                            .availableQuantity(1_000_000)
                            .build()
            );
        };
    }
}
