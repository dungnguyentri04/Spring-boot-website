package com.example.ecommerce_d.repository;

import com.example.ecommerce_d.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
}
