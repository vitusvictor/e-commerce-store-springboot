package com.example.ecommercestore.controller;

import com.example.ecommercestore.dto.ProductDto;
import com.example.ecommercestore.dto.UserDto;
import com.example.ecommercestore.exception.CustomAppException;
import com.example.ecommercestore.models.CartItem;
import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.User;
import com.example.ecommercestore.models.WishListItem;
import com.example.ecommercestore.repository.ProductRepository;
import com.example.ecommercestore.service.UserService;
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
    public String home() {
        return "home";
    }

    @GetMapping("/signup")
    public String  signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public ModelAndView createAccount(@ModelAttribute UserDto user, Model model) {
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

    @GetMapping("/addProduct")
    public String adminAddProductView(Model model){
        model.addAttribute("product", new ProductDto());
        return "adminAddProduct";
    }

    @PostMapping("/adminAddProduct")
    public String adminAddProduct(@ModelAttribute("product") ProductDto product, Model model) {
        userService.createProduct(product);
        return "redirect:/viewProducts";
    }

    @GetMapping("/viewProducts")
    public ModelAndView viewProducts(){
        List<Product> listOfProducts = productRepository.findAll();
        ModelAndView mav = new ModelAndView("viewProducts");
        mav.addObject("listOfProducts", listOfProducts);
        return mav;
    }

    @GetMapping("/viewUsers")
    public ModelAndView viewUsers(){
        List<User> listOfUsers = userService.getAllUsers();
        ModelAndView mav = new ModelAndView("viewUsers");
        mav.addObject("listOfUsers", listOfUsers);
        return mav;
    }


    @GetMapping("/editProductView/{id}")
    public String renderEditPage(@PathVariable String id, Model model){
        Product product = productRepository.findById(Long.parseLong(id))
                .orElseThrow(()-> new CustomAppException("Product doesn't exit."));

        Product product1 = new Product();

        if (product != null) {
            product1.setId(product.getId());
            product1.setProductName(product.getProductName());
            product1.setCategory(product.getCategory());
            product1.setCategory(product.getCategory());
        }

        model.addAttribute("product", product1);
        return "editProductView";
    }

    @PostMapping("/editProduct/{id}")
    public String editProduct(@ModelAttribute Product product, @PathVariable String id) {
        userService.updateProduct(product, Long.parseLong(id));
        return "redirect:/viewProducts";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteAProduct(@PathVariable String id) {
        userService.deleteProduct(Long.parseLong(id));
        return "redirect:/viewProducts";
    }

///////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/customerViewProducts/{email}")
    public ModelAndView customerViewProducts(@PathVariable String email) {
        List<Product> listOfProducts = productRepository.findAll();
        ModelAndView mav = new ModelAndView("customerViewProducts");
        mav.addObject("listOfProducts", listOfProducts);
        return mav;
    }

    @GetMapping("/viewCart/{email}")
    public ModelAndView viewCart(@PathVariable String email) {
        List<CartItem> listOfProducts = userService.getCart(email);
        ModelAndView mav = new ModelAndView("viewCart");
        mav.addObject("listOfProducts", listOfProducts);
        mav.addObject("email", email);
        return mav;
    }

    @GetMapping("/viewWishList/{email}")
    public ModelAndView viewWishlist(@PathVariable String email) {
        List<WishListItem> listOfProducts = userService.getWishList(email);
        ModelAndView mav = new ModelAndView("viewWishList");
        mav.addObject("listOfProducts", listOfProducts);
        mav.addObject("email", email);
        return mav;
    }

    @GetMapping("/addToCart/{id}/{email}")
    public String addToCart(@PathVariable String email, @PathVariable String id) {
        userService.addToCart(Long.parseLong(id), email);
        return "redirect:/customerViewProducts/{email}";
    }

    @GetMapping("/addToWishList/{id}/{email}")
    public String addToWishList(@PathVariable String email, @PathVariable String id) {
        userService.addToWishList(Long.parseLong(id), email);
        return "redirect:/customerViewProducts/{email}";
    }

    @GetMapping("/removeFromCart/{id}/{email}")
    public String removeFromCart(@PathVariable String email, @PathVariable String id) {
        userService.removeFromCart(Long.parseLong(id), email);
        return "redirect:/viewCart/{email}";
    }

    @GetMapping("/removeFromWishList/{id}/{email}")
    public String removeFromWishList(@PathVariable String email, @PathVariable String id) {
        userService.removeFromWishList(Long.parseLong(id), email);
        return "redirect:/viewWishList/{email}";
    }
}
