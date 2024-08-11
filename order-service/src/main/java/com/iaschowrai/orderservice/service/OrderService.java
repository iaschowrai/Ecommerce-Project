package com.iaschowrai.orderservice.service;

import com.iaschowrai.orderservice.webclient.InventoryClient;
import com.iaschowrai.orderservice.dto.OrderRequest;
import com.iaschowrai.orderservice.exception.ProductNotInStockException;
import com.iaschowrai.orderservice.model.Order;
import com.iaschowrai.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;


    // Method to place an order based on the provided OrderRequest
    public void placeOrder(OrderRequest orderRequest) {
        // calling inventory client
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {
            // map OrderRequest to Order object
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());

            log.info("Received Order Details: OrderNumber={}, SkuCode={}, Price={}, Quantity={}",
                    order.getOrderNumber(), order.getSkuCode(), order.getPrice(), order.getQuantity());

            // save order to OrderRepository
            orderRepository.save(order);
        } else {
            throw new ProductNotInStockException("Product with SkuCode " + orderRequest.skuCode() + " is out of stock.");
        }
    }
}
