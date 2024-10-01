package com.example.ecommerce_d.repository;

import com.example.ecommerce_d.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping,Integer> {
}
