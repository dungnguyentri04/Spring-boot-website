package com.example.ecommerce_d.repository;

import com.example.ecommerce_d.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
