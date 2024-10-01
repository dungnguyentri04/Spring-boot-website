package com.example.ecommerce_d.service.impl;

import com.example.ecommerce_d.model.Product;
import com.example.ecommerce_d.repository.ProductRepository;
import com.example.ecommerce_d.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        //set created at
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Boolean deleteProduct(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (!ObjectUtils.isEmpty(product)){ //xem mot doi tuong co rong khong
            productRepository.delete(product);
            //xoa san pham trong category
            return true;
        }
        return false;
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product updateProduct(Product product, MultipartFile image) throws IOException {
        Product changeProduct = getProductById(product.getId());
        String imageName = image.isEmpty() ? changeProduct.getImagePath() : image.getOriginalFilename();
        changeProduct.setPrice(product.getPrice());
        changeProduct.setShortDescription(product.getShortDescription());
        changeProduct.setProductDescription(product.getProductDescription());
        //setupdated at
        changeProduct.setProductWeight(product.getProductWeight());
        changeProduct.setPublished(product.isPublished());
        changeProduct.setImagePath(imageName);
        Product updateProduct = productRepository.save(changeProduct);
        if (!ObjectUtils.isEmpty(updateProduct)){
            if (!image.isEmpty()){
                try {
                    File saveFile = new ClassPathResource("static/img").getFile();
                    Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "product_img" + File.separator + image.getOriginalFilename());
                    Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                }
                catch (Exception e) {
                    // sử dụng logger tốt hơn ?
                    e.printStackTrace();
                }
            }
            return product;
        }
        return null;
    }
}
