package com.example.ecommercestore.controller;

import com.example.ecommercestore.dto.UserRegisterDto;
import com.example.ecommercestore.models.User;
import com.example.ecommercestore.service.UserService;
import com.example.ecommercestore.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView getLoginForm(){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());

        return mav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user ) { // user is coming from the login @getmapping method
        System.out.println("reached here");
        User oauthUser = userService.login(user.getEmail(), user.getPassword());

        System.out.print(oauthUser.getEmail());
        System.out.print(oauthUser.getPassword());

        if(Objects.nonNull(oauthUser))
        {
//            if (oauthUser.getEmail().equals("admin@gmail.com") && oauthUser.getPassword().equals("admin")){
                return "redirect:/admin_home";
//            }
//            return "redirect:/home";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("/home")
    public String home(Model model) {
//        List<User> listOfUsers = userService.getAllUsers();
//        model.addAttribute("listOfUsers", listOfUsers);
        return "home";
    }

    @GetMapping("/signup")
    public String  signup() {
        return "signup";
    }

    //Carry out the login logic
    @PostMapping("/signup")
    public String createAccount(@ModelAttribute UserRegisterDto user, Model model) {
        model.addAttribute("user", user);
        User LoggedInUser = userService.create(user);
//        model.addAttribute("user", LoggedInUser);

        User oauthUser = userService.login(user.getEmail(), user.getPassword());

        if(Objects.nonNull(oauthUser)) {
            return "redirect:/login";
        } else {
            return "redirect:/signup";
        }

    }

    //Carry out the login logic





}
