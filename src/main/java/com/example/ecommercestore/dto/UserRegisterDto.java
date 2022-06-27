package com.example.ecommercestore.dto;


import lombok.Data;

@Data
public class UserRegisterDto {
    private String username;
    private String email;
    private String password;
    private String passwordConfirm;
    private String firstName;
    private String lastName;
    private int age;
}
