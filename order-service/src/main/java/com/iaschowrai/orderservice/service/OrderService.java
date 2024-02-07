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
        // Create a new Order instance
        Order order = new Order();
        // Generate a unique order number using UUID
        order.setOrderNumber(UUID.randomUUID().toString());

        // Map OrderLineItemsDto objects from OrderRequest to OrderLineItems entities
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        // Save the order to the repository
        orderRepository.save(order);
    }

    // Private method to map OrderLineItemsDto to OrderLineItems entity
    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }

}
