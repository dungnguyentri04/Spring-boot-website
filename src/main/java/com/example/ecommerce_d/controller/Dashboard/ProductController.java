package com.example.ecommerce_d.controller.Dashboard;

import com.example.ecommerce_d.model.Category;
import com.example.ecommerce_d.model.Product;
import com.example.ecommerce_d.model.Tag;
import com.example.ecommerce_d.repository.CategoryRepository;
import com.example.ecommerce_d.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/product")
    public String viewProduct(){
        return "admin/product";
    }

    @PostMapping("/saveProduct")// them tag
    public String saveProduct(@ModelAttribute Product product, HttpSession session,
                              @RequestParam("file")MultipartFile image) throws Exception{
        String imagePath = image.isEmpty()?"default.jgp":image.getOriginalFilename();
        product.setImagePath(imagePath);
//        List<Category> categories = categoryRepository.findByCategoryNameIn(categoryNames);
//        product.setCategories(categories);
//        List<String> tag_products = Arrays.asList(tags.split("\\s*,\\s*"));
//tag
        Product saveProduct = productService.saveProduct(product);
        if (!ObjectUtils.isEmpty(saveProduct)){
            File saveFile = new ClassPathResource("static/dashboard_stt/img").getFile(); // lay thu muc tinh
            Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+"product_img"+File.separator+image.getOriginalFilename());//tao duong dan cho tep hinh anh
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }//note:tao thu muc chua hinh anh neu chua ton tai
            Files.copy(image.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);//sao chep noi dung hinh anh tu multipartFile
            session.setAttribute("successMsg","product saved success");
        }
        return "redirect:/dashboard/forms/products";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id, HttpSession session){
        Boolean deleteProduct = productService.deleteProduct(id);
        if (deleteProduct){
            session.setAttribute("successMsg","product delete success");
        }
        else {
            session.setAttribute("errorMsg","something wrong on server");
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/editProduct")
    public String editProduct(HttpSession session, @RequestParam("file") MultipartFile image,@ModelAttribute Product product) throws IOException {
        Product updateProduct = productService.updateProduct(product,image);
        if (!ObjectUtils.isEmpty(updateProduct)){
            session.setAttribute("successMsg","product saved success");
        }
        else {
            session.setAttribute("errorMsg","something wrong on server");
        }
        return "redirect:/admin/products";
    }
}
