package com.example.ecommerce_d.controller.Web;

import com.example.ecommerce_d.model.Cart;
import com.example.ecommerce_d.model.Product;
import com.example.ecommerce_d.model.User;
import com.example.ecommerce_d.service.ProductService;
import com.example.ecommerce_d.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/web")
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(Principal principal,Model model){
        checkPrinciple(principal,model);
        return "web/index";
    }

    @GetMapping("/contact")
    public String contact(Principal principal,Model model){
        checkPrinciple(principal,model);
        return "/web/contact";
    }

    @GetMapping("/shop")
    public String shop(Model model,Principal principal){
        checkPrinciple(principal,model);
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("productList",productList);
        return "web/shop";
    }

    @GetMapping("/cart")
    public String cart(Principal principal,Model model){
        checkPrinciple(principal,model);
        return "web/cart";
    }

    @GetMapping("/testimonial")
    public String testimonial(Principal principal, Model model){
        checkPrinciple(principal,model);
        return "web/testimonial";
    }

    @GetMapping("/404")
    public String error(){

        return "web/404";
    }

    @GetMapping("/login")
    public String login(){
        return "/web/login";
    }

    @GetMapping("/shop-detail")
    public String shopDetail(@RequestParam("id") int id,Principal principal,Model model){
        checkPrinciple(principal,model);
        Product product = productService.getProductById(id);
        if (product==null) return "web/404";
        model.addAttribute("product",product);
        return "web/shop-detail";
    }

     private void checkPrinciple(Principal principal,Model model){
        if (principal!=null){
            User user = userService.getUserByEmail(principal.getName());
            Cart cart = user.getCart();
            model.addAttribute("username",user.getFullName());
            if (cart!=null) {
                model.addAttribute("totalItem", cart.getTotalItems());
            }
            else  model.addAttribute("totalItem",0);
        }
    }

}
