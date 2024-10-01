package com.example.ecommerce_d.repository;

import com.example.ecommerce_d.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByEmail(String email);
}
