package com.iaschowrai.orderservice.service;

import com.iaschowrai.orderservice.dto.OrderLineItemsDto;
import com.iaschowrai.orderservice.dto.OrderRequest;
import com.iaschowrai.orderservice.model.Order;
import com.iaschowrai.orderservice.model.OrderLineItems;
import com.iaschowrai.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    // Method to place an order based on the provided OrderRequest
    public void placeOrder(OrderRequest orderRequest){
       // map OrderRequest to Order object
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());

        // save order to OrderRepository
        orderRepository.save(order);
    }



}
