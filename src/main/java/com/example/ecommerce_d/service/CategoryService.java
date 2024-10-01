package com.example.ecommerce_d.service;

import com.example.ecommerce_d.model.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    public Category saveCategory(Category category);

    public List<Category> getAllCategory();

    public Boolean deleteCategory(Integer id);

    public Category getCategoryById(Integer id);

    public Category updateCategory(Category category, MultipartFile file) throws IOException;//...IOExeption

}
