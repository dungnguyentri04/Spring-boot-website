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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)//fetch???
    private Cart cart;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order> orders;

    private String roles;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private boolean active;

    private String profileImg;

    private String gender;

    private int age;

    private LocalDateTime registerAt;

    private LocalDateTime updateAt;

    public String getFullName(){
        return this.firstName+this.lastName;
    }


}
