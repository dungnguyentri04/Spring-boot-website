package com.example.ecommerce_d.repository;

import com.example.ecommerce_d.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {
}
