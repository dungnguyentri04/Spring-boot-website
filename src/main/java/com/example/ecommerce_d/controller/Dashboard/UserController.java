package com.example.ecommerce_d.controller.Dashboard;

import com.example.ecommerce_d.DTO.UserDTO;
import com.example.ecommerce_d.model.User;
import com.example.ecommerce_d.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String viewUsers(Model m){
        m.addAttribute("users",userService.getAllUser());
        return null;
    }

    @PostMapping("/saveUser")//xu ly password confirm
    public String saveUser(@ModelAttribute UserDTO user, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        String imageName = file.isEmpty()?"default.jpg":file.getOriginalFilename();
//        user.setProfileImg(imageName);
        User saveUser = userService.saveUser(user);
        if (!ObjectUtils.isEmpty(saveUser))
        {
            if (!file.isEmpty()) {
                File saveFile = new ClassPathResource("static/dashboard_stt/img").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "profile_img" + File.separator + file.getOriginalFilename());
                if (!Files.exists(path)){
                    Files.createDirectories(path);
                }
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            session.setAttribute("successMsg","Saved successfully");
        } else {
            session.setAttribute("errorMsg","Something wrong on server");
        }
        return "redirect://dashboard/forms/users";
    }

    @PostMapping("/editUser")
    public String editUser(HttpSession session, @RequestParam("file") MultipartFile image,@ModelAttribute User user) throws IOException {
        User updateUser = userService.updateUser(user,image);
        if (!ObjectUtils.isEmpty(updateUser)){
            session.setAttribute("successMsg","product saved success");
        }
        else {
            session.setAttribute("errorMsg","something wrong on server");
        }
        return null;
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id, HttpSession session){
        Boolean deleteUser = userService.deleteUser(id);
        if (deleteUser){
            session.setAttribute("successMsg","product delete success");
        }
        else {
            session.setAttribute("errorMsg","something wrong on server");
        }
        return null;
    }

}
