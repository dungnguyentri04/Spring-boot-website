package com.example.ecommerce_d.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany
    @JoinTable(name = "order_coupon",
            joinColumns = @JoinColumn(name = "coupon_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id",referencedColumnName = "id"))
    private List<Order> orders;

    private String code;

    private String couponDescription;

    private int discountValue;

    private String discountType;

    private int timeUsed;

    private int maxUsed;

    private LocalDateTime couponStartDate;

    private LocalDateTime couponEndDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
