package com.example.ecommerce_d.service.impl;

import com.example.ecommerce_d.model.*;
import com.example.ecommerce_d.repository.OrderItemRepository;
import com.example.ecommerce_d.repository.OrderRepository;
import com.example.ecommerce_d.repository.ShippingRepository;
import com.example.ecommerce_d.service.CartService;
import com.example.ecommerce_d.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    @Override
    @Transactional//xem lai
    public Order save(Cart cart, Shipping shippingAddress) {
        Order order = new Order();
        order.setUser(cart.getUser());
//        order.setPaymentMethod("money");paidment??
//        order.setOrderStatusId(0);//true,false???
//        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderApprovedAt(LocalDateTime.now());
//        order.setQuantity(cart.getTotalItems());
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()){//xu li nhung cartItem duoc chon
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());//??????
            orderItemRepository.save(orderItem);//
            orderItemList.add(orderItem);
        }
        order.setOrderItems(orderItemList);
        ///shipping,order
//        shippingAddress.se(order);
//        order.setShippingAddress(shippingAddress);
//        shippingAddressRepository.save(shippingAddress);
//        shoppingCartService.deleteCartById(cart.getId());delete cart????
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }
}
