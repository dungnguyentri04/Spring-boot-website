package com.example.ecommerce_d.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String productName;//

    private float price;//

    private int discountPrice;

    private int quantity;//

    private String shortDescription;//

    private String productDescription;//

    private int productWeight;//

    private boolean published;//

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String imagePath;//

    @ManyToMany(mappedBy = "products",cascade = CascadeType.ALL)
    private List<Category> categories;//

    @ManyToMany(mappedBy = "products",cascade = CascadeType.ALL)
    private List<Tag> tags;//

    @OneToMany(mappedBy = "product")
    private List<Gallery> galleries;

//
//    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
//    private List<OrderItem> orderItems;

}
