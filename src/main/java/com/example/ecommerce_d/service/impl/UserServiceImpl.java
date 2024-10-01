package com.example.ecommerce_d.service.impl;

import com.example.ecommerce_d.DTO.UserDTO;
import com.example.ecommerce_d.errors.UserAlreadyExistException;
import com.example.ecommerce_d.model.User;
import com.example.ecommerce_d.repository.UserRepository;
import com.example.ecommerce_d.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User saveUser(UserDTO userDTO){// thay UserAlreadyExistException
        if (emailExist(userDTO.getEmail())) throw new UserAlreadyExistException("There is an account with that email address: " + userDTO.getEmail());
        final User user = new User();
        String password = passwordEncoder.encode(userDTO.getPassword());//ma hoa
        user.setPassword(password);
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
//        user.setRoles("USER");
        return userRepository.save(user);
    }

    private boolean emailExist(String email){
        return userRepository.findByEmail(email)!=null;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user, MultipartFile image) {
        User changeUser = getUserById(user.getId());
        String imageName = image.isEmpty() ? changeUser.getProfileImg() : image.getOriginalFilename();
        changeUser.setFirstName(user.getFirstName());
        changeUser.setEmail(user.getEmail());
        changeUser.setLastName(user.getLastName());
        changeUser.setPhoneNumber(user.getPhoneNumber());
        changeUser.setProfileImg(imageName);
//        changeUser.setRoles(user.getRoles()); // sua ve role
        //them bot mot so thong tin nua
        User updateUser = userRepository.save(changeUser);
        if (!ObjectUtils.isEmpty(updateUser)){
            if (!image.isEmpty()){
                try {
                    File saveFile = new ClassPathResource("static/img").getFile();
                    Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "profile_img" + File.separator + image.getOriginalFilename());
                    Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                }
                catch (Exception e) {
                    //logger
                    e.printStackTrace();
                }
            }
            return user;
        }
        return null;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (!ObjectUtils.isEmpty(user)){
            //xoa moi thu lien quan
            userRepository.delete(user);
            return true;
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
