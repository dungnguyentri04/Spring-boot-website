package com.example.ecommerce_d.service;

import com.example.ecommerce_d.model.Cart;
import com.example.ecommerce_d.model.Product;
import com.example.ecommerce_d.model.User;

import java.util.List;

public interface CartService {
    public Cart addToCart(Product product, User user);
    public Cart deleteItemFromCart(Product product, User user);
    public Cart updateItemFromCart(Product product, User user);
    public Cart removeItemFromCart(Product product, User user);
    public List<Cart> getAllCart();
    public void deleteCartById(int id);
}
