package com.example.ecommerce_d.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int orderId;//????

    private String address;

    private String city;

    private String location;

    private String mobile;

    private String email;

    private String postalCode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
