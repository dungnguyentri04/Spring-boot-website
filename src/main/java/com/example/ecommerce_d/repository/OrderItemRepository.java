package com.example.ecommerce_d.repository;

import com.example.ecommerce_d.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
