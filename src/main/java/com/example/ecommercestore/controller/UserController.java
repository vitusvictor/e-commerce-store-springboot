package com.example.ecommercestore.controller;

import com.example.ecommercestore.dto.ProductDto;
import com.example.ecommercestore.dto.UserDto;
import com.example.ecommercestore.exception.CustomAppException;
import com.example.ecommercestore.models.Cart;
import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.User;
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
    public String login(@ModelAttribute User user, Model model ) { // user is coming from the login @getmapping method
        User oauthUser = userService.login(user.getEmail(), user.getPassword());

        if (oauthUser != null) {
            model.addAttribute("userDetails", oauthUser);
            if (oauthUser.getEmail().equals("admin@gmail.com") && oauthUser.getPassword().equals("1234")){
                return "admin_home";
            }
            return "home";
        } else {
            return "signup";
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

    //Carry out the login logic
    @PostMapping("/signup")
    public String createAccount(@ModelAttribute UserDto user, Model model) {
//        model.addAttribute("user", user);
        System.out.println(user.getFirstName() + " " + user.getEmail());
        User LoggedInUser = userService.create(user);
        model.addAttribute("user", LoggedInUser);

        User oauthUser = userService.login(user.getEmail(), user.getPassword());

        if(Objects.nonNull(oauthUser)) {
            return "redirect:/login";
        } else {
            return "redirect:/signup";
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
        System.out.println("widthin the edit...");
        System.out.println(id);
        System.out.println("name" + product.getProductName());
        System.out.println("price" + product.getPrice());

        userService.updateProduct(product, Long.parseLong(id));

        return "redirect:/viewProducts";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteAProduct(@PathVariable String id) {
        userService.deleteProduct(Long.parseLong(id));

        return "redirect:/viewProducts";
    }

    @GetMapping("/customerViewProducts")
    public ModelAndView customerViewProducts() {
        List<Product> listOfProducts = productRepository.findAll();
        ModelAndView mav = new ModelAndView("customerViewProduct");

        mav.addObject("listOfProducts", listOfProducts);
        return mav;
    }

    @GetMapping("/viewCart")
    public ModelAndView viewCart(@ModelAttribute("userDto") UserDto userDto) {
        List<Cart> listOfProducts = userService.getCart(userDto.getEmail());
        ModelAndView mav = new ModelAndView("viewWishList");

        mav.addObject("listOfProducts", listOfProducts);
        return mav;
    }

    @GetMapping("/viewWishList")
    public ModelAndView viewWishlist(@ModelAttribute("userDto") UserDto userDto) {
        List<Cart> listOfProducts = userService.getCart(userDto.getEmail());
        ModelAndView mav = new ModelAndView("viewWishList");

        mav.addObject("listOfProducts", listOfProducts);
        return mav;
    }
}
