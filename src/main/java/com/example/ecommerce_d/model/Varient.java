package com.example.ecommerce_d.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Varient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int variantAttributeValueId;

    private int productId;
}
