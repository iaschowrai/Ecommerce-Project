package com.iaschowrai.orderservice.controller;

import com.iaschowrai.orderservice.dto.OrderRequest;
import com.iaschowrai.orderservice.exception.ProductNotInStockException;
import com.iaschowrai.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order Placed Successfully");
    }

    @ExceptionHandler(ProductNotInStockException.class)
    public ResponseEntity<String> handleProductNotInStock(ProductNotInStockException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
