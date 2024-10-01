package com.example.ecommerce_d.repository;

import com.example.ecommerce_d.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByCategoryNameIn(List<String> categories);
}
