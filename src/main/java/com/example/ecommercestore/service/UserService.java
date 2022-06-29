package com.example.ecommercestore.service;

import com.example.ecommercestore.dto.ProductDto;
import com.example.ecommercestore.dto.UpdateUserDto;
import com.example.ecommercestore.dto.UserDto;
import com.example.ecommercestore.models.Cart;
import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.User;
import com.example.ecommercestore.models.WishList;

import java.util.List;

public interface UserService {
    User create(UserDto userDto);
    List<User> getAllUsers();
    User getUser(Long id);
    void deleteUser(Long id);

    void deleteProduct(Long id);

    void removeFromWishList(Long id, String email);

    void removeFromCart(Long id, String email);

    User updateUser(UpdateUserDto updateUserDto, String email);
    User login(String email, String password);
    List<Product> getAllProducts();
    Product createProduct(ProductDto productDto);

    WishList addToWishList(Long id, String email);

    Cart addToCart(Long id, String email);

    Product updateProduct(Product product, Long id);
    List<WishList> getWishList(String email);
    List<Cart> getCart(String email);
}
