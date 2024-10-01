package com.example.ecommerce_d.repository;

import com.example.ecommerce_d.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface RatingRepository extends JpaRepository<Rating,Integer> {
}
