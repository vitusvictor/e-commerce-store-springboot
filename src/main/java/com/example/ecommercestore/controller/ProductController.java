//package com.example.ecommercestore.controller;
//
//import com.example.ecommercestore.dto.ProductDto;
//import com.example.ecommercestore.dto.UserRegisterDto;
//import com.example.ecommercestore.exception.CustomAppException;
//import com.example.ecommercestore.models.Product;
//import com.example.ecommercestore.models.User;
//import com.example.ecommercestore.repository.ProductRepository;
//import com.example.ecommercestore.service.UserService;
//import com.example.ecommercestore.service.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
//
//@Controller
//public class ProductController {
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/addProduct")
//    public String adminAddProductView(Model model){
//        model.addAttribute("product", new ProductDto());
//        return "adminAddProduct";
//    }
//
//    @PostMapping("/adminAddProduct")
//    public String adminAddProduct(@ModelAttribute("product") ProductDto product, Model model) {
//        userService.createProduct(product);
//
//        return "redirect:/viewProducts";
//    }
//
//    @GetMapping("/viewProducts")
//    public ModelAndView viewProducts(){
//        List<Product> listOfProducts = productRepository.findAll();
//        ModelAndView mav = new ModelAndView("viewProducts");
//
//        mav.addObject("listOfProducts", listOfProducts);
//        return mav;
//    }
//
//
//    @GetMapping("/editProductView/{id}")
//    public String renderEditPage(@PathVariable String id, Model model){
//        Product product = productRepository.findById(Long.parseLong(id))
//                .orElseThrow(()-> new CustomAppException("Product doesn't exit."));
//
//        Product product1 = new Product();
//
//        if (product != null) {
//            product1.setId(product.getId());
//            product1.setProductName(product.getProductName());
//            product1.setCategory(product.getCategory());
//            product1.setCategory(product.getCategory());
//        }
//
//        model.addAttribute("product", product1);
//        return "editProductView";
//    }
//
//    @PostMapping("/editProduct/{id}")
//    public String editProduct(@ModelAttribute Product product, @PathVariable String id) {
//        System.out.println("widthin the edit...");
//        System.out.println(id);
//        System.out.println("name" + product.getProductName());
//        System.out.println("price" + product.getPrice());
//
//        userService.updateProduct(product, Long.parseLong(id));
//
//        return "redirect:/viewProducts";
//    }
//
//    @GetMapping("/deleteProduct/{id}")
//    public String deleteAProduct(@PathVariable String id) {
//        userService.deleteProduct(Long.parseLong(id));
//
//        return "redirect:/viewProducts";
//    }
//
//}
