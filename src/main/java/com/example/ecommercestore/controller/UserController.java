package com.example.ecommercestore.controller;

import com.example.ecommercestore.dto.UserRegisterDto;
import com.example.ecommercestore.models.User;
import com.example.ecommercestore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

//    @GetMapping("/register")
//    public String register(Model model) {
//        model.addAttribute("userForm", new User());
//        return "register";
//    }

    @RequestMapping(value = {"/register", "/"}, method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @PostMapping("/signup")
    public User creatUser(@RequestBody UserRegisterDto userRegisterDto){
        User user = userService.create(userRegisterDto);
        return user;
    }

}
