package com.example.ecommerce_d.service.impl;

import com.example.ecommerce_d.model.CartItem;
import com.example.ecommerce_d.model.Product;
import com.example.ecommerce_d.model.User;
import com.example.ecommerce_d.repository.CartItemRepository;
import com.example.ecommerce_d.repository.CartRepository;
import com.example.ecommerce_d.repository.ProductRepository;
import com.example.ecommerce_d.repository.UserRepository;
import com.example.ecommerce_d.service.CartItemService;
import com.example.ecommerce_d.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void deleteCartItemByProductId(int id) {
        List<CartItem> cartItems = cartItemRepository.findAll();
        for (CartItem cartItem:cartItems){
            if (cartItem.getProduct().getId() == id){
                cartItemRepository.delete(cartItem);
            }
        }

    }
}
