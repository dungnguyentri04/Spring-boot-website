package com.example.ecommerce_d.service.impl;

import com.example.ecommerce_d.model.Cart;
import com.example.ecommerce_d.model.CartItem;
import com.example.ecommerce_d.model.Product;
import com.example.ecommerce_d.model.User;
import com.example.ecommerce_d.repository.CartItemRepository;
import com.example.ecommerce_d.repository.CartRepository;
import com.example.ecommerce_d.repository.UserRepository;
import com.example.ecommerce_d.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override// phan nay xem lai thuat toan
    @Transactional // hoc lai
    public Cart addToCart(Product product, User user) {
        Cart cart = user.getCart();
        if (cart==null){
            cart = new Cart();
//            cart.setUser(user);
        }
        Set<CartItem> listCartItems = cart.getCartItems();
        CartItem item = findItem(product,listCartItems);
        if (listCartItems == null){
            listCartItems = new HashSet<>();
        }
        if (item==null){
            item = new CartItem();
            item.setProduct(product);
//            item.setPrice(product.getPrice()); co can them khong ?
            //them tong tien cua item
            item.setQuantity(1);//chinh lai phan nay
            item.setCart(cart);
            listCartItems.add(item);
            cartItemRepository.save(item);
        }
        else {
            item.setQuantity(item.getQuantity()+1);// chinh lai phan nay
            cartItemRepository.save(item);
        }
        cart.setCartItems(listCartItems);
        user.setCart(cart);
        cart.setUser(user);
        cart.setTotalItems(cart.getTotalItems()+1);//???
        cart.setTotalPrice(cart.getTotalPrice()+ item.getProduct().getPrice());///???
        return cartRepository.save(cart);//tra ve 1 shoppingcart sau khi luu
    }

    @Override//xem lai
    public Cart deleteItemFromCart(Product product, User user) {
        Cart cart = user.getCart();
        Set<CartItem> listCartItems = cart.getCartItems();
        CartItem item = findItem(product,listCartItems);
        int quantity = item.getQuantity() - 1;//item = null?
        if (quantity<=0){
            return removeItemFromCart(product,user);
        }
        else {
            item.setQuantity(quantity);
            cartItemRepository.save(item);
        }
        cart.setCartItems(listCartItems);
        cart.setTotalPrice(cart.getTotalPrice()-item.getProduct().getPrice());
        cart.setTotalItems(cart.getTotalItems()-1);
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateItemFromCart(Product product, User user) {
        Cart cart = user.getCart();
        Set<CartItem> listCartItems = cart.getCartItems();
        CartItem item = findItem(product,listCartItems);
        int quantity = item.getQuantity() + 1;
        item.setQuantity(quantity);
        cart.setCartItems(listCartItems);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeItemFromCart(Product product, User user) {
        Cart cart = user.getCart();
        Set<CartItem> listCartItems = cart.getCartItems();
        CartItem item = findItem(product,listCartItems);
        listCartItems.remove(item);
        cartItemRepository.delete(item);//item == null?
        cart.setCartItems(listCartItems);
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    @Override//???
    public void deleteCartById(int id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        System.out.println(cart);
        if(!ObjectUtils.isEmpty(cart) && !ObjectUtils.isEmpty(cart.getCartItems())){
            cartItemRepository.deleteAll(cart.getCartItems());
        }
        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);
        cart.setTotalItems(0);
        cartRepository.save(cart);
    }

    private CartItem findItem(Product product,Set<CartItem> listCartItems){
        for (CartItem cartItem:listCartItems){
            if (cartItem.getProduct().getId() == product.getId()){
                return cartItem;
            }
        }
        return null;

    }

}
