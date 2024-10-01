package com.example.ecommerce_d.service;

import com.example.ecommerce_d.model.Cart;
import com.example.ecommerce_d.model.Order;
import com.example.ecommerce_d.model.Shipping;

import java.util.List;

public interface OrderService {
    public Order save(Cart cart, Shipping shippingAddress);
    public List<Order> getAllOrder();
}
