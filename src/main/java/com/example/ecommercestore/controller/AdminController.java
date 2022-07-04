package com.example.ecommercestore.controller;

import com.example.ecommercestore.dto.ProductDto;
import com.example.ecommercestore.exception.CustomAppException;
import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.User;
import com.example.ecommercestore.repository.ProductRepository;
import com.example.ecommercestore.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

//    @Autowired
    private ProductRepository productRepository;
//    @Autowired
    private AdminService adminService;

    @GetMapping("/admin_home")
    public String home(@ModelAttribute String email) {
        return "admin_home";
    }

    @GetMapping("/addProduct")
    public String adminAddProductView(Model model){
        model.addAttribute("product", new ProductDto());
        return "adminAddProduct";
    }

    @PostMapping("/adminAddProduct")
    public String adminAddProduct(@ModelAttribute("product") ProductDto product) {
        adminService.createProduct(product);
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
        List<User> listOfUsers = adminService.getAllUsers();
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
        adminService.updateProduct(product, Long.parseLong(id));
        return "redirect:/viewProducts";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteAProduct(@PathVariable String id) {
        adminService.deleteProduct(Long.parseLong(id));
        return "redirect:/viewProducts";
    }
}
