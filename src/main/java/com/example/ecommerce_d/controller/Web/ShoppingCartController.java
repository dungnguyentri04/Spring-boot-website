package com.example.ecommerce_d.controller.Web;

import com.example.ecommerce_d.model.Cart;
import com.example.ecommerce_d.model.CartItem;
import com.example.ecommerce_d.model.Product;
import com.example.ecommerce_d.model.User;
import com.example.ecommerce_d.service.CartItemService;
import com.example.ecommerce_d.service.CartService;
import com.example.ecommerce_d.service.ProductService;
import com.example.ecommerce_d.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class ShoppingCartController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/cart")
    public String cart(Model m, Principal principal){
        if (principal==null){
            return "redirect://login";
        }
        User user = userService.getUserByEmail(principal.getName());

        Cart cart = user.getCart();
        m.addAttribute("username", user.getFullName());
        if (cart!=null) {
            Set<CartItem> cartItems = cart.getCartItems();
            m.addAttribute("cartItems", cartItems);
            m.addAttribute("user", user);
            m.addAttribute("totalPrice",cart.getTotalPrice());
            m.addAttribute("totalItem",cart.getTotalItems());
        }
        else {
            // Xử lý trường hợp cart là null, ví dụ:
            m.addAttribute("cartItems", new HashSet<>());
            m.addAttribute("user", user);
            m.addAttribute("totalPrice",0);
            m.addAttribute("totalItem",0);

        }
        return "web/cart";
    }

    @GetMapping("add-to-cart")
    public String addToCart(@RequestParam("id") int id,
                            @RequestParam("quantity") int quantity,
                            Model model,
                            Principal principal){
        if (principal == null){
            return "web/login";
        }
        User user = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(id);
        Cart cart = cartService.addToCart(product,user);
        return "redirect:/web/shop";
    }

    @PostMapping(value = "/update-cart")
    public String updateCart(@RequestBody UpdateCartRequest request, Model model,
                             Principal principal,
                             HttpSession session){
        String action = request.getAction();
        if (principal == null){
            return "redirect://login";
        }
        User user  = userService.getUserByEmail(principal.getName());
        Product product = productService.getProductById(request.getId());
        if (action.equals("delete")){
            Cart cart = cartService.deleteItemFromCart(product,user);
        } else if (action.equals("update")) {
            Cart cart = cartService.updateItemFromCart(product,user);
        }
        else {
            Cart cart = cartService.removeItemFromCart(product,user);
        }
        return "web/cart";
    }

    @Data
    public static class UpdateCartRequest {
        private int id;
        private String action;
    }
}
