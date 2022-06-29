package com.example.ecommercestore.dto;


import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
}
