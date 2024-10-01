package com.example.ecommerce_d.service;

import com.example.ecommerce_d.DTO.UserDTO;
import com.example.ecommerce_d.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    public User saveUser(UserDTO user) ;
    public List<User> getAllUser();
    public User getUserById(Integer id);
    public User updateUser(User user, MultipartFile file);
    public Boolean deleteUser(Integer id);
    public User getUserByEmail(String email);
}
