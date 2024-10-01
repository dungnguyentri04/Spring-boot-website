package com.example.ecommerce_d.controller.Web;

import com.example.ecommerce_d.DTO.UserDTO;
import com.example.ecommerce_d.errors.UserAlreadyExistException;
import com.example.ecommerce_d.model.User;
import com.example.ecommerce_d.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/web")
public class UserWebController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(WebRequest request, Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user",userDTO);
        return "/web/register";
    }

    @PostMapping("/saveUser")//xu ly password confirm
    public String registerAccountUser(
            @ModelAttribute("user") @Valid UserDTO userDTO,//valid xac thuc
                                      HttpServletRequest request,
                                      Errors errors,// doi tuong errors
                                      HttpSession session,
                                      Model model) throws IOException {//thieu profileImg
//        String imageName = file.isEmpty()?"default.jpg":file.getOriginalFilename();
//        user.setProfileImg(imageName);
        try {
            System.out.println("oke");
            User register = userService.saveUser(userDTO);
            return "web/login";
        }
        catch (UserAlreadyExistException UaeEx){
            model.addAttribute("message", "An account for that username/email already exists.");
            return "redirect://web/register"; //

        }
//        if (!ObjectUtils.isEmpty(saveUser))
//        {
////            if (!file.isEmpty()) {
////                File saveFile = new ClassPathResource("static/img").getFile();
////                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "profile_img" + File.separator + file.getOriginalFilename());
////                if (!Files.exists(path)){
////                    Files.createDirectories(path);
////                }
////                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
////            }
//            session.setAttribute("successMsg","Saved successfully");
//        } else {
//            session.setAttribute("errorMsg","Something wrong on server");
//        }
    }
}
