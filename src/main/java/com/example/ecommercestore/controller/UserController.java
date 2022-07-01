package com.example.ecommercestore.controller;

import com.example.ecommercestore.dto.UserDto;
import com.example.ecommercestore.models.CartItem;
import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.User;
import com.example.ecommercestore.models.WishListItem;
import com.example.ecommercestore.repository.ProductRepository;
import com.example.ecommercestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping({"/login", "/"})
    public ModelAndView getLoginForm(){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());

        return mav;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("user") User user) {
        User oauthUser = userService.login(user.getEmail(), user.getPassword());

        if (oauthUser != null) {
            if (oauthUser.getEmail().equals("admin@gmail.com") && oauthUser.getPassword().equals("1234")){
                ModelAndView mav = new ModelAndView("admin_home");
                mav.addObject("user", oauthUser);
                mav.addObject("email", user.getEmail());
                return mav;
            }

            ModelAndView mav1 = new ModelAndView("home");
            mav1.addObject("user", oauthUser);
            mav1.addObject("email", user.getEmail());
            return mav1;
        } else {
            return new ModelAndView("signup");
        }
    }

    @GetMapping("/home")
    public String home(@ModelAttribute String email) {
        return "home";
    }

    @GetMapping("/signup")
    public String  signup() {
        return "signup";
    }

    @GetMapping("/signout")
    public String  signout() {
        return "redirect:/login";
    }

    @PostMapping("/signup")
    public ModelAndView createAccount(@ModelAttribute UserDto user) {
        User LoggedInUser = userService.create(user);

        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", LoggedInUser);

        User oauthUser = userService.login(user.getEmail(), user.getPassword());

        if(Objects.nonNull(oauthUser)) {
            return mav;
        } else {
            return new ModelAndView("signup");
        }
    }

    @GetMapping("/customerViewProducts")
    public ModelAndView customerViewProducts(@ModelAttribute String email) {
        List<Product> listOfProducts = productRepository.findAll();
        ModelAndView mav = new ModelAndView("customerViewProducts");
        mav.addObject("listOfProducts", listOfProducts);
        mav.addObject("email", email);
        return mav;
    }

    @GetMapping("/viewCart")
    public ModelAndView viewCart(@ModelAttribute String email) {
        List<CartItem> listOfProducts = userService.getCart(email);
        ModelAndView mav = new ModelAndView("viewCart");
        mav.addObject("listOfProducts", listOfProducts);
        mav.addObject("email", email);
        return mav;
    }

    @GetMapping("/viewWishList")
    public ModelAndView viewWishlist(@ModelAttribute String email) {
        List<WishListItem> listOfProducts = userService.getWishList(email);
        ModelAndView mav = new ModelAndView("viewWishList");
        mav.addObject("listOfProducts", listOfProducts);
        mav.addObject("email", email);
        return mav;
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@ModelAttribute String email, @PathVariable String id) {
        userService.addToCart(Long.parseLong(id), email);
        return "redirect:/customerViewProducts";
    }

    @GetMapping("/addToWishList/{id}")
    public String addToWishList(@ModelAttribute String email, @PathVariable String id) {
        userService.addToWishList(Long.parseLong(id), email);
        return "redirect:/customerViewProducts";

    }

    @GetMapping("/removeFromCart/{id}")
    public String removeFromCart(@ModelAttribute String email, @PathVariable String id) {
        userService.removeFromCart(Long.parseLong(id), email);

        return "redirect:/viewCart";

    }

    @GetMapping("/removeFromWishList/{id}")
    public String removeFromWishList(@ModelAttribute String email, @PathVariable String id) {
        userService.removeFromWishList(Long.parseLong(id), email);

        return "redirect:/viewWishList";

    }
}
