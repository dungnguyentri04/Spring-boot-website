package com.example.ecommerce_d.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @ManyToMany(mappedBy = "orders")
    private List<Coupon> coupons;

//    private int orderStatusId;

    private LocalDateTime orderApprovedAt;

    private LocalDateTime orderDeliverCarrierDate;

    private LocalDateTime orderDeliverCustomerDate;

    //payment
}
