package com.example.ecommerce_d.service.impl;

import com.example.ecommerce_d.model.Category;
import com.example.ecommerce_d.repository.CategoryRepository;
import com.example.ecommerce_d.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (!ObjectUtils.isEmpty(category)){
            categoryRepository.delete(category);
            return true;
        }
        return null;
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category updateCategory(Category category, MultipartFile file) throws IOException {
        return null;
    }
}
