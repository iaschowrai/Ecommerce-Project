package com.iaschowrai.inventoryservice.service;

import com.iaschowrai.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode, Integer quantity){
        // FInd an inventory for a given skuCode where quantity is >= 0
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);
    }

}
