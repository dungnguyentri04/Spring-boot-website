package com.example.ecommerce_d.controller.Dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashBoardController {
    @GetMapping("/index")
    public String viewIndex(){
        return "dashboard/index";
    }

    @GetMapping("/icon_menu")
    public String viewIconMenu(){
        return "dashboard/icon-menu";
    }

    @GetMapping("/sidebar_style")
    public String viewSidebarStyle(){
        return "dashboard/sidebar-style-2";
    }

    @GetMapping("/forms/forms")
    public String viewForm(){
        return "dashboard/forms/forms";
    }

    @GetMapping("/charts/charts")
    public String viewCharts(){
        return "dashboard/charts/charts";
    }

    @GetMapping("/charts/sparkline")
    public String viewSparkline(){
        return "dashboard/charts/sparkline";
    }

//    @GetMapping("/tables")
//    public String viewTables(){
//        return "dashboard/tables/tables";
//    }

    @GetMapping("/information/users")
    public String viewUsers(){
        return "dashboard/information/users";
    }

    @GetMapping("/forms/users")
    public String formUsers(){
        return "dashboard/forms/users";
    }

    @GetMapping("/forms/categories")
    public String formCategories(){
        return "dashboard/forms/categories";
    }

    @GetMapping("/forms/products")
    public String formProducts(){
        return "dashboard/forms/products";
    }

    @GetMapping("/forms/coupons")
    public String formCoupons(){
        return "dashboard/forms/coupons";
    }

    @GetMapping("/forms/ratings")
    public String formRatings(){
        return "dashboard/forms/ratings";
    }

    @GetMapping("/forms/shippings")
    public String formShippings(){
        return "dashboard/forms/shippings";
    }

    @GetMapping("/tables/users")
    public String tableUser(){
        return "dashboard/tables/user";
    }


//    @GetMapping("/datatables")
//    public String viewDataTables(){
//        return "dashboard/tables/datatables";
//    }
}
