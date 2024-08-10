package com.iaschowrai.inventoryservice.controller;

import com.iaschowrai.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping //("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity){
        log.info("Received orderRequest: skuCode: {} and quantity: {}", skuCode, quantity);

        return inventoryService.isInStock(skuCode, quantity);
    }
}
