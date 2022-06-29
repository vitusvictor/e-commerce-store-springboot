package com.example.ecommercestore.service;

import com.example.ecommercestore.dto.ProductDto;
import com.example.ecommercestore.dto.UpdateProductDto;
import com.example.ecommercestore.dto.UpdateUserDto;
import com.example.ecommercestore.dto.UserRegisterDto;
import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.User;

import java.util.List;

public interface UserService {
    User create(UserRegisterDto userRegisterDto);
    List<User> getAllUsers();
    User getUser(Long id);
    void deleteUser(Long id);

    void deleteProduct(Long id);

    User updateUser(UpdateUserDto updateUserDto, String email);
    User login(String email, String password);
    List<Product> getAllProducts();
    Product createProduct(ProductDto productDto);
    Product updateProduct(Product product, Long id);
}
