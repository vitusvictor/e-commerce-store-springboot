package com.example.ecommercestore.service;

import com.example.ecommercestore.dto.ProductDto;
import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.User;

import java.util.List;

public interface AdminService {
    void deleteProduct(Long id);
    List<User> getAllUsers();
    Product createProduct(ProductDto productDto);
    Product updateProduct(Product product, Long id);
    User getUser(Long id);


}
