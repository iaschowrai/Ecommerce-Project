package com.iaschowrai.orderservice.repository;


import com.iaschowrai.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
