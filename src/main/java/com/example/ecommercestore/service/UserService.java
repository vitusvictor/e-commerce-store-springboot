package com.example.ecommercestore.service;

import com.example.ecommercestore.dto.UpdateUserDto;
import com.example.ecommercestore.dto.UserRegisterDto;
import com.example.ecommercestore.models.User;

import java.util.List;

public interface UserService {
    User create(UserRegisterDto userRegisterDto);
    List<User> getAllUser();
    User getUser(Long id);
    void deleteUser(Long id);
    User updateUser(UpdateUserDto updateUserDto, String email);
}
