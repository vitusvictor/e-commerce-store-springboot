package com.example.ecommercestore.service;

import com.example.ecommercestore.dto.ProductDto;
import com.example.ecommercestore.dto.UpdateUserDto;
import com.example.ecommercestore.dto.UserDto;
import com.example.ecommercestore.models.CartItem;
import com.example.ecommercestore.models.Product;
import com.example.ecommercestore.models.User;
import com.example.ecommercestore.models.WishListItem;

import java.util.List;

public interface UserService {
    User create(UserDto userDto);
    void removeFromWishList(Long id, String email);
    void removeFromCart(Long id, String email);
    User login(String email, String password);
    List<Product> getAllProducts();
    WishListItem addToWishList(Long id, String email);
    CartItem addToCart(Long id, String email);
    List<WishListItem> getWishList(String email);
    List<CartItem> getCart(String email);
}
