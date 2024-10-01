package com.example.ecommerce_d.service;

import com.example.ecommerce_d.model.CartItem;
import com.example.ecommerce_d.model.User;

import java.util.List;

public interface CartItemService {
    public void deleteCartItemByProductId(int id);
}
