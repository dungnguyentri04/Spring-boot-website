package com.example.ecommerce_d.repository;

import com.example.ecommerce_d.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
