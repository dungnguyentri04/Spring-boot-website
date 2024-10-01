package com.example.ecommerce_d.model;

import jakarta.persistence.*;
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
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String imagePath;

    private boolean thumbnail;

    private String displayColour;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
