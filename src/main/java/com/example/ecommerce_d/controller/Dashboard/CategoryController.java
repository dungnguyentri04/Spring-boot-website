package com.example.ecommerce_d.controller.Dashboard;

import com.example.ecommerce_d.model.Category;
import com.example.ecommerce_d.service.CategoryService;
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

@Controller
@RequestMapping("/dashboard")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String viewCategory(){
        return "admin/category";
    }

    @PostMapping("/saveCategory")
    public String savCategory(@ModelAttribute Category category, HttpSession session, @RequestParam("file") MultipartFile image) throws Exception{
        String imagePath = image.isEmpty()?"default.jgp":image.getOriginalFilename();
        category.setImagePath(imagePath);
        System.out.println("1");
        Category saveCategory = categoryService.saveCategory(category);
        //tim nhung category trung nhau
        if (!ObjectUtils.isEmpty(saveCategory)){
            File saveFile = new ClassPathResource("static/dashboard_stt/img").getFile(); // lay thu muc tinh
            Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+"category_img"+File.separator+image.getOriginalFilename());//tao duong dan cho tep hinh anh
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }//tao thu muc chua hinh anh neu chua ton tai
            Files.copy(image.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);//sao chep noi dung hinh anh tu multipartFile
            session.setAttribute("successMsg","category saved success");
        }
        return "redirect:/dashboard/forms/categories";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteProduct(@PathVariable int id, HttpSession session){
        Boolean deleteCategory = categoryService.deleteCategory(id);
        if (deleteCategory){
            session.setAttribute("successMsg","category delete success");
        }
        else {
            session.setAttribute("errorMsg","something wrong on server");
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/editCategory")
    public String editCategory(HttpSession session, @RequestParam("file") MultipartFile image,@ModelAttribute Category category) throws IOException {
        Category updateCategory = categoryService.updateCategory(category,image);
        if (!ObjectUtils.isEmpty(updateCategory)){
            session.setAttribute("successMsg","category saved success");
        }
        else {
            session.setAttribute("errorMsg","something wrong on server");
        }
        return "redirect:/admin/categories";
    }
}
